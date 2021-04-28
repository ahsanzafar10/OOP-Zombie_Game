package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * 
 * Abstract class representing a Special Action for shooting with a Shot Gun.
 *
 * @author Ahsan Zafar
 */

public abstract class ShotGunAction extends AttackAction {
	private String direction;
	private Display display = new Display();
	private Random chance = new Random();
	
	/**
	 * Constructor 
	 * 
	 * @param direction - The direction of shot - (North, South, East or West)
	 */
	public ShotGunAction(String direction) {
		super(null);
		this.direction = direction;
	}
	
	
	public abstract Set <ArrayList<Integer>> getDirections(int x, int y);
		
	
	/**
	 * Check to see if a 0<= num <= maxRange.
	 * 
	 * @param num - Number to check.
	 * @param maxRange - Maximum range.
	 * @return True if 0<= num <= maxRang, else return False.
	 */
	private boolean InRange(int num, int maxRange) {
		
		if (num>=0 && num <= maxRange) {
			return true;
		}
		
		return false;
	}
	
         		
	@Override
	/**
	 * Executes a Shotgun shooting attack.
	 */
	public String execute(Actor actor, GameMap map) {
		
		// Obtain all the locations that can be reached from a shotgun shooting.
		Set<ArrayList<Integer>> coordinates = this.getDirections(map.locationOf(actor).x(),map.locationOf(actor).y());
		
		// Loop over each location, and check if there is an actor on that location.
		// If actor is there, then execute the action.
		for (List<Integer> list: coordinates) {
			int x = (int) list.get(0);
			int y = (int) list.get(1);
			
			// If x coordinate and y coordinate are not within valid range, then we should ignore that location.
			if (! (InRange(x, map.getXRange().max()) && InRange(y, map.getYRange().max())) ) {
				continue;
			}
			
			// Obtain actor at location.
			Location TargetLocation = map.at(x, y);			
			Actor target =  map.getActorAt(TargetLocation);
			
			//Set hitchance of 75% and execute shooting.
			int chanceNum = 1 + chance.nextInt(100);
			if (target!=null && chanceNum < 75) {				
				
				target.hurt(45);
				String result = actor + " " + "shoots " + " " + target + " with shotgun for " + 45 + " damage.";
				
				if (!target.isConscious()) {
					
					this.processKill(map, target);	
					result = target + " is killed.";
					display.println(result);
				}
			}
		}
		
		return "Player fires with a shotgun in the " + direction + " direction";
	}
	
	/**
	 * Menu description to display to console
	 * 
	 * @return A string describing action to choose.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots in " + direction + " direction";
	}
	
}
