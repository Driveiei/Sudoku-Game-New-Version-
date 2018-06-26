package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RandomNumber is a singleton class that can generate set of number with not
 * duplicate number in each grid, column and row with a fix size which is 3x3 or
 * 4x4 grid. RandomNumber can merge the duplicate list which helps generate set
 * of number, and many methods that help the process easier.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class RandomNumber {

	/**
	 * The instance arbitrates access to the resource and storage-related state
	 * information.
	 */
	private static RandomNumber random = null;

	/** Declare Table class */
	private static Table table;

	/** Declare random process */
	private Random rand;

	/**
	 * To initialize the random.
	 */
	private RandomNumber() {
		rand = new Random();
	}

	/**
	 * Get the RandomNumber's instance.
	 * 
	 * @return the RandomNumber's instance.
	 */
	public static RandomNumber getInstance() {
		if (random == null) {
			table = new Table(3);
			random = new RandomNumber();
		}
		return random;
	}

	/**
	 * Change table's size.
	 * 
	 * @param number - a number to set of table's size.
	 */
	public static void setRandomNumber(int number) {
		table = new Table(number);
		random = new RandomNumber();
	}

	/**
	 * Get the table's size.
	 * 
	 * @return table's size.
	 */
	public int getSize() {
		return table.getSize();
	}

	/**
	 * Get a list of sudoku puzzle which already generated with fix number.
	 * 
	 * @return list of sudoku puzzle which already generated with fix number.
	 */
	public List<GridManager> getPuzzle() {
		int size = (table.getSize() * table.getSize());
		for (int grid = 0; grid < size; grid++) {
			table.getList().add(new GridManager());
			createRandomSet(createNumberSet(size), grid, size);
		}
		return table.getList();
	}

	/**
	 * Merge two lists(on the top and left of this numberGrid) which contain
	 * duplicate number in the list to wipe it out.
	 * 
	 * @param numberGrid - a number of grid(0-8 or 0-15).
	 * @param column - a number of column in puzzle(0-8 or 0-15).
	 * @param row - a number of row in puzzle(0-8 or 0-15).
	 * @return list of number which contains a unique number.
	 */
	public List<Integer> mergeDuplicateList(int numberGrid, int column, int row) {
		List<Integer> merge = new ArrayList<Integer>();
		merge.addAll(table.duplicateColumn(numberGrid, column));
		List<Integer> secondList = new ArrayList<Integer>();
		secondList.addAll(table.duplicateRow(numberGrid, row));
		for (Integer number : secondList) {
			if (!merge.contains(number))
				merge.add(number);
		}
		return merge;
	}

	/**
	 * Generate random set of number(1-9 or 1-16) for each grid and undo generate set
	 * of number of this grid and forward grid if this grid cann't generate number
	 * directly.
	 * 
	 * @param setofNumber - set of number(1-9 or 1-16) to put into each box.
	 * @param numberGrid - a number of grid(0-8 or 0-15).
	 * @param size - a number of box in one column or row.
	 */
	public void createRandomSet(List<Integer> setOfNumber, int numberGrid, int size) {
		int realSize = table.getSize();// 3
		int column;
		int row;
		List<Integer> duplicateSet = new ArrayList<Integer>();
		List<Integer> collecting = new ArrayList<Integer>();
		for (int box = 0; box < size; box++) {

			column = identifyColumn(realSize, numberGrid, box);
			row = identifyRow(realSize, numberGrid, box);
			duplicateSet.addAll(mergeDuplicateList(numberGrid, column, row));

			for (Integer out : duplicateSet) {
				if (setOfNumber.contains(out)) {
					setOfNumber.remove(setOfNumber.indexOf(out));
					collecting.add(out);
				}
			}

			try {
				int target = randomNumber(setOfNumber);
				table.insert(numberGrid, target);
				setOfNumber.remove(setOfNumber.indexOf(target));
				setOfNumber.addAll(collecting);
			} catch (IllegalArgumentException ex) {
				box = undoCreateGrid(numberGrid, size, realSize);
				table.clear(numberGrid);
				setOfNumber.clear();
				setOfNumber.addAll(createNumberSet(size));
			}
			collecting.clear();
			duplicateSet.clear();
		}
	}

	/**
	 * Identify a number of row by grid's number, size's number, and box's number.
	 * 
	 * @return a number of row.
	 * */
	public int identifyRow(int realSize, int numberGrid, int box) {
		return realSize * (numberGrid / realSize) + box / realSize;
	}

	/**
	 * Identify a number of column by grid's number, size's number, and box's number.
	 * 
	 * @return a number of column.
	 * */
	public int identifyColumn(int realSize, int numberGrid, int box) {
		return realSize * (numberGrid % realSize) + box % realSize;
	}

	/**
	 * Random a number in the list.
	 * 
	 * @param setOfNumber - a set of number which contains number that can put into the box.
	 * @return a number which generates by random in setOfNumber.
	 * */
	public int randomNumber(List<Integer> setOfNumber) {
		int cursor = rand.nextInt(setOfNumber.size());
		int target = setOfNumber.get(cursor);
		return target;
	}

	/**
	 * Undo the process of generating a set of number(1-9 or 1-16) to makes new order of number.
	 * 
	 * @param numberGrid - a number of grid(0-8 or 0-15).
	 * @param size - a number of box in one column or row(9 or 16).
	 * @param realSize - a number of box in one grid(3 or 4). 
	 * @return a number to redo generating number of a grid.
	 * */
	public int undoCreateGrid(int numberGrid, int size, int realSize) {
		int remainder = numberGrid % realSize;
		for (int i = 0; i < remainder; i++) {
			table.clear(numberGrid - remainder + i);
			createRandomSet(createNumberSet(size), numberGrid - remainder + i, size);
		}
		return -1;
	}

	/**
	 * Create set of number that starts from 1 to size.
	 * 
	 * @param size - the target of number to create from one to any number.
	 * @return list of number that starts from 1 to size.
	 * */
	public List<Integer> createNumberSet(int size) {
		if (size <= 0)
			return null;
		List<Integer> number = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++) {
			number.add(i);
		}
		return number;
	}

	/**
	 * Get the Table(for test code).
	 * 
	 * @return the Table class. 
	 */
	public Table getTable() {
		return table;
	}
}
