/** class represents the points system for the tic tac toe game.
 * 
 * @author Owoaje Temi
 *
 */
public class pointgain {

	public  int x;
	public  int y;
	/**
	 * constructor for class takes in two parameters x and y representing row and column on tic tac toe board.
	 * @param x - row
	 * @param y- column
	 */
	public pointgain(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * getter for the x attribute
	 * @return
	 */
	public int getX() {
		return x;
	}
	/**
	 * getter for the y attribute
	 * @return
	 */
	public int getY() {
		return y;
	}
	@Override
	/**
	 * returns a string representation of class.
	 */
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
	
	
}
