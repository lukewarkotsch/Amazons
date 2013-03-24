package src;
import java.util.ArrayList;

public class Dist {
	/**
	 * 
	 * @param arr
	 *            holds the distance values for the specified player should be
	 *            initially set all values to +inf
	 * @param chArr
	 *            character array holding the board position
	 * @param loc
	 *            the location of the array to test on
	 * @param d
	 *            a boolean value deciding -true if computing the distance to
	 *            the rest of the board and false if only computing the squares
	 *            moveable to in one move
	 */
	private static void intervalSpread(int[][] arr, char[][] chArr,
			Location loc, boolean d) {
		int cur = arr[loc.x][loc.y];
		ArrayList<Location> next = new ArrayList<Location>();
		boolean up, down, left, right, upleft, upright, downleft, downright;
		up = down = left = right = upleft = upright = downleft = downright = true;
		++cur;
		int y = loc.y;
		int sy = loc.y;
		for (int x = loc.x + 1; x < arr.length; x++) {
			y++;
			sy--;
			// right span
			if (right) {
				if (chArr[x][loc.y] != 'S')
					right = false;
				else {
					if (arr[x][loc.y] > cur) {
						arr[x][loc.y] = cur;
						next.add(new Location(x, loc.y));
					}
				}
			}
			// upright span
			if (upright) {
				if (y == 10 || chArr[x][y] != 'S')
					upright = false;
				else {
					if (arr[x][y] > cur) {
						arr[x][y] = cur;
						next.add(new Location(x, y));
					}
				}
			}
			// downright span
			if (downright) {

				if (sy == -1 || chArr[x][sy] != 'S')
					downright = false;
				else {
					if (arr[x][sy] > cur) {
						arr[x][sy] = cur;
						next.add(new Location(x, sy));
					}
				}
			}
		}
		sy = loc.y;
		y = loc.y;
		for (int x = loc.x - 1; x >= 0; x--) {
			sy--;
			y++;
			// left span
			if (left) {
				if (chArr[x][loc.y] != 'S')
					left = false;
				else {
					if (arr[x][loc.y] > cur) {
						arr[x][loc.y] = cur;
						next.add(new Location(x, loc.y));
					}
				}
			}
			// upleft span
			if (upleft) {
				if (y == 10 || chArr[x][y] != 'S')
					upleft = false;
				else {
					if (arr[x][y] > cur) {
						arr[x][y] = cur;
						next.add(new Location(x, y));
					}
				}
			}
			// downleft span
			if (downleft) {
				if (sy == -1 || chArr[x][sy] != 'S')
					downleft = false;
				else {
					if (arr[x][sy] > cur) {
						arr[x][sy] = cur;
						next.add(new Location(x, sy));
					}
				}
			}
		}
		for (y = loc.y + 1; y < arr.length; y++) {
			// up span
			if (up) {
				if (chArr[loc.x][y] != 'S')
					up = false;
				else {
					if (arr[loc.x][y] > cur) {
						arr[loc.x][y] = cur;
						next.add(new Location(loc.x, y));
					}
				}
			}
		}
		for (y = loc.y - 1; y >= 0; y--) {
			// down span
			if (down) {
				if (chArr[loc.x][y] != 'S')
					down = false;
				else {
					if (arr[loc.x][y] > cur) {
						arr[loc.x][y] = cur;
						next.add(new Location(loc.x, y));
					}
				}
			}
		}
		if (d)
			for (Location locat : next) {
				intervalSpread(arr, chArr, locat, d);
			}
	}

	/**
	 * Helper function to shoot arrows from a given location and add the
	 * resulting character arrays from a given location
	 * 
	 * @param arr
	 *            -array holding the arrow position
	 * @param l
	 *            - the location of the Amazon
	 * @param thar
	 *            - The array that gets filled of successors
	 */
	private static void arrowmaker(char[][] arr, Location l,
			ArrayList<char[][]> thar) {
		int[][] th = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				th[i][j] = 100;
			}
		}
		th[l.x][l.y] = 0;
		intervalSpread(th, arr, l, false);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (th[i][j] == 1) {
					char[][] temp = copy(arr);
					temp[i][j] = 'A';
					thar.add(temp);
				}
			}
		}
	}

	/**
	 * Helper function that moves the amazon its legal moves then calls
	 * arrowmaker which shoots arrows and adds successors
	 * 
	 * @param mast
	 *            - the arraylist of board positions
	 * @param arr
	 *            -the current board
	 * @param l
	 *            -the location of the Amazon
	 */
	private static void moveMaker(ArrayList<char[][]> mast, char[][] arr,
			Location l) {
		int[][] th = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				th[i][j] = 100;
			}
		}
		th[l.x][l.y] = 0;
		intervalSpread(th, arr, l, false);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (th[i][j] == 1) {
					char[][] temp = copy(arr);
					temp[i][j] = arr[l.x][l.y];
					temp[l.x][l.y] = 'S';
					arrowmaker(temp, new Location(i, j), mast);
				}
			}
		}
	}
/**
 * 
 * @param cur- the current board position
 * @param turn - B for blacks turn W for whites turn
 * @return
 * 	an ArrayList containing board positions
 */
	public static ArrayList<char[][]> success(char[][] cur, char turn) {
		ArrayList<char[][]> mast = new ArrayList<char[][]>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (cur[i][j] == turn) {
					moveMaker(mast, cur, new Location(i, j));
				}
			}
		}
		return mast;
	}
	/**
	 * I dont trust built in functions
	 * @param in - character array
	 * @return
	 * a copy of a character array
	 */
	private static char[][] copy(char[][] in) {
		char[][] ret = new char[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ret[i][j] = in[i][j];
			}
		}
		return ret;
	}

	/**
	 * B is black W is white A is arrow S is space
	 * 
	 * @param arr
	 *            array holding game position
	 * @param player
	 *            the player ('W' or 'B')
	 * @return an array holding the distance to a square for a specified player
	 */
	public static int[][] distanceTo(char[][] arr, char player) {
		int[][] ret = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ret[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (arr[i][j] == player) {
					ret[i][j] = 0;
					intervalSpread(ret, arr, new Location(i, j), true);
				}
			}
		}
		return ret;
	}
	public static int squaresOwned(char[][] arr,char player){
		int[][] ours=distanceTo(arr,player);
		int[][] theirs=(distanceTo(arr,player=='W'?'B':'W'));
		int score=0;
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				if(ours[i][j]!=Integer.MAX_VALUE || theirs[i][j]!=Integer.MAX_VALUE){
					score += ours[i][j]<=theirs[i][j]?1:-1;
				}
			}
		}
		return score;
	}

	public static void main(String[] args) {
		char arr[][] = { { 'S', 'S', 'S', 'S', 'S', 'A', 'S', 'S', 'S', 'S' },
				{ 'S', 'S', 'S', 'A', 'S', 'A', 'S', 'S', 'S', 'S' },
				{ 'S', 'S', 'S', 'S', 'A', 'A', 'S', 'S', 'B', 'S' },
				{ 'W', 'S', 'A', 'W', 'A', 'A', 'A', 'S', 'A', 'S' },
				{ 'S', 'S', 'A', 'B', 'A', 'A', 'S', 'A', 'A', 'S' },
				{ 'S', 'A', 'A', 'A', 'A', 'S', 'A', 'S', 'S', 'S' },
				{ 'S', 'S', 'S', 'S', 'A', 'S', 'S', 'A', 'S', 'A' },
				{ 'A', 'A', 'A', 'A', 'A', 'A', 'S', 'S', 'S', 'S' },
				{ 'A', 'A', 'A', 'W', 'A', 'A', 'B', 'A', 'A', 'S' },
				{ 'A', 'S', 'B', 'A', 'S', 'A', 'A', 'S', 'S', 'W' }, };
		System.out.println(squaresOwned(arr,'W'));
		int[][] arrr=distanceTo(arr,'W');
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.printf("%2d ",arrr[i][j]==Integer.MAX_VALUE?-1:arrr[i][j]);
			}
			System.out.println();
		}
		System.out.println("---------------------------------");
		arrr=distanceTo(arr,'B');
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.printf("%2d ",arrr[i][j]==Integer.MAX_VALUE?-1:arrr[i][j]);
			}
			System.out.println();
		}
	}
}
