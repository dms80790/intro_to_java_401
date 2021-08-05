/*David Stropkey
**CS 401
**Assignment 4
*/

import java.util.Random;     //imports Random class

//class weapon is used to set up weapon characteristics for player and enemy
public class Weapon
{
	//weapon max/min constants
	public static int SHORT_SWORD_MIN = 1;
    public static int SHORT_SWORD_MAX = 4;
    public static int LONG_SWORD_MIN = 3;
    public static int LONG_SWORD_MAX = 7;
    public static int JUMP_KICK_MIN = 2;
    public static int JUMP_KICK_MAX = 6;
    public static int AXE_MIN = 2;
    public static int AXE_MAX = 6;
	public static int MACE_MIN = 2;
    public static int MACE_MAX = 6;
    public static int FIRE_BLAST_MIN = 4;
    public static int FIRE_BLAST_MAX = 10;
	
	private String name;            //holds weapon name
	private int minDamage;          //holds weapon minimum damage
	private int maxDamage;          //holds weapon maximum damage
	
	//contructor for Weapon class sets fields based on parameters
	public Weapon(String _name, int _minDamage, int _maxDamage)
	{
		this.name = _name;
		this.minDamage = _minDamage;
		this.maxDamage = _maxDamage;
	}
		
	//accessor for weapon name field
	public String getName()
	{
		return this.name;
	}

	//accessor for weapon minimum damage
	public int getMinDamage()
	{
		return this.minDamage;
	}
		
	//accessor for weapon maximum damage
	public int getMaxDamage()
	{
		return this.maxDamage;
	}

	//determines weapon damage by generating a random number between min and max range
	public int getDamage()
	{
		int weaponDamage = 0;     //random amount of damage by given weapon
		int damageRange = this.maxDamage - this.minDamage;   //used in determining damage range
		
		Random rand = new Random();    //creates a new Random object
		
		//set weapon damage based on weapon name
		if (this.name.equals("Short Sword"))
		{
			weaponDamage = rand.nextInt(damageRange) + 1;
		}
		else if (this.name.equals("Long Sword"))
		{
			weaponDamage = rand.nextInt(damageRange) + 3;
		}
		else if (this.name.equals("Jump Kick"))
		{
			weaponDamage = rand.nextInt(damageRange) + 2;
		}
		else if (this.name.equals("Axe"))
		{
			weaponDamage = rand.nextInt(damageRange) + 2;
		}
		else if (this.name.equals("Fire Blast"))
		{
			weaponDamage = rand.nextInt(damageRange) + 4;
		}
		
		return weaponDamage;
	}
}