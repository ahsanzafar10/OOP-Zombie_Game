package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

public class BiteAttackAction extends AttackAction{

	public BiteAttackAction(Actor target) {
		super(target);
	}
	
	/*
	 super.execute(target, chance)
	 */
	
	public String execute(Actor actor, GameMap map) {
		
		
		Weapon weapon = actor.getWeapon();
		
		// if ( [ choose int between 0 and 100 ] if int < hitChance ) 
		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
			
//		actor.heal(5);
		
//		return executeHelper(actor, map, damage);
		}
		return null;
	}

}