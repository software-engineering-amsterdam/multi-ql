class Listener {
	constructor() {
		this.listenerMap = [];
	}

	register(label, questionNode) {
		if (this.listenerMap[label] !== undefined) {
			this.listenerMap[label].push(questionNode);
		}
		else {
			this.listenerMap[label] = [questionNode];
		}
	}

	notify(label) {
		if (this.listenerMap[label] !== undefined) {
			for (var i = 0; i < this.listenerMap[label].length; i++) {
				var dependant = this.listenerMap[label][i];
				dependant.notify();
				this.notify(dependant.label);
			}
		}
	}
}