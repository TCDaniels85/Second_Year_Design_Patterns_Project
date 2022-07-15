package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 
 * @author Thomas Daniels
 * class for to represent the game object, planet Earth, provides concrete realisation of abstract Planet class methods,
 * pointsAdd(), pointsRemove(), and returnName()
 *
 */
public class Earth extends Planet {
	private String planetName;
	
	public Earth(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		image = new Image(Earth.class.getResource("earth.png").toExternalForm());
		super.update();
		planetName = "earth";
	}
	
	/**
	 * Overrides game object image size to give some sense
	 * of relative sizes of planets
	 */
	public void update() {
		if(image!=null)
			gc.drawImage(image, x, y,35,35);
		updateRectangle();
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

	@Override
	/**
	 * Returns String:  planet name
	 */
	public String returnName() {
		
		return planetName;
	}

}
