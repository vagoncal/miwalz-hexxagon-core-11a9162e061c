package de.htwg.hexxagon.controller.impl;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.htwg.hexxagon.controller.IHexxagonController;
import de.htwg.hexxagon.controller.impl.HexxagonController;
import de.htwg.hexxagon.model.IBoard;
import de.htwg.hexxagon.model.impl.Board;

public class HexxagonControllerTest {
//teest
	
	private IHexxagonController controller;
	private IBoard board = new Board();
	
	@Before
	public void setUp() throws Exception {
		controller = new HexxagonController();
		controller.setPlayers("name1", Color.RED, "name2", Color.BLUE);
	}
	
	@Test
	public void testSetPlayers() {
		controller.setPlayers("name1", Color.RED, "name2", Color.BLUE);
		assertEquals("name1", controller.getPlayerName(0));		
	}
	
	@Test
	public void testGetTurnPlayer() {
		controller.setTurnPlayer(0);
		assertEquals(0, controller.getTurnPlayer());
	}
	
	@Test
	public void testGetPlayerScore() {
		assertEquals(3, controller.getPlayerScore(Color.blue));
	}
	
	@Test
	public void testGetPlayerName() {
		assertEquals("name1", controller.getPlayerName());
	}
	
	@Test
	public void testIsSelectable() {
		assertTrue(controller.isSelectable(new Point(0, 2)));
	}
	
	@Test
	public void testGetJumpPossibilities() {
		List<Point> expectedList = new ArrayList<Point>();
		List<Point> actualList = new ArrayList<Point>();
		
		expectedList.add(new Point(0, 4));
		expectedList.add(new Point(1, 3));
		expectedList.add(new Point(2, 1));
		expectedList.add(new Point(2, 2));
		expectedList.add(new Point(2, 3));
		
		actualList = controller.getJumpPossibilities(new Point(0, 2));
		
		assertTrue(expectedList.containsAll(actualList));
	}
	
	@Test
	public void testGetJumpPossibilities2() {
		List<Point> expectedList = new ArrayList<Point>();
		List<Point> actualList = new ArrayList<Point>();
		
		expectedList.add(new Point(0, 1));
		expectedList.add(new Point(1, 2));
		expectedList.add(new Point(2, 2));
		expectedList.add(new Point(3, 2));
		expectedList.add(new Point(3, 1));
		expectedList.add(new Point(3, 0));
		
		actualList = controller.getJumpPossibilities(new Point(1, 0));
		
		assertTrue(expectedList.containsAll(actualList));
	}
	
	@Test
	public void testGetDuplicatePossibilities() {
		List<Point> expectedList = new ArrayList<Point>();
		List<Point> actualList = new ArrayList<Point>();
		
		expectedList.add(new Point(0, 3));
		expectedList.add(new Point(1, 1));
		expectedList.add(new Point(1, 2));
		
		actualList = controller.getDuplicatePossibilities(new Point(0, 2));
		assertTrue(expectedList.containsAll(actualList));
	}
	
	@Test
	public void testGetDuplicatePossibilities2() {
		List<Point> expectedList = new ArrayList<Point>();
		List<Point> actualList = new ArrayList<Point>();
		
		expectedList.add(new Point(0, 2));
		expectedList.add(new Point(0, 3));
		expectedList.add(new Point(0, 4));
		expectedList.add(new Point(1, 1));
		expectedList.add(new Point(1, 3));
		expectedList.add(new Point(2, 2));
		expectedList.add(new Point(2, 3));
		expectedList.add(new Point(2, 4));
		
		actualList = controller.getDuplicatePossibilities(new Point(1, 2));
		assertTrue(expectedList.containsAll(actualList));
	}
	
	@Test
	public void testDoMove() {
		board.setColor(new Point(0, 5), Color.BLUE);
		assertTrue(controller.doMove(new Point(0, 2), new Point(0, 4)));
		assertTrue(controller.doMove(new Point(0, 2), new Point(0, 3)));
		assertFalse(controller.doMove(new Point(0, 4), new Point(3, 4)));
	}
	
	@Test
	public void testGetGridString() {
		String expected = "\t0\t1\t2\t3\t4\t5\t6\t7\t8\t\n\n" +
				"0:\tX\tX\tX\t0\tB\t0\tX\tX\tX\t\n" +
				"1:\tX\t0\t0\t0\t0\t0\t0\t0\tX\t\n" +
				"2:\tR\t0\t0\t0\t0\t0\t0\t0\tR\t\n" +
				"3:\t0\t0\t0\t0\tX\t0\t0\t0\t0\t\n" +
				"4:\t0\t0\t0\tX\t0\tX\t0\t0\t0\t\n" +
				"5:\t0\t0\t0\t0\t0\t0\t0\t0\t0\t\n" +
				"6:\tB\t0\t0\t0\t0\t0\t0\t0\tB\t\n" +
				"7:\tX\tX\t0\t0\t0\t0\t0\tX\tX\t\n" +
				"8:\tX\tX\tX\tX\tR\tX\tX\tX\tX\t\n";
		assertEquals(expected, controller.getGridString());
	}
	
	@Test
	public void testGetStatus() {
		String expected = "name1 3 : 3 name2";
		assertEquals(expected, controller.getStatus());
	}
	
	@Test
	public void testSetFieldColor() {
		Color expected = Color.RED;
		controller.setFieldColor(new Point(0, 0), expected);
		assertEquals(expected, controller.getFieldColor(new Point(0, 0)));
	}
	
	@Test
	public void testGameOver() {
		assertFalse(controller.gameOver());
		controller.setFieldColor(new Point(0, 2), Color.BLUE);
		controller.setFieldColor(new Point(4, 8), Color.BLUE);
		controller.setFieldColor(new Point(8, 2), Color.BLUE);
		assertTrue(controller.gameOver());
	}
}


