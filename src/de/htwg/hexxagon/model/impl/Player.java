package de.htwg.hexxagon.model.impl;

import java.awt.Color;

import de.htwg.hexxagon.model.IPlayer;

public final class Player implements IPlayer {
	
	private String name;
	private Color color;
	
	public Player(String name, Color color) {
		setName(name);
		setColor(color);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
