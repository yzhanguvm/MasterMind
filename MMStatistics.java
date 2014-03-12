

import java.io.*;
import java.util.*;

/**
 * The MasterMind program simulates Master-mind game.
 * It allows the user to play against the computer.
 * The computer generates the correct answer and evaluates the guesses provided by the user
 * and gives hints and scores after each guess.
 *
 * @author Anthony Sweet
 * 
 */

 
/**
 * This class reads and writes to text files
 * Files are saved in the following format, one line for each mode
 * 
 */
public class MMStatistics 
{
	private String standardFILE = "StandardMasterMind.txt";
	private String challengeFILE = "AdvancedMasterMind.txt";
	int MAX_SIZE = 101;							// The Maximum # of guesses a user can have before they automatically lose
	int [] guessArray = new int[MAX_SIZE];	// Holds the number of users with the correct answer (where index = guesses)

	/**
	 * Sets the mode so the appropriate file can be used to save the information
	 * @param currentMode is the GLOBAL integer value associated with each mode type from MasterMind.java
	 */	
	/*public void setMode(int currentMode) 
	{
		mode = currentMode;
	}*/
	
	/**
	 * Function Loads the current data from the appropriate file in order to edit the statistics
	 */
	private int loadStats(int mode)throws IOException 
	{		
		// Variables
	    String line; 								// Full line from file
		int guessCount = 0;						// The amount of guesses it took to get the correct answer	
		boolean hasTotal = false;
		int tokenItem;								// A token from the document loaded
		int totalGames = 0;
		
		// Open File to begin reading
		BufferedReader	br;
		
		if (mode == 6)
			br = new BufferedReader(new FileReader(standardFILE));
		else
			br = new BufferedReader(new FileReader(challengeFILE));

		// Read next line and process it if it exists
		while	( (line = br.readLine())	!=	null)
		{				// Stop if there is not another line

			// Begin Tokenizing the whole line for multiple values
			StringTokenizer token =	new StringTokenizer(line);				
			
			// Process whole line adding each value into the array
			while	(token.hasMoreTokens())
			{//	Doesnt allow for the array to be overloaded
			
				// Get Token from the file
				tokenItem = Integer.parseInt(token.nextToken());
				
				// Checks to see if the first line has been read.
				// The first line contains the total games played
				if (hasTotal) 
				{				
					if (tokenItem != 0) 
					{
						guessArray[guessCount] = tokenItem;
					} else {
						guessArray[guessCount] = 0;
					}
				} else {
					totalGames = tokenItem;
					hasTotal = true;
				}
				
				// Increase line number (Corresponds to the number of guesses it took)
				guessCount++;
			}			
		}

		br.close();									// Close BufferedReader
		return totalGames;
	}
	/**
	 * Function clears out the statistics files
	 */	
	public void clearStats() throws IOException 
	{
		File AdvancedFile = new File("AdvancedMasterMind.txt");
		File StandardFile = new File("StandardMasterMind.txt");
		AdvancedFile.delete();
		StandardFile.delete();
		File AFile = new File("AdvancedMasterMind.txt");
		File SFile = new File("StandardMasterMind.txt");
		AFile.createNewFile();
		SFile.createNewFile();

	}										//ClearStats
	/**
	 * Function displays all of the stats on the screen for each gameplay mode
	 */	
	public void displayStats(int mode) throws IOException 
	{
		int totalGames = loadStats(mode);								// Get the statistics
		int guessCount = 0;						// Counter for # of guesses it took to correctly guess the MasterMind
		int averageTotal = 0;
		
		System.out.println("Total Games Played: " + totalGames);

		// Calculate the average guesses
		for (int i=0; i<MAX_SIZE;i++)
		{		
			// Display all of the stats if there was someone who correctly guesses
			if (guessArray[i] != 0) 
			{
				averageTotal += guessArray[i] * guessCount;
			}	
			guessCount++;
		}	
		if (totalGames >= 0)
			System.out.println("On Average It Took " + (averageTotal / ((double) totalGames)) + " Guesses to Successfully Answer The MasterMind.\n");

		// Reset Guess Count to go through the array again 
		guessCount = 0;
		if (totalGames != 0)
		{
			for (int i=0; i<MAX_SIZE;i++)
			{		
				// Display all of the stats if there was someone who correctly guesses
				if (guessArray[i] != 0) 
				{
					System.out.println(guessArray[i] + " users (" + (guessArray[i] / ((double) totalGames)) * 100 + "%) took " + guessCount + " guess(es) to successfully solve the MasterMind.");
				}	
				guessCount++;
			}	
		}
	}

	/**
	 * Function to save all of the statistics into the appropriate file
	 * @param guessesUntilSuccess is the number of guesses it took the player to correctly guess the MasterMind
	 */	
	public void saveStats(int guessesUntilSuccess, int mode) throws IOException 
	{
		int totalGames = loadStats(mode);			// Retreive All Data
		totalGames++;								// Increase the total games played
		
		// Add the users total guesses into array
		guessArray[guessesUntilSuccess] = guessArray[guessesUntilSuccess] + 1;
		
		// Create file to write to
		BufferedWriter	writer;
		
		if (mode == 6)
			writer = new BufferedWriter(new FileWriter(standardFILE));
		else
			writer = new BufferedWriter(new FileWriter(challengeFILE));
		
		// Write the first line (Total number of guesses)
		writer.write(Integer.toString(totalGames));
		
		// Write each value in array to file, separated by lines
		for (int i = 1; i < MAX_SIZE; i++)
		{
			writer.newLine();
			writer.write(Integer.toString(guessArray[i]));
		}

		writer.close();							// Close BufferedWriter

	}

   // tester of MMStatisitcs class
   public static void main (String args[]) throws IOException 
	{
	
		MMStatistics test = new MMStatistics();
		test.displayStats(6);
		test.clearStats();
		test.displayStats(6);
		
		for (int i = 0; i<1000; i++) {
			test.saveStats(1+(int)(Math.random() * 100), 6);
		}
		
		test.displayStats(6);
		test.clearStats();
		test.displayStats(7);
		//test.loadStats();

		
   }//tester
   
}//MMStatisitcs class
