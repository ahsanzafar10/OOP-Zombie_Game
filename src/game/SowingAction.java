package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;


/**
 * Action for sowing on a dirt.
 * 
 * @author HinSeng Leong
 */
public class SowingAction extends Action {
	
	
	/**
	 * The location needs to be sowed.
	 */
	
	protected Location target;
	
	
	/**
	 * Constructor.
	 * 
	 * @param location the location to sow
	 */
	public SowingAction(Location location) {
		this.target = location;
	}

	/**
	 * Executes a sow on target.
	 */
	@Override
	public String execute(Actor actor,GameMap map) {
		
		target.setGround(new Crop());
		return menuDescription(actor);	
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " sows on a dirt";
	}

}
