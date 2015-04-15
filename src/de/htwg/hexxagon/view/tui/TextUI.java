/**
 * Hexxagon
 * 
 * @author Michael Walz & Tarek Schneider
 * @version 2013-01-24
 *  
 */

package de.htwg.hexxagon.view.tui;

import java.awt.Point;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.htwg.hexxagon.controller.impl.HexxagonController;
import de.htwg.hexxagon.observer.IObserver;
import de.htwg.hexxagon.util.ConstantColors;

public final class TextUI implements IObserver, ConstantColors {

	private HexxagonController controller;
	private Scanner scanner;
	
	public TextUI(HexxagonController controller) {
		this.controller = controller;
		controller.addObserver(this);
		scanner = new Scanner(System.in);
	}

	public boolean iterate() {
		return handleInputOrQuit(scanner.next());
	}
	
	public void update() {
		printTUI();
	}
	
	public void printTUI() {
		final String manual = "Please enter a command: xy - Visit field (x,y), q - quit";
		println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		println("##########################################################################");
		println(controller.getStatus());
		println("##########################################################################\n");
		println(controller.getGridString());
		println("##########################################################################\n");
		println(manual);
	}
	
	public boolean handleInputOrQuit(String line) {
		boolean quit = false;
		int[] argStart = new int[2];
		Point startField = new Point();
		
		if (line.equalsIgnoreCase("q")) {
			quit = true;
		} else if (getPattern(line) != null ) {
			argStart = getPattern(line);
			startField.x = argStart[0];
			startField.y = argStart[1];
			if (!controller.isSelectable(startField)) {
				println("The chosen field is not selectable.");
			} else {
				println(mergePossibilitiesToString(startField));
				println("Please select a field: xy - Go to field (x,y)");
				quit = handleMove(scanner.next(), startField);
			}
		} else {
			println("Invalid input. Try again.");
		}
		if (quit) {
			controller.removeAllObservers();
		}
		return quit;
	}
	
	public void setPlayers() {
		controller.setPlayers("RED", COLOR_PLAYER1, "BLUE", COLOR_PLAYER2);
	}
	
	private boolean handleMove(String line, Point startField) {
		Point destinationField = new Point();
		int[] argDestination = new int[2];
		String input = line;

		do {
			if (input.equalsIgnoreCase("q")) {
				return true;
			} else {
				if (getPattern(input) != null ) {
					argDestination = getPattern(input);
					destinationField.x = argDestination[0];
					destinationField.y = argDestination[1];
					if (controller.doMove(startField, destinationField)) {
						break;
					} else {
						println("Field is unaccessible. Please try another one.");
						input = scanner.next();
					}
				} else {
					println("Invalid input. Try again.");
					input = scanner.next();
				}
			}	
		} while (true);

		return controller.gameOver();
	}
	
	private int[] getPattern(String line) {
		if (!line.matches("[0-8][0-8]")) {
			return null;
		} else {
			Pattern p = Pattern.compile("[0-8]");
			Matcher m = p.matcher(line);
			int[] arg = new int[2];
			for (int i = 0; i < arg.length; i++) {
				m.find();
				arg[i] = Integer.parseInt(m.group());
			}
			return arg;
		}
	}
	
	private String mergePossibilitiesToString(Point point) {
		List<Point> duplicateList = controller.getDuplicatePossibilities(point);
		List<Point> jumpList = controller.getJumpPossibilities(point);
		StringBuilder strBuilder = new StringBuilder("");
		strBuilder.append(possibilityToString(duplicateList));
		strBuilder.append("\n");
		strBuilder.append(possibilityToString(jumpList));
		return strBuilder.toString();
	}
	
	private String possibilityToString(List<Point> possibilities) {
		StringBuilder strBuilder = new StringBuilder("");
		strBuilder.append("{");
		for (Point point : possibilities) {
			strBuilder.append("(").append((int) point.getX()).append(",").append((int) point.getY()).append(")");
		}
		strBuilder.append("}");
		return strBuilder.toString();
	}
	
	private void println(String string) {
		PrintStream out = System.out;
		out.print(string + "\n");
	}
	
}