package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * Satellite Class, creates a satellite enemy, additional fields from the superclass are int time to keep track of frames 
 * for animation, Image a,b,and c to store animation frames, and a boolean drop to signal a change
 * in strategy for the satellite enemy. 
 */
public class Satellite extends Enemy {
	private int time = 0;
	protected boolean drop = false;	//flag to set a new strategy in the main class
	protected Image a, b, c;

	public Satellite(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		a = new Image(Satellite.class.getResource("sat1.png").toExternalForm());
		b = new Image(Satellite.class.getResource("sat2.png").toExternalForm());
		c = new Image(Satellite.class.getResource("sat3.png").toExternalForm());
		image = a;		//initial image
		pointsValue = 35; //points value for shooting the asteroid
		update();
		name = "satellite"; //provides enemy name
	}
	/**
	 * The update method overrides the super class method to add if statements which swap in images
	 * every 5 frames to achieve animation. Then makes a call to the super class to update
	 * position
	 */
	public void update() {
		time += 1;
		if(time==5)			
			image = b;
		if(time==10)			
			image = c;
		if(time>15) {			
			image = a;
			time = 0;
		}
		super.update();
	}
	/**
	 * Returns drop field
	 * @return boolean whether satellite should drop
	 */
	public boolean getDrop() {
		return drop;
	}
	/**
	 * sets drop field to true or false
	 * @param setDrop should the object drop
	 */
	public void setDrop(boolean setDrop) {
		drop = setDrop;
	}
}
