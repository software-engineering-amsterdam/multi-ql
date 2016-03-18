package eu.bankersen.kevin.ql.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FXGui extends Application {

    public static void main(String[] args) {
	launch(args);
    }

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

	VBox questions = new VBox();
	questions.setSpacing(15);

	// This is a question.
	BorderPane grid = new BorderPane();

	Label category = new Label(
		"This is a sample question? This is a sample question? This is a sample question? This is a sample question? This is a sample question? This is a sample question? This is a sample question? This is a sample question? This is a sample question? This is a sample question?");
	category.setWrapText(true);
	category.setTextAlignment(TextAlignment.JUSTIFY);
	category.setMaxWidth(325);
	grid.setLeft(category);

	HBox container = new HBox();
	container.setSpacing(10);
	ToggleGroup group = new ToggleGroup();
	RadioButton button1 = new RadioButton("True");
	button1.prefWidth(150);
	button1.setToggleGroup(group);
	button1.setSelected(true);
	RadioButton button2 = new RadioButton("False");
	button2.setToggleGroup(group);

	container.getChildren().add(button1);
	container.getChildren().add(button2);

	grid.setRight(container);
	questions.getChildren().add(grid);
	// End of question

	// Question 2
	BorderPane grid2 = new BorderPane();

	Label category2 = new Label("This is a sample question?");
	grid2.setLeft(category2);

	HBox container2 = new HBox();
	container2.setSpacing(10);
	ToggleGroup group2 = new ToggleGroup();
	RadioButton button12 = new RadioButton("True");
	button12.prefWidth(150);
	button12.setToggleGroup(group);
	button12.setSelected(true);
	RadioButton button22 = new RadioButton("False");
	button22.setToggleGroup(group2);

	container2.getChildren().add(button12);
	container2.getChildren().add(button22);

	grid2.setRight(container2);

	questions.getChildren().add(grid2);

	border.setCenter(questions);
	// Builds the application
	stack.getChildren().add(border);
	stage.setScene(scene);
	stage.show();

    }

}
