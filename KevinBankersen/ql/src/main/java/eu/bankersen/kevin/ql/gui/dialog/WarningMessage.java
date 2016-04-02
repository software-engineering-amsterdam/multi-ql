package eu.bankersen.kevin.ql.gui.dialog;

import java.util.Iterator;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WarningMessage<T> {

	public WarningMessage(Iterator<T> list) {
		StringBuilder sb = new StringBuilder();
		list.forEachRemaining(warning -> sb.append(warning + "\n"));
		showWarning(sb.toString());
	}

	private void showWarning(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
