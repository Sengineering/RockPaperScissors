public class Game 
{
	/*	This game of Rock Paper Scissors was created using Java 9.
	 * 
	 * 	First player to reach 3 wins is the winner.
	 * 
	 * 	Game is played using a simple GUI.
	 * 
	 * 	The time taken to complete this project was: approx. 2h 10m
	 */
	
	// Initialise number of wins for each player
	public static int p1Wins = 0;
	public static int p2Wins = 0;
	
	// Initialise number of ties
	public static int ties = 0;
	
	// Initialise number of turns taken
	public static int turns = 0;
	
	// Initialise number of times each move has been used
	public static int timesUsedRock = 0;
	public static int timesUsedPaper = 0;
	public static int timesUsedScissors = 0;
	
	// Determine if player has chosen their move
	public static boolean p1HasPlayed = false;
	public static boolean p2HasPlayed = false;
	
	// Hold which move player has played
	public static String p1Move;
	public static String p2Move;
	
	// Hold status of round
	public static String status;
	
	// Create window
	public static void Window()
	{
		Window window = new Window();
	}
	
	// Check player moves to find out winner
	public static void checkMoves()
	{
		// Check if round is a tie
		if( p1Move == p2Move )
		{
			status = "Tie";
			
			// Increase counter of turns and ties
			ties++;
			turns++;
		}
		
		// Check if player 1 wins
		if( ( p1Move == "Rock" && p2Move == "Scissors" ) || ( p1Move == "Paper" && p2Move == "Rock" ) || 
				( p1Move == "Scissors" && p2Move == "Paper" ) )
		{
			status = "Player 1 wins!";
			
			// Increase counter of player 1 wins and turns
			p1Wins++;
			turns++;
		}
		
		// Check if player 2 wins
		if( ( p2Move == "Rock" && p1Move == "Scissors" ) || ( p2Move == "Paper" && p1Move == "Rock" ) ||
				( p2Move == "Scissors" && p1Move == "Paper" ) )
		{
			status = "Player 2 wins!";
			
			// Increase counter of player 2 wins and turns
			p2Wins++;
			turns++;
		}
	}
	
	// Check to see if a player has won
	public static void win()
	{
		// A player wins the game when 3 rounds have been won
		if( p1Wins == 3 || p2Wins == 3 )
		{
			// Disable next round button
			Window.nextRound.setEnabled( false );
			
			// Determine who has more round wins and display
			String winner;
			if( p1Wins > p2Wins )
			{
				winner = "Player 1";
			}
			else
			{
				winner = "Player 2";
			}
			Window.winner.setText( winner + " is the WINNER!" );
			
			// Display number of turns taken to win
			Window.numberOfTurns.setText( "Number of turns taken to win: " + turns );
			
			// Determine which move was used the most and display
			String mostUsedMove = "";
			if( ( timesUsedRock >= timesUsedPaper ) && ( timesUsedRock >= timesUsedScissors ) )
			{
				mostUsedMove = "Rock";
			}
			else if( ( timesUsedPaper >= timesUsedRock ) && ( timesUsedPaper >= timesUsedScissors ) )
			{
				mostUsedMove = "Paper";
			}
			else if( ( timesUsedScissors >= timesUsedRock ) && ( timesUsedScissors >= timesUsedPaper ) )
			{
				mostUsedMove = "Scissors";
			}
			Window.mostUsedMove.setText( "Most used move: " + mostUsedMove );
		}
	}
	
	// Reset all counters and information
	public static void reset()
	{
		p1HasPlayed = false; p2HasPlayed = false;
		p1Move = ""; p2Move = "";
		p1Wins = 0; p2Wins = 0; ties = 0;
		turns = 0;
		timesUsedRock = 0; timesUsedPaper = 0; timesUsedScissors = 0;
		status = "";
	}
	
	public static void main( String[] args )
	{
		// Create window and run game
		Window();
	}
}
