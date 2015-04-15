/**
 * Hexxagon
 * 
 * @author Michael Walz & Tarek Schneider
 * @version 2013-01-24
 *  
 */

package de.htwg.hexxagon.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Observable implements IObservable  {

	private List<IObserver> subscribers = new ArrayList<IObserver>(2);

	public void addObserver(IObserver s) {
		subscribers.add(s);
	}

	public void removeObserver(IObserver s) {
		subscribers.remove(s);
	}

	public void removeAllObservers() {
		subscribers.clear();
	}

	public void notifyObservers() {
		for (Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			observer.update();
		}
	}
}
