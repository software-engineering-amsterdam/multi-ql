class Environment {
	constructor() {
		this.valueMap = [];
	}

	setValue(label, value) {
		this.valueMap[label] = value;
	}

	getValue(label) {
		return this.valueMap[label];
	}

}