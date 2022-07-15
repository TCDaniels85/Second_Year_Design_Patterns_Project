package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 
 * @author Thomas Daniels
 * class for to represent the game object, planet Saturn, provides concrete realisation of abstract Planet class methods,
 * pointsAdd(), pointsRemove(), and returnName()
 *
 */
public class Saturn extends Planet {
		
	public Saturn(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		image = new Image(Saturn.class.getResource("saturn.png").toExternalForm());
		super.update();
		planetName = "saturn";
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
		// TODO Auto-generated method stub
		return 2000;
	}

	@Override
	
	/**
	 * Returns the points to deduct from the player
	 */
	
	public int pointsRemove() {
		// TODO Auto-generated method stub
		return -20;
	}

}
