export class Log {
	constructor() {
		this.errors = [];
		this.warnings = [];
	}
	logError(lines, message) {
		this.errors.push(new LinesMessage(lines, message));
	}
	logWarning(lines, message) {
		this.warnings.push(new LinesMessage(lines, message));
	}
}

export class LinesMessage {
	constructor(lines, message) {
		this.lines = lines;
		this.message = message;
	}
}