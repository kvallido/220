/*
    Name: Kim Vallido
    Date: 1/8/2020
    File Name: Driver.java
    Description: Runs the Choose Your Own Adventure Program.

 */

import java.io.*;
import java.util.Scanner;

public class Driver
{
    private static Scanner console = new Scanner(System.in);

    /**
     * This class runs the Choose Your Own Adventure Program.
     *
     * @param args unused?
     */
    public static void main(String[] args)
    {
        System.out.println("Welcome to my Choose Your Own Adventure program!");
        System.out.println("Please choose a story: ");

        // lists the stories in the incomplete folder
        StoryModule.listStories("incomplete");

        // creates new Story from user prompt, which is then used
        // to validate whether the user entered an available Story
        Story current = new Story(StoryModule.whichStory());
        try
        {
            // gets Story path String to validate file exists
            if (!StoryModule.fileExists(current.getStoryPath()))
            {
                throw new FileNotFoundException("Please enter a valid story" +
                        " name..");
            }
            // overwrites completed file if story has been completed in the past
            if (StoryModule.fileExists(current.getCompletedStory()))
            {
                current.overWrite();
            }
            // instantiate and fill array by indexing each line.
            String[] storyArray = StoryModule.fillArray(current);
            // displays first turn to user
            current.turn(storyArray, 1);
            int nextChoice;
            do
            {
                nextChoice = console.nextInt();
                if (nextChoice < 0)
                {
                    break;
                }
                current.turn(storyArray, current.validChoice(nextChoice));
            } while (nextChoice > 0);

            // prints out less than ideal ending message
            if (nextChoice == -1)
            {
                System.out.println();
                System.out.println("Better luck next time?");
            }

            // prints out successful ending message
            else if (nextChoice == -2)
            {
                System.out.println();
                System.out.println("Hope you enjoyed the ride!");
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
