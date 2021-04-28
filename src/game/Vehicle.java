package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;


/**
 * A Vehicle.
 * 
 * @author HinSeng Leong
 *
 */
public class Vehicle extends Item {

	/**
	 * The default constructor to create a vehicle
	 * 
	 * @param name the vehicle's display name
	 * @param displayChar character that will represent the vehicle on the map 
	 */
	public Vehicle(String name, char displayChar) {
		super(name, displayChar,false);
		// TODO Auto-generated constructor stub

	}
	
	/**
	 * Action that will be added in this vehicle.
	 * 
	 * @param action Action to be added.
	 */
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}

}
