/*
    Name: Kim Vallido
    Date: 1/8/2020
    File Name: StoryModule.java
    Description: A module containing static methods useful in the Driver
    class for my Choose Your Own Adventure game.

 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Contains useful static methods that interact with the Story
 * and their file names/paths.
 *
 * @author Kim Vallido
 * @version 1.0
 *
 */

public class StoryModule
{
    /**
     * Lists all the story titles that exist in the provided folder
     *
     * @param folder name of folder
     */
    // lists all stories in any given folder
    public static void listStories(String folder)
    {
        File folderName = new File("./stories/" + folder + "/");
        String[] titles;
        titles = folderName.list();
        if (titles != null)
        {
            for (String hasExtension : titles)
            {
                String[] title = hasExtension.split(".txt");
                // print title without file extension
                System.out.println(title[0]);
            }
        }
    }

    /**
     * Prompts user for name of story. Ensures user does not
     * enter null value or empty string.
     *
     * @return title of story selected
     */
    // intended to ask user to enter a story from the titles listed
    public static String whichStory()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Story name: ");
        String title = console.nextLine();
        while (title == null || title.equals("")) {
            System.out.println("Please do not input an empty value.");
            title = console.nextLine();
        }
        return title;
    }

    /**
     * Intended to validate whether the file containing a story
     * already exists.
     *
     * @param path the local path of the story chosen
     * @return true if file exists
     */
    // returns true if the file path passed in parameter exists
    public static boolean fileExists(String path)
    {
        boolean here = false;
        File story = new File(path);
        if (story.exists())
        {
            here = true;
        }
        return here;
    }

    /**
     * Stores the selected Story into an array with one line per index.
     * Intended to be used with values retrieved from the Story class.
     *
     * @param story new Story initialized in Driver class
     * @return Story lines stored in an array
     */
    // Initializes an array using the int returned from the numLines() method
    // in the Story class, reads from a file and fills the array index with its
    // corresponding line.
    public static String[] fillArray(Story story)
    {
        String[] newArray = {};
        try (Scanner reader = new Scanner(
                new FileInputStream(story.getStoryPath())))
        {
            newArray = new String[story.numLines()];
            for (int i = 0; i < newArray.length; i++) {
                // represents first index of new array
                int lines = 0;
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    newArray[lines] = line;
                    lines++;
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Error reading from file..");
        }
        return newArray;
    }

}