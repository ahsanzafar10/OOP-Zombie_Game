package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * A Farmer.
 * 
 * @author HinSeng Leong
 *
 */
public class Farmer extends Human{
	
	private Random random = new Random();
	
	/**
	 * Behaviours that farmer has.
	 */
	private Behaviour[] behaviours = {new FertilizeBehaviour(), new SowingBehaviour(), new HarvestBehaviour(),new HumanPickUpBehaviour(), new EatingBehaviour(), new WanderBehaviour() };
	
	/**
	 * The default constructor creates default Farmers
	 * 
	 * @param name the Farmer's display name
	 */
	public Farmer(String name) {
		super(name, 'F', 50);
	}
	
	/**
	 * The protected constructor can be used to create subtypes
	 * of Farmer
	 * 
	 * @param name the farmer's display name
	 * @param displayChar character that will represent the Farmer in the map 
	 * @param hitPoints amount of damage that the Farmer can take before it dies
	 */
	protected Farmer(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
}
