package de.htwg.hexxagon.model.impl;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import de.htwg.hexxagon.model.IBoard;
import de.htwg.hexxagon.model.IField;
import de.htwg.hexxagon.util.ConstantColors;
import de.htwg.hexxagon.util.ConstantNumbers;

public final class Board implements ConstantNumbers, ConstantColors, IBoard {

	private static final int BOARDSIZE = 13;

	private IField[][] board;

	public Board() {
		board = new IField[BOARDSIZE][BOARDSIZE];
		resetBoard();
	}

	public IField[][] getBoard() {
		return this.board;
	}

	public List<Point> getDuplicatePossibilities(Point point, Color targetColor) {
		List<Point> possibilities = new ArrayList<Point>();
		int tmpColumn;
		int tmpRow;

		/* get the fields over and under the selected field */
		tmpRow = point.y - 1;
		possibilities = checkAddTarget(possibilities, tmpRow, point.x,
				targetColor);
		tmpRow = point.y + 1;
		possibilities = checkAddTarget(possibilities, tmpRow, point.x,
				targetColor);

		/* get the fields next to the selected field */
		if (point.x % 2 == 0) {
			/* get the fields on the right */
			tmpColumn = point.x + 1;
			tmpRow = point.y - 1;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
			tmpRow = point.y;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);

			/* get the fields on the left */
			tmpColumn = point.x - 1;
			tmpRow = point.y - 1;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
			tmpRow = point.y;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
		} else {
			/* get the fields on the right */
			tmpColumn = point.x + 1;
			tmpRow = point.y;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
			tmpRow = point.y + 1;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);

			/* get the fields on the left */
			tmpColumn = point.x - 1;
			tmpRow = point.y;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
			tmpRow = point.y + 1;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
		}
		return possibilities;
	}

	public List<Point> getJumpPossibilities(Point point, Color targetColor) {
		List<Point> possibilities = new ArrayList<Point>();
		int tmpColumn;
		int tmpRow;

		/* get the fields over and under the selected field */
		tmpRow = point.y - 2;
		possibilities = checkAddTarget(possibilities, tmpRow, point.x,
				targetColor);
		tmpRow = point.y + 2;
		possibilities = checkAddTarget(possibilities, tmpRow, point.x,
				targetColor);

		/* get the fields next to the selected field */
		if (point.x % 2 == 0) {
			/* get the fields on the inner right */
			tmpColumn = point.x + 1;
			tmpRow = point.y - 2;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
			tmpRow = point.y + 1;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);

			/* get the fields on the inner left */
			tmpColumn = point.x - 1;
			tmpRow = point.y - 2;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
			tmpRow = point.y + 1;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
		} else {
			/* get the fields on the inner right */
			tmpColumn = point.x + 1;
			tmpRow = point.y - 1;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
			tmpRow = point.y + 2;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);

			/* get the fields on the inner left */
			tmpColumn = point.x - 1;
			tmpRow = point.y - 1;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
			tmpRow = point.y + 2;
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
		}
		/* get the fields on the outer right */
		tmpColumn = point.x + 2;
		for (tmpRow = point.y - 1; tmpRow <= point.y + 1; tmpRow++) {
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
		}
		/* get the fields on the outer left */
		tmpColumn = point.x - 2;
		for (tmpRow = point.y - 1; tmpRow <= point.y + 1; tmpRow++) {
			possibilities = checkAddTarget(possibilities, tmpRow, tmpColumn,
					targetColor);
		}

		return possibilities;
	}

	public boolean isSelectable(Point point, Color targetColor) {
		Color selectedColor = board[point.y][point.x].getColor();
		return selectedColor.equals(targetColor);
	}

	public int getScore(Color color) {
		int score = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getColor().equals(color)) {
					score++;
				}
			}
		}
		return score;
	}

	public void setColor(Point point, Color color) {
		board[point.y][point.x].setColor(color);
	}

	public Color getColor(Point point) {
		return board[point.y][point.x].getColor();
	}

	public String toString() {
		final int frameSize = 2;
		StringBuilder strBuilder = new StringBuilder("");
		Color currentColor;
		strBuilder.append('\t');
		for (int m = 0; m < board.length - 2 * frameSize; m++) {
			strBuilder.append(m).append("\t");
		}
		strBuilder.append("\n\n");
		for (int i = frameSize; i < board.length - frameSize; i++) {
			strBuilder.append(i - frameSize).append(":\t");
			for (int j = frameSize; j < board[i].length - frameSize; j++) {
				currentColor = board[i][j].getColor();
				if (currentColor.equals(COLOR_BLOCKED)) {
					strBuilder.append("X\t");
				} else if (currentColor.equals(COLOR_EMPTY)) {
					strBuilder.append("0\t");
				} else if (currentColor.equals(COLOR_PLAYER2)) {
					strBuilder.append("B\t");
				} else if (currentColor.equals(COLOR_PLAYER1)) {
					strBuilder.append("R\t");
				}
			}
			strBuilder.append('\n');
		}
		return strBuilder.toString();
	}

	private List<Point> checkAddTarget(List<Point> list, int row, int column,
			Color targetColor) {
		if (board[row][column].getColor().equals(targetColor)) {
			list.add(new Point(column, row));
		}
		return list;
	}

	private void resetBoard() {
		// set each field to black
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				board[row][column] = new Field(COLOR_BLOCKED);
			}
		}

		// set white fields
		setWhite(TWO, FOUR, EIGHT);
		setWhite(THREE, THREE, EIGHT);
		setWhite(FOUR, THREE, NINE);
		setWhite(FIVE, TWO, NINE);
		setWhite(SIX, TWO, TEN);
		setWhite(SEVEN, TWO, NINE);
		setWhite(EIGHT, THREE, NINE);
		setWhite(NINE, THREE, EIGHT);
		setWhite(TEN, FOUR, EIGHT);

		// set players start fields
		board[FOUR][TWO].setColor(COLOR_PLAYER1);
		board[TEN][SIX].setColor(COLOR_PLAYER1);
		board[FOUR][TEN].setColor(COLOR_PLAYER1);
		board[EIGHT][TWO].setColor(COLOR_PLAYER2);
		board[TWO][SIX].setColor(COLOR_PLAYER2);
		board[EIGHT][TEN].setColor(COLOR_PLAYER2);

		// set black fields in the middle of the board
		board[SIX][FIVE].setColor(COLOR_BLOCKED);
		board[FIVE][SIX].setColor(COLOR_BLOCKED);
		board[SIX][SEVEN].setColor(COLOR_BLOCKED);
	}

	private void setWhite(int column, int fromRow, int toRow) {
		for (int row = fromRow; row <= toRow; row++) {
			board[row][column].setColor(COLOR_EMPTY);
		}
	}
}