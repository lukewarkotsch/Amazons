package src;
import java.util.ArrayList;

public class Successor {
	private static ArrayList<State> successors = new ArrayList<State>();

	public static int nodeCount(){
		// Used for analytics
		return successors.size();
	}
	
	public static void findSuccessors(State s){
		// Change player turn
		char next;
		if(s.getPlayer() == 'W')
			next = 'B';
		else
			next = 'W';

		// Build all children board of given state
		ArrayList<char[][]> childBoards = Dist.success(s.getBoard(), next);
		for (char[][] childBoard : childBoards) {
			State child = new State(); //new child
			child.setPlayer(next); // Who moved to create this board
			child.setBoard(childBoard); // set board
			child.setValue(Dist.squaresOwned(childBoard, next)); // find heuristic value for board
			child.setParent(s); //set parent
			s.addChild(child); //set child
			successors.add(child); //add to successors
		}
	}

	public static int[][] extractLocs(char[][] oldBoard, char[][] newBoard, char player){
		int[][] loc = new int[3][2];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(oldBoard[i][j]==player && newBoard[i][j]=='S'){ //starting location check
					loc[0][0] = i;
					loc[0][1] = j;
				}
				if(oldBoard[i][j]=='S' && newBoard[i][j]==player){ //new location check
					loc[1][0] = i;
					loc[1][1] = j;
				}
				if(oldBoard[i][j]=='S' && newBoard[i][j]=='A'){ //arrow location check
					loc[2][0] = i;
					loc[2][1] = j; 
				}
			}
		}
		return loc;
	}
	
	public static void printLocations(int[][] locs){
		System.out.println("\nLocations for current state:");
		System.out.println("FROM : "+locs[0][0]+", "+locs[0][1]);
		System.out.println("TO : "+locs[1][0]+", "+locs[1][1]);
		System.out.println("ARROW : "+locs[2][0]+", "+locs[2][1]);
	}

	// To be used when recreating a board from a set of locations
	public char[][] makeMove (char[][] board, int[][] locations, char player){
		board[locations[0][0]][locations[0][1]] = 'S';
		board[locations[1][0]][locations[1][1]] = player;
		board[locations[2][0]][locations[2][1]] = 'A';
		return board;
	}
}