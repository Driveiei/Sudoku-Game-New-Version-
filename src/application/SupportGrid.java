package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.BoxManager;
import strategy.Mode;

/**
 * SupportGrid helps Grid genearate puzzle and manage logic easier.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class SupportGrid {

	/** Declare mode process */
	private Mode mode;
	/** Declare random process */
	private Random rand;

	/** a number of box in one grid. */
	private int size;
	/** a number of box in one column. */
	private int realSize;

	/**
	 * Initialize the size (a number of box in one grid) and get mode instance.
	 * 
	 * @param size - a number of box in one grid.
	 */
	public SupportGrid(int size) {
		rand = new Random();
		this.size = size;
		realSize = size * size;
		mode = Mode.getInstance();
	}

	/**
	 * Random a number from one to target number.
	 * 
	 * @param target - scope's number to random number between zero to target number.
	 * @return number between zero to target number.
	 */
	public int randomCursor(int target) {
		return rand.nextInt(target);
	}

	/**
	 * Change scale from array(column and row) to list's scale(grid and box) to use
	 * in grid identify position.
	 * 
	 * @param column - the cursor position of puzzle in vertical box.
	 * @param row - the cursor position of puzzle in horizontal box.
	 * @return number of grid identify.
	 */
	public int adaptGrid(int column, int row) {
		return (row / size) * size + column / size;
	}

	/**
	 * Change scale from array(column and row) to list scale(grid and box) to use in
	 * box identify position.
	 * 
	 * @param column - the cursor position of puzzle in vertical box.
	 * @param row - the cursor position of puzzle in horizontal box.
	 * @return number of box in grid identify.
	 */
	public int adaptBox(int column, int row) {
		return size * (row % size) + column % size;
	}

	/**
	 * Change scale from grid and box to array scale(column and row) to use in
	 * column identify position.
	 * 
	 * @param grid - cursor number of grid(0-8 or 0-15).
	 * @param box - cursor number of box(0-8 or 0-15).
	 * @return number of column identify.
	 */
	public int scaleToArrayColumn(int grid, int box) {
		return box % size + (grid % size) * size;
	}

	/**
	 * Change scale from grid and box to array scale(column and row) to use in row
	 * identify position.
	 * 
	 * @param grid - cursor number of grid(0-8 or 0-15).
	 * @param box - cursor number of box(0-8 or 0-15).
	 * @return number of row identify.
	 */
	public int scaleToArrayRow(int grid, int box) {
		return box / size + (grid / size) * size ;
	}

	/**
	 * Get the puzzle that user can put the number onto the box(blank box).
	 * 
	 * @return list of box that filter only the box which user can input the number
	 *         to it.
	 */
	public List<BoxManager> getSpaceBoxList() {
		List<BoxManager> puzzle = new ArrayList<BoxManager>();
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {
				int grid = adaptGrid(selectBox, selectGrid);
				int box = adaptBox(selectBox, selectGrid);
				BoxManager oneBox = mode.getPuzzle().get(grid).getList().get(box);
				if (!oneBox.getLock()) {
					puzzle.add(oneBox);
				}
			}
		}
		return puzzle;
	}
}