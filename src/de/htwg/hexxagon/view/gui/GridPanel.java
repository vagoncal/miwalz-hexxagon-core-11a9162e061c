/**
 * Hexxagon
 * 
 * @author Michael Walz & Tarek Schneider
 * @version 2013-01-24
 *  
 */

package de.htwg.hexxagon.view.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map.Entry;

import de.htwg.hexxagon.controller.impl.HexxagonController;
import de.htwg.hexxagon.observer.IObserver;
import de.htwg.hexxagon.util.ConstantColors;

public class GridPanel extends JPanel implements IObserver, ConstantColors {
	
	private static final long serialVersionUID = 1L;
	
	private PolygonGrid grid = new PolygonGrid();
	private HexxagonController controller;
	
	private List<Point> drawDuplicatePossibillities;
	private List<Point> drawJumpPossibillities;
	private Point startOfMove = null;
	
	/**
	 * Create the panel.
	 */
	public GridPanel(HexxagonController controller) {
		this.controller = controller;
		controller.addObserver(this);
		setBackground(COLOR_GRIDPANEL_BACKGROUND);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				handleMove(arg0);
			}
		});
	}
	
	public void handleMove(MouseEvent event) {
		
		Polygon currentPolygon = getMousePolygon(event);
		Point currentPoint = null;
		
		for (Entry<Point, Polygon> e : grid.getPolyGrid().entrySet()) {
			if (e.getValue().equals(currentPolygon)) {
				currentPoint = e.getKey();
				break;
			}
		}
		
		if (currentPoint != null) {
			if (controller.isSelectable(currentPoint)) {					
				showPossibillities(currentPoint);
				startOfMove = currentPoint;
			} else {
				controller.doMove(startOfMove, currentPoint);
			}
			if (controller.gameOver()) {
				controller.removeAllObservers();
				this.setEnabled(false);
				new GameOverDialog(controller);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for (Entry<Point, Polygon> e : grid.getPolyGrid().entrySet()) {
			if (drawDuplicatePossibillities != null && drawDuplicatePossibillities.contains(e.getKey())) {
				g2d.setColor(COLOR_DUPLICATE);
			} else if (drawJumpPossibillities != null && drawJumpPossibillities.contains(e.getKey())) {
				g2d.setColor(COLOR_JUMP);
			} else {
				g2d.setColor(controller.getFieldColor(e.getKey()));
			}
			g2d.draw(e.getValue());
			g2d.fill(e.getValue());
		}
		
		drawDuplicatePossibillities = null;
		drawJumpPossibillities = null;
	}

	 private Polygon getMousePolygon(MouseEvent event) {
		 int mouseX = event.getX();
		 int mouseY = event.getY();
		 
		 for (Entry<Point, Polygon> e : grid.getPolyGrid().entrySet()) {
			 if (e.getValue().contains(mouseX, mouseY)) {				 
				 return e.getValue();
			 } 
		 }
		 return null;
	 }

	private void showPossibillities(Point point) {
		drawDuplicatePossibillities = controller.getDuplicatePossibilities(point);
		drawJumpPossibillities = controller.getJumpPossibilities(point);
		repaint();		
	}
	
	public void update() {
		repaint();
	}
}
