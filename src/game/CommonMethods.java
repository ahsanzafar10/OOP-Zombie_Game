package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class CommonMethods {
	
	public void KillActor(Actor target, GameMap map) {
		Item corpse;
		
		// If human is dead, it needs rise from dead, so we create a resurrectable corpse item.
		// Else, we create a normal corpse item.
		if (target instanceof Human) {
			corpse = new ResurrectableCorpseItem("Dead " + target, '^');		
		} else {
			corpse = new PortableItem("dead " + target, '%');
		}
		
		map.locationOf(target).addItem(corpse);
		
		Actions dropActions = new Actions();
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)		
			drop.execute(target, map);
		map.removeActor(target);	
	}
}
