package game;
/*
	Name: Kim Vallido
	Date: 3/1/2020
	File name: JosephusCircle.java
 */

import java.util.LinkedList;


/**
 * A class to represent a Josephus Circle.
 *
 * @author Kim Vallido
 * @version 1.0
 */
public class JosephusCircle
{
	//fields go here...
	private JosephusNode head;
	private JosephusNode tail;
	private JosephusNode cursor;
	private int size;

	/**
	 * Creates a new JosephusCircle object with the maximum players given.
	 *
	 * @param playerCount the maximum number of players that can play in this circle
	 */
	public JosephusCircle(int playerCount)
	{
		this.size = playerCount;
	}

	/**
	 * Adds a new player to the game from left-to-right in the internal linked list.
	 *
	 * @param player the new player name
	 */
	public void addPlayer(String player)
	{
		//Adds a new player to the game from left-to-right in the
		//internal linked list.
		if (head == null)
		{
			head = tail = new JosephusNode(player);
			head.setPrevious(tail);
		}
		else if (head.getNext() == null) {
			head.setNext(new JosephusNode(player));
			tail = head.getNext();
			tail.setNext(head);
			tail.setPrevious(head);
			head.setPrevious(tail);
		}
		else
		{
			JosephusNode temp;
			temp = tail;
			tail.setNext(new JosephusNode(player));
			tail = tail.getNext();
			tail.setPrevious(temp);
			tail.setNext(head);
			head.setPrevious(tail);
		}
		cursor = head;
	}

	/**
	 * Moves the current player (the cursor) from the current position
	 * the given number of steps to the left or right
	 *
	 * @param direction which direction to move the cursor
	 * @param steps how many steps to move the cursor
	 * @return the player the cursor ends up on after moving
	 */
	public String moveCursor(Direction direction, int steps)
	{
		//Moves the current player (the cursor) from the current
		//position the given number of steps to the left or right
		if (direction.equals(Direction.RIGHT))
		{
			while (steps != 0)
			{
				cursor = cursor.getNext();
				steps--;
			}
		}

		else if (direction.equals(Direction.LEFT))
		{
			while (steps != 0)
			{
				cursor = cursor.getPrevious();
				steps--;
			}
		}
		return cursor.toString();
	}

	/**
	 * Removes the player at the cursor position from the circle.
	 *
	 * @param direction moves the cursor one position to the
	 *                  left or right depending on the direction
	 */
	public void removeAtCursor(Direction direction)
	//Removes the player at the cursor position from the circle.
	{
		if (direction.equals(Direction.RIGHT))
		{
			cursor = cursor.getNext();
			cursor.setNext(cursor.getNext());
			cursor.setPrevious(cursor.getPrevious().getPrevious());
			cursor.getPrevious().setNext(cursor);
			size--;
		}

		else if(direction.equals(Direction.LEFT))
		{
			cursor = cursor.getPrevious();
			cursor.setNext(cursor.getNext().getNext());
			cursor.getNext().setPrevious(cursor);
			size--;
		}
	}

	/**
	 * Returns a string representing the internal state of the circle,
	 * with the following format:
	 *
	 * "<-- Player A <--> Player B <--> Player C <--> Player D -->"
	 *
	 * @return a string representation of the circle of players
	 */
	public String getCircle()
	{
		//Returns a string representing the internal state of the circle,
		//with the following format:
		//<-- Player A <--> Player B <--> Player C <--> Player D -->
		if (head == null) {
			return "[]";
		}
		String result = "<-- " + head.getData();
		JosephusNode current = head.getNext();
		while (current != head)
		{
			result += " <--> " + current.getData();
			current = current.getNext();
		}
		return result + " -->";
	}

	/**
	 * Returns true if the game is over. That is, this method will
	 * return true if only one player remains in the circle.
	 *
	 * @return true if game over, otherwise false
	 */
	public boolean isGameOver()
	{
		//Returns true if the game is over. That is, this method will
		//return true if only one player remains in the circle.
		if (size == 1) {
			return true;
		}
		return false;
	}
}
