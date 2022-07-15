package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * creates a bullet the player can fire at enemies
 * @author chris Daniels 
 *
 */
public class Bullet extends ForegroundGameObject{

	public Bullet(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		image = new Image(Bullet.class.getResource("bullet.png").toExternalForm()); 
		super.update();
	}
	/**
	 * Overrides superclass update to provide motion up the screen
	 */
	public void update() {
		if(y>10) {//bullet can't go past this co-ordinate
			y-=7;
			super.update();
		}
				
	}

}
