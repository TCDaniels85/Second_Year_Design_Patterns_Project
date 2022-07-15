package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 
 * @author Thomas Daniels
 * class for to represent the game object, planet Mars, provides concrete realisation of abstract Planet class methods,
 * pointsAdd(), pointsRemove(), and returnName()
 *
 */
public class Mars extends Planet{
	
	public Mars(double x, double y, GraphicsContext gc) {
		super(x, y, gc);		
		image = new Image(Mars.class.getResource("mars.png").toExternalForm());
		super.update();
		planetName = "mars";
	}

	@Override
	/**
	 * Returns points to add to player score
	 */
	public int pointsAdd() {
		
		return 2000;
	}

	@Override
	/**
	 * Returns the points to deduct from the player
	 */
	public int pointsRemove() {
		
		return -10;
	}
}
