package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class AimingAction extends Action {
	
	// int level = 1
	
	public AimingAction(Action action) {
		//lastAction = 
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		// if level = 1 
		// 1. shoot 75
		// 2. aim again 100
		// chose 2
		// level ++
		// if level 2
		// 1. shoot instakill
		// sniper.aimimg =false
		return null;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		// player continues using sniper...
		return null;
	}
	

}
