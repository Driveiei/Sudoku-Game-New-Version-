package strategy;

import java.util.ArrayList;
import java.util.List;

import logic.GridManager;
import logic.RandomNumber;

/**
 * GreaterThanStrategy keeps a puzzle from RandomNumber class.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class GreaterThanStrategy extends Mode {
	/** List of puzzle to keep */
	private List<GridManager> list;
	/** Declare RandomNumber class to generate a puzzle. */
	private RandomNumber random;
	/** A number of box in one grid. */
	private int size;
	/** A display size of each box. */
	private int base;

	/**
	 * Initialize necessary attributes.
	 */
	public GreaterThanStrategy() {
		random = RandomNumber.getInstance();
		this.base = 80;
		size = random.getSize();
		list = new ArrayList<>();		
	}

	/**
	 * Get a number of size in one grid.
	 * 
	 * @return a number of size in one grid.
	 */
	@Override
	public int getSize() {
		return size;
	}
	
	/** Set a set of number to makes a puzzle and keep into this class. */
	public void setPuzzle() {
		list.addAll(random.getPuzzle());
		randomInvisible();
	}
	
	/** Set invisible value of a number to display on the grid or not */
	@Override
	public void randomInvisible() {
		
	}

	/**
	 * Get a puzzle of sudoku.
	 * 
	 * @return a list puzzle of sudoku.
	 */
	@Override
	public List<GridManager> getPuzzle() {
		return list;
	}

	/**
	 * Get a display size of each box.
	 * 
	 * @return a display size of each box.
	 */
	@Override
	public int getBase() {
		return base;
	}

	/** Clear a puzzle of sudoku. */
	@Override
	public void clearPuzzle() {
		list.clear();
	}

}
