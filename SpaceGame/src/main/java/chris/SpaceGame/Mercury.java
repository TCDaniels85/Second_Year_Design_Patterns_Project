package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Mercury extends Planet{
	
	

	public Mercury(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		image = new Image(Mercury.class.getResource("mercury.png").toExternalForm());
		super.update();
		planetName = "mercury";
	}
	/**
	 * Returns points to add to player score
	 */
	@Override
	public int pointsAdd() {
		return 1000;
	}
	/**
	 * Returns the points to deduct from the player
	 */
	@Override
	public int pointsRemove() {		
		return -10;
	}

	

}
