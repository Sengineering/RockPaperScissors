import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame implements ActionListener
{
	// Declare window panel
	JPanel panel = new JPanel();
	
	// Get screen dimensions
	final int W = 1200;
	final int H = 720;
	
	// Declare labels for player names
	JLabel player1 = new JLabel( "Player 1" );
	JLabel player2 = new JLabel( "Player 2" );
	
	// Declare text fields for game information
	JTextArea p1Choice = new JTextArea( 1, 40 );
	JTextArea p2Choice = new JTextArea( 1, 40 );
	
	JTextArea score = new JTextArea( 1, 40 );
	static JTextArea winner = new JTextArea( 1, 40 );
	
	static JTextArea numberOfTurns = new JTextArea( 1, 40 );
	
	static JTextArea mostUsedMove = new JTextArea( 1, 40 );
	
	// Declare buttons to be used in the game
	JButton p1Rock = new JButton( "Rock" );
	JButton p1Paper = new JButton( "Paper" );
	JButton p1Scissors = new JButton( "Scissors" );
	
	JButton p2Rock = new JButton( "Rock" );
	JButton p2Paper = new JButton( "Paper" );
	JButton p2Scissors = new JButton( "Scissors" );
	
	static JButton nextRound = new JButton( "Next Round" );
	JButton playAgain = new JButton( "Play Again" );
	
	// Build window constructor
	public Window()
	{
		// Window name
		super( "Rock Paper Scissors" );
		
		// Set window size
		setSize( W, H );
		
		// Exit the program when window is closed
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		// Add panel to window
		add( panel );
		
		// Set window visibility to true
		setVisible( true );
		
		// Set default layout to null
		setLayout( null );
		panel.setLayout( null );
		
		// Position player names
		labelPos( player1, W/8, H/8 );
		labelPos( player2, 6*W/8, H/8 );
		
		// Add information field to centre of screen
		textPos( p1Choice, W/3, H/4 );
		textPos( p2Choice, W/3, 9*H/32 );
		p1Choice.setText( "Player 1 chose:" );
		p2Choice.setText( "Player 2 chose:" );
		
		textPos( score, W/3, 11*H/32 );
		score.setText( "Player 1: " + Game.p1Wins + "  Player 2: " + Game.p2Wins + "  Ties: " + Game.ties );
		
		textPos( winner, W/3, 12*H/32 );
		
		textPos( numberOfTurns, W/3, 13*H/32 );
		
		textPos( mostUsedMove, W/3, 15*H/32 );
		
		// Add buttons to the window panel
		buttonPos( p1Rock, W/8, H/4 );
		buttonPos( p1Paper, W/8, 3*H/8 );
		buttonPos( p1Scissors, W/8, H/2 );
		
		buttonPos( p2Rock, 3*W/4, H/4 );
		buttonPos( p2Paper, 3*W/4, 3*H/8 );
		buttonPos( p2Scissors, 3*W/4, H/2 );
		
		// Set player 2's buttons to disabled until player 1 makes their move
		disableButtons( p2Rock, p2Paper, p2Scissors );
		
		buttonPos( nextRound, W/3, 13*H/16 );
		buttonPos( playAgain, (2*W/3) - (W/8), 13*H/16 );
	}
	
	// Function to set position of a button
	public void buttonPos( JButton button, int x, int y )
	{
		button.setLayout( null );
		button.setBounds( x, y, W/8, H/16 );
		panel.add( button );
		
		button.addActionListener( this );
	}
	
	// Function to set position of a label
	public void labelPos( JLabel label, int x, int y )
	{
		label.setLayout( null );
		label.setBounds( x, y, W/8, H/5 );
		panel.add( label );
	}
	
	// Function to set position of text area
	public void textPos( JTextArea text, int x, int y )
	{
		text.setLayout( null );
		text.setBounds( x, y, W/3, H/32 );
		panel.add( text );
	}
	
	// Function to enable buttons
	public void enableButtons( JButton button1, JButton button2, JButton button3 )
	{
		button1.setEnabled( true );
		button2.setEnabled( true );
		button3.setEnabled( true );
	}
	
	// Function to disable buttons
	public void disableButtons( JButton button1, JButton button2, JButton button3 )
	{
		button1.setEnabled( false );
		button2.setEnabled( false );
		button3.setEnabled( false );
	}
	
	// Function to respond to buttons being pressed
	public void actionPerformed( ActionEvent event )
	{
		// Buttons only activate while the game is in action (a player has less than 3 wins)
		if( Game.p1Wins <= 3 && Game.p2Wins <= 3 )
		{
			// Activate if players press rock
			if( event.getSource() == p1Rock && Game.p1HasPlayed == false )
			{
				// Set information
				Game.p1HasPlayed = true;
				Game.p1Move = "Rock";
				Game.timesUsedRock++;
				
				// Disable player 1 buttons and re-enable player 2 buttons
				disableButtons( p1Rock, p1Paper, p1Scissors );
				enableButtons( p2Rock, p2Paper, p2Scissors );
			}
			if( event.getSource() == p2Rock && Game.p2HasPlayed == false )
			{
				// Set information
				Game.p2HasPlayed = true;
				Game.p2Move = "Rock";
				Game.timesUsedRock++;
				
				// Display what each player's move was
				p1Choice.setText( "Player 1 chose: " + Game.p1Move );
				p2Choice.setText( "Player 2 chose: " + Game.p2Move );
				
				// Disable player 2's buttons
				disableButtons( p2Rock, p2Paper, p2Scissors );
				
				// Check who won the round and if an overall winner has been found
				Game.checkMoves();
				Game.win();
				
				// Display score
				score.setText( "Player 1: " + Game.p1Wins + "  Player 2: " + Game.p2Wins + "  Ties: " + Game.ties );
			}
			
			// Activate if players press paper
			if( event.getSource() == p1Paper && Game.p1HasPlayed == false )
			{
				// Set information
				Game.p1HasPlayed = true;
				Game.p1Move = "Paper";
				Game.timesUsedPaper++;
				
				// Disable player 1 buttons and re-enable player 2 buttons
				disableButtons( p1Rock, p1Paper, p1Scissors );
				enableButtons( p2Rock, p2Paper, p2Scissors );
			}
			if( event.getSource() == p2Paper && Game.p2HasPlayed == false )
			{
				// Set information
				Game.p2HasPlayed = true;
				Game.p2Move = "Paper";
				Game.timesUsedPaper++;
				
				// Display what each player's move was
				p1Choice.setText( "Player 1 chose: " + Game.p1Move );
				p2Choice.setText( "Player 2 chose: " + Game.p2Move );
				
				// Disable player 2's buttons
				disableButtons( p2Rock, p2Paper, p2Scissors );
				
				// Check who won the round and if an overall winner has been found
				Game.checkMoves();
				Game.win();
				
				// Display score
				score.setText( "Player 1: " + Game.p1Wins + "  Player 2: " + Game.p2Wins + "  Ties: " + Game.ties );
			}
			
			// Activate if players press scissors
			if( event.getSource() == p1Scissors && Game.p1HasPlayed == false )
			{
				// Set information
				Game.p1HasPlayed = true;
				Game.p1Move = "Scissors";
				Game.timesUsedScissors++;
				
				// Disable player 1 buttons and re-enable player 2 buttons
				disableButtons( p1Rock, p1Paper, p1Scissors );
				enableButtons( p2Rock, p2Paper, p2Scissors );
			}
			if( event.getSource() == p2Scissors && Game.p2HasPlayed == false )
			{
				// Set information
				Game.p2HasPlayed = true;
				Game.p2Move = "Scissors";
				Game.timesUsedScissors++;
				
				// Display what each player's move was
				p1Choice.setText( "Player 1 chose: " + Game.p1Move );
				p2Choice.setText( "Player 2 chose: " + Game.p2Move );
				
				// Disable player 2's buttons
				disableButtons( p2Rock, p2Paper, p2Scissors );
				
				// Check who won the round and if an overall winner has been found
				Game.checkMoves();
				Game.win();
				
				// Display score
				score.setText( "Player 1: " + Game.p1Wins + "  Player 2: " + Game.p2Wins + "  Ties: " + Game.ties );
			}
		}
		
		// Moves to next round
		if( event.getSource() == nextRound )
		{
			// Reset chosen moves
			Game.p1Move = ""; Game.p2Move = "";
			p1Choice.setText( "Player 1 chose:" );
			p2Choice.setText( "Player 2 chose:" );
			
			// Reset played status
			Game.p1HasPlayed = false; Game.p2HasPlayed = false;
			
			// Re-enable player 1 button, and disable player 2 buttons
			enableButtons( p1Rock, p1Paper, p1Scissors );
			disableButtons( p2Rock, p2Paper, p2Scissors );
		}
		
		// Resets the game
		if( event.getSource() == playAgain )
		{
			// Reset chosen moves
			p1Choice.setText( "Player 1 chose:" );
			p2Choice.setText( "Player 2 chose:" );
			
			// Reset all game information
			Game.reset();
			score.setText( "Player 1: " + Game.p1Wins + "  Player 2: " + Game.p2Wins + "  Ties: " + Game.ties );
			winner.setText( "" );
			numberOfTurns.setText( "" );
			mostUsedMove.setText( "" );
			
			// Re-enable player 1 button, and disable player 2 buttons
			enableButtons( p1Rock, p1Paper, p1Scissors );
			disableButtons( p2Rock, p2Paper, p2Scissors );
			
			// Re-enable next round button
			nextRound.setEnabled( true );
		}
	}
}
