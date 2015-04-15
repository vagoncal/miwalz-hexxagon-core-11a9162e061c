package de.htwg.hexxagon.model.impl;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import de.htwg.hexxagon.model.IPlayer;
import de.htwg.hexxagon.model.impl.Player;

public class PlayerTest {

	private IPlayer player;
	
	@Before
	public void setUp() throws Exception {
		player = new Player("name", Color.RED);
	}

	@Test
	public void testGetName() {
		player.setName("testName");
		assertEquals("testName", player.getName());
	}
	
	@Test
	public void testGetColor() {
		player.setColor(Color.BLUE);
		assertEquals(Color.BLUE, player.getColor());
	}

}
