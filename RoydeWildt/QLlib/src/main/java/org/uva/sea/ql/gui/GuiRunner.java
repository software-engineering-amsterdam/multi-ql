package org.uva.sea.ql.gui;
/**
 * Created by roydewildt on 18/02/16.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.checker.Checker;
import org.uva.sea.ql.checker.message.Message;
import org.uva.sea.ql.gui.view.EditorView;
import org.uva.sea.ql.gui.view.PreviewView;
import org.uva.sea.ql.parser.QLRunner;

import java.util.List;

public class GuiRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage preview = PreviewStage();
        preview.show();

        Stage editor = EditorStage();
        editor.setX(preview.getX() - 510);
        editor.setY(preview.getY() - 14);
        editor.show();

    }

    public Stage PreviewStage() {

        Form f = parseFromPath("src/test/resources/example1.ql");
        PreviewView preview = new PreviewView(f);

        List<Message> messages = new Checker(f).getMessages();

        Scene scene = new Scene(preview.getRootPane());
        scene.getStylesheets().add("customStylesheet.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }

    public Stage EditorStage() {
        EditorView editor = new EditorView();
        Scene scene = new Scene(editor.getRootPane());
        scene.getStylesheets().add("customStylesheet.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
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
