/*David Stropkey
**CS 401
**Assignment 4
*/

import java.util.Scanner;   //import Java Scanner class
import java.util.Random;

//Player class is a subclass of abstract Character class
public class Player extends Character
{
	private int coins;                                    //field that holds player coins
	private Potion[] inventory = new Potion[5];           //field that holds player Potion inventory
	
	//constructor for Player class. Sets up fields and fields of Character parent class
	public Player(int playerType)
	{
		super(playerType);     //call to parent class constructor
		this.coins = 0;
		this.inventory = new Potion[5];
	}
	
	//method to increase Player strength
	public void increaseStrength(int _strengthIncrease)
	{
		this.setStrength(_strengthIncrease);
	}

	//accessor for Player coins field
	public int getCoins()
	{
		return this.coins;
	}
	
	public Potion[] getInventory()
	{
		return this.inventory;
	}
	//mutator to increase Player coins field
	public void increaseCoins(int _coins)
	{
		this.coins += _coins;
	}

	//mutator to decrease Player coins field
	public void decreaseCoins(int _coins)
	{
		this.coins -= _coins;
	}

	//mutator to add potion to potion inventory array
	public void addToInventory(Potion _potion)
	{
		for(int i = 0; i < 5; i++)
		{
			if(inventory[i] == null)   //starts at inventory slot 1 and goes until finds an empty slot
			{	
				inventory[i] = _potion;     //add potion to index i;
				i = 5;     //causes drop out of for loop
			}
		}
	}
	
	//method to remove a potion from the inventory array
	public void removeFromInventory(int _index)
	{
		inventory[_index] = new Potion(Potion.Type.EMPTY);
	}
	
	//method to display character's inventory
	public void displayInventory()
	{
		String name = "";
		
		System.out.println("\nYour inventory is:");
		for(int i = 0; i < inventory.length; i++)
		{
			if(inventory[i] == null)
				name = "";
			else if(inventory[i] != null)
				name = inventory[i].getName();
			
		System.out.print("[" + (i+1) + "] " + name + "\n");
		}
		System.out.println("\n");
	}
	
	//method to determine number of open inventory slots
	public int getNumOpenSlots()
	{
		int count = 5;
		
		for(int i = 0; i < inventory.length; i++)
		{
			if(inventory[i] != null)
			    count --;
			else
				count-= 0;
		}
		return count;
	}
	
	//method that starts a battle with skeletons or goblins
	public void battleMinion(Enemy _enemy)
	{
		Scanner keyboard = new Scanner(System.in);  //creates a new Scanner object
		
		while(!(this.isDefeated()) && !(_enemy.isDefeated()))
        {
			this.attack(_enemy);   //call to method for player to attack enemy
			
			if(_enemy.isDefeated())
				break;
				
			_enemy.attack(this);
				
			if(this.isDefeated())
				break;
		}
	}
	
	public void battleWizard(Enemy _enemy)
	{
		int playerActionChoice;
		int randomNumAnswer;
		int randomNumGuess;

		Scanner keyboard = new Scanner(System.in);
		Random randomNums = new Random();

		randomNumAnswer = randomNums.nextInt(5) + 1;
	
		do{
			System.out.println("Choose your action:\n1. Attack\n2. Attempt Spell Cast\n");
			System.out.print("What would you like to do: ");
			playerActionChoice = keyboard.nextInt();

			switch(playerActionChoice)
			{
				case 1:
					this.attack(_enemy);   //call to method for player to attack wizard
				
					if(_enemy.isDefeated())
					break;
						
					_enemy.attack(this);
					
					break;
				case 2:
						System.out.print("Enter your guess: ");
						randomNumGuess = keyboard.nextInt();
						
						if (randomNumGuess == randomNumAnswer)
						{
							System.out.println("\nCorrect!\n");
							System.out.printf("The %s's spell is cast successfully! The Wizard's HP is now 0!\n\n", this.getName());
							_enemy.decreaseHitPoints(_enemy.getHitPoints());
						}
						else
							System.out.println("\nIncorrect! The spell cast fails!\n");
			}
		}while (!(this.isDefeated()) && !(_enemy.isDefeated()));
	}
}