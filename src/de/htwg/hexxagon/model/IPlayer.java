package de.htwg.hexxagon.model;

import java.awt.Color;

public interface IPlayer {

	/**
	 * @return name of player
	 */
	String getName();

	/**
	 * @param name
	 */
	void setName(String name);

	/**
	 * @return color of player
	 */
	Color getColor();

	/**
	 * @param color
	 */
	void setColor(Color color);

}