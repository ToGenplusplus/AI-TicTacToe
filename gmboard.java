import java.util.ArrayList;
import java.util.List;

public class gmboard {

	static final int PLAYER_H = 3;
	static final int PLAYER_AI = 1;
	static final int PLAYER_NONE = 0;
	private static final int[][] gmboard = new int[3][3];
	private static final char[][] actboard = new char[3][3];
	public pointgain compmove;
	/**
	 * method checks to see if the games done, by calling the playerwon method on both ai and human players.
	 *  Also if is the draw by returning the available spaces method if its empty.
	 *
	 * @return
	 */
	public boolean isgamedone() {
		return PlayerWon(PLAYER_AI) || PlayerWon(PLAYER_H) || availableSpaces().isEmpty();
	}
	/**
	 * method check to see if a player has won, by analyzing the tic tac toe game board rules.
	 * combowins = {
				{0, 1, 2},
				{3, 4, 5},
				{6, 7, 8},
				{0, 3, 6},
				{1, 4, 7},
				{2, 5, 8},
				{0, 4, 8},
				{6, 4, 2}
						};
		}
	 * @param player
	 * @return
	 */
	public boolean PlayerWon(int player) {
		// TODO Auto-generated method stub
		if((gmboard[0][0] == gmboard[1][1] && gmboard[0][0] == gmboard[2][2] && gmboard[0][0] == player)
				|| (gmboard[0][2] == gmboard[1][1] && gmboard[0][2] == gmboard[2][0] && gmboard[0][2] == player)) {
			return true;
		}
		for(int i= 0; i < 3; i++) {
			if((gmboard[i][0] == gmboard[i][1] && gmboard[i][0] == gmboard[i][2] && gmboard[i][0] == player)
					|| (gmboard[0][i] == gmboard[1][i] && gmboard[0][i] == gmboard[2][i] && gmboard[0][i] == player)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method creates an array list, to find out what spaces
	 * are open on the board, if there are no spaces, games a tie.
	 * @return
	 */
	public List<pointgain> availableSpaces() {
		// TODO Auto-generated method stub;
		//creates new list.
		List<pointgain> openspaces = new ArrayList<>(); // create a list of point gain objects
		
		// iterate through the gameboard, add a new pointgain score if there isn't a player
		for(int i=0; i<3; i++){
			for(int j=0; j <3; j++){
				if(gmboard[i][j] == PLAYER_NONE) {
					openspaces.add(new pointgain(i,j));
				}
			 	}
			  }
		return openspaces;
	}
	
	/**
	 * Method ensures a move has been set by a player, if there is no player(i.e game is done)
	 * returns false;
	 * @param player
	 * @return
	 */
	public boolean setmove(pointgain point,int player) {
		if(gmboard[point.getX()][point.getY()] != PLAYER_NONE) {
			return false;
		}
		gmboard[point.getX()][point.getY()] = player;
		return true;
	}
	
	/**
	 * Method creates the tic tac toe board and displays it when called.
	 */
	public void displayGameBoard() {
		System.out.println();
		
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	// if computer chooses this spot, actboard sets content = X
            	if(gmboard[i][j] == PLAYER_AI) {
            		actboard[i][j] = 'X';
            		// if user  chooses this spot, actboard sets content = O
            	}else if(gmboard[i][j] == PLAYER_H) {
            		actboard[i][j] = 'O';
            	
            	}
		            }
        	System.out.println();
        	
        	
		        }
				System.out.println("  0  1  2");
		        System.out.println("0 " + " " + actboard[0][0] + "|" + actboard[0][1] + "|"
		                + actboard[0][2]);
		        System.out.println("  --------");
		        System.out.println("1 " + " " + actboard[1][0] + "|" + actboard[1][1] + "|"
		                + actboard[1][2]);
		        System.out.println("  --------");
		        System.out.println("2 " + " " + actboard[2][0] + "|" + actboard[2][1] + "|"
		                + actboard[2][2]);
		    
	}
	/**
	 * method implements the minimax algorithm, which examines the best move for the computer to make to ensure
	 * computer win or game is a draw. We maximize the best move for computer and minimize move for user. Takes in parameters depth of game(game states) and which player.
	 * @param depth
	 * @param playerturn
	 * @return
	 */
	public int minimax(int depth, int playerturn) {

		// TODO Auto-generated method stub
		List<pointgain> openspaces =  availableSpaces();// assigns all available spaces to openspaces.
		
		if(PlayerWon(PLAYER_AI)) {
			return 1;
		}else if(PlayerWon(PLAYER_H)) {
			return -1;
		}else if (openspaces.isEmpty()) {
			return 0;
			}
		
		
		// set the min to the max integer value
		int min = Integer.MAX_VALUE;
		// set the max to the min integer value
		int max = Integer.MIN_VALUE;
		
		// iterate through the open spaces on the board.
		for(int i=0; i< openspaces.size(); i++) {
			pointgain point = openspaces.get(i); // for each open space assign it to a point gain object point.
			
			// if its the ai's turn
			if(playerturn == PLAYER_AI) {
				setmove(point,PLAYER_AI);// computer sets a move at amn available space
				int recentscore = minimax(depth + 1, PLAYER_H); // launches the minimax algorithm for the user, with depth + 1
				max= Math.max(recentscore, max);// maximize the best move for the given game state computer, want to result in either a win or draw.
				
				if(depth == 0) {
					System.out.println("Computer score " + point + "=" + recentscore);
				}
				// if this score maximizes the potential for AI to win, we make sure computer takes move.
				if(recentscore >= 0) {
					if(depth == 0) {
						compmove = point;
					}
				}
				// empty the boardspace only if score ois equal to one.
				if(recentscore == 1) {
					gmboard[point.getX()][point.getY()] = PLAYER_NONE;
					break;
				}
				// if interactive with all sells and max is less than 0, set compmove equal to point.
				if(i == openspaces.size()-1 && max < 0) {
					if(depth == 0) {
						compmove = point;
					}
				}
			}else if ( playerturn == PLAYER_H) {
				setmove(point, PLAYER_H);// set the user move.
				int recentscore = minimax(depth + 1, PLAYER_AI);	 // launches the minimax algorithm for the AI, with depth + 1
				min= Math.min(recentscore, min);// we want to minimze the move in the game state played by user,so we set min to math.min the recent score.
				
				if( min == -1) {
					gmboard[point.getX()][point.getY()] = PLAYER_NONE;
					break;
				}
			}
			// at the end of each iteration must reset point to 0;
			gmboard[point.getX()][point.getY()] = PLAYER_NONE;
		}
		return playerturn == PLAYER_AI ? max : min;// if ai player turn return max else return min
	}
	/**
	 * helper method declares the winner.
	 */
	public void declarewinner() {
		if(PlayerWon(PLAYER_AI))
			System.out.println("You lost");
		else if(PlayerWon(PLAYER_AI) )
			System.out.println("You Win!!!");
		else
			System.out.println("Its a Draw");
	}
}
