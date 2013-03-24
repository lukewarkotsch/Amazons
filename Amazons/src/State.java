package src;
import java.util.ArrayList;

public class State{
	private char[][] board; // board at this state
	private char player; // who moved to make this board
	private State parent; // what state came before this one
	private int value; // heuristic value at this state
	private ArrayList<State> children; // list of possible states after this one
	
	public State(){
		this.board = new char[10][10];
		this.children = new ArrayList<State>();
	}

	public void setParent(State parent) {
		this.parent = parent;
	}
	
	public State getParent() {
		return parent;
	}
	
	// add a child to this states children
	public void addChild(State state){
		this.children.add(state);
	}

	public ArrayList<State> getChildren() {
		return children;
	}

	public void setPlayer(char player) {
		this.player = player;
	}
	
	public char getPlayer() {
		return player;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
	
	// Initialize this states board to the game starting state
	public void initBoard(){
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				board[i][j] = 'S';
			}
		}
		board[3][0] = board[0][3] = board[0][6] = board[3][9] = 'B';
		board[6][0] = board[9][3] = board[9][6] = board[6][9] = 'W';
	}
	
	public void printBoard(){
		System.out.print("Current gameboard state: \n");
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(board[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}
}
