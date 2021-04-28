package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;


/**
 * Tracker class extends GameMap to keep track whether Mambo Marie exists on the map.
 * 
 * @author HinSeng Leong
 *
 */
public class Tracker extends GameMap {

	private int x;
	private int y;
	private Random random = new Random();
	Actor Marie = new MamboMarie("Marie",'M',10);
	Display display = new Display();
	
	
	/**
	 * The default constructor to create a Tracker 
	 * 
	 * @param groundFactory Factory to create Ground objects
	 * @param lines         List of Strings representing rows of the map
	 */
	public Tracker(GroundFactory groundFactory, List<String> lines) {
		super(groundFactory, lines);
		// TODO Auto-generated constructor stub
	}



	/**
	 * Called once per turn, so that maps can experience the passage of time.
	 */
	@Override
	public void tick() {
		super.tick();
		// Tick over all the items in inventories.
		addMamboMarie(this);

		
		
	}
	
	protected Actor testing() {
		return Marie;
	}
	
	
	/**
	 * Used to check whether Mambo Marie exists on the map and whether she is alive. 
	 * If she does not exist on the map and has not been killed before, 
	 * it has 5% probability to spawn Mambo Marie at random location at the edge of map.
	 */
	private void addMamboMarie(GameMap gameMap) {
		ArrayList<Location> locations = new ArrayList<Location>();
		for (int x:gameMap.getXRange()) {
			locations.add(gameMap.at(x, gameMap.getYRange().min()));
			locations.add(gameMap.at(x, gameMap.getYRange().max()));
		}
		for (int y:gameMap.getYRange()) {
			if (y != 0 && y != gameMap.getYRange().max()) {
			locations.add(gameMap.at(gameMap.getXRange().min(), y));
			locations.add(gameMap.at(gameMap.getXRange().max(), y));
			}
		}
		
		
		if (gameMap.contains(Marie)) {
			
		}
		else if (!gameMap.contains(Marie) && Marie.isConscious()){
			if (random.nextInt(100) < 5) {
				do {
					Location location = locations.get(random.nextInt(locations.size()));
					x = location.x();
					y = location.y();
				}
				while(gameMap.at(x, y).containsAnActor());
				gameMap.at(x, y).addActor(Marie);
				display.println("Marie has appeared");
			}
		}
	}
		
}
