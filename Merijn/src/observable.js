export class Observable {
	constructor() {
		this.observers = [];
	}
	registerObserver(observer) {
		this.observers.push(observer);
	}
	notifyObservers() {
		for (let observer of this.observers) {
			observer.notify();
		}
	}
}

