package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * Asteroid Class, creates an Asteroid enemy, additional fields from the superclass are int time to keep track of frames 
 * for animation, and Image a,b,and c to store animation frames
 * @author Chris Daniels
 *
 */
public class Asteroid extends Enemy  {
	private int time = 0;	
	protected Image a, b, c;
	
	public Asteroid(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		a = new Image(Asteroid.class.getResource("asteroid1.png").toExternalForm());
		b = new Image(Asteroid.class.getResource("asteroid2.png").toExternalForm());
		c = new Image(Asteroid.class.getResource("asteroid3.png").toExternalForm());
		image = a;		//initial image
		pointsValue = 10; //provides points value for shooting the asteroid
		update();
		name = "asteroid"; //provides enemy name 
		
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
