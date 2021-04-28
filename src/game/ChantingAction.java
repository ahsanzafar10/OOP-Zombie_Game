package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


/**
 * Action for MamboMarie to spawn five new zombies
 * 
 * @author HinSeng Leong
 */

public class ChantingAction extends Action {

	
	private int x;
	private int y;
	
    /**
     * Count how many zombies have been spawned.
     */
	static int number = 0;
	
	
	private String name;
	private Random random = new Random();
	
	
	
	
	/**
	 * Executes a chanting and spawn five zombies at five different locations.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			number++;
			name = "Zombie " + number;
			do {
				x = random.nextInt(map.getXRange().max());
				y = random.nextInt(map.getYRange().max());
			} 
			while (map.at(x, y).containsAnActor());
			map.at(x,  y).addActor(new Zombie(name));	
		}
		return actor + " spawns five new zombies";
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return null;
	}

}
