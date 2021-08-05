/*David Stropkey
**CS 401
**Assignment 4
*/

//Potion is a class that sets up potion name and its effects upon use
public class Potion
{
	public static enum Type {MINOR_HEALING, HEALING, MINOR_STRENGTH, STRENGTH, EMPTY}

	private String name;
	private Type type;

	//constructor for Potion class that sets name field according to type
	public Potion(Type _type)
	{
		this.type = _type;
		
		switch(type)
		{
			case MINOR_HEALING:
				this.name = "Minor Healing Potion";
				break;
			case HEALING:
				this.name = "Healing Potion";
				break;
			case MINOR_STRENGTH:
				this.name = "Minor Strength Potion";
				break;
			case STRENGTH:
				this.name = "Strength Potion";
				break;
			case EMPTY:
				this.name = "";
				
		}
	}
	
	//accessor for weapon name field
	public String getName()
	{
		return this.name;
	}

	//method for different effects once player consumes a given potion
	public void drink(Player player)
	{		
			switch(this.type)
			{
				case MINOR_HEALING:
				{
					System.out.print("\nYou drank a Minor Healing potion!" +
									   " Your HP is now " + player.getHitPoints() + " + " + "5");
					player.increaseHitPoints(5);
					System.out.println(" = " + player.getHitPoints() + "\n");
					break;
				}
				case HEALING:
				{
					System.out.print("\nYou drank a Healing potion !" +
									   " Your HP is now " + player.getHitPoints() + " + " + "10");
					player.increaseHitPoints(10);	
					System.out.println(" = " + player.getHitPoints() + "\n");
					break;
				}
				case MINOR_STRENGTH:
				{
					System.out.print("\nYou drank a Minor Strength potion!" +
									   " Your strength is now " + player.getStrength() + " + " + "2");
					player.increaseStrength(2);
					System.out.println(" = " + player.getStrength() + "\n");
					break;
				}
				case STRENGTH:
				{
					System.out.print("\nYou drank a Strength potion!" +
				                   " Your strength is now " + player.getStrength() + " + " + "5"
								   );
					player.increaseStrength(5);
					System.out.println(" = " + player.getStrength() + "\n");
					break;
				}
				default:
				{
					System.out.print("\nYou don't have any potions in this slot!");
					break;
				}
			}
	}
}