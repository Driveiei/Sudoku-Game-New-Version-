package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Table class contains necessary methods which help RandomNumber class makes
 * correctly order of number and add the number to the puzzle.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class Table {
	/** List of puzzle in sudoku which contains many grids(9 or 16 grids). */
	private List<GridManager> sudoku;
	/** a number of box in one grid. */
	private int size;
	/** a number to make each box unique */
	private int code = 1;

	/**
	 * Initialize the size of Table(a number of box in one grid).
	 * 
	 * @param size - a number of box in one grid.
	 */
	public Table(int size) {
		this.size = size;
		sudoku = new ArrayList<GridManager>();
	}

	/**
	 * Get the list of number in any grid which has set of number on the left of
	 * this numberGrid.
	 * 
	 * @param numberGrid - a number of grid(0-8 or 0-15).
	 * @param row - a number of row in puzzle(0-8 or 0-15).
	 * @return list of number which is on the left of this grid.
	 */
	public List<Integer> duplicateRow(int numberGrid, int row) {
		int position = numberGrid % size;
		int rowGrid = row / size;
		int rowBox = row % size;
		List<Integer> check = new ArrayList<Integer>();
		if (position == 0)
			return check;
		for (int times = 0; times < position; times++) {
			for (int box = 0; box <= (size - 1); box++) {
				check.add(sudoku.get(size * rowGrid + times).getList().get(size * rowBox + box).getNumber());
			}
		}
		return check;
	}

	/**
	 * Get the list of number in any grid which has set of number above this
	 * numberGrid.
	 * 
	 * @param numberGrid - a number of grid(0-8 or 0-15).
	 * @param column - a number of column in puzzle(0-8 or 0-15).
	 * @return list of number which above this grid.
	 */
	public List<Integer> duplicateColumn(int numberGrid, int column) {
		int position = numberGrid / size;
		int columnGrid = column / size;
		int columnBox = column % size;
		List<Integer> check = new ArrayList<Integer>();
		if (position == 0)
			return check;
		for (int times = 0; times < position; times++) {
			for (int box = 0; box <= (size - 1); box++) {
				check.add(sudoku.get(columnGrid + (size * times)).getList().get(size * box + columnBox).getNumber());
			}
		}
		return check;
	}

	/**
	 *  Add a target number to the numberGrid of puzzle.
	 * 
	 * @param numberGrid - a number of grid(0-8 or 0-15).
	 * @param target - a number to add into this grid.
	 * */
	public void insert(int numberGrid, int target) {
		sudoku.get(numberGrid).getList().add(new BoxManager(target, false, false, code++));
	}

	/**
	 * Clear all numbers in grid.
	 * 
	 * @param numberGrid - a number of grid(0-8 or 0-15).
	 * */
	public void clear(int numberGrid) {
		sudoku.get(numberGrid).getList().clear();
	}

	/**
	 * Get size a number of box in one grid. 
	 * 
	 * @return a number of box in one grid. 
	 * */
	public int getSize() {
		return size;
	}

	/**
	 * Get list of puzzle in sudoku which contains many grids(9 or 16 grids). 
	 * 
	 * @return a list of puzzle in sudoku which contains many grids(9 or 16 grids). 
	 * */
	public List<GridManager> getList() {
		return sudoku;
	}
	
	/**
	 * Get a number of code. 
	 * 
	 * @return a number of code. 
	 * */
	public int getCode() {
		return code;
	}
}
