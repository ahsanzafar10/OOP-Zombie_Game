package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for performing shooting action that fires in the South direction.
 * 
 * @author Ahsan Zafar
 */

public class ShotGunFiringSouthAction extends ShotGunAction {

	/** 
	 * Constructor
	 */
	public ShotGunFiringSouthAction() {
		super("South");
	}
	
	/**
	 * Obtain all the locations that can be reached from a shotgun shooting in South Direction.
	 * 
	 * @return The set of coordinates that can be reached from a shotgun shooting.
	 */
	public Set<ArrayList<Integer>> getDirections(int x, int y) {
		Set<ArrayList<Integer>> coordinates = new HashSet<ArrayList<Integer>>();
		ArrayList<Integer> list;
		
		for (int i=1; i<4; i++) {
			
			
			int move = 0;
			
			for (int j=0; j<i; j++) {
				
				move += 1;
				
				list = new ArrayList<Integer>();
				list.add(x);
				list.add(y+i);
				coordinates.add(list);
				
				list = new ArrayList<Integer>();
				list.add(x-move);
				list.add(y+i);
				coordinates.add(list);
				
				list = new ArrayList<Integer>();
				list.add(x+move);
				list.add(y+i);
				coordinates.add(list);
			}
		}
		return coordinates;
	}

}
