package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
/**
 * Implements the character factory interface to create gameObjects
 * @author chris
 *
 */
public class CharacterFactory implements CharacterFactoryIF{
	GraphicsContext gc;

	public CharacterFactory(GraphicsContext gc) {
		super();
		this.gc = gc;
	}
	/**
	 * Concrete implementation to create foreground game objects
	 * @return ForegroundGameObject created
	 * @param description of the object to create and it's starting co-ordinates
	 */
	@Override
	public ForegroundGameObject createCharacter(String description, double x, double y) {
		
		if (description.equals("player"))
			return new Player(x,y,gc);
		if (description.equals("asteroid"))
			return new Asteroid(x,y,gc);
		if (description.equals("fireAsteroid"))
			return new FireAsteroid(x,y,gc);
		if (description.equals("satellite"))
			return new Satellite(x,y,gc);
		if (description.equals("bullet"))
			return new Bullet(x,y,gc);
		if(description.equals("mercury"))
			return new Mercury(x,y,gc);
		if(description.equals("venus"))
			return new Venus(x,y,gc);
		if(description.equals("earth"))
			return new Earth(x,y,gc);
		if(description.equals("mars"))
			return new Mars(x,y,gc);
		if(description.equals("jupiter"))
			return new Jupiter(x,y,gc);
		if(description.equals("saturn"))
			return new Saturn(x,y,gc);		
		if(description.equals("uranus"))
			return new Uranus(x,y,gc);
		if(description.equals("neptune"))
			return new Neptune(x,y,gc);
		return null;
		
	}
	/**
	 * Concrete implementation to create foreground game objects
	 * @return BackgroundGameObject that is created
	 * @param description of the object to create and it's starting co-ordinates
	 */
	@Override
	public BackgroundGameObject createCharacterB(String description, double x, double y) {
		if (description.equals("star"))
			return new Star(x,y,gc);
		if (description.equals("small-star"))
			return new SmallStar(x,y,gc);
		return null;
	}

}
