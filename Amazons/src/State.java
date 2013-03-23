import java.util.ArrayList;


	/**
	 *  board has been renamed to locations
	 *  It now stores 3 values: starting position of a piece, location it moved to,
	 *  and location arrow was fired
	 *  STARTING	[x][y]
	 *  NEW			[x][y]
	 *  ARROW		[x][y]
	 * */

public class State{
	private char[][] locations;
	private char player;
	private State parent;
	private int value = Integer.MAX_VALUE;
	private ArrayList<State> children;
	
	public State(char player){
		this.player = player;
		this.locations = new char[3][3];
		this.children = new ArrayList<State>();
	}
	
	public State(char[][] loc){
		this.locations = loc;
		this.children = new ArrayList<State>();
	}
	
	public void setLoc(char[][] loc){
		this.locations = loc;
	}
	
	public char[][] getLoc(){
		return locations;
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
