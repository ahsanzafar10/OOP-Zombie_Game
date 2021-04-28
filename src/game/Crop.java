package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;


/**
 * A crop that starts as a unripe crop and grows into a ripe crop.
 * 
 * @author HinSeng Leong
 *
 */
public class Crop extends Ground{
	private int age = 0;
	
	public Crop() {
		super('o');
	}
	
	@Override
	public void tick(Location location) {
		super.tick(location);
		age++;
		if (age >= 20)
			displayChar = 'O';
	}
	
	/**
	 * Method that allows crop grows 10 turn faster.
	 */
	public void increasetick()
	{
		age = age + 10;

	}
	
	/**
	 * Harvest action would display on player's menu once crop ripen.
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
		if (location.getGround().getDisplayChar() == 'O') {
			actions.add(new PlayerHarvestAction(location,direction));
		}
		return actions;	
	}
}
