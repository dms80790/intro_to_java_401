/*David Stropkey
**CS 401
**Assignment 4
*/

import java.util.Scanner;   //imports java Scanner class
import java.util.Random;    //imports java Random class

//program for Adventure Game V3
public class AdventureGameV3
{
	public static void main(String[] args)
	{
		int characterChoice;      //holds the number for user's character choice
		int numMinions;           //holds the number of goblins/skeletons
		int itemChoice;           //holds user action choice
		int potionChoice;         //holds user potion slot choice
		
		Scanner keyboard = new Scanner(System.in);     //creates a new Scanner object
		Random rand = new Random();     //creates a Random number object
		
		//welcomes the user
		System.out.println("\nAdventure Game - Start!\n");
        System.out.println("Here are the characters:");
        System.out.println("1. Rogue\n2. Paladin\n3. Jackie Chan\n");

        //prompt user for character choice
		System.out.print("Which character do you choose?: ");
        characterChoice = keyboard.nextInt();
		
		Player player = new Player(characterChoice);    //creates a new Player object
		
        System.out.printf("\nYou chose: %s\n\n", player.getName());    //display character choice

        //game script
		System.out.println("The Evil Wizard must be defeated! He is in The Castle.  To get to ");
        System.out.println("The Castle, you must travel through The Forest and then through The ");
        System.out.println("Graveyard.  Let's go!\n");
		
        numMinions = Enemy.getNumGoblins();    //generates a random number of goblins
		
		//displays number of goblins to be fought
		System.out.printf("Once you enter The Forest, you encounter %d goblins! Time for battle!\n\n",
                                  numMinions); 
		
        for (int i = 1; i <= numMinions; i++)
        {
			Enemy goblin = new Enemy(4);
			
			System.out.printf("***%s vs %s %d***\n", player.getName(), goblin.getName(), i);
			
			player.battleMinion(goblin);
			
			if(!player.isDefeated())
			{
				player.increaseCoins(goblin.dropCoins());
				System.out.printf("%s defeated %s "+ i + "!\n", player.getName(), goblin.getName());
				System.out.printf("%s gains %d gold coins!\n\n", player.getName(), goblin.dropCoins());
				
				System.out.print("Press Enter to continue...\n\n");
				keyboard.nextLine();

			}
			else if(player.isDefeated())
			{
				System.out.printf("%s has been defeated. Game Over.", player.getName());
				System.exit(0);
			}
			
		}
		
		System.out.printf("--%s wins the battle!\n\n", player.getName());

        System.out.printf("Your HP is: %d\n\n", player.getHitPoints());

		//ask user what next action will be
        do
		{ System.out.print("What would you like to do now?" +
						   "\n1. View inventory\n2. Drink a potion" +
						   "\n3. Visit the Item Shop\n4. Continue\n\n");
        System.out.print("Enter your choice here: ");
        itemChoice = keyboard.nextInt();

		switch(itemChoice)
		{
			case 1:
				player.displayInventory();
				break;
			case 2:
				Potion[] inventoryCopy = new Potion[5];
				inventoryCopy = player.getInventory();
				
				if(inventoryCopy[0] == null && inventoryCopy[1] == null && inventoryCopy[2] == null && inventoryCopy[3] == null && inventoryCopy[4] == null)
					System.out.println("You don't have any potions in inventory!\n");
				else
				{
					System.out.println("Which potion would you like to drink? ");
					potionChoice = -1 + keyboard.nextInt();
					if(inventoryCopy[potionChoice] == null)
						System.out.println("You don't have any potions in this slot!");
					else	
					inventoryCopy[potionChoice].drink(player);
					player.removeFromInventory(potionChoice);
				}			
				break;
			case 3:
				ItemShop.visitItemShop(player);
				break;
			case 4:
				break;		
			}
		}while(itemChoice != 4);
	
		numMinions = Enemy.getNumSkeletons();    //generates a random number of goblins
		
		//displays number of goblins to be fought
		System.out.printf("Once you enter The Graveyard, you encounter %d skeletons! Time for battle!\n\n",
                                  numMinions); 
		
        for (int i = 1; i <= numMinions; i++)
        {
			Enemy skeleton = new Enemy(5);  //creates new Enemy object for skeleton
			
			System.out.printf("***%s vs %s %d***\n", player.getName(), skeleton.getName(), i);
			
			player.battleMinion(skeleton);
			
			if(!player.isDefeated())
			{
				player.increaseCoins(skeleton.dropCoins());
				System.out.printf("%s defeated %s "+ i + "!\n", player.getName(), skeleton.getName());
				System.out.printf("%s gains %d gold coins!\n\n", player.getName(), skeleton.dropCoins());
				
				System.out.print("Press Enter to continue...\n\n");
				keyboard.nextLine();
			}
			else if(player.isDefeated())
			{
				System.out.printf("%s has been defeated. Game Over.", player.getName());
				System.exit(0);
			}
		}
		
		System.out.printf("--%s wins the battle!\n\n", player.getName());

        System.out.printf("Your HP is: %d\n\n", player.getHitPoints());

        do
		{ System.out.print("What would you like to do now?" +
						   "\n1. View inventory\n2. Drink a potion" +
						   "\n3. Visit the Item Shop\n4. Continue\n\n");
        System.out.print("Enter your choice here: ");
        itemChoice = keyboard.nextInt();

		switch(itemChoice)
		{
			case 1:
				player.displayInventory();
				break;
			case 2:
				Potion[] inventoryCopy = new Potion[5];
				inventoryCopy = player.getInventory();
				
				if(inventoryCopy[0] == null && inventoryCopy[1] == null && inventoryCopy[2] == null && inventoryCopy[3] == null && inventoryCopy[4] == null)
					System.out.println("You don't have any potions in inventory!\n");
				else
				{
					System.out.println("Which potion would you like to drink? ");
					potionChoice = -1 + keyboard.nextInt();
					if(inventoryCopy[potionChoice] == null)
						System.out.println("You don't have any potions in this slot!");
					else	
						inventoryCopy[potionChoice].drink(player);
					player.removeFromInventory(potionChoice);
				}			
				break;
			case 3:
				ItemShop.visitItemShop(player);
				break;
			case 4:
				break;		
			}
		}while(itemChoice != 4);
	
		System.out.println("You have now reached The Castle! Time to battle The Evil Wizard!\n");
	   
		System.out.printf("***%s vs The Evil Wizard***\n", player.getName());
		
		//creates new Enemy object wizard
		Enemy wizard = new Enemy(6);
		
		//call method to start battle with wizard
		player.battleWizard(wizard);
		
		//if player is defeated
		if(player.isDefeated())
		{
			System.out.println(player.getName() + " has been defeated.");
			System.out.print("---GAME OVER---");
		}

		//if wizard is defeated
		if(wizard.isDefeated())
		{
			System.out.println("The " + wizard.getName() + " has been defeated!");
			System.out.print("Congratulations! You win!");
		}
		
		//exits the game
		System.exit(0);
	}
}