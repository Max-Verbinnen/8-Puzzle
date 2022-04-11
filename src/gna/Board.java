package gna;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {

	private int[][] tiles;

	public Board(int[][] tiles) {
		// Make deep copy
		this.tiles = Arrays.stream(tiles).map(el -> el.clone()).toArray($ -> tiles.clone());
	}
	
	// Returns number of blocks that are out of place
	public int hamming() {
		int amount = 0;
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				int tile = tiles[i][j];
				if (tile != i * tiles.length + j + 1 && tile != 0) amount++;
			}
		}
		
		return amount;
	}
	
	// Returns sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int amount = 0;
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				int tile = tiles[i][j];
				int goalI = (tile - 1) / tiles.length;
				int goalJ = (tile - 1) % tiles.length;
				if (tiles[i][j] != 0) amount += Math.abs(i - goalI) + Math.abs(j - goalJ);
			}
		}
		
		return amount;
	}
	
	// Two boards are equal when they both were constructed using tiles[][] arrays that contained the same values
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Board)) return false;
		return Arrays.deepEquals(tiles, ((Board)other).tiles);
	}

	// Since we override equals(), we must also override hashCode()
    @Override
    public int hashCode() {
		return Arrays.deepHashCode(tiles);
	}
    
    // Returns whether board is in goal state
    public boolean isGoal() {
    	for (int i = 0; i < tiles.length; i++) {
    		for (int j = 0; j < tiles.length; j++) {
    			if ((i != tiles.length - 1 || j != tiles.length - 1) && tiles[i][j] != i * tiles.length + j + 1) return false;
    		}
    	}
    	
    	return tiles[tiles.length - 1][tiles.length - 1] == 0;
    }
	
	// Returns a Collection of all neighboring board positions
	public Collection<Board> neighbors() {
		List<Board> boards = new ArrayList<Board>();
		
		// Find empty space
		int[] indices = findElement(0, tiles);
		int i = indices[0];
		int j = indices[1];

		boards.add(swap(i, j, i + 1, j));
		boards.add(swap(i, j, i, j + 1));
		boards.add(swap(i, j, i - 1, j));
		boards.add(swap(i, j, i, j - 1));
		
		return boards.stream().filter(e -> e != null).toList();
	}
	
	// Returns string representation of board
	public String toString() {
		return Arrays.deepToString(tiles);
	}

	// Returns whether board is solvable
	public boolean isSolvable() {
		// Move empty tile to end by pushing to right & bottom as far as possible
		int[][] swappedTiles = swapToLast(0);
		
		// Implement formula for determining whether board is solvable
		float quotient = 1;
		for (int i = 1; i < tiles.length * tiles.length; i++) {
			for (int j = i + 1; j < tiles.length * tiles.length; j++) {
				float numerator = (position(j, swappedTiles) - position(i, swappedTiles));
				float denominator = (j - i);
				quotient *= (numerator / denominator);
			}
		}
		
		return quotient >= 0;
	}
	
	// Position in linearised matrix starting with 1
	private int position(int element, int[][] tiles) {
		int[] indices = findElement(element, tiles);
		return indices[0] * tiles.length + indices[1] + 1;
	}
	
	// Returns position [i, j] of element in tiles
	private int[] findElement(int element, int[][] tiles) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if (tiles[i][j] == element) return new int[] {i, j};
			}
		}
		
		throw new IllegalArgumentException();
	}
	
	// Returns a new board with elements on given indices swapped
	private Board swap(int x, int y, int i, int j) {
		if (i < 0 || i >= tiles.length || j < 0 || j >= tiles.length) return null;
		
		Board board = new Board(this.tiles);
		int temp = board.tiles[x][y];
		board.tiles[x][y] = board.tiles[i][j];
		board.tiles[i][j] = temp;
		
		return board;
	}
	
	// Swap given element to bottom right
	private int[][] swapToLast(int element) {
		int[][] tiles = Arrays.stream(this.tiles).map(el -> el.clone()).toArray($ -> this.tiles.clone());
		int[] indices = findElement(element, tiles);
		int i = indices[0];
		int j = indices[1];
		
		if (i != tiles.length - 1 || j != tiles.length - 1) {
			// Swap with elements to the right
			for (int z = j + 1; z < tiles.length; z++, j++) {
				int temp = tiles[i][j];
				tiles[i][j] = tiles[i][z];
				tiles[i][z] = temp;
			}
			
			// Swap with elements beneath
			for (int z = i + 1; z < tiles.length; z++, i++) {
				int temp = tiles[i][j];
				tiles[i][j] = tiles[z][j];
				tiles[z][j] = temp;
			}
		}
		
		return tiles;
	}
	
}

