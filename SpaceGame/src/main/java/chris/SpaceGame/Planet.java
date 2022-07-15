package chris.SpaceGame;

import javafx.scene.canvas.GraphicsContext;
/**
 * Abstract class Planet, provides methods and fields that are inherited by the  8 subclass planet types
 * @author Chris Daniels
 *
 */
public abstract class Planet extends ForegroundGameObject{
	protected StrategyInterface strategy;//strategy being used by the class
	protected String planetName;
	public Planet(double x, double y, GraphicsContext gc) {
		super(x, y, gc);
		
	}
	//Abstract methods, concrete implementation provided by subclass
	public abstract int pointsAdd();
	public abstract int pointsRemove();
	
	
	/**
	 * Sets the strategy to be used by the Planet subclass
	 * @param strategy: The strategy to be implemented by the Planet subclass object
	 */
	public void setStrategy(StrategyInterface strategy) {
		this.strategy = strategy;
		
	}
	/**
	 * Executes the current strategy set in the strategy field 
	 */
	public void execute() {		
		this.strategy.execute(this);		
	}
	
	/**
	 * Returns String:  planet name
	 */
	public String returnName() {
		
		return planetName;
	}

}
