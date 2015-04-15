package de.htwg.hexxagon.controller.impl;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import de.htwg.hexxagon.controller.IHexxagonController;
import de.htwg.hexxagon.model.IBoard;
import de.htwg.hexxagon.model.IField;
import de.htwg.hexxagon.model.IPlayer;
import de.htwg.hexxagon.model.impl.Board;
import de.htwg.hexxagon.model.impl.Player;
import de.htwg.hexxagon.observer.Observable;
import de.htwg.hexxagon.util.ConstantColors;

public final class HexxagonController extends Observable implements ConstantColors, IHexxagonController {
	
	private IBoard board;
	private IPlayer[] player;
	private int turnPlayer;

	private static final int FRAMESIZE = 2;
	
	public HexxagonController() {
		this.board = new Board();
		this.turnPlayer = 0;
		this.player = new IPlayer[2];
	}

	public String getGridString() {
		return board.toString();
	}

	public String getStatus() {
		String status = player[0].getName() + " " + board.getScore(player[0].getColor()) +
				" : " + board.getScore(player[1].getColor()) + " " + player[1].getName();
		return status;
	}
	
	public List<Point> getDuplicatePossibilities(Point point) {
		Point convertedPoint = convertPoint(point);
		return convertList(board.getDuplicatePossibilities(convertedPoint, COLOR_EMPTY));
	}
	
	public List<Point> getJumpPossibilities(Point point) {
		Point convertedPoint = convertPoint(point);
		return convertList(board.getJumpPossibilities(convertedPoint, COLOR_EMPTY));
	}
	
	public boolean isSelectable(Point point) {
			return board.isSelectable(convertPoint(point), getPlayerColor());
	}

	public boolean doMove(Point start, Point destination) {
		List<Point> duplicateList = convertList(board.getDuplicatePossibilities(convertPoint(start), COLOR_EMPTY));
		boolean success = false;
		for (Point point : duplicateList) {
			if (point.equals(destination)) {
				board.setColor(convertPoint(destination), player[turnPlayer].getColor());
				success = true;
				break;
			}
		}
		if (!success) {
			List<Point> jumpList = convertList(board.getJumpPossibilities(convertPoint(start), COLOR_EMPTY));
			for (Point point : jumpList) {
				if (point.equals(destination)) {
					board.setColor(convertPoint(start), COLOR_EMPTY);
					board.setColor(convertPoint(destination), player[turnPlayer].getColor());
					success = true;
					break;
				}
			}
		}
		if (success) {
			infectNeighbours(destination);
			if (turnPlayer == 0) {
				turnPlayer = 1;
			} else {
				turnPlayer = 0;
			}
			notifyObservers();
			return true;
		} else {
			return false;
		}
	}

	public void setPlayers(String name1, Color color1, String name2, Color color2) {
		if (player[0] == null || player[1] == null) {
			this.player[0] = new Player(name1, color1);
			this.player[1] = new Player(name2, color2);
		} else {
			this.player[0].setName(name1);
			this.player[0].setColor(color1);
			this.player[1].setName(name2);
			this.player[1].setColor(color2);
		}
	}

	public String getPlayerName() {
		return player[turnPlayer].getName();
	}

	public String getPlayerName(int numberOfPlayer) {
		return player[numberOfPlayer].getName();
	}

	public int getPlayerScore(Color color) {
		return board.getScore(color);
	}
	
	public int getPlayerScore(int numberOfPlayer) {
		if (numberOfPlayer == 0) {
			return getPlayerScore(ConstantColors.COLOR_PLAYER1);
		}
		if (numberOfPlayer == 1) {
			return getPlayerScore(ConstantColors.COLOR_PLAYER2);
		}
		return -1;
	}

	public int getTurnPlayer() {
		return turnPlayer;
	}

	public void setTurnPlayer(int turnPlayer) {
		this.turnPlayer = turnPlayer;
	}

	public boolean gameOver() {
		int p1 = getPlayerScore(COLOR_PLAYER1);
		int p2 = getPlayerScore(COLOR_PLAYER2);
		int emptyFields = getPlayerScore(COLOR_EMPTY);

		if (p1 == 0 || p2 == 0 || emptyFields == 0) {
			return true;
		}
		return false;
	}
	
	public boolean gameOverByLeftPossibilities() {
		IField[][] fields = getBoard();

		int offset = 2;
		for (int i = offset; i < fields.length - offset; i++) {
			for (int j = offset; j < fields[i].length - offset; j++) {
				if (getPlayerColor().equals(fields[j][i].getColor())) {
					Point current = new Point(j - offset, i - offset);
					if ((!getJumpPossibilities(current).isEmpty()) ||

					(!getDuplicatePossibilities(current).isEmpty())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public Color getFieldColor(Point point) {
		return board.getColor(convertPoint(point));
	}

	public void setFieldColor(Point point, Color color) {
		board.setColor(convertPoint(point), color);
	}

	private void infectNeighbours(Point point) {
		Color targetColor;
		if (turnPlayer == 0) {
			targetColor = player[1].getColor();
		} else {
			targetColor = player[0].getColor();
		}
		List<Point> neighbours = convertList(board.getDuplicatePossibilities(convertPoint(point), targetColor));
		for (Point p : neighbours) {
			if (board.getColor(convertPoint(p)).equals(targetColor)) {
				board.setColor(convertPoint(p), player[turnPlayer].getColor());
			}
		}
	}
	
	private Point convertPoint(Point point) {
		return new Point(point.x + FRAMESIZE, point.y + FRAMESIZE);
	}
	
	private List<Point> convertList(List<Point> list) {
		List<Point> convertedList = list;
		for (Point point : convertedList) {
			point.x -= 2;
			point.y -= 2;
		}
		return list;
	}
	
	private Color getPlayerColor() {
		return player[turnPlayer].getColor();
	}
	
	public IField[][] getBoard() {
		return this.board.getBoard();
	}
}
