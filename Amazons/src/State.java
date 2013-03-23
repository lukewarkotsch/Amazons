import java.util.ArrayList;


public class State{
	private char[][] board;
	private char player;
	private State parent;
	private int value = Integer.MAX_VALUE;
	private ArrayList<State> children;
	
	public State(char player){
		this.player = player;
		this.board = new char[10][10];
		this.children = new ArrayList<State>();
	}
	
	public State(char[][] board){
		this.board = board;
		this.children = new ArrayList<State>();
	}
	
	public void setBoard(char[][] board){
		this.board = board;
	}
	
	public void initBoard(){
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				board[i][j] = 'S';
			}
		}
		board[3][0] = board[0][3] = board[0][6] = board[3][9] = 'B';
		board[6][0] = board[9][3] = board[9][6] = board[6][9] = 'W';
		printBoard();
	}
	
	public char[][] getBoard(){
		return board;
	}

	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

	public ArrayList<State> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<State> children) {
		this.children = children;
	}
	
	public void addChild(State state){
		this.children.add(state);
	}
	
	public void printBoard(){
		System.out.print("Current gameboard state: \n");
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(this.board[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}

	public char getPlayer() {
		return player;
	}

	public void setPlayer(char player) {
		this.player = player;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
