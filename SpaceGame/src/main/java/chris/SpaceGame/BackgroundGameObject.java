package chris.SpaceGame;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
/**
 * Abstract class for background game objects, provides methods and fields that are inherited by the subclass objects
 * that in the background of the game.
 * @author Chris Daniels
 *
 */
public abstract class BackgroundGameObject extends GameObject {
	protected Random random;
	
	public BackgroundGameObject(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		
	}
	
	

}
