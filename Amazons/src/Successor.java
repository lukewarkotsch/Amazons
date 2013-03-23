import java.util.ArrayList;
import java.util.Iterator;

public class Successor {
	final private static char white = 'W';
	final private static char black = 'B';
	private State root;
	private ArrayList<State> successors;

	public Successor(char player){
		root = new State(player);
		root.initBoard();
		successors = new ArrayList<State>();
		successors.add(root);
	}

	public ArrayList<State> getSuccessors() {
		return successors;
	}

	public void setSuccessors(ArrayList<State> successors) {
		this.successors = successors;
	}

	public void findSuccessors(){
		for (State node : successors){
			char next;
			if(node.getPlayer()==white)
				next = black;
			else
				next = white;
			ArrayList<char[][]> childBoards = Dist.success(node.getBoard(), next);
			for (char[][] childBoard : childBoards) {
				State child = new State(next);
				child.setBoard(childBoard);
				child.setParent(node);
				node.addChild(child);
				successors.add(child);
				child.printBoard();
			}
		}
	}

	public void printList(){
		Iterator<State> it = successors.iterator();
		while(it.hasNext()){
			it.next().printBoard();
		}
	}
}
