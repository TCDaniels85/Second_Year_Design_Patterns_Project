package chris.SpaceGame;
/**
 * CharacterFactoryInterface, classes implementing this interface provide a concrete implementation of it's 
 * methods to create foreground and background gameObjects
 * @author Chris Daniels
 *
 */
public interface CharacterFactoryIF {
	ForegroundGameObject createCharacter(String description, double x, double y);
	BackgroundGameObject createCharacterB(String description, double x, double y);
}
