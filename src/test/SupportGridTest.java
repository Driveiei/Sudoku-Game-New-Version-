package test;

import static org.junit.Assert.*;

import org.junit.Test;

import application.SupportGrid;

/**
 * JUnit 4 tests for SupportGrid using only SupportGrid's method test in anyway.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class SupportGridTest {

	/** Declare for calling in to this test. */
	private SupportGrid support = new SupportGrid(3);

	/**
	 * To test adaptGrid can change scale from array to list scale to use in grid
	 * identify or not
	 */
	@Test
	public void testAdaptGrid() {
		assertEquals(7, support.adaptGrid(5, 6));
		assertEquals(0, support.adaptGrid(2, 2));
		assertEquals(3, support.adaptGrid(2, 5));
		assertEquals(8, support.adaptGrid(7, 6));
		assertEquals(4, support.adaptGrid(4, 5));
	}

	/**
	 * To test adaptBox can change scale from array to list scale to use in box
	 * identify or not
	 */
	@Test
	public void testAdaptBox() {
		assertEquals(2, support.adaptBox(5, 6));
		assertEquals(7, support.adaptBox(1, 8));
		assertEquals(6, support.adaptBox(3, 2));
		assertEquals(7, support.adaptBox(4, 2));
		assertEquals(7, support.adaptBox(7, 5));
	}

	/**
	 * To test scaleToArrayColumn can change scale from list to array scale to use
	 * in column identify or not
	 */
	@Test
	public void testScaleToArrayColumn() {
		assertEquals(5, support.scaleToArrayColumn(4, 8));
		assertEquals(6, support.scaleToArrayColumn(5, 3));
		assertEquals(1, support.scaleToArrayColumn(0, 7));
		assertEquals(6, support.scaleToArrayColumn(2, 6));
		assertEquals(1, support.scaleToArrayColumn(3, 1));
	}

	/**
	 * To test scaleToArrayRow can change scale from list to array scale to use in
	 * row identify or not
	 */
	@Test
	public void testScaleToRow() {
		assertEquals(8, support.scaleToArrayRow(7, 6));
		assertEquals(4, support.scaleToArrayRow(5, 4));
		assertEquals(5, support.scaleToArrayRow(3, 8));
		assertEquals(1, support.scaleToArrayRow(2, 3));
		assertEquals(6, support.scaleToArrayRow(8, 0));
	}

	/**
	 * Test randomCursor can random a number from one to target number or not
	 */
	@Test
	public void testRandomCursor() {
		int randomOne = support.randomCursor(6);
		int randomTwo = support.randomCursor(100);
		int randomThree = support.randomCursor(3);
		int randomFour = support.randomCursor(2);
		int randomFive = support.randomCursor(6);
		boolean check = (randomOne <= 6 && randomOne >= 0);
		assertTrue(check);
		check = (randomTwo <= 100 && randomTwo >= 0);
		assertTrue(check);
		check = (randomThree <= 3 && randomThree >= 0);
		assertTrue(check);
		check = (randomFour <= 2 && randomFour >= 0);
		assertTrue(check);
		check = (randomFive <= 6 && randomFive >= 0);
		assertTrue(check);
	}
}
