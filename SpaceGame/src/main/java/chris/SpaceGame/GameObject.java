package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
/**
 * GameObject class for all game objects on the canvas, this is subclassed to background and foreground 
 * objects. Requires x and y co-ordinates of the object, image, and a graphics context.
 * @author Chris Daniels
 *
 */
public class GameObject {
	protected Image image;
	protected double x, y;
	protected GraphicsContext gc;	
	
	public GameObject(double x, double y, GraphicsContext gc) {
		super();
		this.x = x;
		this.y = y;
		this.gc = gc;
		
	}
	
	/**
	 * Draws the game object on the canvas
	 */
	public void update() {
		if(image!=null)
			gc.drawImage(image, x, y,30,30);		
	}	

}
