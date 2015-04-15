package de.htwg.hexxagon.view.tui;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import de.htwg.hexxagon.controller.impl.HexxagonController;
import de.htwg.hexxagon.view.tui.TextUI;

public class TextUITest {

	private HexxagonController controller;
	private TextUI tui;
	
	@Before
	public void setUp() throws Exception {
		controller = new HexxagonController();
		tui = new TextUI(controller);
	}
	
	@Test
	public void testUpdate() {
	}
	
	
	@Test
	public void testHandleInputOrQuit() {
		assertEquals(false, tui.handleInputOrQuit("name1"));
	}
	
}
