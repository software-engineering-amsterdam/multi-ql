package org.uva.sea.ql.gui;
/**
 * Created by roydewildt on 18/02/16.
 */

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.evaluator.EvalVisitor;
import org.uva.sea.ql.parser.QLRunner;

import java.util.List;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        initPreview();

    }

    public void initPreview() {
        Stage previewStage = new Stage();

        Form f = parseFromPath("src/test/resources/gui1.ql");
        List<Question> questions = (new EvalVisitor(f)).getQuestions();
        Parent uiElements = (new GuiBuilder(f.getId(),questions)).getFormUI();

        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(uiElements);

        Scene scene = new Scene(pane);
        previewStage.setScene(scene);
        previewStage.setWidth(500);
        previewStage.setHeight(600);

        previewStage.show();
    }

    private Form parseFromPath(String path){
        try {
            return QLRunner.ParseFromPath(path);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
