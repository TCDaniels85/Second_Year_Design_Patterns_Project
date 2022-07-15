package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 
 * @author Thomas Daniels
 * class for to represent the game object, planet Jupiter, provides concrete realisation of abstract Planet class methods,
 * pointsAdd(), pointsRemove(), and returnName()
 *
 */
public class Jupiter extends Planet {
	private String planetName;
	
	public Jupiter(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		image = new Image(Jupiter.class.getResource("jupiter.png").toExternalForm());
		super.update();
		planetName = "jupiter";
	}
	
	/**
	 * Overrides game object image size to give some sense
	 * of relative sizes of planets
	 */
	public void update() {
		if(image!=null)
			gc.drawImage(image, x, y,50,50);
		updateRectangle();
	}

	@Override
	/**
	 * Returns points to add to player score
	 */
	public int pointsAdd() {
		
		return 3000;
	}

	@Override
	/**
	 * Returns the points to deduct from the player
	 */
	public int pointsRemove() {
		
		return -15;
	}

	@Override
	/**
	 * Returns String:  planet name
	 */
	public String returnName() {
		
		return planetName;
	}

}
