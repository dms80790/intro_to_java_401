/*David Stropkey
**CS 401
**Assignment 4
*/

public abstract class Character
{
	enum Type {ROGUE, PALADIN, JACKIE_CHAN, GOBLIN, SKELETON, WIZARD}

	//static constants for player HP and strength
	public static int ROGUE_INIT_HP = 55;
    public static int ROGUE_INIT_STRENGTH = 8;
    public static int PALADIN_INIT_HP = 35;
    public static int PALADIN_INIT_STRENGTH = 14;
    public static int CHAN_INIT_HP = 45;
    public static int CHAN_INIT_STRENGTH = 10;
	
	//static constants for minion HP and strength
	public static int MINION_INIT_HP = 25;
    public static int GOBLIN_INIT_STRENGTH = 4;
    public static int SKELETON_INIT_STRENGTH = 3;
	
	//static constants for wizard HP and strength
	public static int WIZARD_INIT_HP = 40;
    public static int WIZARD_INIT_STRENGTH = 8;
	
	private String name;     //field for character name String
	private int hitPoints;   //field for character HP
	private int strength;    //field for character strength
	private Weapon weapon;   //field for Weapon object
	
	//constructor for Character superclass
	public Character(int characterType)
	{
		//set fields according to characterType
		switch(characterType)
		{
			case 1:
				this.name = "Rogue";
				this.hitPoints = ROGUE_INIT_HP;
				this.strength = ROGUE_INIT_STRENGTH;
				this.weapon = new Weapon("Short Sword", Weapon.SHORT_SWORD_MIN, Weapon.SHORT_SWORD_MAX);
				break;
			case 2:
				this.name = "Paladin";
				this.hitPoints = PALADIN_INIT_HP;
				this.strength = PALADIN_INIT_STRENGTH;
				this.weapon = new Weapon("Long Sword", Weapon.LONG_SWORD_MIN, Weapon.LONG_SWORD_MAX);
				break;
			case 3:
				this.name = "Jackie Chan";
				this.hitPoints = CHAN_INIT_HP;
				this.strength = CHAN_INIT_STRENGTH;
				this.weapon = new Weapon("Jump Kick", Weapon.JUMP_KICK_MIN, Weapon.JUMP_KICK_MAX);
				break;
			case 4:
				this.name = "Goblin";
				this.hitPoints = MINION_INIT_HP;
				this.strength = GOBLIN_INIT_STRENGTH;
				this.weapon = new Weapon("Axe", Weapon.AXE_MIN, Weapon.AXE_MAX);
				break;
			case 5:
				this.name = "Skeleton";
				this.hitPoints = MINION_INIT_HP;
				this.strength = SKELETON_INIT_STRENGTH;
				this.weapon = new Weapon("Short Sword", Weapon.SHORT_SWORD_MIN, Weapon.SHORT_SWORD_MAX);
				break;
			case 6:
				this.name = "Evil Wizard";
				this.hitPoints = WIZARD_INIT_HP;
				this.strength = WIZARD_INIT_STRENGTH;
				this.weapon = new Weapon("Fire Blast", Weapon.FIRE_BLAST_MIN, Weapon.FIRE_BLAST_MAX);
				break;
		}		
	}
	
	//accessor for character name field
	public String getName()
	{
		return this.name;
	}

	//accessor for character HP field
	public int getHitPoints()
	{
		return this.hitPoints;
	}

	//accessor for character strength
	public int getStrength()
	{
		return this.strength;
	}

	//mutator for character strength
	public void setStrength(int _strength)
	{
		this.strength += _strength;
	}
	
	//mutator for character weapon
	public void setWeapon(Weapon _weapon)
	{
		this.weapon = _weapon;
	}
	
	//method that simulates a character attack onto opponent parameter
	public void attack(Character _opponent)
	{
		int playerDamage =  this.weapon.getDamage();    //determines weapon damage
        int playerATK = this.strength + playerDamage;   //calculates total character attack
            
		//shows result of character attack
		System.out.printf("%s attacks with ATK = %d + %d = %d\n", this.name, this.strength, playerDamage, playerATK);
        System.out.printf("%s HP is now %d - %d = ", _opponent.getName(), _opponent.getHitPoints(), playerATK);
		_opponent.decreaseHitPoints(playerATK);
		System.out.printf("%d\n\n", _opponent.getHitPoints());
	}
	
	//method to increase character's HP
	public void increaseHitPoints(int _pointIncrease)
	{
		this.hitPoints += _pointIncrease;
	}

	//method to decrease character's HP
	public void decreaseHitPoints(int _pointDecrease)
	{
		this.hitPoints -= _pointDecrease;
	}

	//method to declare character dead
	public boolean isDefeated()
	{
		boolean status;
		
		if (this.hitPoints <= 0)
			status = true;
		else
			status = false;
		
		return status;
	}
}