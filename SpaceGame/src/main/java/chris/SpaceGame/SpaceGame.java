package chris.SpaceGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
/**
 * Student ID: @00171034 
 * Username: AGD830 
 * Assessment Title: A JavaFX app Utilising Design Patterns
 * Module Name: Design Patterns
 * Module CRN: 52789
 * 
 * 
 * Planet Collector Game
 * Main class which extends application, provides the game mechanics
 * @author Thomas Christopher Daniels "Chris Daniels" 
 *
 */
public class SpaceGame extends Application {
	FlowPane root; //Flow pane used so two separate panes can be used for score and game display
	Scene scene;
	Pane scoreDisplay, gameScreen; //Separates player HUD from game screen
	Canvas scoreCanvas, gameCanvas, gameOverCanvas; 
	GraphicsContext gcGame, gcScore;
	
	private Button startBtn;
	private Label scoreLabelText, scoreLabelInt, playerLivesText, gameTitle, highScore, collectedLabel, scores, gameDescription;
	private int playerScore, planetPlacementX, nextRow, count;	
	private boolean gameOver = false;
	
	protected CharacterFactoryIF factory, factoryUI;
	private Random random;
	
	
	protected ForegroundGameObject player, enemy, bullet;
	protected EnemyStrategy lvl1A = new EnemyStrategy();
	protected EnemyStrategyB lvl1B = new EnemyStrategyB(); //test
	protected EnemyStrategyLevel2A lvl2A = new EnemyStrategyLevel2A();
	protected EnemyStrategyLevel2B lvl2B = new EnemyStrategyLevel2B();
	protected PlanetStrategyA planetStratA = new PlanetStrategyA();
	protected PlanetStrategyB planetStratB = new PlanetStrategyB();
	protected SatelliteStrategyA satStratA = new SatelliteStrategyA();
	protected SatelliteStrategyB satStratB = new SatelliteStrategyB();
	
	
	
	private ArrayList<GameObject> stars = new ArrayList<GameObject>();
	private ArrayList<ForegroundGameObject> meteors = new ArrayList<ForegroundGameObject>(); //enemy list
	private ArrayList<ForegroundGameObject> bullets = new ArrayList<ForegroundGameObject>();
	private ArrayList<ForegroundGameObject> removeGameItem = new ArrayList<ForegroundGameObject>();
	private ArrayList<ForegroundGameObject> lives = new ArrayList<ForegroundGameObject>();
	private ArrayList<ForegroundGameObject> planets = new ArrayList<ForegroundGameObject>();
	private ArrayList<ForegroundGameObject> collectedPlanets = new ArrayList<ForegroundGameObject>();	
	private ArrayList<Integer> highScoreTable = new ArrayList<Integer>();	
	private ArrayList<Label> labelsToRemove = new ArrayList<Label>();
	
	//event handler to handle user input keys
	EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.D) {
				((Player)player).moveRight();
			}
			if(event.getCode() == KeyCode.A) {
				((Player)player).moveLeft();
			}
			if(event.getCode() == KeyCode.S) {
				((Player)player).moveDown();
			}
			if(event.getCode() == KeyCode.W) {
				((Player)player).moveUp();
			}
			if(event.getCode() == KeyCode.SPACE) {
				if(bullets.size() < 6) //creates 6 bullets at the player co-ordinates
					bullets.add(bullet = factory.createCharacter("bullet", player.getX(), player.getY()));
			}
			
		}};
		
	
	AnimationTimer timer = new AnimationTimer() {
		
		/**
		 * Animation handle method override, provides loops, checks and method calls for each 
		 * frame of animation
		 */
		@Override
		public void handle(long now) {			
			
			gcGame.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
			count++;
			//sets star movement
			for(GameObject star:stars)
				star.update();			
			
			//set planet strategy depending on whether they are in the first 4 or last 4 to be displayed			
			Planet currentPlanet = (Planet) planets.get(0);
			if(count>100) {	//sets the time delay between planets displaying			
				if(planets.size()>4) {
					currentPlanet.setStrategy(planetStratA);
				} else {
					currentPlanet.setStrategy(planetStratB);
				}				
				currentPlanet.execute();
				if(currentPlanet.getY()>550)
					count=0;
			}	
			//allows player to collect planet
			for(ForegroundGameObject planet: planets) {
				if(player.getHitBox().intersects(planet.getHitBox().getX(),planet.getHitBox().getY(),planet.getHitBox().getWidth(),planet.getHitBox().getHeight())) {
					collectPlanet(planet);
					removeGameItem.add(planet); //planet object to be removed
					adjustScore(((Planet) planet).pointsAdd());
					count=0;					
				}	
			}		
			//set all enemy strategies within the meteors array, when four planets are collected the strategy changes to increase difficulty
			for(ForegroundGameObject enemy:meteors) {
				if(planets.size()>4) {
					if(((Enemy) enemy).getName().equals("asteroid")) {
						((Enemy) enemy).setStrategy(lvl1A);
						((Enemy) enemy).execute();
					} else if (((Enemy) enemy).getName().equals("fireAsteroid")) {
						((Enemy) enemy).setStrategy(lvl1B);
						((Enemy) enemy).execute();						
					} 
				}else if (planets.size()>0) {
					//changes strategy to increase difficulty
					if(((Enemy) enemy).getName().equals("asteroid")) {
						((Enemy) enemy).setStrategy(lvl2A);
						((Enemy) enemy).execute();
					} else if (((Enemy) enemy).getName().equals("fireAsteroid")) {
						((Enemy) enemy).setStrategy(lvl2B);
						((Enemy) enemy).execute();
					} else if (((Enemy)enemy).getName().equals("satellite")) { //satellite enemy strategy set so this now appears on screen
						((Enemy)enemy).setStrategy(satStratA);
						if(((Satellite)enemy).getDrop() == true)
							((Enemy)enemy).setStrategy(satStratB);							
					((Enemy)enemy).execute();
					}
				}
				//removes player life and sets hit flag to true to trigger delegation to playerExplode class
				if(player.getHitBox().intersects(enemy.getHitBox().getX(),enemy.getHitBox().getY(),enemy.getHitBox().getWidth(),enemy.getHitBox().getHeight())) {
					((Player)player).setHit(true);
					enemy.setY(-40);
					removeLife();					
					}				
			}
			//Loop to set bullet interactions with gameObjects, bullets added to arraylist for removal
			for(ForegroundGameObject bullet:bullets) {
				bullet.update();
				if(bullet.getY() <20)
					removeGameItem.add(bullet);
				//loop removes bullet and adjusts score when hitting enemy
				for(ForegroundGameObject meteor:meteors) {
					if(bullet.getHitBox().intersects(meteor.getHitBox().getX(),meteor.getHitBox().getY(),meteor.getHitBox().getWidth(),meteor.getHitBox().getHeight())) {
						removeGameItem .add(bullet);
						meteor.setY(600);
						adjustScore(((Enemy)meteor).getPointsValue());						
					}
				}
				//loop removes bullet and adjusts score when hitting planet
				for(ForegroundGameObject planet:planets) {
					if(bullet.getHitBox().intersects(planet.getHitBox().getX(),planet.getHitBox().getY(),planet.getHitBox().getWidth(),planet.getHitBox().getHeight())) {
						adjustScore(((Planet) planet).pointsRemove());
						removeGameItem .add(bullet);
					}
				}
			}
			
			
			bullets.removeAll(removeGameItem ); //removes instances of bullets from screen
			planets.removeAll(removeGameItem ); //removes instances of planet
			((Player)player).playerExploded();		//checks if player has collided with enemy to trigger delegation to player explode class	

			//end game once planets are collected, sets gameOver flag to true
			if(planets.isEmpty())
				gameOver = true;
			
			//initiates game over screen if gameOver boolean is true and resets value
			if(gameOver == true) {
				gameOver();
				gameOver = false;
			}			
			
		}};
	/**
	 * Main method to launch application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);		

	}
	/**
	 * Override application start method, instantiates objects to create the user interface
	 * and start screen information
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		planetPlacementX = 0;
		nextRow = 0;	
		count = 0;
		random = new Random(System.currentTimeMillis());
		
		root = new FlowPane();
		scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();		
		//set game screen pane
		gameScreen = new Pane();
		gameScreen.setPrefSize(800, 500);		
		gameCanvas = new Canvas(800, 500);
		gcGame = gameCanvas.getGraphicsContext2D();
		gcGame.setFill(Color.BLACK);
		gcGame.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());		
		
		//set score display pane
		scoreDisplay = new Pane();
		scoreDisplay.setPrefSize(800, 100);
		scoreCanvas = new Canvas(800, 100);
		gcScore = scoreCanvas.getGraphicsContext2D();
		gcScore.setFill(Color.BLACK);
		gcScore.fillRect(500, 0, 300, scoreCanvas.getHeight());
		gcScore.setFill(Color.SLATEGRAY);
		gcScore.fillRect(0, 0, 500, scoreCanvas.getHeight());
		gcScore.setFill(Color.PURPLE);
		gcScore.fillRect(0, 0, scoreCanvas.getWidth(), 2);
		gcScore.fillRect(499, 0, 2, scoreCanvas.getHeight());
		gcScore.fillRect(199, 0, 2, scoreCanvas.getHeight());
		gcScore.setFill(Color.SLATEGRAY);		
		
		
		root.getChildren().addAll(gameScreen,scoreDisplay);
		gameScreen.getChildren().add(gameCanvas);
		scoreDisplay.getChildren().add(scoreCanvas);		
		
		createStartButton();
		//Labels created for start screen
		gameTitle = new Label("Planet\nCollector");
		gameTitle.setLayoutX(260);
		gameTitle.setLayoutY(30);
		gameTitle.setStyle(" -fx-font: bold 40pt \"showcard gothic\";");
		gameTitle.setTextFill(Color.PURPLE);
		gameTitle.setTextAlignment(TextAlignment.CENTER);
		
		gameDescription = new Label("Collect all 8 of the solar system's planets while avoiding crashing into the asteroids and satellites\n" +
		"Score points for shooting the asteroids, score even more points for collecting the planets.\n" +
		"Be careful not to shoot the planets though, this will cause you to lose points.\n\n" +
		"Spaceship controls:\n"
		+ "W => Move Up\n"
		+ "S => Move Down\n"
		+ "A => Move Left\n"
		+ "D => move Right\n"
		+ "Space Bar => Fire gun");
		gameDescription.setLayoutX(85);
		gameDescription.setLayoutY(200);
		gameDescription.setStyle(" -fx-font: bold 12pt \"impact\";");
		gameDescription.setTextFill(Color.LIMEGREEN);
		gameDescription.setTextAlignment(TextAlignment.CENTER);
		
		gameScreen.getChildren().addAll(gameTitle, gameDescription);
		
		//Labels created for score display HUD
		scoreLabelText = new Label("Player Score\n");		
		scoreLabelText.setLayoutX(285);		
		scoreLabelText.setLayoutY(10);
		scoreLabelText.setStyle(" -fx-font: bold 15pt \"showcard gothic\";");
		scoreLabelText.setTextFill(Color.PURPLE);				
		
		scoreLabelInt = new Label(String.valueOf(playerScore));
		scoreLabelInt.setLayoutX(345);
		scoreLabelInt.setLayoutY(50);
		scoreLabelInt.setStyle(" -fx-font: bold 15pt \"showcard gothic\";");
		scoreLabelInt.setTextFill(Color.PURPLE);
		
		playerLivesText = new Label("Player Lives");
		playerLivesText.setLayoutX(40);
		playerLivesText.setLayoutY(10);
		playerLivesText.setStyle(" -fx-font: bold 15pt \"showcard gothic\";");
		playerLivesText.setTextFill(Color.PURPLE);		
		
		scoreDisplay.getChildren().addAll(scoreLabelInt, scoreLabelText, playerLivesText);
		
		scene.setOnKeyPressed(keyHandler);		
	}
	/**
	 * Creates instances of game objects, adds them to the game canvas, and starts the time to start the game
	 */
	public void gameStart() {
		//removes any on screen labels
		gameScreen.getChildren().removeAll(gameTitle, highScore, collectedLabel, scores, gameDescription);
		gameScreen.getChildren().removeAll(labelsToRemove);
		
		factoryUI = new CharacterFactory(gcScore);
		factory = new CharacterFactory(gcGame);
		random = new Random(System.currentTimeMillis());
		scoreLabelInt.setText(String.valueOf(playerScore));
		//adds lives to player HUD
		for (int i = 0;  i<3; i++) {
			lives.add(factoryUI.createCharacter("player", 53+i*30, 45));		
		}
		//adds stars to an array for background animation object pool
		for (int i =0; i<10; i++) {			
			stars.add(factory.createCharacterB("star", random.nextInt(800), random.nextInt(500)));
			stars.add(factory.createCharacterB("small-star", random.nextInt(800), random.nextInt(500)));			
		}
		
		//Adds all enemies to an array list	object pool	
		for (int i = 0;  i<20; i++) {
			meteors.add(factory.createCharacter("asteroid", random.nextInt(800), -40));
			if(i%3==0) {
				meteors.add(factory.createCharacter("fireAsteroid", random.nextInt(800), -40));
			}			
		}
		meteors.add(factory.createCharacter("satellite", -40, 50)); //adds one satellite to array
		
		player = factory.createCharacter("player", 380, 430);	//add player to screen	
		
		//adds planets to canvas
		planets.add(factory.createCharacter("mercury", (random.nextInt(700) + 50), -50));
		planets.add(factory.createCharacter("venus", (random.nextInt(700) + 50), -50));
		planets.add(factory.createCharacter("earth", (random.nextInt(700) + 50), -50));
		planets.add(factory.createCharacter("mars", (random.nextInt(700) + 50), -50));
		planets.add(factory.createCharacter("jupiter", (random.nextInt(700) + 50), -50));
		planets.add(factory.createCharacter("saturn", (random.nextInt(700) + 50), -50));
		planets.add(factory.createCharacter("uranus", (random.nextInt(700) + 50), -50));
		planets.add(factory.createCharacter("neptune", (random.nextInt(700) + 50), -50));		
		
		timer.start();		
	}
	/**
	 * Adjusts player score according to on screen objects interacted with by player character
	 * @param points: points value of object shot/collected
	 */
	public void adjustScore(int points) {
		playerScore += points;
		scoreLabelInt.setText(String.valueOf(playerScore));
	}

	/**
	 * Removes player life from player HUD
	 */
	public void removeLife() {
		int remainingLife = lives.size();
		if(lives.size() > 0) {
			ForegroundGameObject toRemove = lives.get(remainingLife -1);
			gcScore.setFill(Color.SLATEGRAY);
			gcScore.fillRect(toRemove.getX(), toRemove.getY(), 30, 30); //fills canvas
			lives.remove(toRemove); //removes life object from score screen
			if(remainingLife == 1) {				
				gameOver = true; //sets flag to game over once lives are used
			}			
		} else {
			gameOver();
		}
	}
	/**
	 * Displays game over screen, removes all instances of game object from the canvas and resets
	 * the player HUD
	 */
	public void gameOver() {		
		
		int planetsCollected = collectedPlanets.size();
		//remove all GameObjects
		collectedPlanets.removeAll(collectedPlanets);		
		lives.removeAll(lives);
		stars.removeAll(stars);		
		meteors.removeAll(meteors);
		planets.removeAll(planets);
		planetPlacementX=0; //reset value
		nextRow=0;  //resets value
		count = 0; //reset count
		//reset canvas to black
		gcScore.setFill(Color.BLACK);
		gcScore.fillRect(500, 2, 300, 98);
		
		scoreDisplay.getChildren().removeAll(labelsToRemove); //removes all planet labels and any others added to this list
		
		
		timer.stop();
		
		//add labels for game over screen
		gameCanvas = new Canvas(800, 500);
		gcGame = gameCanvas.getGraphicsContext2D();
		gcGame.setFill(Color.BLACK);
		gcGame.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
		gameScreen.getChildren().add(gameCanvas);
		
		gameTitle = new Label("Planet Collector");
		gameTitle.setLayoutX(220);
		gameTitle.setLayoutY(30);
		gameTitle.setStyle(" -fx-font: bold 30pt \"showcard gothic\";");
		gameTitle.setTextFill(Color.PURPLE);
		
		
		highScore = new Label("High Scores");
		highScore.setLayoutX(300);
		highScore.setLayoutY(150);
		highScore.setStyle(" -fx-font: bold 25pt \"showcard gothic\";");
		highScore.setTextFill(Color.PURPLE);
		
		
		
		collectedLabel = new Label("You collected " + planetsCollected + " planets");
		collectedLabel.setLayoutX(240);
		collectedLabel.setLayoutY(100);
		collectedLabel.setStyle(" -fx-font: bold 20pt \"showcard gothic\";");
		collectedLabel.setTextFill(Color.RED);
		
		
		highScoreTable.add(playerScore);
		//Sort collection and add the top five high scores to the scores label
		Collections.sort(highScoreTable, Collections.reverseOrder());
		int index = 0;
		String scoreList = "";		
		while(index<5 && index<highScoreTable.size()) {
			scoreList = scoreList + highScoreTable.get(index).toString() + "\n";			
			index++;
		}
		scores = new Label(scoreList);
		scores.setLayoutX(370);
		scores.setLayoutY(195);
		scores.setStyle(" -fx-font: bold 22pt \"showcard gothic\";");
		scores.setTextFill(Color.PURPLE);
		scores.setTextAlignment(TextAlignment.CENTER);
		gameScreen.getChildren().add(scores);
		
		
		playerScore = 0;
		gameScreen.getChildren().addAll(highScore, gameTitle, collectedLabel);
		
		createStartButton();
		
	}
	/**
	 * Places planets collected in the player HUD so the user can see what planets have 
	 * been collected
	 * @param planet: planet collected in game
	 */
	public void collectPlanet(GameObject planet) {
		
		planetPlacementX += 60;
		//Places planets on next row once four are added to canvas
		if(planetPlacementX == 300) {
			nextRow = 45;
			planetPlacementX = 60;
		}
		
		
		factoryUI = new CharacterFactory(gcScore);
		collectedPlanets.add(factoryUI.createCharacter(((Planet) planet).returnName(), 480 + planetPlacementX, 10 + nextRow));
		String planetName = ((Planet) planet).returnName();
		Label label = new Label (planetName);
		
		//adjusts label placement on these three planets
		if(planetName.equals("mercury")) {
			label.setTranslateX(476 + planetPlacementX);
		} else if(planetName.equals("neptune")) {
			label.setTranslateX(474 + planetPlacementX);
		} 
		else if(planetName.equals("mars")) {
			label.setTranslateX(483 + planetPlacementX);
		}else {
			label.setTranslateX(480 + planetPlacementX);
		}
		
		label.setTranslateY(37 + nextRow);
		labelsToRemove.add(label); //adds label to list for removal when game ends
		
		
		//adds label to score display
		label.setTextFill(Color.PURPLE);
		scoreDisplay.getChildren().add(label);
		
	}
	/**
	 * Create start button to start game
	 * This was created twice, on the initial start screen and the game over screen, therefore a method
	 * to create the button was more efficient
	 */
	public void createStartButton() {
		
		startBtn = new Button();
		startBtn.setText("Start Game");
		startBtn.setTranslateX(370);
		startBtn.setTranslateY(430);
		
		gameScreen.getChildren().add(startBtn);
		//event handler to start game and remove button
		startBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {	
				gameStart();				
				startBtn.setVisible(false);
				
			}});
	}
 
}
