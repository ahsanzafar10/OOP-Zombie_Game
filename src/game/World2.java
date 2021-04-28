package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

/**
 * Class representing the game world, including the locations of all Actors, the
 * player, and the playing grid.
 */
public class World2 extends World {
	
	
	boolean playerWinning = false;

	/**
	 * Constructor
	 * @param display - The Display that will display this World.
	 */
	public World2(Display display) {
		super(display);
	}
	
	@Override
	protected String endGameMessage() {
		if (playerWinning) {
			return "Player wins \nGame Over. ";
		}
		
		else {
			return "Player loses \nGame Over.";
		}
		
	}

	/*
	 * Check if Mambo Marie and Zombies are on the map.
	 * If present, return True. Else, return False.
	 */
	protected boolean ZombiesOnMap() {
		
		for (GameMap map: gameMaps) {
			int xRange = map.getXRange().max();
			int yRange = map.getYRange().max();
			
			for (int x=0; x<=xRange; x++) {
				for (int y=0; y<=yRange; y++) {
					Location location = map.at(x, y);
					if (location.containsAnActor() && location.getActor().hasCapability(ZombieCapability.UNDEAD))
						return true;
					}
				}
			}
		return false;
	}
	
	@Override
	protected boolean stillRunning() {
		
		// If player is killed or all the other humans in the compound are killed.
		if (!(actorLocations.contains(player) && this.HumansOnMap()))  {
			playerWinning = false;
			return false;
		}
		
		// If zombies and Mambo Marie have been wiped out and the compound is safe
		if (!ZombiesOnMap()) {
			playerWinning = true;
			return false;
		}
		
		return true;

	}
	
	/*
	 * Check if there is any humans on the map other than player.
	 * Return True if this is the case, else False. 
	 */
	protected boolean HumansOnMap() {
	
		for (GameMap map: gameMaps) {
			int xRange = map.getXRange().max();
			int yRange = map.getYRange().max();
			
			for (int x=0; x<=xRange; x++) {
				for (int y=0; y<=yRange; y++) {
					Location location = map.at(x, y);
					if ((!(location.getActor() == player)) && (location.getActor() instanceof Human))  {
						return true;
					}
				}
			}
		}
		return false;
	}

}
