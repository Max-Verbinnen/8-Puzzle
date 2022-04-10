package gna;

import java.util.Collection;
import java.util.List;

import libpract.PriorityFunc;

public class Solver
{
	/**
	 * Finds a solution to the initial board.
	 *
	 * @param priority is either PriorityFunc.HAMMING or PriorityFunc.MANHATTAN
	 */
	public Solver(Board initial, PriorityFunc priority)
	{
		// Use the given priority function (either PriorityFunc.HAMMING
		// or PriorityFunc.MANHATTAN) to solve the puzzle.
		if (priority == PriorityFunc.HAMMING) {
			// TODO
		} else if (priority == PriorityFunc.MANHATTAN) {
			// TODO
		} else {
			throw new IllegalArgumentException("Priority function not supported");
		}

		// TODO
	}
	

	/**
	 * Returns a List of board positions as the solution. It should contain the initial
	 * Board as well as the solution (if these are equal only one Board is returned).
	 */
	public List<Board> solution()
	{
		return null; // TODO
	}
}


