package gna;

import java.util.Collection;
import java.util.Arrays;

public class Board
{
	private int[][] tiles;

	// construct a board from an N-by-N array of tiles
	public Board( int[][] tiles )
	{
		// TODO (make a deep copy of tiles and store it into this.tiles)
		throw new RuntimeException("not implemented");
	}
	
	// return number of blocks out of place
	public int hamming()
	{
		throw new RuntimeException("not implemented"); // TODO
	}
	
	// return sum of Manhattan distances between blocks and goal
	public int manhattan()
	{
		throw new RuntimeException("not implemented"); // TODO
	}
	
	// Does this board equal y. Two boards are equal when they both were constructed
	// using tiles[][] arrays that contained the same values.
	@Override
	public boolean equals(Object y)
	{
		if ( !(y instanceof Board) )
			return false;

		Board other = (Board)y;
		return Arrays.deepEquals(tiles, other.tiles);
	}

	// Since we override equals(), we must also override hashCode(). When two objects are
	// equal according to equals() they must return the same hashCode. More info:
	// - http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java/27609#27609
	// - http://www.ibm.com/developerworks/library/j-jtp05273/
    @Override
    public int hashCode()
	{
		return Arrays.deepHashCode(tiles);
	}
	
	// return a Collection of all neighboring board positions
	public Collection<Board> neighbors()
	{
		throw new RuntimeException("not implemented"); // TODO
	}
	
	// return a string representation of the board
	public String toString()
	{
		return "<empty>"; // TODO
	}

	// is the initial board solvable? Note that the empty tile must
	// first be moved to its correct position.
	public boolean isSolvable()
	{
		throw new RuntimeException("not implemented"); // TODO
	}
}

