package game;

/**
 * Class representing Ammunition for ShotGun.
 * 
 * @author Ahsan Zafar
 */

public class SniperAmmo extends PortableItem{

	/**
	 * Constructor
	 */
	public SniperAmmo() {
		super("Sniper Ammo", '>');
		this.addCapability(GunCapability.SNIPER);
	}

}
