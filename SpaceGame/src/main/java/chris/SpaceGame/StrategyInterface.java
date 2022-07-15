package chris.SpaceGame;
/**
 * 
 * @author Chris Daniels
 * Strategy Interface which enables strategies to be provided for foreground game objects.
 * The strategy classes provide a concrete implementation for the execute method.
 *
 */
public interface StrategyInterface {
	public void execute(ForegroundGameObject object);
	
}
