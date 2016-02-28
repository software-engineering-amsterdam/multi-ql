package org.uva.sea.ql.gui;
/**
 * Created by roydewildt on 18/02/16.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.visitor.EvalVisitor;
import org.uva.sea.ql.parser.QLRunner;

import java.util.List;

public class Main extends Application {

    private Parent formUI;
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
        formUI = (new GuiVisitor(f,this)).getFormUI();

        Scene scene = new Scene(formUI);
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
