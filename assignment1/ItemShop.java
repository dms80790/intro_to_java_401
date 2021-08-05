/*David Stropkey
CS 0401
Assignment 1*/

import java.util.Scanner;

public class ItemShop
{
	public static void main(String[] args)
	{
		//initialize constants for menu items
		final int LONG_SWORD = 120;
		final int SHORT_SWORD = 90;
		final int MACE = 80;
		final int MAGIC_RING = 150;
		final int HEALING_POTION = 10;
		
		String name;   //holds the user's name
		int coins, quantity, itemNumber, totalCost = 0, discount, finalCost;
		
		//Get user name and number of coins
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter your name: ");
		name = keyboard.nextLine();
		System.out.print("Enter your number of gold coins: ");
		coins = keyboard.nextInt();
		
		//Welcome the user and display menu options
		System.out.println("Welcome to The Item Shop, " + name + "\n");
		System.out.println("Here's what we have for sale (all prices are in units of gold):" +
						   "\n\n1. Long sword\t\t" + LONG_SWORD +
						   "\n2. Short sword\t\t" + SHORT_SWORD +
						   "\n3. Mace\t\t\t" + MACE +
						   "\n4. Magic ring\t\t" + MAGIC_RING +
						   "\n5. Healing potion\t" +HEALING_POTION + "\n");
		
		//Get user's item choice and quantity
		System.out.print("Please enter the item number: ");
		itemNumber = keyboard.nextInt();
		System.out.print("Please enter the quantity: ");
		quantity = keyboard.nextInt();
		
		//determine totalCost depending on item choice and quantity
		switch (itemNumber)
		{
			case 1:
				totalCost = quantity * LONG_SWORD;
				break;
			case 2:
				totalCost = quantity * SHORT_SWORD;
				break;
			case 3:
				totalCost = quantity * MACE;
				break;	
			case 4:
				totalCost = quantity * MAGIC_RING;
				break;	
			case 5:
				totalCost = quantity * HEALING_POTION;
				break;	
		}
		
		//determine if discount applies and execute
		if (quantity >= 3)
		{
			discount = totalCost / 10;
		}
		else
		{
			discount = 0;
		}
		
		//Determine the final cost
		finalCost = totalCost - discount;
		
		System.out.println("\nTotal cost: $" + totalCost + " gold" +
						  "\nDiscount: $" + discount + " gold" +
						  "\nFinal cost: $" + finalCost + " gold\n");
		
		//Determine if the user has sufficient funds for the purchase
		if (finalCost <= coins)
		{
			System.out.print("Thank you, " + name + "! Your transaction is complete! Please stop by any time.");
		}
		else
		{
			System.out.print(name + ", you have insufficient funds! Please come back with more gold!");
		}
	}
}