/*David Stropkey
**CS 401
**Assignment 4
*/

import java.util.Random;    //imports Random class

//Enemy class is a subclass of Character class
public class Enemy extends Character
{
	//constructor for Enemy class that calls on parent constructor
	public Enemy(int enemyType)
	{
		super(enemyType);
	}
	
	//method to determine random number of coins dropped upon minion death
	public int dropCoins()
	{
		int coins;
		
		Random rand = new Random();    //creates a random object
		
		coins = rand.nextInt(21) + 30;
		
		return coins;
	}
	
	//generates a random number of goblins for battle
	public static int getNumGoblins()
	{
		int numGoblins;
		
		Random rand = new Random();
		
		numGoblins = rand.nextInt(4) + 2;
		
		return numGoblins;
	}

	//generates a random number of skeletons to battle
	public static int getNumSkeletons()
	{
		int numSkeletons;
		
		Random rand = new Random();
		
		numSkeletons = rand.nextInt(5) + 3;
		
		return numSkeletons;
	}
}