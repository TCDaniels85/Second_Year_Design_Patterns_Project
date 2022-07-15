package chris.SpaceGame;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/** 
 * @author Chris Daniels
 * smallStar Class, provides a background star image to give the impression the player is flying 
 * space. SmallStar traverses the canvas slower than the large Star to achieve a sense of background depth
 * so the smaller star appears further away.
 *
 */
public class SmallStar extends BackgroundGameObject{
	private double yStart = 0;
	
	
	public SmallStar(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		image = new Image(Star.class.getResource("star-small.png").toExternalForm());
		update();
	}
	
	/**
	 * Provides movement to the background class Star updating the position y by +4 
	 * each animation frame. Once the star has reached the bottom of the screen, the y 
	 * position is reset to 0 and the x position is set to using a random number generate a
	 * random on screen x position
	 */
	public void update() {
		y+=4;
		random = new Random(System.currentTimeMillis());
		
		if (y>500) {
			y=yStart;
			x=random.nextInt(800);
		}
		super.update();
	}

}
