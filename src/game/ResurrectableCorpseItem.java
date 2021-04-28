package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Class represening a corpse item which should resurrect 
 * as a zombie 10 turns later.
 * 
 * @author Ahsan Zafar
 */

public class ResurrectableCorpseItem extends PortableItem{
	int age = 0;
	int ResurrectAge = 10;
	Zombie zombie = new Zombie(this.name);

	
	/**
	 * Constructor 
	 * 
	 * @param name - name of the human
	 * @param displayChar - Display character of the corpse item on map.
	 */
	public ResurrectableCorpseItem(String name, char displayChar) {
		super(name, displayChar);
	}
	
	
	/**
	 * Obtain a random location on the map such that an actor can enter it.
	 * This location is used by ResurrectCorpse() method to place a new zombie at that location.
	 * 
	 * @param map - Reference to the map which actor is on
	 * @return - A location on the map where an actor is allowed to enter it.
	 */
	private Location getRandomLocation(GameMap map) {
		
		int x;
		int y;
		
		// Keep doing this until we find an allowable location, then return it
		do {
			x = (int) Math.floor(Math.random() * 20.0 + 30.0);
			y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			
			System.out.println(map.at(x, y).getDisplayChar());
		} 
		while (!map.at(x, y).canActorEnter(zombie));
		
		return map.at(x, y);
	}
	
	/**
	 *  Resurrects a corpse by adding a new zombie to the map.
	 *  
	 *  @param map - Reference to the map the corpse is on
	 */
	private void ResurrectCorpse(GameMap map) {
		// Obtain Location to resurrect corpse
		Location ResurrectLocation = getRandomLocation(map);
		// Place zombie to location
		map.at(ResurrectLocation.x(), ResurrectLocation.y()).addActor(zombie);	
	}
	
	@Override
	
	/**
	 * This method is called one per turn by the engine.
	 * Method keeps record of the age of the corpse.
	 * Once corse is old enough, the corpe is removed from actor's inventory and 
	 * a new Zombie is placed on map.
	 * 
	 * @param currentLocation - Current location of the corpse item
	 * @param actor - The actor that is holding the corpse in its inventory.
	 */
	public void tick(Location currentLocation, Actor actor) {
		age += 1;
		
		if (this.age >= this.ResurrectAge)  {
			ResurrectCorpse(currentLocation.map());
			actor.removeItemFromInventory(this);
		}
	}
	
	/**
	 * This method is called one per turn by the engine.
	 * Method keeps record of the age of the corpse.
	 * Once corse is old enough, the corpe is removed from map 
	 * a new Zombie is placed on map.
	 * 
	 * @param currentLocation - Current location of the corpse item
	 */
	public void tick(Location currentLocation) {
		age += 1;
		
		if (this.age >= this.ResurrectAge)  {
			GameMap map = currentLocation.map();
			ResurrectCorpse(map);
			map.at(currentLocation.x(), currentLocation.y()).removeItem(this);
		}
	}
	

}
