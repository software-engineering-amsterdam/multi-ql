package eu.bankersen.kevin.ql.gui.dialog;

import java.util.List;

import eu.bankersen.kevin.ql.form.typechecker.warnings.TypeCheckWarning;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WarningDialog {

    public WarningDialog(List<TypeCheckWarning> list) {
	StringBuilder sb = new StringBuilder();
	list.forEach(warning -> sb.append(warning + "\n"));
	showWarning(sb.toString());
    }

    private void showWarning(String message) {
	Alert alert = new Alert(AlertType.WARNING);
	alert.setTitle("Warning Dialog");
	alert.setHeaderText(null);
	alert.setContentText("Careful with the next step!");
	alert.showAndWait();
    }
}
