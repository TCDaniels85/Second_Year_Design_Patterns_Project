package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 
 * @author Chris Daniels
 * PlayerExplode class, this class is delegated to following the player intersecting an enemy hit box
 * Implements delegation interface 
 *
 */
public class PlayerExplode extends ForegroundGameObject implements DelegationInterface {
	private int time = 0;
	protected Image a,b,c;
	public PlayerExplode(double x, double y, GraphicsContext gc) {
		super(x, y, gc);		
		a = new Image(PlayerExplode.class.getResource("explode1.png").toExternalForm());
		b = new Image(PlayerExplode.class.getResource("explode2.png").toExternalForm());
		c = new Image(PlayerExplode.class.getResource("explode3.png").toExternalForm());
		image = a;
		super.update();		
	}
	
	/**
	 * The update method overrides the super method to add if statements which swap in images
	 * every 2 frames to achieve animation 
	 */
	public void update() {
		time++;
		if(time==2)			
			image = b;
		if(time==6)			
			image = c;
		if(time>7) {			
			image = a;
			time = 0;
		}		
		super.update();		
	}


}
