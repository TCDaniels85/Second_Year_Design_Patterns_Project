package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * FireAsteroid Class, creates a FireAsteroid enemy, additional fields from the superclass are int time to keep track of frames 
 * for animation, and Image a,b,and c to store animation frames 
 * 
 *@author Chris Daniels
 */
public class FireAsteroid extends Enemy {
	private int time = 0;
	protected Image a, b, c;
	
	public FireAsteroid(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		a = new Image(FireAsteroid.class.getResource("fireasteroid1.png").toExternalForm());
		b = new Image(FireAsteroid.class.getResource("fireasteroid2.png").toExternalForm());
		c = new Image(FireAsteroid.class.getResource("fireasteroid3.png").toExternalForm());
		image = a; //initial image
		pointsValue = 25;//points value for shooting the fire asteroid
		name = "fireAsteroid";//provides enemy name
		update();
		
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
		if(time>6) {			
			image = a;
			time = 0;
		}
		super.update();
	}
}
