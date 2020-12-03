package launch;

import game.Direction;
import game.JosephusCircle;

import java.util.Scanner;

/**
 * Allows a user to solve the Josephus circle problem on the 
 * Java console.
 * 
 * DO NOT ALTER THIS FILE!
 * 
 * @author Josh Archer
 * @version 1.0
 */
public class JosephusGame
{
	private static Scanner console = new Scanner(System.in);
	
	/**
	 * Entry point for the application.
	 * 
	 * @param args command-line arguments (unused)
	 */
	public static void main(String[] args)
	{
		//prompt the user
		System.out.println("Welcome to the Josephus game!");
		
		//how many players?
		int players = getInteger("Please enter a number of players: ");
		while (players <= 0)
		{
			players = getInteger("Please enter a (positive) number of players: ");
		}
		
		//how many steps? 
		int steps = getInteger("Please enter a number of steps: ");
		while (steps <= 0)
		{
			steps = getInteger("Please enter a (positive) number of steps: ");
		}
		
		//direction?
		String directionString = getString(
				"Please enter the direction the game will be played (left/right) ");
		while (!directionString.equals("left") && !directionString.equals("right"))
		{
			directionString = getString(
				"Please enter the direction the game will be played (left/right) ");
		}
		Direction direction = directionString.equals("left") ?
				              Direction.LEFT : Direction.RIGHT;
		
		solveJosephusCircle(players, steps, direction);
	}
	
	//solves the Josephus Circle problem, given a number of players, 
	//number of steps and an initial direction
	private static void solveJosephusCircle(int players, int steps, Direction direction)
	{
		//prepare to start the game
		JosephusCircle josephusCircle = new JosephusCircle(players);
		
		//get players
		for (int i = 1; i <= players; i++)
		{
			josephusCircle.addPlayer(getString("Enter player name: "));
		}
		
		//play the game
		while (!josephusCircle.isGameOver())
		{
			//print the state of the game
			System.out.println("Current circle: " + josephusCircle.getCircle());
			System.out.println("Moving " + steps + " to the " +
			                    direction.toString().toLowerCase() + ".");
			
			//move and remove the next player
			String removed = josephusCircle.moveCursor(direction, steps);
			josephusCircle.removeAtCursor(direction);
			System.out.println("Removed player: " + removed);
		}
		
		//print the winner
		System.out.println("Game over! The winner is: " +
		                   josephusCircle.moveCursor(direction, 1));
	}
	
	//prompts for a string from the Java console
	private static String getString(String message)
	{
		System.out.println(message);
		
		return console.nextLine();
	}
	
	//prompts for an int from the Java console
	private static int getInteger(String message)
	{
		System.out.println(message);
		
		int result = console.nextInt();
		console.nextLine(); //clear the internal buffer
		
		return result;
	}
}
