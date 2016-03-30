package eu.bankersen.kevin.ql.gui;

import java.io.File;
import java.util.Iterator;

import eu.bankersen.kevin.ql.form.FormBuilder;
import eu.bankersen.kevin.ql.form.ast.form.Form;
import eu.bankersen.kevin.ql.gui.widgets.QuestionWidget;
import eu.bankersen.kevin.ql.interperter.Evaluator;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Gui extends Application {

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

		// border.setCenter(new Label("Open a form to begin!"));
		File testFile = new File("C:\\Users\\Kevin\\Documents\\multi-ql\\KevinBankersen\\ql\\resources\\test.form");
		border.setCenter(openForm(testFile));

		Button open = new Button("Open Form");
		open.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				File file = fileChooser.showOpenDialog(new Stage());
				if (file != null) {
					border.setCenter(openForm(file));
				}
			}
		});

		Button save = new Button("Save Results");

		BorderPane footer = new BorderPane();
		footer.setLeft(open);
		footer.setCenter(new Label("Made by Kevin Bankersen"));
		footer.setRight(save);

		border.setBottom(footer);
		border.setAlignment(footer, Pos.CENTER);

		stack.getChildren().add(border);
		stage.setScene(scene);
		stage.show();
	}

	private ScrollPane openForm(File file) {
		ScrollPane sp = new ScrollPane();
		sp.setStyle("-fx-background-color:transparent;");
		sp.setFitToWidth(true);
		try {
			Form form = new FormBuilder().createForm(file.toString());
			sp.setContent(questionWidgets(form));

		} catch (IllegalStateException e) {
			sp.setContent(new Label("Error opening form, please select a correct form"));
		}
		return sp;
	}

	private VBox questionWidgets(Form form) {

		Evaluator evaluator = new Evaluator(form);

		VBox questionsWidgets = new VBox();

		questionsWidgets.setSpacing(15);

		Iterator<QuestionWidget> itr = new GuiBuilder(form).questionIterator();
		while (itr.hasNext()) {
			QuestionWidget question = itr.next();
			evaluator.addDataListener(question);
			question.addUIListener(evaluator);
			questionsWidgets.getChildren().add(question.widget());
		}

		return questionsWidgets;
	}

}
