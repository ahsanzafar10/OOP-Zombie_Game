package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

public class ZombieAttackBehaviour extends AttackBehaviour {

	public ZombieAttackBehaviour(ZombieCapability attackableTeam) {
		super(attackableTeam);
		// TODO Auto-generated constructor stub
	}
	
//	public Action getAction(Actor actor, GameMap map) {
//		// Is there an attackable Actor next to me?
//		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
//		Collections.shuffle(exits);
//		
//		for (Exit e: exits) {
//			if (!(e.getDestination().containsAnActor()))
//				continue;
//			if (e.getDestination().getActor().hasCapability(attackableTeam)) {
//				if (actor.getWeapon().verb() == "bite") {
//					return new BiteAttackAction(e.getDestination().getActor());
//				} else  {
//					return AttackAction(e.getDestination().getActor());
//				}
//				
//			}
//		}
//		return null;
//	}
//	
	


	
	// Needs to determine if attack is a biteAttackAction or AttackAction.
	// This behavior will return a biteAttackAction or AttackAction.
	
	
	
}
