/**
 * Hexxagon
 * 
 * @author Michael Walz & Tarek Schneider
 * @version 2013-01-24
 *  
 */

package de.htwg.hexxagon;

import de.htwg.hexxagon.controller.impl.HexxagonController;
import de.htwg.hexxagon.view.gui.GraphicUI;
import de.htwg.hexxagon.view.tui.TextUI;

public final class Hexxagon {

	private Hexxagon() {}
	
	public static void main(String[] args) {
		// Test
		HexxagonController controller = new HexxagonController();
		TextUI tui = new TextUI(controller);
		tui.setPlayers();
		new GraphicUI(controller);
		tui.printTUI();	
		boolean quit = false;
		while (!quit) {
			quit = tui.iterate();
		}
	}
}