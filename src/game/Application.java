package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World2 world = new World2(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"#...................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		
		
		//Town map
		List<String> map2 = Arrays.asList(
		"################################",
		"#..............................#",
		"#..............................#",
		"#.........................######",
		"#..........+...................#",
		"#..............++.........#....#",
		"#...................++....#....#",
		"#.........................#....#",
		"#.........................#....#",
		"#.........................#....#",
		"#.........................#....#",
		"#....+....................#....#",
		"#....+....................#....#",
		"#.........................#....#",
		"#.........................#....#",
		"#..............+++........#....#",
		"#..............++.........#....#",
		"#..............+..........#....#",
		"#.........................#....#",
		"#.........................#....#",
		"#.........................#....#",
		"#.........................#....#",
		"#................##########....#",
		"#..............................#",
		"#################################");

		
	
		
		Tracker gameMap = new Tracker(groundFactory, map );
		world.addGameMap(gameMap);
		GameMap gameMap2 = new GameMap(groundFactory, map2 );
		world.addGameMap(gameMap2);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(40, 17));
		
		
		//Add MoveActorAction to vehicle
		Vehicle vehicle = new Vehicle("Car",'C');
		vehicle.addAction(new MoveActorAction(gameMap2.at(25,4),"Moved to townMap"));
		gameMap.at(41,13).addItem(vehicle);
		
		//Add MoveActorAction to vehicle
		Vehicle car2 = new Vehicle("Car2",'C');
		car2.addAction(new MoveActorAction(gameMap.at(41,13),"Moved to originalMap"));
		gameMap2.at(25,4).addItem(car2);

		
	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		String[] humans2 = {};
		int x, y;
		for (String name : humans2) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
		}
		
		// place a simple weapon
//		gameMap.at(40, 17).addItem(new Plank());
		gameMap.at(40, 17).addItem(new ShotGun());
		gameMap2.at(28, 4).addItem(new ShotGun());
		
		// place Ammunition
		gameMap.at(38, 18).addItem(new ShotGunAmmo());
		gameMap2.at(28, 5).addItem(new ShotGunAmmo());
		gameMap.at(40, 17).addItem(new SniperAmmo());
		gameMap.at(39, 18).addItem(new SniperAmmo());
		gameMap2.at(29, 5).addItem(new SniperAmmo());
		
		// place Sniper
		gameMap.at(40, 17).addItem(new Sniper());
		gameMap2.at(29, 4).addItem(new Sniper());
		
		// FIXME: Add more zombies!
//		gameMap.at(4,4).addActor(new Zombie("Groan"));
//		gameMap.at(41,15).addActor(new Zombie("Boo"));
		gameMap.at(40,12).addActor(new Zombie("Uuuurgh"));
		
//		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
//		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
//		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));	
//		gameMap.at(42, 12).addActor(new Farmer("Farmer"));
		

		gameMap.at(4, 2).addActor(new Human("aaa"));
		gameMap.at(4, 3).addActor(new Human("bbb"));
		
//		gameMap2.at(15, 15).addActor(new Farmer("John"));
//		gameMap2.at(18, 21).addActor(new Farmer("George"));
//		gameMap2.at(4, 2).addActor(new Farmer("Lucas"));
		world.run();
	}
}
