package eu.bankersen.kevin.ql.gui;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import eu.bankersen.kevin.ql.form.FormBuilder;
import eu.bankersen.kevin.ql.form.ast.Form;
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.UserQuestion;
import eu.bankersen.kevin.ql.form.ast.visitors.TopDownVisitor;
import eu.bankersen.kevin.ql.form.interperter.Evaluator;
import eu.bankersen.kevin.ql.gui.dialog.ErrorMessage;
import eu.bankersen.kevin.ql.gui.widgets.Widget;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Gui extends Application {

	private Evaluator evaluator;

	@Override
	public void start(Stage stage) throws Exception {

		BorderPane application = new BorderPane();
		Scene scene = new Scene(application, 500, 500);

		setMenuBar(application);

		stage.setTitle("Question Language");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	private void setMenuBar(BorderPane application) {
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem menuOpen = new MenuItem("Open");
		MenuItem menuSave = new MenuItem("Save");
		menuFile.getItems().addAll(menuOpen, menuSave);
		menuBar.getMenus().add(menuFile);
		application.setTop(menuBar);

		menuOpen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("QL-Form", "*.form"));
				File file = fileChooser.showOpenDialog(new Stage());

				if (file != null) {
					application.setCenter(openForm(file));
				}
			}
		});

		menuSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save Answers");
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
				File file = fileChooser.showSaveDialog(new Stage());

				if (file != null && evaluator != null) {
					saveResult(file);
				}
			}
		});
	}

	private void saveResult(File file) {
		try {
			PrintWriter out = new PrintWriter(file);
			out.write(evaluator.toString());
			out.close();
		} catch (IOException ex) {
			new ErrorMessage("IO Error", "Could not save the file at location:\n" + file);
		}
	}

	private Pane openForm(File file) {
		try {
			Form form = new FormBuilder().createForm(file);
			return createContent(form);
		} catch (IllegalStateException e) {
			return new Pane();
		}
	}

	private Pane createContent(Form form) {
		evaluator = new Evaluator(form);

		BorderPane content = new BorderPane();
		content.setPadding(new Insets(10, 20, 10, 20));

		Text title = new Text(String.format("Form: %s", form.name()));
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));

		content.setTop(title);
		content.setAlignment(title, Pos.CENTER);

		content.setCenter(createWidgets(form));
		return content;
	}

	private Node createWidgets(Form form) {

		ScrollPane sp = new ScrollPane();
		sp.setStyle("-fx-background-color:transparent;");
		sp.setFitToWidth(true);

		VBox widgets = new VBox();
		widgets.setSpacing(15);

		form.accept(new TopDownVisitor<Set<String>>() {

			@Override
			public void visit(UserQuestion question, Set<String> processed) {
				createWidget(question.name(), question.widget(), processed);
			}

			@Override
			public void visit(ComputedQuestion question, Set<String> processed) {
				createWidget(question.name(), question.widget(), processed);
			}

			private void createWidget(String name, Widget widget, Set<String> processed) {
				if (!processed.contains(name)) {
					evaluator.addDataListener(widget);
					widget.addViewListener(evaluator);
					widgets.getChildren().add(widget);
					processed.add(name);
				}
			}

		}, new HashSet<>());

		sp.setContent(widgets);

		return sp;
	}

}
