package de.htwg.hexxagon.model.impl;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import de.htwg.hexxagon.model.IField;
import de.htwg.hexxagon.model.impl.Field;

public class FieldTest {

	private IField field;

	@Before
	public void setUp() throws Exception {
		field = new Field(Color.BLACK);
	}

	@Test
	public void testGetColor() {
		assertEquals(Color.BLACK, field.getColor());
		field.setColor(Color.RED);
		assertEquals(Color.RED, field.getColor());
	}

}
