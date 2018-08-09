import java.util.Random;
import java.util.Scanner;
/**
 * main class for tic tac toe program, creates user interface.
 * @author Owoaje Temi
 *
 */
public class runTicTacToe {
	private static Scanner scan = new Scanner(System.in);; 
	int ai = gmboard.PLAYER_AI;		// 1
	int human = gmboard.PLAYER_H;	// 3
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		runTicTacToe r = new runTicTacToe();
		r.StartGame();			// initialize startgame function.

	}
/**
 * startgame function, to actually run the game.
 */
	public void StartGame() {
		System.out.println("Welcome to Tic Tac Toe, objective\ntry and beat computer GOOD LUCK!!");
		
		gmboard gb = new gmboard();				// create new gmboard object
		
		Random rand = new Random();
	
		
		gb.displayGameBoard();					// display the initial gameboard
		
		
		
		System.out.println("Choose who goes first: 1 - computer(X), 3- user(O)");

		
		int choice = scan.nextInt();			// get the integer choice from the user
		
		// if user selects computer
		if(choice == ai) {					
			pointgain pnt = new pointgain(rand.nextInt(3), rand.nextInt(3));
			gb.setmove(pnt,ai);
			gb.displayGameBoard();
		}
		// code runs while the game isn't done.
		while(!gb.isgamedone()) { 
			boolean okmove = true;
			
				// this do while loop ensures that the user enters a valid move
				do {
					
					if(!okmove) {
						System.out.println("cell already filled");
					}
				
					userMove();
					okmove=  gb.setmove(userMove(), human);
					
				} while(!okmove);
				
				gb.displayGameBoard();
				
				if(gb.isgamedone()) {
					break;
				}
				// call the minimax algorithm with the initial depth at 0 and player = AI
				gb.minimax(0,ai);
				
				System.out.println("Computer chose " + gb.compmove);
				gb.setmove(gb.compmove,ai);
				gb.displayGameBoard();
			}
		
		gb.declarewinner(); // when game is done, function called.
		// statement checks to see if the user wants to play again.
		if(playAgain()) {
			StartGame();
		}
		
	}
	/**
	 * helper method, gets the user move. returns the move as a pointgain object
	 */
	public pointgain userMove() {
		pointgain usermove = null;
		System.out.println("your move: position formt # #");
		
		usermove = new pointgain(scan.nextInt(), scan.nextInt());
	
		return usermove;
	}
	/**
	 * method allows the user to choose whether they want to play again.
	 * @return true of yes, false if no.
	 */
	public boolean playAgain() {
		boolean ok = true;
		System.out.println("Would you like to play again, yes or no");
		String choice = scan.nextLine();
		if(choice.toLowerCase().charAt(0) == 'y') {
			ok= true;
		}else if(choice.toLowerCase().charAt(0) == 'n'){
			ok = false;
		}else {
			while(choice.toLowerCase().charAt(0) != 'n' && choice.toLowerCase().charAt(0) != 'y') {
				System.out.println("Invalid input, please choose yes or no");
				 choice = scan.nextLine();
			}
			//ok= true;
		}
		return ok;
	}
}
