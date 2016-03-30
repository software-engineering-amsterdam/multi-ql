package eu.bankersen.kevin.ql.gui.dialog;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorDialog<T> {

    public ErrorDialog(String errorType, String message) {
	showError(errorType, message);
    }

    public ErrorDialog(List<T> list) {
	StringBuilder sb = new StringBuilder();
	list.forEach(error -> sb.append(error + "\n"));
	showError("Errors!", sb.toString());
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
