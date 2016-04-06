package eu.bankersen.kevin.ql.gui;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import eu.bankersen.kevin.ql.form.FormBuilder;
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.Form;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Gui extends Application {

	private Evaluator evaluator;

	@Override
	public void start(Stage stage) throws Exception {

		StackPane stack = new StackPane();
		Scene scene = new Scene(stack, 500, 500);

		String titleText = "Welcome to QL!";
		stage.setTitle(titleText);

		BorderPane border = new BorderPane();
		border.setPadding(new Insets(10, 20, 10, 20));

		Text title = new Text(titleText);
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
		title.setTextAlignment(TextAlignment.CENTER);

		border.setTop(title);
		border.setAlignment(title, Pos.CENTER);

		border.setCenter(openForm(new File("resources\\test.form")));

		Button open = new Button("Open Form");
		open.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					border.setCenter(openForm(file));
				}
			}
		});

		Button save = new Button("Save Results");
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save Answers");
				File file = fileChooser.showSaveDialog(stage);
				if (file != null) {
					try {
						PrintWriter out = new PrintWriter(file);
						out.write(evaluator.toString());
						out.close();
					} catch (IOException ex) {
						new ErrorMessage<>("IO Error", "Could not save the file at location:\n" + file);
					}
				}
			}
		});

		BorderPane footer = new BorderPane();
		footer.setLeft(open);
		footer.setCenter(new Label("Made by Kevin Bankersen"));
		footer.setRight(save);

		border.setBottom(footer);
		border.setAlignment(footer, Pos.CENTER);

		stack.getChildren().add(border);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	private ScrollPane openForm(File file) {
		ScrollPane sp = new ScrollPane();
		sp.setStyle("-fx-background-color:transparent;");
		sp.setFitToWidth(true);
		try {
			Form form = new FormBuilder().createForm(file.toString());
			sp.setContent(createWidgets(form));

		} catch (IllegalStateException e) {
			sp.setContent(new Label("Error opening form"));
		}
		return sp;
	}

	private Pane createWidgets(Form form) {

		evaluator = new Evaluator(form);

		VBox widgets = new VBox();
		widgets.setSpacing(15);

		form.accept(new TopDownVisitor<Set>() {

			@Override
			public void visit(UserQuestion question, Set processed) {
				createWidget(question.name(), question.widget(), processed);
			}

			@Override
			public void visit(ComputedQuestion question, Set processed) {
				createWidget(question.name(), question.widget(), processed);
			}

			private void createWidget(String name, Widget widget, Set processed) {
				if (!processed.contains(name)) {
					evaluator.addDataListener(widget);
					widget.addViewListener(evaluator);
					widgets.getChildren().add(widget);
					processed.add(name);
				}
			}

		}, new HashSet<>());

		return widgets;
	}

}
