/*David Stropkey
**CS 401
**Assignment 4
*/
import java.util.Scanner;

//ItemShop class simulates a visit to the Item Shop
public class ItemShop
{
	//item price constants
	private static int LONG_SWORD_COST = 120;
    private static int SHORT_SWORD_COST = 90;
    private static int MACE_COST = 80;
    private static int MINOR_HEALING_POTION_COST = 5;
    private static int HEALING_POTION_COST = 10;
	private static int MINOR_STRENGTH_POTION_COST = 20;
	private static int STRENGTH_POTION_COST = 40;
	
	//method for a visit to the Item Shop
	public static void visitItemShop(Player player)
	{
		int reshop;     //determines if user wants to continue shopping
		int itemChoice, quantity;
		int discount = 0, totalCost = 0, finalCost = 0;
		String userName, itemName = "";
		int openSlots;

		Scanner keyboard = new Scanner(System.in);   //creates a new Scanner object for input

		System.out.println("\nWelcome to The Item Shop!\n");

		do{
		  System.out.println("\nYou currently have " + player.getCoins() + " gold.\n");

		  System.out.println("Here's what we have for sale (all prices are in units of gold):\n");

		  System.out.printf("1. %-24s%-3d\n", "Long Sword", 120);
		  System.out.printf("2. %-24s%-3d\n", "Short Sword", 90);
		  System.out.printf("3. %-24s%-3d\n", "Mace", 80);
		  System.out.printf("4. %-24s%-3d\n", "Minor Healing Potion", 5);
		  System.out.printf("5. %-24s%-3d\n", "Healing Potion", 10);
		  System.out.printf("6. %-24s%-3d\n", "Minor Strength Potion", 20);
		  System.out.printf("7. %-24s%-3d\n", "Strength Potion", 40);

		  System.out.print("\nPlease enter the item number: ");
		  itemChoice = keyboard.nextInt();

		  System.out.print("Please enter the quantity: ");
		  quantity = keyboard.nextInt();

		  //determines cost based on item and quantity
		  switch(itemChoice)
		  {
			case 1:
			  itemName = "Long Sword";
			  totalCost = LONG_SWORD_COST * quantity;
			  break;

			case 2:
			  itemName = "Short Sword";
			  totalCost = SHORT_SWORD_COST * quantity;
			  break;

			case 3:
			  itemName = "Mace";
			  totalCost = MACE_COST * quantity;
			  break;

			case 4:
			  itemName = "Minor Healing Potion";
			  totalCost = MINOR_HEALING_POTION_COST * quantity;
			  break;

			case 5:
			  itemName = "Healing Potion";
			  totalCost = HEALING_POTION_COST * quantity;
			  break;
			case 6:
			  itemName = "Minor Strength Potion";
			  totalCost = MINOR_STRENGTH_POTION_COST * quantity;
			  break;
			case 7:
			  itemName = "Strength Potion";
			  totalCost = STRENGTH_POTION_COST * quantity;
			  break;
		  }

		  //determines if a discount is applicable (more than 2 items purchased)
		  if (quantity > 2)
			discount = (int) (totalCost * 0.1);

		  finalCost = totalCost - discount;

		  System.out.printf("\n%-10s: %5d gold", "Total cost", totalCost);
		  System.out.printf("\n%-10s: %5d gold", "Discount", discount);
		  System.out.printf("\n%-10s: %5d gold", "Final cost", finalCost);

		  openSlots = player.getNumOpenSlots();
		  
		  //case: player has insufficient inventory space
		  
		  if(openSlots < quantity)
			{
			  System.out.println("\n\nYou only have " + openSlots + " open slots. Consume a potion or buy fewer items.");
			}
		  
		  //case: player has insufficient gold for total price
		  else if (player.getCoins() < finalCost){
			System.out.println("\n\nYou have insufficient funds! Please come back with more gold!");
		  }

		  //case: player has sufficient funds. Transaction occurs
		  else
		  {
			player.decreaseCoins(finalCost);
			System.out.println("\nThe transaction is successful!\nYour remaining funds: " + player.getCoins() + " gold\n");
			System.out.println("Thank you! Your transaction is complete!\n");
			System.out.println("You purchased: " + quantity + " " + itemName);
			switch(itemChoice){
			  case 1:
				System.out.println("Your weapon damage is now: 3 - 7");
				Weapon longSword = new Weapon("Long Sword", Weapon.LONG_SWORD_MIN, Weapon.LONG_SWORD_MAX);
				player.setWeapon(longSword);
				break;
			  case 2:
				System.out.println("Your weapon damage is now: 1 - 4");
				Weapon shortSword = new Weapon("Short Sword", Weapon.SHORT_SWORD_MIN, Weapon.SHORT_SWORD_MAX);
				player.setWeapon(shortSword);
				break;
			  case 3:
				System.out.println("Your weapon damage is now: 2 - 6");
				Weapon mace =  new Weapon("Mace", Weapon.MACE_MIN, Weapon.MACE_MAX);
				player.setWeapon(mace);
				break;
			  case 4:
				System.out.println("Your potion(s) has/have been added to your inventory");
				Potion minorHealing = new Potion(Potion.Type.MINOR_HEALING);
				for(int i = 0; i < quantity; i++)
				{
					player.addToInventory(minorHealing);
				}
				break;
			  case 5:
				System.out.println("Your potion(s) has/have been added to your inventory");
				Potion healing = new Potion(Potion.Type.HEALING);
				for(int i = 0; i < quantity; i++)
				{
					player.addToInventory(healing);
				}
				break;
			  case 6:
				System.out.println("Your potion(s) has/have been added to your inventory");
				Potion minorStrength = new Potion(Potion.Type.MINOR_STRENGTH);
				for(int i = 0; i < quantity; i++)
				{
					player.addToInventory(minorStrength);
				}
				break;
			  case 7:
				System.out.println("Your potion(s) has/have been added to your inventory");
				Potion strength = new Potion(Potion.Type.STRENGTH);
				for(int i = 0; i < quantity; i++)
				{
				player.addToInventory(strength);
				}
				break;
			}
		  }

		  System.out.println("\nWould you like to make another purchase?");
		  System.out.print("Enter 1 for \"yes\" or 0 for \"no\": ");
		  reshop = keyboard.nextInt();

		} while(reshop == 1);//end do while

		System.out.println("\nGoodbye! Please stop by again!\n");

	  } //end method
}