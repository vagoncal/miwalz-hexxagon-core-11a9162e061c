package de.htwg.hexxagon.model.impl;

import java.awt.Color;

import de.htwg.hexxagon.model.IField;

public class Field implements IField {
	
	private Color color;

	public Field(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
