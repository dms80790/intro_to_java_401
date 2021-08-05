/**David Stropkey
*/

import java.util.Scanner;		//imports the Scanner class
import java.util.Random;		//imports the Random class

public class AdventureGame
{
	public static void main(String[] args)      //main method
	{
		String characterChoice, pathChoice;  //holds strings for character and path selection
		int playerHealth;                    //holds int for player health points
		final String WELCOME_MESSAGE = "Adventure Game --- Start!\n";   // welcome message
		
		System.out.println(WELCOME_MESSAGE);   //displays welcome message
		
		characterChoice = characterSelection();   //call on method for character selection
		pathChoice = pathSelection();			  //call on method for path selection
		playerHealth = battle(characterChoice, pathChoice);	 //call on method for first round of battles
		wizard(characterChoice, playerHealth);   //call on method for battle with wizard
	}
	
	public static String characterSelection()     //method for character selection
	{
		int characterNumber = 0;      //holds value for user input of character number choice
		String characterName = "";     //holds character name as a string
		Scanner stdInScanner = new Scanner(System.in);   //creates scanner object
		
		//prompt user for character choice
		System.out.print("Here are the characters:\n1. Rogue\n2. Paladin\n3. Jackie Chan" +
						 "\n\nWhich character do you choose? ");
		characterNumber = stdInScanner.nextInt();   

		//determine character choice
		switch (characterNumber)
		{
			case 1:
				characterName = "Rogue";
				System.out.println("\nYou chose: " + characterName);
				break;
			case 2:
				characterName = "Paladin";
				System.out.println("\nYou chose: " + characterName);
				break;
			case 3:
				characterName = "Jackie Chan";
				System.out.println("\nYou chose: " + characterName);	
				break;
		}
		return characterName;   //return the character choice to main method
	}
	
	public static String pathSelection()     //method for path selection
	{
		int pathNumber = 0;      //holds path choice number
		String pathName = "";    //holds the string name of chosen path
		
		Scanner stdInScanner = new Scanner(System.in);    //creates Scanner object
		
		//prompt user for path choice
		System.out.print("\nThe Evil Wizard must be defeated! He is in the castle." +
						 " To get to The Castle, you must travel through either:\n" +
						 "1. The Forest\n2. The Graveyard\n\nWhich path will you take? ");
		pathNumber = stdInScanner.nextInt();
		
		//determine path choice using input number
		switch (pathNumber)
		{
			case 1:
				System.out.println("\nYou chose: The Forest\n");
				pathName = "The Forest";
				break;
			case 2:
				System.out.println("\nYou chose: The Graveyard\n");
				pathName = "The Graveyard";
				break;
		}
		return pathName;      //returns pathName string to main method
	}
	
	public static int battle(String characterChoice, String pathChoice)    //method for first battle
	{
		int numEnemies = 0;					//holds number of enemies
		int playerHealth = 0, playerStrength = 0, playerWeaponStrength = 0, playerTotalAttack = 0;  //holds player attributes
		int enemyHealth = 0, enemyStrength = 0, enemyWeaponStrength = 0, enemyTotalAttack = 0;     //holds enemy attributes
		String enemyName = "";		//holds enemy name as string
		
		//generate player characteristics
		if (characterChoice.equals("Rogue"))
			{
				playerHealth = 55;
				playerStrength = 8;
			}
		else if (characterChoice.equals("Paladin"))
			{
				playerHealth = 35;
				playerStrength = 14;
			}
		else if (characterChoice.equals("Jackie Chan"))
			{
				playerHealth = 45;
				playerStrength = 10;
			}
		
		//generate enemy characteristics
		if (pathChoice.equals("The Graveyard"))
			{
				enemyName = "Skeleton";
				numEnemies = 5;
				enemyHealth = 25;
				enemyStrength = 3;
				
			}
		else if (pathChoice.equals("The Forest"))
			{
				enemyName = "Goblin";
				numEnemies = 3;
				enemyHealth = 25;
				enemyStrength = 4;
			}
		
		//display chosen path, enemy type, and number of enemies
		System.out.println("Once you enter " + pathChoice +", you encounter " + numEnemies +
						   " " + enemyName + "s. Time for battle!\n");
		
		//loop for multiple enemies
		for (int i = 1; i <= numEnemies; i++)
		{
			enemyHealth = 25;     //set initial enemy HP to 25 
			
			System.out.println("***" + characterChoice + " vs " + enemyName + " " + i + " ***\n");
			//loop for individual battles
			while (playerHealth > 0 && enemyHealth > 0)   //while loop for battle while both HPs are above 0
			{
				if (playerHealth > 0)
				{
					Random rand = new Random();    //generates a random object
					
					//generates random numbers for weapon strength depending on character
					switch(characterChoice)
					{
						case "Rogue":
							int shortSword = rand.nextInt(4) + 1;
							playerWeaponStrength = shortSword;
							break;
						case "Paladin":
							int longSword = rand.nextInt(5) + 3;
							playerWeaponStrength = longSword;
							break;
						case "Jackie Chan":
							int jumpKick = rand.nextInt(5) + 2;
							playerWeaponStrength = jumpKick;
							break;
					}
					playerTotalAttack = playerStrength + playerWeaponStrength;  //holds total attack value
					
					//print results of player attack
					System.out.print(characterChoice + " attacks with ATK = " + playerStrength + " + " +
							 playerWeaponStrength + " = " + playerTotalAttack + "\n");
					System.out.print(enemyName + " HP is now " + enemyHealth + " - " +
							   playerTotalAttack + " = ");		
					enemyHealth -= playerTotalAttack;
					System.out.println(enemyHealth + "\n");
				}
				else if (playerHealth <= 0)   //print if player is dead
					System.out.print(characterChoice + "has been defeated. Game over.\n");
				
				if (enemyHealth > 0)
				{
					Random rand = new Random();  //create random object
					
					//randomly generate enemy weapon strength depending on enemy
					switch(enemyName)
					{
						case "Goblin":
							int axe = rand.nextInt(5) + 2;
							enemyWeaponStrength = axe;
							break;
						case "Skeleton":
							int shortSword = rand.nextInt(4) + 1;
							enemyWeaponStrength = shortSword;
							break;
					}
					
					enemyTotalAttack = enemyStrength + enemyWeaponStrength;   //enemy total attack
					
					//print results of enemy attack
					System.out.print(enemyName + " attacks with ATK = " + enemyStrength + " + " +
							 enemyWeaponStrength + " = " + enemyTotalAttack + "\n");
					System.out.print(characterChoice + " HP is now " + playerHealth + " - " +
							   enemyTotalAttack + " = ");		
					playerHealth -= enemyTotalAttack;
					System.out.println(playerHealth + "\n");
				}
				else if(enemyHealth <= 0)    //print if enemy is dead
					System.out.println(characterChoice + " defeated " + enemyName + " " + i + "!\n");	
			}
		}
		
		System.out.println("Your HP is " + playerHealth + "\n");   //print player HP after series of battles
		
		return playerHealth;   //returns HP to main method
	}
	
	public static void wizard(String characterChoice, int playerHealth)    //method for wizard battle
	{
		int rewardNumber, playerStrength = 0, actionChoice;
		Scanner stdInScanner = new Scanner(System.in);     //creates scanner object
		int playerWeaponStrength = 0, playerTotalAttack = 0;     //holds player weapon strength and total attack
		int wizardHealth = 40,wizardStrength = 8, wizardTotalAttack = 0;   //holds wizard attributes
		int guess = 0;    //holds value for player guess during spell casting

		
		Random rand = new Random();     //generates a random object
		int castRand = rand.nextInt(5) + 1;    //generates a random number for spell cast from 1-5
		
		//sets player strength depending on character
		if (characterChoice.equals("Rogue"))
			{
				playerStrength = 8;
			}
		else if (characterChoice.equals("Paladin"))
			{
				playerStrength = 14;
			}
		else if (characterChoice.equals("Jackie Chan"))
			{
				playerStrength = 10;
			}
		//prompt player for reward before battle with wizard
		System.out.print("Please choose a reward:\n1. Healing Potion\n2. Ring of Strength\n" +
						   "\nWhich item do you choose? ");
		rewardNumber = stdInScanner.nextInt();
		
		//results of reward depending on player choice
		if (rewardNumber == 1)
		{
			System.out.print("\nYou chose: Healing Potion\n\n" +
						     "Your health has increased to " + playerHealth + " + 10 = ");
			playerHealth += 10;				 
			System.out.print(playerHealth);
		}
		else if (rewardNumber == 2)
		{
			System.out.print("\nYou chose: Ring of Strength\n\n"+
						     "Your strength has increased to " + playerStrength + " + 5 = ");
			playerStrength += 5;				 
			System.out.print(playerStrength);
		}	
		
		//welcome message to Castle
		System.out.print("\n\nYou have now reached The Castle! Time to battle The Evil Wizard!" +
						 "\n\n***" + characterChoice + " vs The Evil Wizard***");
		while (playerHealth > 0 && wizardHealth > 0)   //creates loop for battle while both HPs are above 0
		{
			if (playerHealth > 0)    //if loop for player attack
			{
				//generates random number for weapon strength depending on character
				switch(characterChoice)
					{
						case "Paladin":
							int shortSword = rand.nextInt(4) + 1;
							playerWeaponStrength = shortSword;
							break;
						case "Rogue":
							int longSword = rand.nextInt(5) + 3;
							playerWeaponStrength = longSword;
							break;
						case "Jackie Chan":
							int jumpKick = rand.nextInt(5) + 2;
							playerWeaponStrength = jumpKick;
							break;
					}
				//prompt user for action choice
				System.out.print("\nChoose your action:\n1. Attack\n2. Attempt Spell Cast\n\n" +
								 "What would you like to do? ");
				actionChoice = stdInScanner.nextInt();	
				
				//attack action sequence
				if(actionChoice == 1)
				{
					playerTotalAttack = playerWeaponStrength + playerStrength;
					System.out.print("\n" +characterChoice + " attacks with ATK = " + playerStrength + " + " +
							 playerWeaponStrength + " = " + playerTotalAttack + "\n");
					System.out.print("Wizard HP is now " + wizardHealth + " - " +
							   playerTotalAttack + " = ");		
					wizardHealth -= playerTotalAttack;
					System.out.println(wizardHealth + "\n");
				}	
				//spell cast action sequence
				else if(actionChoice == 2)
				{
						//prompt user to guess a number 1-5
						System.out.print("\nEnter your guess (1-5): ");
						guess = stdInScanner.nextInt();
						
						if(guess == castRand)
							{
								wizardHealth = 0;  //wizard is dead if guess = random number
							}
						else if (guess != castRand)   //spell not successful if guess != random number
						{
								System.out.print(characterChoice +"'s spell was not successfully cast.\n\n");
						}
				}
			}
						
			//if loop for wizard attack
			if(wizardHealth > 0)
			{
				int fireBlast = rand.nextInt(7) + 4;   //randomly generates wizard weapon strength
				int wizardWeaponStrength = fireBlast;
							
				wizardTotalAttack = wizardStrength + wizardWeaponStrength;
				
				//print result of wizard attack
				System.out.print("The Wizard attacks with ATK = " + wizardStrength + " + " +
						     	 wizardWeaponStrength + " = " + wizardTotalAttack + "\n");
				System.out.print(characterChoice + "'s HP is now " + playerHealth + " - " +
							     wizardTotalAttack + " = ");		
				playerHealth -= wizardTotalAttack;
				System.out.print(playerHealth + "\n");
			}
	
		}
		//output if player or wizard HP is less than 0.
		if(playerHealth <= 0)
				System.out.print("\n" + characterChoice + " has been defeated. Better luck next time.");
		if(wizardHealth <= 0)
				System.out.print("The Wizard's HP is now 0!\n\n---" + characterChoice +
											     " wins the battle!---\n\nYou win, congratulations!");
	}
}