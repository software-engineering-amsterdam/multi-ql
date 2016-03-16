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
import org.uva.sea.ql.gui.view.PreviewView;
import org.uva.sea.ql.parser.QLRunner;

import java.util.List;

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
        PreviewView preview = new PreviewView(f);

        List<Message> messages = new Checker(f).getMessages();

        if(messages.isEmpty()){
            Scene scene = new Scene(preview.getRootPane());
            scene.getStylesheets().add("customStylesheet.css");
            stage.setScene(scene);
            stage.show();
        }
        else {
            for(Message m : messages){
                System.out.println(m.getMsg());
            }
        }
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
