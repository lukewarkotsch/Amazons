package src;
public class Player {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		long timer = System.currentTimeMillis();

		State root = new State(); // current state of game (should change turn by turn
		/*
		char lateBoard[][] = { 
				{ 'S', 'S', 'S', 'S', 'S', 'A', 'A', 'S', 'S', 'S' },
				{ 'S', 'S', 'S', 'S', 'S', 'W', 'B', 'S', 'S', 'S' },
				{ 'W', 'A', 'B', 'A', 'A', 'A', 'S', 'A', 'A', 'S' },
				{ 'A', 'A', 'A', 'B', 'S', 'S', 'A', 'S', 'S', 'S' },
				{ 'S', 'A', 'A', 'A', 'S', 'A', 'A', 'A', 'A', 'A' },
				{ 'S', 'S', 'A', 'B', 'S', 'A', 'W', 'S', 'S', 'S' },
				{ 'S', 'S', 'A', 'A', 'A', 'A', 'A', 'S', 'A', 'S' },
				{ 'S', 'S', 'S', 'S', 'S', 'A', 'S', 'A', 'S', 'S' },
				{ 'S', 'S', 'S', 'A', 'S', 'S', 'W', 'S', 'S', 'S' },
				{ 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S' } };
		root.setBoard(lateBoard);
		*/
		
		root.initBoard(); // set up for beginning of game
		root.setPlayer('B'); // setting the player of this state to black means white goes next

		Minimax.startTimer(); // Begin timer to detect cutoff
		State s = Minimax.miniMax(root, 'B', 'W'); // returns state representing the best move to make
		int[][] moveToMake = Successor.extractLocs(root.getBoard(), s.getBoard(), s.getPlayer()); // The move we have chosen to make
		
		
		// -----------------------------------------------------
		// CODE BELOW HERE IS FOR ANALYTICS AND TESTING PURPOSES
		// -----------------------------------------------------
		System.out.println("\nTIME TAKEN: " + (System.currentTimeMillis()-timer));
		System.out.println("\nTHE BEST MOVE TO MAKE (H = " + s.getValue() + "):");
		
		Successor.printLocations(Successor.extractLocs(root.getBoard(), s.getBoard(), s.getPlayer()));
		s.printBoard();
		
		System.out.println("\nThe given board state distances for W:");
		int[][] dist = Dist.distanceTo(s.getBoard(), 'W');
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				if(dist[i][j]>100)
					System.out.print('-'+"\t");
				else
					System.out.print(dist[i][j]+"\t");
			}
			System.out.print("\n");
		}
		
		System.out.println("\nThe given board state distances for B:");
		dist = Dist.distanceTo(s.getBoard(), 'B');
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				if(dist[i][j]>100)
					System.out.print('-'+"\t");
				else
					System.out.print(dist[i][j]+"\t");
			}
			System.out.print("\n");
		}
		
		System.out.println("\nOwned by W: "+Dist.squaresOwned(s.getBoard(), 'W'));
		System.out.println("\nOwned by B: "+Dist.squaresOwned(s.getBoard(), 'B'));
		
		System.out.println("\nNODES EXPANDED: " + Successor.nodeCount());
	}
}
