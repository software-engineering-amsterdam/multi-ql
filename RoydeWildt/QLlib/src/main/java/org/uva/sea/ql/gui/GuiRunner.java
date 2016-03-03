package org.uva.sea.ql.gui;
/**
 * Created by roydewildt on 18/02/16.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.parser.QLRunner;

public class GuiRunner extends Application {
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        initPreview();

    }

    public void initPreview() {

        Form f = parseFromPath("src/test/resources/example1.ql");
        FormUIBuilder guiBuilder = new FormUIBuilder(f);

        Scene scene = new Scene(guiBuilder.getRootPane());
        stage.setScene(scene);
        stage.show();
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
