/*David Stropkey
**CS 401
**Assignment 3
*/

import java.util.Scanner;     //imports the Scanner class
import java.util.Random;	  //imports the Random class
import java.io.*;

public class AdventureGameV22
{
	public static void main(String[] args) throws IOException//main method
        {
                //constant values for weapon power ranges
				final int SHORT_SWORD_MIN = 1;
                final int SHORT_SWORD_MAX = 4; 
                final int LONG_SWORD_MIN = 3;
                final int LONG_SWORD_MAX = 7;
                final int JUMP_KICK_MIN = 2;
                final int JUMP_KICK_MAX = 6;
                final int AXE_MIN = 2;
                final int AXE_MAX = 6;
                final int FIRE_BLAST_MIN = 4;
                final int FIRE_BLAST_MAX = 10;

                //constant values for character HP and strength
				final int ROGUE_INIT_HP = 55;
                final int ROGUE_INIT_STRENGTH = 8;
                final int PALADIN_INIT_HP = 35;
                final int PALADIN_INIT_STRENGTH = 14;
                final int CHAN_INIT_HP = 45;
                final int CHAN_INIT_STRENGTH = 10;

                //constant values for enemy HP and strength
				final int MINION_INIT_HP = 25;
                final int GOBLIN_INIT_STRENGTH = 4;
                final int SKELETON_INIT_STRENGTH = 3;
                final int WIZARD_INIT_HP = 40;
                final int WIZARD_INIT_STRENGTH = 8;
				
				//new array objects for player and enemy attributes
				int[] playerAttributes = new int[5];
				int[] enemyAttributes = new int[5];

                //string reference variable for character name
				String playerName = "";

                //string reference variable for enemy name
				String enemyName = "";
				
				//int variable for shopping multiple times at item shop
				int repeat = 0;
				
				int finalResultNum = 0;
				String finalResult = "";

                //variables for choices made by player
				int characterChoice = 0, pathChoice = 0, itemChoice = 0, numEnemies = 0;
                String pathName = "";

                //variables for player and enemy damage and attack
				int playerDamage, playerATK;
                int enemyDamage, enemyATK;

                //variables for random number generations
				int playerActionChoice, randomNumAnswer, randomNumGuess;

                Scanner keyboard = new Scanner(System.in);   //creates a new Scanner object
				Random randNums = new Random();			//creates a new Random object
				PrintWriter outputWriter = new PrintWriter(“AdventureGameResults.txt”);
				
                System.out.println("\nAdventure Game - Start!\n");

                characterChoice = getCharacter();	//call to get method for character selection

				//assignment of character attributes depending on character choice
                switch(characterChoice)
                {
                        case 1:
                                playerName = "Rogue";
                                playerAttributes[0] = ROGUE_INIT_HP;
                                playerAttributes[1] = ROGUE_INIT_STRENGTH;
                                playerAttributes[2] = SHORT_SWORD_MIN;
                                playerAttributes[3] = SHORT_SWORD_MAX;
                                break;
                        case 2:
                                playerName = "Paladin";
                                playerAttributes[0] = PALADIN_INIT_HP; 
                                playerAttributes[1] = PALADIN_INIT_STRENGTH; 
                                playerAttributes[2] = LONG_SWORD_MIN; 
                                playerAttributes[3] = LONG_SWORD_MAX;
                                break;
                        case 3:
                                playerName = "Jackie Chan";
                                playerAttributes[0] = CHAN_INIT_HP;
                                playerAttributes[1] = CHAN_INIT_STRENGTH;
                                playerAttributes[2] = JUMP_KICK_MIN;
                                playerAttributes[3] = JUMP_KICK_MAX;
                                break;
                }

                System.out.printf("\nYou chose: %s\n\n", playerName);
				
				pathChoice = getPath();		//call to the getPath method to determine player path
				
                //assignment of enemy attributes and number of enemies depending on path chosen
				switch(pathChoice)
                {
                        case 1:
                                pathName = "The Forest";
                                enemyName = "Goblin";
                                numEnemies = randNums.nextInt(4) + 2;   //generates a random number of goblins from 2-6
							
                                enemyAttributes[0] = MINION_INIT_HP;
                                enemyAttributes[1] = GOBLIN_INIT_STRENGTH;
                                enemyAttributes[2] = AXE_MIN;
                                enemyAttributes[3] = AXE_MAX;
								enemyAttributes[4] = randNums.nextInt(21)+30;
                                break;
                        case 2:
                                pathName = "The Graveyard";
                                enemyName = "Skeleton";
								numEnemies = randNums.nextInt(5) + 3;   //generates a random number of skeletons from 3-8

                                enemyAttributes[0] = MINION_INIT_HP;
                                enemyAttributes[1] = SKELETON_INIT_STRENGTH;
                                enemyAttributes[2] = SHORT_SWORD_MIN;
                                enemyAttributes[3] = SHORT_SWORD_MAX;
								enemyAttributes[4] = randNums.nextInt(21)+30;
                                break;
                }

                System.out.printf("\nYou chose: %s\n\n", pathName);
                System.out.printf("Once you enter %s, you encounter %d %ss! Time for battle!\n\n", pathName,
                                  numEnemies, enemyName); 
				if(playerAttributes[0] >= 1)				  
				{
				playerAttributes = fightMinion(numEnemies, playerName, enemyName, playerAttributes, enemyAttributes);

                System.out.printf("Your HP is: %d\n\n", playerAttributes[0]);

				System.out.println("/n/nWelcome to The Item Shop!");
                do
				{
				visitItemShop(playerAttributes, playerName);
				System.out.print("\nWould you like to go make another purchase?\nEnter a \"1\" for yes or a \"0\" for no.\n\n");
				repeat = keyboard.nextInt();
				}while(repeat == 1);      //end of do-while loop
			
				System.out.print("Goodbye. Please Come again!\n\n");
                System.out.println("You have now reached The Castle! Time to battle The Evil Wizard!\n");

				//assignment of wizard attributes to the enemyAttributes array
				enemyName = "Wizard";
                enemyAttributes[0] = WIZARD_INIT_HP;
                enemyAttributes[1] = WIZARD_INIT_STRENGTH;
                enemyAttributes[2] = FIRE_BLAST_MIN; 
                enemyAttributes[3] = FIRE_BLAST_MAX; 
                
				finalResultNum = fightWizard(playerName, enemyName, enemyAttributes, playerAttributes);				//call to method to fight the wizard
				
				if(finalResultNum == 1)
					finalResult = "Won";
				else
					finalResult = "Lost";
				}
				outputWriter.printf("%-16s%-12s%-8s%-8s%-12s", "Character", "Weapon", "HP", "Path", "Won or lost?");
				outputWriter.printf("%-16s%-12s%-8s%-8s%-12s", "---------", "------", "--", "----", "------------");
				outputWriter.printf("-16s%-12s%-8d%-8s%-12s", playerName, itemName, playerAttributes[0], finalResult);
				outputWriter.close();
        } // end of main 
		
		public static int[] visitItemShop(int[] playerAttributes, String playerName)   //method for visiting the item shop
        {
                final int LONG_SWORD_COST = 120;
                final int SHORT_SWORD_COST = 90;
                final int MACE_COST = 80;
                final int STRENGTH_RING_COST = 150;
                final int HEALING_POTION_COST = 10;

                int userGoldCoins, itemChoice, quantity;
                int discount = 0, totalCost = 0, finalCost = 0;
                String userName, itemName = "";

                Scanner keyboard = new Scanner(System.in);

                System.out.print("Welcome to The Item Shop!\n\n");
                System.out.printf("You currently have %d gold.\n\n", playerAttributes[4]);
		
                System.out.println("Here's what we have for sale (all prices are in units of gold):\n");

                System.out.printf("1. %-18s%-3d\n", "Long Sword", 120);
                System.out.printf("2. %-18s%-3d\n", "Short Sword", 90);
                System.out.printf("3. %-18s%-3d\n", "Mace", 80);
                System.out.printf("4. %-18s%-3d\n", "Ring of Strength", 150);
                System.out.printf("5. %-18s%-3d\n", "Healing Potion", 10);

                System.out.print("\nPlease enter the item number: ");
                itemChoice = keyboard.nextInt();

                System.out.print("Please enter the quantity: ");
                quantity = keyboard.nextInt();

                //alter player attributes depending on item choice and quantity
				switch(itemChoice)
                {
                        case 1:
                                itemName = "Long Sword";
                                totalCost = LONG_SWORD_COST * quantity;
								playerAttributes[2] = 3;
								playerAttributes[3] = 7;
                                break;
                        case 2:
                                itemName = "Short Sword";
                                totalCost = SHORT_SWORD_COST * quantity;
                                playerAttributes[2] = 1;
								playerAttributes[3] = 4;
								break;
                        case 3:
                                itemName = "Mace";
                                totalCost = MACE_COST * quantity;
								playerAttributes[2] = 3;
								playerAttributes[3] = 6;
                                break;
                        case 4:
                                itemName = "Ring of Strength";
                                totalCost = STRENGTH_RING_COST * quantity;
                                playerAttributes[1] += 5 * quantity; 
								break;
                        case 5:
                                itemName = "Healing Potion";
                                totalCost = HEALING_POTION_COST * quantity;
                                playerAttributes[0] += 10 * quantity;
								break;
                }

                //application of discount if quantity bought is greater than 2
				if (quantity > 2)
                        discount = (int) (totalCost * 0.1);

                //assignment of final cost after discount
				finalCost = totalCost - discount;

                System.out.printf("\n%-10s: %5d gold", "Total cost", totalCost);
                System.out.printf("\n%-10s: %5d gold", "Discount", discount);
                System.out.printf("\n%-10s: %5d gold", "Final cost", finalCost);

                //if-else statement to determine if player has sufficent funds
				if (playerAttributes[4] < finalCost)
                        System.out.println("\n\nYou have insufficient funds! Please come back with more gold!\n");
                else
				{
					System.out.printf("\n\nThank you, %s! Your transaction is complete!\n\n", playerName);
					playerAttributes[4] -= finalCost;
					System.out.println("Your remaining funds: " + playerAttributes[4] + "\n");
					
					if(itemChoice == 5)
						System.out.println("You purchased: " + itemName + ". Your HP is now : " + playerAttributes[0]);
					else if(itemChoice == 4)
						System.out.println("You purchased: " + itemName + ". Your strength is now : " + playerAttributes[1]);
					else
						System.out.println("You purchased: " + itemName + ". Your weapon damage is now : " +playerAttributes[2] + " - " + playerAttributes[3]);
				}	
				return playerAttributes;   //returns the playerAttributes array to the main method
	}
        public static int getCharacter()    //method for determining character choice
        {   
            int characterChoice;    //variable for character selection
            
			Scanner keyboard = new Scanner(System.in);    //creates a Scanner object
            
			System.out.println("Here are the characters:");
                System.out.println("1. Rogue\n2. Paladin\n3. Jackie Chan\n");

                System.out.print("Which character do you choose?: ");
                characterChoice = keyboard.nextInt();
                return characterChoice;    //reuturns character choice to the main method
        }
			
		public static int getPath()    //method for determining path chosen
		{
			Scanner keyboard = new Scanner(System.in);     //creates new Scanner object
			
			int pathChoice;     //variable for path choice
			
			System.out.print("The Evil Wizard must be defeated! He is in The Castle. To get to ");
            System.out.println("The Castle, you must travel through either:");
            System.out.println("1. The Forest\n2. The Graveyard\n");

            System.out.print("Which path will you take?: ");
            pathChoice = keyboard.nextInt();
			return pathChoice;   //returns path selection to the main method
		}
        
		public static int[] fightMinion(int numEnemies, String playerName, String enemyName, int[] playerAttributes, int[] enemyAttributes)
		{
				Scanner keyboard = new Scanner(System.in);
				Random randomNums = new Random();
				
			     for (int i = 1; i <= numEnemies; i++)
                {
					final int MINION_INIT_HP = 25;
					int playerDamage = 0, playerATK = 0;
					int enemyDamage = 0, enemyATK = 0, enemyCoins = 0;

					enemyAttributes[0] = MINION_INIT_HP;
					enemyAttributes[4] = randomNums.nextInt(21) + 30;
						
                    System.out.printf("***%s vs %s %d***\n", playerName, enemyName, i);

                    while(enemyAttributes[0] > 0 && playerAttributes[0] > 0)
                    {
                        playerDamage = randomNums.nextInt(playerAttributes[3] - playerAttributes[2] + 1) + playerAttributes[3];
                        playerATK = playerAttributes[1] + playerDamage;
                        enemyAttributes[0] -= playerATK;
                        System.out.printf("%s attacks with ATK = %d + %d = %d\n", playerName, playerAttributes[1], playerDamage, playerATK);
                        System.out.printf("%s HP is now %d - %d = %d\n\n", enemyName, enemyAttributes[0] + playerATK, playerATK, enemyAttributes[0]);

						if (enemyAttributes[0] <= 0)
						{           
							playerAttributes[4] += enemyAttributes[4];   
							break;
						}
								
                        enemyDamage = randomNums.nextInt(enemyAttributes[3] - enemyAttributes[2] + 1) + enemyAttributes[2];
                        enemyATK = enemyAttributes[1] + enemyDamage;
                        playerAttributes[0] -= enemyATK;
                        System.out.printf("%s attacks with ATK = %d + %d = %d\n", enemyName, enemyAttributes[1], enemyDamage, enemyATK);
                        System.out.printf("%s HP is now %d - %d = %d\n\n", playerName, playerAttributes[0] + enemyATK, enemyATK, playerAttributes[0]);
                        } // end of while loop

 
						if (playerAttributes[0] > 0)
                        {
							System.out.printf("%s defeated %s %d!\n", playerName, enemyName, i);
							System.out.println(playerName + " has has gained " + enemyAttributes[4] + " coins!\n");
							System.out.println("Press enter for the next battle");
							keyboard.nextLine();
                        }
						else
                        {
                            System.out.printf("--%s is defeated in battle!--\n\nGAME OVER\n", playerName);
                            break;
                        }
                } // end of for loop
				return playerAttributes;
		}
		
		public static int fightWizard(String playerName, String enemyName,int[] enemyAttributes,int[] playerAttributes)
		{
                Scanner keyboard = new Scanner(System.in);
				Random randomNums = new Random();
				
				int randomNumAnswer = randomNums.nextInt(6) + 1;
				int playerActionChoice = 0;
				int randomNumGuess = 0;
				int finalResult = 0;
				
				int playerDamage, playerATK, enemyDamage, enemyATK;
				
                System.out.printf("***%s vs The Evil Wizard***\n", playerName);
                while(playerAttributes[0] > 0 && enemyAttributes[0] > 0)
                {
                    System.out.println("Choose your action:\n1. Attack\n2. Attempt Spell Cast\n");
                    System.out.print("What would you like to do: ");
                    playerActionChoice = keyboard.nextInt();

                    switch(playerActionChoice)
                    {
                        case 1:
                            playerDamage = randomNums.nextInt(playerAttributes[3] - playerAttributes[2] + 1) + playerAttributes[2];
                            playerATK = playerAttributes[1] + playerDamage;
                            enemyAttributes[0] -= playerATK;
                            System.out.printf("\n%s attacks with ATK = %d + %d = %d\n", playerName, playerAttributes[0], playerDamage, playerATK);
                            System.out.printf("%s HP is now %d - %d = %d\n\n", enemyName, enemyAttributes[0] + playerATK, playerATK, enemyAttributes[0]);
                            break;
                        case 2:
                            System.out.print("Enter your guess: ");
                            randomNumGuess = keyboard.nextInt();
                            if (randomNumGuess == randomNumAnswer)
                            {
                                System.out.println("\nCorrect!\n");
                                System.out.printf("The %s's spell is cast successfully! The Wizard's HP is now 0!\n\n", playerName);
                                enemyAttributes[0] = 0;
                            }
                            else
                                System.out.println("\nIncorrect! The spell cast fails!\n");
                                break;
                    }

                    if (enemyAttributes[0] <= 0)
                        break;

                    enemyDamage = randomNums.nextInt(enemyAttributes[3] - enemyAttributes[2] + 1) + enemyAttributes[2];
                    enemyATK = enemyAttributes[1] + enemyDamage;
                    playerAttributes[0] -= enemyATK;
                    System.out.printf("%s attacks with ATK = %d + %d = %d\n", enemyName, enemyAttributes[1], enemyDamage, enemyATK);
                    System.out.printf("%s HP is now %d - %d = %d\n\n", playerName, playerAttributes[0] + enemyATK, enemyATK, playerAttributes[0]);

                } // end of while loop

                if (playerAttributes[0] > 0)
                {
                    System.out.printf("--%s wins the battle!--\n\n", playerName);
                    System.out.println("You win! Congratulations!");
					finalResult = 1;
                }
                else
                {
                    System.out.printf("--%s is defeated in battle!--\n\nGAME OVER\n", playerName);
					finalResult = 0;
                }
		}
} // end of class


    

