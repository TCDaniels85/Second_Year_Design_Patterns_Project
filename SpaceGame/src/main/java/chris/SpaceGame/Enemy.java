package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * Abstract class enemy, provides methods and fields that are inherited by the subclass enemy types, Asteroid, FireAsteroid, 
 * and satellite.
 * @author Chris Daniels
 *
 */
public abstract class Enemy extends ForegroundGameObject {	
	protected StrategyInterface strategy; //strategy being used by the class
	protected int pointsValue;
	protected boolean isVisible = false; //Enemy visibility on canvas
	protected boolean directionLeft = false; //direction of travel
	protected String name; //enemy name
	
	public Enemy(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		
	}
	/**
	 * Sets the strategy to be used by the Enemy subclass
	 * @param strategy: The strategy to be implemented by the enemy subclass object
	 */
	public void setStrategy(StrategyInterface strategy) {
		this.strategy = strategy;
		
	}
	/**
	 * Executes the current strategy set in the strategy field 
	 */
	public void execute() {		
		this.strategy.execute(this);		
	}
	

	/**
	 * Method to return the amount of points each enemy is worth when they are destroyed by
	 * player
	 * @return pointsValue: number of points enemy is worth
	 */
	public int getPointsValue() {
		return pointsValue;
	}
	/**
	 * Returns boolean if the enemy is visible on the canvas
	 * @return isVisible
	 */
	public boolean getIsVisible() {
		return isVisible;
	}
	/**
	 * set the objects visibility on the canvas
	 * @param visibility is the object visible
	 */
	public void setIsVisible(boolean visibility) {
		isVisible = visibility;
	}
	/**
	 * Returns the enemy name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns whether or not the enemy is set to move left
	 * @return boolean left direction or not
	 */
	public boolean getDirectionLeft() {
		return directionLeft;
	}
	/**
	 * Sets the direction of the enemy object
	 * @param isLeft: true if object is to move, false if object is to move
	 * right
	 */
	public void setDirectionLeft(boolean isLeft) {
		directionLeft = isLeft;
	}
	
}
