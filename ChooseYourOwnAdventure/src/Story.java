/*
    Name: Kim Vallido
    Date: 1/8/2020
    File Name: Story.java
    Description: This file contains a Story class.
 */

import java.io.*;
import java.util.Scanner;

/**
 * This class represents the Story chosen by the user
 * in the Choose Your Own Adventure Driver program. It
 * contains the Story's line count, as well as String
 * representations of the Story's title, folder location,
 * and local path.
 *
 *
 * @author Kim Vallido
 * @version 1.0
 *
 */

public class Story
{
    private final String PATH = "./stories/";
    private final String COMPLETE = PATH + "complete/";
    private String folder;
    private String title;
    private int lineCount;
    private String storyPath;
    private String completedStory;
    private int[] validChoices = {};


    /**
     * The default Story is a representation
     * of "zombies.txt" which was used to model this
     * project.
     *
     */
    // default Story
    public Story()
    {
        folder = "incomplete";
        title = "zombies";
        lineCount = 21;
        storyPath = PATH + folder + "/" + title + ".txt";
        completedStory = COMPLETE + title + ".txt";
    }


    /**
     * Creates a new Story by passing the file name (or title)
     * as a parameter in the constructor. Also contains the path
     * for where the completed story will be located.
     *
     * @param title the file name (or title) of your Story
     */
    // create a new Story in the incomplete folder
    public Story(String title)
    {
        this.title = title;
        this.folder = "incomplete";
        this.storyPath = PATH + folder + "/" + title + ".txt";
        this.completedStory = COMPLETE + title + ".txt";
    }

    // getters (accessors)
    public String getCompletedStory()
    {
        return completedStory;
    }
    public String getStoryPath()
    {
        return storyPath;
    }
    public String getTitle()
    {
        return title;
    }
    public int getLineCount()
    {
        return lineCount;
    }

    /**
     * Returns the total line count of your Story.
     * This method is particularly useful when creating
     * a new array place your Story in.
     *
     * @return the number of lines in your Story
     */
    // counts lines in story
    public int numLines()
    {
        int count = 0;
        try (Scanner reader = new Scanner(
                new FileInputStream(storyPath)))
        {
            while (reader.hasNextLine())
            {
                count++;
                reader.nextLine();
            }
            lineCount = count;
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error reading from file..");
        }
        return count;
    }

    /**
     * Using the String[] that indexes each line of the Story
     * this method prints the Story's current turn to the console
     * as well as to the document that will contain the completed Story.
     *
     * @param story the array filled with each line of your story
     * @param lineNum can either be the user choice for that turn
     *                or the line (or turn) of the Story you would
     *                like to display.
     */
    // prints turn to console & writes turn to new file
    public void turn(String[] story, int lineNum)
    {
        lineNum -= 1; // to account for being off by one index
        System.out.println();
        String line = story[lineNum];

        // splits turn description from rest of string,
        // prints in console & writes to completed story file.
        String[] descChoices = line.split(" : ");
        write(descChoices[0]);
        // splits each possible choice into a new array
        String[] choices = descChoices[1].split(" \\| ");

        validChoices = new int[choices.length];
        // indexes valid choices into array while printing to console
        // and writing to new file
        for (int i = 0; i < choices.length; i++)
        {
            String[] choice = choices[i].split(" = ");
            int userChoice = Integer.parseInt(choice[1]);
            validChoices[i] = userChoice;
            write(userChoice + ": " + choice[0]);
        }
    }

    /**
     * Writes any user inputted choices to the completed Story file.
     * Checks if user input was a valid choice for the turn.
     * If not, it will prompt the user for a different option
     * until the user has picked a valid choice.
     * If so, method returns the user's choice.
     *
     * @param userChoice the numeric choice or option inputted/selected by the user
     * @return a valid choice, used to navigate to the next turn
     */
    // ensures user has chosen a valid option
    public int validChoice(int userChoice)
    {
        writeChoice(userChoice);
        boolean okay = false;

        // iterates through stored valid choices until user choice is found
        for (int validChoice : validChoices) {
            if (userChoice == -1 || validChoice == userChoice) {
                okay = true;
                break;
            }
        }
        if (okay)
        {
            return userChoice;
        }
        else
        {
            write("Please enter a valid option...");
            Scanner console = new Scanner(System.in);
            while (!console.hasNextInt())
            {
                write("Please enter a valid numeric option...");
            }
            userChoice = console.nextInt();
            validChoice(userChoice);
        }
        return userChoice;
    }

    // prints current turn to completed Story file
    private void write(String turn)
    {
        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream(COMPLETE + title + ".txt", true)))
        {
            writer.println(turn);
            System.out.println(turn);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error writing to file :-(");
        }
    }

    // prints the numeric choice a user has made to completed Story file
    private void writeChoice(int choice)
    {
        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream(COMPLETE + title + ".txt", true)))
        {
            writer.println(choice);
            writer.println();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error writing to file..");
        }
    }

    /**
     * Clears the contents of a previously completed Story's text file
     * so that a new play through may be saved.
     */
    public void overWrite()
    {
        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream(COMPLETE + title + ".txt", false)))
        {
//            writer = new PrintWriter(
//                    new FileOutputStream(COMPLETE + title + ".txt", false));
            writer.println("");
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error writing to file..");
        }
    }


    /**
     * Returns the title and path of the Story
     *
     * @return title and path of Story
     */
    @Override
    public String toString()
    {
        return "Title: " + getTitle() + "\nPath: " + getStoryPath();
    }
}
