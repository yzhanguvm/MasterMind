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
 * The Hint class gives the user a hint based on the MMAnswer class
 * @author Anthony Sweet
 *
 */
public class MMHint
{
   private char[] answer;
	private int hintsUsed = 0;
	
	/**
	 * Constructs a MMHint object which gives the user a hint
	 * @param answer - the char array indicates the answer made by the computer
	 * @param hints - the amount of guesses used
	 */
	public MMHint(char[] answer, int hints)
	{
      this.answer = answer;
		this.hintsUsed = hints;
	}

	/**
	 * Shows the user a hint based on the amount of times they have used the function
	 * @return integer output corresponding to the number of hints used
	 */
	public int getHint()
	{		
		if (hintsUsed < answer.length)
		{
         Arrays.sort(answer);
			System.out.println("Hint: There is at least one " + answer[hintsUsed] + " peg.");
		}
		else 
		{
			System.out.println("Hint: Sorry, you have already used all of your hints.");
		}
		
		hintsUsed++;
		
		return hintsUsed;
	}

	//tester for MMHint class
	public static void main (String args[])
	{
		MMAnswer testAnswer = new MMAnswer(6);
		System.out.println(testAnswer.getAnswer());
		
		for (int i = 0; i<4; i++) 
		{
			MMHint hint = new MMHint(testAnswer.getAnswer(), i);
			hint.getHint();
		}		
	
	}//tester for MMHint class
	
}//MMHint class
