/**
 * Hexxagon
 * 
 * @author Michael Walz & Tarek Schneider
 * @version 2013-01-24
 *  
 */

package de.htwg.hexxagon.view.gui;

import java.awt.Point;
import java.awt.Polygon;
import java.util.HashMap;
import java.util.Map;
import de.htwg.hexxagon.util.ConstantNumbers;


public class PolygonGrid implements ConstantNumbers {
	
	private static final int FIELD_LENGTH = 30;
	private static final int STRECHING = 6;
	private static final int MARGIN = 50;
	private static final int PADDING = 4;
	
	private Map<Point, Polygon> polyGrid;

	public PolygonGrid() {
		polyGrid = new HashMap<Point, Polygon>();
		generatePolygons();
	}
	
	public Map<Point, Polygon> getPolyGrid() {
		return polyGrid;
	}
	
	private void generatePolygons() {
		generatePolygonColumn(ZERO, TWO, SIX);
		generatePolygonColumn(ONE, ONE, SIX);
		generatePolygonColumn(TWO, ONE, SEVEN);
		generatePolygonColumn(THREE, ZERO, SEVEN);
		generatePolygonColumn(FOUR, ZERO, EIGHT);
		generatePolygonColumn(FIVE, ZERO, SEVEN);
		generatePolygonColumn(SIX, ONE, SEVEN);
		generatePolygonColumn(SEVEN, ONE, SIX);
		generatePolygonColumn(EIGHT, TWO, SIX);
	}
	
	private void generatePolygonColumn(int column, int start, int end) {
		int xStart = MARGIN + column * (PADDING + FIELD_LENGTH + STRECHING + (FIELD_LENGTH) / TWO);
		int yStart;
		
		if (column > FOUR) {
			yStart = MARGIN + FIELD_LENGTH * (column - FOUR);
		} else {
			yStart = MARGIN + FIELD_LENGTH * (FOUR - column);
		}
		
		Point m = new Point(xStart, yStart);
		
		for (int row = start; row <= end; row++) {
			int[] xCoord = {m.x - FIELD_LENGTH - STRECHING, m.x - FIELD_LENGTH / TWO, m.x + FIELD_LENGTH / TWO, m.x + FIELD_LENGTH + STRECHING, m.x + FIELD_LENGTH / TWO, m.x - FIELD_LENGTH / TWO};
			int[] yCoord = {m.y, m.y + FIELD_LENGTH, m.y + FIELD_LENGTH, m.y, m.y - FIELD_LENGTH, m.y - FIELD_LENGTH};
			
			polyGrid.put(new Point(column, row), new Polygon(xCoord, yCoord, xCoord.length));
			
			// move in y-direction
			m.y += FIELD_LENGTH * TWO + PADDING;
		}
	}
}