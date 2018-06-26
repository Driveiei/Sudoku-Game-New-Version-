package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.GridManager;
import logic.RandomNumber;

/**
 * HardStrategy keeps a puzzle from RandomNumber class and generate chance for
 * all numbers in a puzzle which are invisible or not.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class HardStrategy extends Mode {

	/** List of puzzle to keep */
	private List<GridManager> list;
	/** Declare RandomNumber class to generate a puzzle. */
	private RandomNumber random;
	/** Declare Random to process the status chance of each number. */
	private Random rand;
	/** A number of box in one grid. */
	private int size;
	/** A display size of each box. */
	private int base;
	/** A number of box in one column or row. */
	private int realSize;
	/** Chance of number which will be invisible or not. */
	private final int PERCENTAGE = 40;
	/** The maximum percentage */
	private final int ONE_HUNDRED = 100;

	/**
	 * Initialize necessary attributes.
	 */
	public HardStrategy() {
		random = RandomNumber.getInstance();
		this.base = calculateBase();
		size = random.getSize();
		realSize = size * size;
		list = new ArrayList<>();
		rand = new Random();
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

	/** Set a set of number to makes a puzzle and keep into this class.*/
	@Override
	public void setPuzzle() {
		list.addAll(random.getPuzzle());
		randomInvisible();
	}

	/** Set invisible value of a number to display on the grid or not */
	@Override
	public void randomInvisible() {
		for (int grid = 0; grid < realSize; grid++) {
			for (int box = 0; box < realSize; box++) {
				int percentage = rand.nextInt(ONE_HUNDRED);
				if (percentage <= PERCENTAGE) {
					list.get(grid).getList().get(box).setLock(true);
					list.get(grid).getList().get(box).setCheck(true);
				} else {
					list.get(grid).getList().get(box).setLock(false);
					list.get(grid).getList().get(box).setCheck(false);
				}
			}
		}
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
	
	/**
	 * Fix size of each box base on puzzle's size.
	 * 
	 * @return size of each box.
	 * */
	public int calculateBase() {
		if(random.getSize() == 3)
		return 80;
		else return 45;
	}
}
