function throwError(line, errorMsg) {
	renderDebugMessage("error", line, errorMsg);
}

function throwWarning(line, warningMsg) {
	renderDebugMessage("warning", line, warningMsg);
}