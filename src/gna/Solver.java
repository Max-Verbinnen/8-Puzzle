package gna;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import libpract.PriorityFunc;

public class Solver {
	
	private Tuple solution;
	
	public class Tuple implements Comparable<Tuple> {
		public final Board board;
		public final int moves;
		public final Tuple previous;
		public final int priority;
		
		public Tuple(Board board, int moves, Tuple previous, int priority) {
			this.board = board;
			this.moves = moves;
			this.previous = previous;
			this.priority = priority;
		}
		
		@Override
		public int compareTo(Tuple tup) {
			return this.priority - tup.priority;
		}
	}
	
	/**
	 * Finds a solution to the initial board.
	 *
	 * @param priority is either PriorityFunc.HAMMING or PriorityFunc.MANHATTAN
	 */
	public Solver(Board initial, PriorityFunc priority) {
		
		PriorityQueue<Tuple> PQ = new PriorityQueue<Tuple>();
		PQ.add(new Tuple(initial, 0, null, 0));
		
		while (!PQ.isEmpty()) {
			Tuple min = PQ.poll();
			
			// If minimum element represents final board state, we're done!
			if (min.board.isGoal()) {
				this.solution = min;
				break;
			}
			
			// Add neighbors to PQ
			for (Board neighbor: min.board.neighbors()) {
				int prio = (priority == PriorityFunc.HAMMING) ? min.board.hamming() : min.board.manhattan();
				PQ.add(new Tuple(neighbor, min.moves + 1, min, prio + min.moves + 1));
			}
		}
	}
	

	/**
	 * Returns a List of board positions as the solution.
	 * It contains the initial board as well as the solution.
	 */
	public List<Board> solution() {
		List<Board> steps = new ArrayList<Board>();
		Tuple current = solution;
		
		while (current != null) {
			steps.add(current.board);
			current = current.previous;
		}
		
		Collections.reverse(steps);
		return steps;
	}

}