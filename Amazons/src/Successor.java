import java.util.ArrayList;
import java.util.Iterator;

public class Successor {
	final private static char white = 'W';
	final private static char black = 'B';
	private State root;
	private ArrayList<State> successors;

	public Successor(char player){
		root = new State(player);

		//Set initial location to all zeroes
		char[][] rootNode = null;
		rootNode[0][0] = 0; rootNode[1][0] = 0; rootNode[2][0] = 0; 
		rootNode[0][1] = 0; rootNode[1][1] = 0; rootNode[2][1] = 0; 

		root.setLoc(rootNode);
		successors = new ArrayList<State>();
		successors.add(root);
	}

	public ArrayList<State> getSuccessors() {
		return successors;
	}

	public void setSuccessors(ArrayList<State> successors) {
		this.successors = successors;
	}

	public void findSuccessors(char[][] inputBoard){
		for (State node : successors){
			char next;
			if(node.getPlayer() == white)
				next = black;
			else
				next = white;
			
			ArrayList<char[][]> childBoards = Dist.success(inputBoard, next);
			for (char[][] childBoard : childBoards) {
				State child = new State(next); //new child
				child.setLoc(extractLocs(inputBoard, childBoard, next)); //extract locations
				Dist.squaresOwned(childBoard, next); //set value
				child.setParent(node); //set parent
				node.addChild(child); //set child
				successors.add(child); //add to successors
				//child.printBoard(); //no longer functioning
			}
			childBoards = null;
		}
	}
	
	public char[][] extractLocs(char[][] oldBoard, char[][] newBoard, char player){
		//initialise loc; set all locations to (11,11)
		char[][] loc = null;
		loc[0][0] = 11; loc[1][0] = 11; loc[2][0] = 11;
		loc[0][1] = 11; loc[1][1] = 11; loc[2][1] = 11;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(oldBoard[i][j]==player && newBoard[i][j]=='S'){ //starting location check
					loc[0][0] = (char) i;
					loc[0][1] = (char) j;
				}
				if(oldBoard[i][j]=='S' && newBoard[i][j]==player){ //new location check
					loc[1][0] = (char) i;
					loc[1][1] = (char) j;
				}
				if(oldBoard[i][j]=='S' && newBoard[i][j]=='A'){ //arrow location check
					loc[2][0] = (char) i;
					loc[2][1] = (char) j; 
				}
				//If the X positions for all three locations are no longer 11,
				//then all three have been found and there is no reason to continue the loops
				if(loc[0][0] != 11 && loc[1][0] != 11 && loc[2][0] != 11){
					return loc;
				}
			}
		}
		return loc;
	}

	public void initBoard(char[][] board){
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				board[i][j] = 'S';
			}
		}
		board[3][0] = board[0][3] = board[0][6] = board[3][9] = 'B';
		board[6][0] = board[9][3] = board[9][6] = board[6][9] = 'W';
		printBoard();
	}
	
	public void printList(){
		Iterator<State> it = successors.iterator();
		while(it.hasNext()){
//			it.next().printBoard();
		}
	}
	
	public void printBoard(){
		System.out.print("Current gameboard state: \n");
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
//				System.out.print(this.board[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}
}