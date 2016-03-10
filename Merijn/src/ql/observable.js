export class Observable {
	constructor() {
		this._listeners = [];
	}
	listen(listener) {
		this._listeners.push(listener);
	}
	notifyListeners() {
		for (let listener of this._listeners) {
			listener();
		}
	}
}
