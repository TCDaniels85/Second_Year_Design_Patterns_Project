package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 
 * @author Chris Daniels
 * Player class, instantiates a player object the user can control which contains the methods for movement.
 * This class implements the delegation interface and delegates to the PlayerExplode class once the field hit
 * is read as being true
 *
 */
public class Player extends ForegroundGameObject implements DelegationInterface{
	DelegationInterface delegate;
	protected int time, counter = 0; 
	protected Image a,b,c; //images to hold animation frames
	protected boolean hit = false; // To record if the player has intersected an enemy hitbox
	
	//Constructor adds three images, used for animation of the Player object
	public Player(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		a = new Image(Player.class.getResource("ship1.png").toExternalForm());
		b = new Image(Player.class.getResource("ship2.png").toExternalForm());
		c = new Image(Player.class.getResource("ship3.png").toExternalForm());
		image = a; //initial image
		update();
		delegate = this;
		
	}
	
	/**
	 * The update method overrides the super class method to add if statements which swap in images
	 * every 2 frames to achieve animation. Then makes a call to the super class to update
	 * position
	 */
	public void update() {
		time += 1;
		if(time==2)			
			image = b;
		if(time==4)			
			image = c;
		if(time==6) {			
			image = a;
			time = 0;
		}
		super.update();
	}
	
	/**
	 * Checks if the boolean hit is true, once true delegates to the player explode object 
	 * for a set period of time to provide an explosion animation
	 */
	public void playerExploded() {
		//checks hit field
		if(hit == true) {
			if(counter==0)
				delegate = new PlayerExplode(x,y,gc);
			counter+=1;			
		} 
		//delegates back to player object and resets player position
		if(counter == 65) {
			delegate = this;
			x=380;
			y=430;
			counter = 0;  //resets counter
			hit = false;			
		} 		
		delegate.update();		
	}	
	/**
	 * Method to move player right while preventing player character from exiting screen
	 */
	public void moveRight() {
		if(x<770) {
			x+=5;
			//gc.fillRect((x-1), y,30, 30);
			gc.drawImage(image, x, y, 30, 30);
		}
	}
	/**
	 * Method to move player left while preventing player character from exiting screen
	 */
	public void moveLeft() {
		if(x>0) {
			x-=5;
			//gc.fillRect((x+1), y,30, 30);
			gc.drawImage(image, x, y, 30, 30);
		}
	}
	/**
	 * Method to move player up while preventing player character from exiting screen
	 */
	public void moveUp() {
		if(y>5) {
			y-=5;
			//gc.fillRect(x, (y+1),30, 30);
			gc.drawImage(image, x, y, 30, 30);
		}
	}
	/**
	 * Method to move player down while preventing player character from exiting screen
	 */
	public void moveDown() {
		if(y<470)
		{
			y+=5;
			//gc.fillRect(x, (y-1), 30, 30);
			gc.drawImage(image, x, y, 30, 30);
		}
	}
	/**
	 * sets the boolean value of the field hit
	 * @param if the player object has been intersected an enemy 
	 */
	public void setHit(boolean isHit) {
		hit = isHit;
	}
}
