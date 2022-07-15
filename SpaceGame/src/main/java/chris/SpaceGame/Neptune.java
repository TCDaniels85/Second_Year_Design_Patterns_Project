package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 
 * @author Thomas Daniels
 * class for to represent the game object, planet Neptune, provides concrete realisation of abstract Planet class methods,
 * pointsAdd(), pointsRemove(), and returnName()
 *
 */
public class Neptune extends Planet {
		
	public Neptune(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		image = new Image(Neptune.class.getResource("neptune.png").toExternalForm());
		super.update();
		planetName = "neptune";
	}
	
	/**
	 * Overrides game object image size to give some sense
	 * of relative sizes of planets
	 */
	public void update() {
		if(image!=null)
			gc.drawImage(image, x, y,40,40);
		updateRectangle();
	}

	@Override
	/**
	 * Returns points to add to player score
	 */
	public int pointsAdd() {
		
		return 4000;
	}

	@Override
	/**
	 * Returns the points to deduct from the player
	 */
	public int pointsRemove() {
		
		return -30;
	}

	
}
