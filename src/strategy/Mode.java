package strategy;

import java.util.List;

import logic.GridManager;

/**
 * Mode contains of necessary method with easy, hard, and greaterThan strategy.
 * Mode define which strategy using to create numbers and mode of each game.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public abstract class Mode {

	/**
	 * The instance arbitrates access to the resource and storage-related state
	 * information.
	 */
	private static Mode mode = null;

	/**Set invisible value of a number to display on the grid or not*/
	public abstract void randomInvisible();

	/**
	 * Get the mode's instance.
	 * 
	 * @return the mode's instance.
	 */
	public static Mode getInstance() {
		if (mode == null)
			mode = new EasyStrategy();
		return mode;
	}

	/**
	 * Change the game's mode.
	 * 
	 * @param strategy - game's mode.
 	 */
	public static void setMode(Mode strategy) {
		mode = strategy;
	}

	/**
	 * Get a number of size in one grid.
	 * @return a number of size in one grid.
	 * */
	public abstract int getSize();

	/**Set a set of number to makes a puzzle.*/
	public abstract void setPuzzle();

	/**
	 * Get a puzzle of sudoku.
	 * @return a list puzzle of sudoku.
	 * */
	public abstract List<GridManager> getPuzzle();

	/**
	 * Get a display size of each box.
	 * @return a display size of each box.
	 * */
	public abstract int getBase();

	/**Clear a puzzle of sudoku.*/
	public abstract void clearPuzzle();

}
