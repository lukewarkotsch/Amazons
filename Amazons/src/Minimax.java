package src;
import java.util.ArrayList;
import java.util.Iterator;

public class Minimax {

	// CHANGE MAX DEPTH AND TIME TO MODIFY CUTOFF FUNCTION
	private final static int MAX_DEPTH = 2; 
	private final static int MAX_TIME = 28000;
	// ---------------------------------------------------
	private static int depth = 0; // current depth
	private static long time;
	
	// start timer to track cut off
	public static void startTimer(){
		time = System.currentTimeMillis();
	}
	
	// used when next turn begins
	public static void resetDepth(){
		depth = 0;
	}
	
	// Function used to determine if more nodes should be expanded
	private static boolean cutoff(){
		if(depth>=MAX_DEPTH || System.currentTimeMillis()-time>MAX_TIME)
			return true;
		else return false;
	}

	public static State miniMax(State s, char MIN, char MAX){
		if(cutoff()){
			depth--;
			return s;
		}
		depth++;
		System.out.println("Running MiniMax");
		Successor.findSuccessors(s);
		if(s.getChildren().size()==0)
			return s;
		else if(s.getPlayer()==MIN)
			return max(s, MIN, MAX);
		else if(s.getPlayer()==MAX)
			return min(s, MIN, MAX);
		else return s;
	}

	private static State max(State s, char MIN, char MAX){
		ArrayList<State> list = s.getChildren();
		System.out.println("Running Max");
		Iterator<State> it = list.iterator();
		State maxState = new State();
		maxState.setValue(Integer.MIN_VALUE);
		State tempState = null;
		while (it.hasNext()){
			tempState = it.next();
			if(!cutoff()){
				Successor.findSuccessors(tempState);
			}
			if(tempState.getChildren().size()==0){
				if(tempState.getValue()>maxState.getValue())
					maxState = tempState;
			} else
				maxState = miniMax(tempState, MIN, MAX);
		}
		depth--;
		return maxState;
	}

	private static State min(State s, char MIN, char MAX){
		ArrayList<State> list = s.getChildren();
		System.out.println("Running Min");
		Iterator<State> it = list.iterator();
		State minState = new State();
		minState.setValue(Integer.MAX_VALUE);
		State tempState = null;
		while (it.hasNext()){
			tempState = it.next();
			if(!cutoff()){
				Successor.findSuccessors(tempState);
			}
			if(tempState.getChildren().size()==0){
				if(tempState.getValue()<minState.getValue())
					minState = tempState;
			}
			else
				minState = miniMax(tempState, MIN, MAX);
		}
		depth--;
		return minState;
	}
}
