package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
/**
 * Abstract class ForegroundGameObject, provides methods and fields that are inherited by the subclass objects
 * that in the foreground of the game that the player can interact with
 * @author Chris Daniels
 *
 */
public abstract class ForegroundGameObject extends GameObject {
	protected Rectangle hitBox; //hit box field to enable object collision detection on the canvas
	
	public ForegroundGameObject(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		hitBox = new Rectangle(0,0,30,30);
	}
	/**
	 * Calls to super class GameObject update method and makes a call to update the hitbox;
	 */
	public void update() {
		updateRectangle();
		super.update();
	}
	/**
	 * updates the co-ordinates of the hitbox to be the same as the object
	 */
	public void updateRectangle() {
		hitBox.setX(x);
		hitBox.setY(y);
		hitBox.setWidth(30);
		hitBox.setHeight(30);
	}
	/**
	 * Retrieve object x co-ordinate
	 * @return x: the object x co-ordinate
	 */
	public double getX() {
		return x;
	}
	/**
	 * Retrieve object Y co-ordinate
	 * @return y: the objects y co-ordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Sets an objects x co-ordinate on the canvas.
	 * @param xValue: enemy x co-ordinate
	 */
	public void setX(double xValue) {
		this.x = xValue;
	}
	/**
	 * Sets an objects y co-ordinate on the canvas.
	 * @param yValue: enemy y co-ordinate
	 */
	public void setY(double yValue) {
		this.y = yValue;
	}
	/**
	 * Returns hitBox dimensions and position on canvas
	 * @return hitbox dimensions
	 */
	public Rectangle getHitBox() {
		return hitBox;
	}

}
