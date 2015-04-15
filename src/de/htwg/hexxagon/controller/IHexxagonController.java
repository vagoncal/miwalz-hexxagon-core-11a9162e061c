package de.htwg.hexxagon.controller;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

public interface IHexxagonController {

	/**
	 * @return grid String
	 */
	String getGridString();

	/**
	 * @return game status
	 */
	String getStatus();

	/**
	 * @param point
	 * @return list of duplicate possibilities
	 */
	List<Point> getDuplicatePossibilities(Point point);

	/**
	 * @param point
	 * @return list of jump possibilities
	 */
	List<Point> getJumpPossibilities(Point point);

	/**
	 * @param point
	 * @return true if field is selectable
	 */
	boolean isSelectable(Point point);

	/**
	 * @param start
	 * @param destination
	 * @return true if move was successful
	 */
	boolean doMove(Point start, Point destination);

	/**
	 * @param name1
	 * @param color1
	 * @param name2
	 * @param color2
	 */
	void setPlayers(String name1, Color color1, String name2, Color color2);

	/**
	 * @return player name
	 */
	String getPlayerName();

	/**
	 * @param numberOfPlayer
	 * @return player name
	 */
	String getPlayerName(int numberOfPlayer);

	/**
	 * @param color
	 * @return player score
	 */
	int getPlayerScore(Color color);

	/**
	 * @return number of active player (0 or 1)
	 */
	int getTurnPlayer();

	/**
	 * @param turnPlayer
	 */
	void setTurnPlayer(int turnPlayer);

	/**
	 * @return true if game is over
	 */
	boolean gameOver();

	/**
	 * @param point
	 * @return field color
	 */
	Color getFieldColor(Point point);

	/**
	 * @param point
	 * @param color
	 */
	void setFieldColor(Point point, Color color);

}