package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import logic.BoxManager;
import logic.GridManager;
import logic.RandomNumber;
import logic.Table;

/**
 * JUnit 4 tests for RandomNumber using only RandomNumber's method and table's
 * method to test in anyway.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class RandomNumberTest {

	/** Declare RandomNumber class to generate a puzzle. */
	private RandomNumber random;
	/** Declare Table class to get size of board. */
	private Table table;

	/**
	 * Initialize a puzzle to makes test easier.
	 */
	@Before
	public void setUp() throws Exception {
		RandomNumber.setRandomNumber(3);
		random = RandomNumber.getInstance();
		table = random.getTable();
		for (int grid = 0; grid < 9; grid++) {
			table.getList().add(new GridManager());
		}
		for (int grid = 0; grid < 9; grid++) {
			for (int box = 0; box < 9; box++) {
				table.getList().get(grid).getList().add(new BoxManager(box, false,false, 50));
			}
		}
	}

	/**
	 * Test createRandomNumber method can create set of number that starts from one
	 * to any number or not.
	 */
	@Test
	public void testCreateRandomNumber() {
		List<Integer> test = new ArrayList<Integer>();
		assertNull(random.createNumberSet(0));
		assertNull(random.createNumberSet(-1));
		test.addAll(random.createNumberSet(10));
		assertEquals(10, test.size());
		assertFalse(test.contains(0));
		assertTrue(test.contains(1));
		assertTrue(test.contains(2));
		assertTrue(test.contains(3));
		assertTrue(test.contains(4));
		assertTrue(test.contains(5));
		assertTrue(test.contains(6));
		assertTrue(test.contains(7));
		assertTrue(test.contains(8));
		assertTrue(test.contains(9));
		assertTrue(test.contains(10));
		assertFalse(test.contains(11));
	}

	/**
	 * Test randomNumber method can random number in any list or not.
	 */
	@Test
	public void testRandomNumber() {
		List<Integer> setOfNumber = new ArrayList<Integer>();
		setOfNumber.add(5);
		setOfNumber.add(4);
		setOfNumber.add(6);
		setOfNumber.add(9);
		// Random many times for make sure correctly.
		assertTrue(setOfNumber.contains(random.randomNumber(setOfNumber)));
		assertTrue(setOfNumber.contains(random.randomNumber(setOfNumber)));
		assertTrue(setOfNumber.contains(random.randomNumber(setOfNumber)));
		assertTrue(setOfNumber.contains(random.randomNumber(setOfNumber)));
	}

	/**
	 * Test identifyColumn method can identify any number of each box and return
	 * number of column or not.
	 */
	@Test
	public void testIdentifyColumn() {
		int threeSize = 3;
		assertEquals(6, random.identifyColumn(threeSize, 5, 6));
		assertEquals(1, random.identifyColumn(threeSize, 0, 4));
		assertEquals(4, random.identifyColumn(threeSize, 7, 7));
		int fourSize = 4;
		assertEquals(12, random.identifyColumn(fourSize, 3, 8));
		assertEquals(7, random.identifyColumn(fourSize, 13, 15));
		assertEquals(8, random.identifyColumn(fourSize, 6, 12));
	}

	/**
	 * Test identifyRow method can identify any number of each box and return number
	 * of row or not.
	 */
	@Test
	public void testIdentifyRow() {
		int threeSize = 3;
		assertEquals(1, random.identifyRow(threeSize, 1, 5));
		assertEquals(5, random.identifyRow(threeSize, 3, 8));
		assertEquals(7, random.identifyRow(threeSize, 8, 4));
		int fourSize = 4;
		assertEquals(6, random.identifyRow(fourSize, 4, 9));
		assertEquals(11, random.identifyRow(fourSize, 10, 15));
		assertEquals(12, random.identifyRow(fourSize, 13, 1));
	}

	/**
	 * Test mergeDuplicateList method can merge two lists(on the top and left of
	 * this numberGrid) which contain duplicate number in the list to wipe it out or
	 * not.
	 */
	@Test
	public void testMergeDuplicateList() {
		List<Integer> duplicateList = new ArrayList<Integer>();
		// has no list.
		duplicateList.addAll(random.mergeDuplicateList(0, 1, 1));
		assertEquals(0, duplicateList.size());
		duplicateList.clear();

		// has upper List without left list.
		duplicateList.addAll(random.mergeDuplicateList(3, 1, 3));
		assertEquals(3, duplicateList.size());
		assertTrue(duplicateList.contains(1));
		assertTrue(duplicateList.contains(4));
		assertTrue(duplicateList.contains(7));
		duplicateList.clear();

		// has left List without upper list.
		duplicateList.addAll(random.mergeDuplicateList(2, 8, 0));
		assertEquals(3, duplicateList.size());// 0, 1, and 2 are duplicate.
		assertTrue(duplicateList.contains(0));
		assertTrue(duplicateList.contains(1));
		assertTrue(duplicateList.contains(2));
		duplicateList.clear();

		// have upper and left list.
		duplicateList.addAll(random.mergeDuplicateList(5, 6, 5));
		assertEquals(5, duplicateList.size());
		assertTrue(duplicateList.contains(0));
		assertTrue(duplicateList.contains(3));
		assertTrue(duplicateList.contains(6));
		assertTrue(duplicateList.contains(7));
		assertTrue(duplicateList.contains(8));
		assertFalse(duplicateList.contains(9));
		duplicateList.clear();

	}

	/**
	 * Test undoCreateGrid method can undo the process of generating a set of
	 * number(1-9 or 1-16) to makes new order of number or not.
	 */
	@Test
	public void testUndoCreateGrid() {
		int realSize = 3;
		assertEquals(3, table.getSize());
		BoxManager firstBox = table.getList().get(6).getList().get(0);
		BoxManager secondBox = table.getList().get(6).getList().get(4);
		BoxManager thirdBox = table.getList().get(7).getList().get(8);
		BoxManager notChangeBox = table.getList().get(5).getList().get(3);
		BoxManager originalBox = table.getList().get(8).getList().get(5);
		BoxManager notChangeBoxAgain = table.getList().get(2).getList().get(3);
		// change sixth and seventh grid.
		random.undoCreateGrid(8, realSize * realSize, realSize);
		assertFalse(table.getList().get(6).getList().get(0) == firstBox);
		assertFalse(table.getList().get(6).getList().get(4) == secondBox);
		assertFalse(table.getList().get(7).getList().get(8) == thirdBox);
		assertTrue(table.getList().get(8).getList().get(5) == originalBox);
		assertTrue(table.getList().get(5).getList().get(3) == notChangeBox);
		assertTrue(table.getList().get(2).getList().get(3) == notChangeBoxAgain);
	}

	/**
	 * Test createRandomSet method can generate random set of number(1-9 or 1-16)
	 * for each grid or not.
	 */
	@Test
	public void testCreateRandomSet() {
		BoxManager firstBox = table.getList().get(7).getList().get(4);
		BoxManager secondBox = table.getList().get(7).getList().get(8);
		BoxManager thirdBox = table.getList().get(8).getList().get(8);
		table.getList().remove(8);
		table.getList().remove(7);
		table.getList().add(new GridManager());
		table.getList().add(new GridManager());
		random.createRandomSet(new ArrayList<Integer>(), 7, 9);
		random.createRandomSet(new ArrayList<Integer>(), 8, 9);
		GridManager seventhGrid = table.getList().get(7);
		GridManager eighthGrid = table.getList().get(8);
		BoxManager oneBox = seventhGrid.getList().get(4);
		BoxManager twoBox = seventhGrid.getList().get(8);
		BoxManager threeBox = eighthGrid.getList().get(8);
		assertEquals(9, seventhGrid.getList().size());
		assertEquals(9, eighthGrid.getList().size());
		assertNotEquals(firstBox, oneBox);
		assertNotEquals(secondBox, twoBox);
		assertNotEquals(thirdBox, threeBox);

	}
}
