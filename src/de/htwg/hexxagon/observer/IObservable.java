package de.htwg.hexxagon.observer;

public interface IObservable {

	/**
	 * @param s
	 */
	void addObserver(IObserver s);

	/**
	 * @param s
	 */
	void removeObserver(IObserver s);

	/**
	 * removes all observers
	 */
	void removeAllObservers();

	/**
	 * notifies all observers
	 */
	void notifyObservers();

}