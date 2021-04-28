package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;


/**
 * A new type of zombie called Mambo Marie.
 * 
 * @author HinSeng Leong
 *
 */
public class MamboMarie extends ZombieActor {
	
	/**
	 * Behaviours that Mambo Marie has.
	 * 
	 */
	private Behaviour wanderbehaviour = new WanderBehaviour();
	private Behaviour chantingbehaviour = new ChantingBehaviour();
	
	/**
	 * Variable to count the playturn of Mambo Marie.
	 * 
	 */
	private int turn = 0;
	Display display = new Display();
	
	
	/**
	 * The default constructor to create Mambo Marie.
	 * 
	 * @param name the Mambo Marie's display name
	 * @param displayChar character that will represent the Mambo Marie in the map 
	 * @param hitPoints amount of damage that the Mambo Marie can take before it dies
	 */
	public MamboMarie(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.UNDEAD);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Every 10 turns Mambo Marie will spawns five zombies on the map. She will vanish at turn 30.
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// TODO Auto-generated method stub
		turn++;
//		for (Behaviour behaviour : behaviours) {
//			Action action = behaviour.getAction(this, map);
//			if (action != null)
//				return action;
//		}
		if (turn %10 == 0) {
			Action action = chantingbehaviour.getAction(this,map);
			if (turn == 30) {
				map.removeActor(this);
				display.println("Marie has vanished");
			}
			return action;
		}
		else {
			Action action = wanderbehaviour.getAction(this, map);
			return action;
		}	
		
	}
	
}
