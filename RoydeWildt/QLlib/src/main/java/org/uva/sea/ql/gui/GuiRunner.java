package org.uva.sea.ql.gui;
/**
 * Created by roydewildt on 18/02/16.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.uva.sea.ql.gui.view.editor.EditorView;

public class GuiRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage editor = editorStage();
        editor.show();
    }

    private Stage editorStage() {

        EditorView editor = new EditorView();

        Scene scene = new Scene(editor.getRootPane());
        scene.getStylesheets().add("customStylesheet.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }
}
