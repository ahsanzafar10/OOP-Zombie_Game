package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	protected int WaitingRounds = 0;
	

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		
		// Ignore if statement!
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		
		actions.add(new QuitAction());
		
		// If actor did something else, concentration of aiming using Sniper is broken.
		if (!(lastAction instanceof SniperFiringAction)) {
			WaitingRounds = 0;
		}
		
		Action returnAction = menu.showMenu(this, actions, display);
		
		if (returnAction instanceof ShotGunAction) {
			RemoveAmmoFromInventory();
		}
		
		return returnAction;
	}
	
	public void RemoveAmmoFromInventory() {
		Item itemToRemove = null;
		boolean remove = false;
		
		for (Item item: this.getInventory()) {
			
			if ( item.hasCapability(GunCapability.SHOTGUN)) {
				itemToRemove = item;
				remove = true;
			}
		}
		
		if (remove) {
			this.removeItemFromInventory(itemToRemove);
		}
	}
	
}
