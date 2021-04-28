package game;

/**
 * Class representing Ammunition for ShotGun.
 * 
 * @author Ahsan Zafar
 */

public class ShotGunAmmo extends PortableItem{
	
	/**
	 * Constructor
	 */
	public ShotGunAmmo() {
		super("Shotgun Ammo", '<');
		this.addCapability(GunCapability.SHOTGUN);
	}
	
}
