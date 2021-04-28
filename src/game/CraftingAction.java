package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;


/**
 * Action for player to craft weapons
 * 
 * @author HinSeng Leong
 */
public class CraftingAction extends Action {
	
	/**
	 * The weapon item needs to be crafted.
	 */
	protected WeaponItem item;
	
	
	/**
	 * Constructor.
	 * 
	 * @param item the weapon item needs to be crafted.
	 */
	public CraftingAction(WeaponItem item) {
		this.item = item;
	}

	/**
	 * Executes a crafting depends on the weapon .
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(item);
		if (item.getClass() == ZombieArm.class) {
			actor.addItemToInventory(new ZombieClub());
		} else if(item.getClass() == ZombieLeg.class) {
		  actor.addItemToInventory(new ZombieMace());
		}
		return actor + " has succefully crafted a weapon!";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " crafts " + item.toString();
	}

}
