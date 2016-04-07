package eu.bankersen.kevin.ql.gui.dialog;

import java.util.Iterator;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorMessage {

	public ErrorMessage(String errorType, String message) {
		showError(errorType, message);
	}

	public ErrorMessage(String errorType, Iterator list) {
		StringBuilder sb = new StringBuilder();
		list.forEachRemaining(error -> sb.append(error.toString() + "\n"));
		showError(errorType, sb.toString());
	}

	private void showError(String errorType, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(errorType);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
		throw new IllegalStateException();
	}
}
