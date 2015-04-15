package de.htwg.hexxagon.model;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

public interface IBoard {

	/**
	 * @param point
	 * @param targetColor
	 * @return list of duplicate possibilities
	 */
	List<Point> getDuplicatePossibilities(Point point, Color targetColor);

	/**
	 * @param point
	 * @param targetColor
	 * @return list of jump possibilities
	 */
	List<Point> getJumpPossibilities(Point point, Color targetColor);

	/**
	 * @param point
	 * @param targetColor
	 * @return true if field is selectable
	 */
	boolean isSelectable(Point point, Color targetColor);

	/**
	 * @param color
	 * @return score
	 */
	int getScore(Color color);

	/**
	 * @param point
	 * @param color
	 */
	void setColor(Point point, Color color);

	/**
	 * @param point
	 * @return field color
	 */
	Color getColor(Point point);

	/**
	 * @return TUI of the board as string
	 */
	String toString();
	
	public abstract IField[][] getBoard();

}