package chris.SpaceGame;

import java.util.Random;
/**
 * All strategy classes are written within this file
 * 
 * EnemyStrategy class provides a concrete implementation for the for the execute strategy in
 * the strategy interface.
 * Strategy moves foreGround game objects either  left or right depending on the directionLeft 
 * boolean in the enemy, this is set when the object is moved to the top of the screen. When the object
 * reaches the bottom of the screen, it is reset to the top of the screen (beyond the visible boundary)
 * at a random x co-ordinate 
 * 
 * @author Chris Daniels
 *
 */
public class EnemyStrategy implements StrategyInterface{
		
	@Override
	public void execute(ForegroundGameObject enemy) {
		Random random = new Random();
		//sets random appearance 1\900 chance each frame
		if(random.nextInt(900) == 4 && ((Enemy)enemy).getIsVisible() == false) {
			((Enemy)enemy).setIsVisible(true);			
			enemy.setX(random.nextInt(800));
			((Enemy)enemy).setDirectionLeft(random.nextBoolean());						
		}
		if(((Enemy)enemy).getIsVisible() == true) {
			enemy.y += 3;
			if(((Enemy)enemy).getDirectionLeft()==true) {
				enemy.x -=1;
			} else {
				enemy.x +=1;
			}	
		}
		if(enemy.getY() > 550) { // resets enemy outside screen boundary
			enemy.setY(-40);
			((Enemy)enemy).setX(random.nextInt(800));			
			((Enemy)enemy).setIsVisible(false);
		}
		enemy.update();
	}

}
/**
 * EnemyStrategyB class provides a concrete implementation for the for the execute strategy in
 * the strategy interface.  
 * This Strategy moves the game object down the screen in a circular motion, when the object reaches
 * the bottom of the screen, it is reset to the top of the screen (beyond the visible boundary)
 * at a random x co-ordinate
 * 
 * @author Chris Daniels
 *
 */
class EnemyStrategyB implements StrategyInterface{
	protected double r = 0.0;//radian for sin/cos function
	@Override
	public void execute(ForegroundGameObject enemy) {
		Random random = new Random();
		//sets random appearance 1\1300 chance each frame
		if(random.nextInt(1300) == 4 && ((Enemy)enemy).getIsVisible() == false) {
			((Enemy)enemy).setIsVisible(true);			
			enemy.setX(random.nextInt(800));						
		}
		
		//circular motion
		if(((Enemy)enemy).getIsVisible() == true) {
			r += 0.04;
			enemy.x = enemy.x + Math.cos(r) * 1.5;
			enemy.y = enemy.y + Math.sin(r) * 1.5;			
			enemy.y += 1;
			enemy.y+=2;
		}
		
		
		if(enemy.y > 550) { // resets enemy to top for test
			enemy.y=-40;
			((Enemy)enemy).setX(random.nextInt(800));
			((Enemy)enemy).setIsVisible(false);
		}		
		enemy.update();		
	}	
}
/**
 * EnemyStrategyLevel2A class provides a concrete implementation for the for the execute strategy in
 * the strategy interface.
 * Same as EnemyStrategy but the initial random integer boundary to set enemy visibility is lowered to
 * 600 to give a higher chance that an enemy will appear sooner creating a harder difficulty
 * 
 * @author Chris Daniels
 *
 */
class EnemyStrategyLevel2A implements StrategyInterface {
	
	
	@Override
	public void execute(ForegroundGameObject enemy) {
		Random random = new Random();
		//sets random appearance 1\600 chance each frame
		if(random.nextInt(600) == 4 && ((Enemy)enemy).getIsVisible() == false) {
			((Enemy)enemy).setIsVisible(true);			
			enemy.setX( random.nextInt(800));
			((Enemy)enemy).setDirectionLeft(random.nextBoolean());						
		}
		if(((Enemy)enemy).getIsVisible() == true) {
			enemy.y += 4;
			if(((Enemy)enemy).getDirectionLeft()==true) {
				enemy.x -=1;
			} else {
				enemy.x +=1;
			}		
		}
		if(enemy.y > 550) { // resets enemy to top for test
			enemy.setY(-40);
			((Enemy)enemy).setX(random.nextInt(800));
			((Enemy)enemy).setIsVisible(false);
		}
		enemy.update();		
	}
}
/**
 * EnemyStrategyLevel2B class provides a concrete implementation for the for the execute strategy in
 * the strategy interface.
 * Same as EnemyStrategyB but the initial random integer boundary to set enemy visibility is lowered to
 * 1000 to give a higher chance that an enemy will appear sooner creating a harder difficulty
 * 
 * @author Chris Daniels
 *
 */
class EnemyStrategyLevel2B implements StrategyInterface{
	protected double r = 0.0;//radian for sin/cos function
	@Override
	public void execute(ForegroundGameObject enemy) {
		Random random = new Random();
		//sets random appearance 1\1000 chance each frame
		if(random.nextInt(1000) == 4 && ((Enemy)enemy).getIsVisible() == false) {
			((Enemy)enemy).setIsVisible(true);			
			enemy.setX(random.nextInt(800));						
		}		
		//circular motion
		if(((Enemy)enemy).getIsVisible() == true) {
			r += 0.04;
			enemy.x = enemy.x + Math.cos(r) * 1.5;
			enemy.y = enemy.y + Math.sin(r) * 1.5;			
			enemy.y += 1;
			enemy.y+=2.5;
		}
		
		
		if(enemy.y > 550) { // resets enemy to top for test
			enemy.setY(-40);
			((Enemy)enemy).setX(random.nextInt(800));
			((Enemy)enemy).setIsVisible(false);
		}		
		enemy.update();		
	}	
}
/**
 * PlanetStrategyA class provides a concrete implementation for the for the execute strategy in
 * the strategy interface.
 * This strategy moves the planets down the screen in a straight line, if the object reaches the bottom of the
 * screen the y co-ordinate is reset to off screen and a new random x co-ordinate is assigned 
 * @author Chris Daniels
 *
 */
class PlanetStrategyA implements StrategyInterface{
	
	@Override
	public void execute(ForegroundGameObject object) {
		Random random = new Random();
		
		object.y += 1;
		object.update();
		//reset position
		if(object.y>550) {
			object.setY(-50);
			object.setX(random.nextInt(700) + 50);
			}
	}	
}
/**
 * PlanetStrategyB class provides a concrete implementation for the for the execute strategy in
 * the strategy interface.
 * This strategy moves the planets down the screen in a diagonally right at a quicker speed that planetstraegyA,
 * if the object reaches the bottom of the screen the y co-ordinate is reset to off screen and a new 
 * random x co-ordinate is assigned  * 
 * @author Chris Daniels
 *
 */
class PlanetStrategyB implements StrategyInterface{
	
	@Override
	public void execute(ForegroundGameObject object) {
		Random random = new Random();
		object.y += 2.5;		
		object.x +=0.4;
		
		
		object.update();
		//reset position
		if(object.y>550) {
			object.setY(-50);
			object.setX(random.nextInt(700) + 50);
		}
	}
	
}
/**
 * SatelliteStrategyA class provides a concrete implementation for the for the execute strategy in
 * the strategy interface.
 * This strategy moves the satellite object across the screen from left to right, within the visible 
 * boundary, at a random point the drop field is set to true which indicates that the next strategy can
 * be selected  
 * @author Chris Daniels
 *
 */
class SatelliteStrategyA implements StrategyInterface{
	private boolean travelRight = true;
	@Override
	public void execute(ForegroundGameObject object) {
		Random random = new Random();
		if(travelRight == true)
			object.x +=7;
		else 
			object.x -=7;
		//changes direction when edge of screen is encountered
		if(object.getX() > 770) {
			travelRight = false;
		} else if (object.getX() < 0) {
			travelRight = true;
		}
		//1/600 chance each frame that drop is set to true
		if(random.nextInt(600) == 4)
			((Satellite)object).setDrop(true);		
		object.update();

	}	
}
/**
 * SatelliteStrategyB class provides a concrete implementation for the for the execute strategy in
 * the strategy interface.
 * This strategy moves the satellite down the screen in a snaking motion, when it reaches the bottom 
 * the position is reset to off screen and the drop field is also reset. 
 * @author Chris Daniels
 *
 */
class SatelliteStrategyB implements StrategyInterface{
	private double r=0.0; //radian for sin/cos function
	@Override
	public void execute(ForegroundGameObject object) {
		
		
		//snaking motion
		r += 0.02;
		object.x = object.x + Math.cos(r) * 1.5;
		object.y = object.y + 1;
		//resets position of satellite when it reaches bottom of screen
		if(object.getY()>550) {
			object.setX(-50);
			object.setY(70);
			((Satellite)object).setDrop(false);
		}
		
		
		object.update();

	}	
}
