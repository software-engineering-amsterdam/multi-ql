package org.uva.sea.ql.gui.view.preview;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;
import org.uva.sea.ql.evaluator.FormEvaluator;
import org.uva.sea.ql.evaluator.SymbolTable;
import org.uva.sea.ql.evaluator.adt.value.Bool;
import org.uva.sea.ql.evaluator.adt.value.Str;
import org.uva.sea.ql.evaluator.adt.value.Value;
import org.uva.sea.ql.evaluator.adt.value.numeric.Double;
import org.uva.sea.ql.evaluator.adt.value.numeric.Int;
import org.uva.sea.ql.evaluator.adt.visitor.ValueVisitor;
import org.uva.sea.ql.gui.view.editor.EditorView;
import org.uva.sea.ql.gui.widget.Widget;
import org.uva.sea.ql.gui.widget.binary.BooleanWidget;
import org.uva.sea.ql.gui.widget.factory.DefaultWidgets;
import org.uva.sea.ql.gui.widget.factory.WidgetFactory;
import org.uva.sea.ql.gui.widget.number.NumberWidget;
import org.uva.sea.ql.gui.widget.string.StringWidget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by roy on 25-2-16.
 */
public class PreviewView implements ValueVisitor<Widget,EvaluatedQuestion> {
    private EditorView editor;
    private Stage previewStage;

    private GridPane rootPane;
    private VBox questionsBox;
    private WidgetFactory widgetFactory;

    private Form form;
    private Map<Var, Value> symbolTable;

    public PreviewView(EditorView editor) {
        rootPane = new GridPane();
        rootPane.setPrefSize(500,600);
        rootPane.setVgap(10);
        rootPane.setPadding(new Insets(0, 0, 0, 0));
        rootPane.setAlignment(Pos.TOP_CENTER);

        this.editor = editor;
        this.editor.addEditorChangedEventListener(editorChangedEvent -> buildPreviewFromSource(editorChangedEvent.getSource()));

        this.widgetFactory = new DefaultWidgets();
    }

    private void buildPreviewFromSource(Object source) {
        this.form = (Form) source;
        this.symbolTable = new SymbolTable(this.form).getSymbolTable();

        List<EvaluatedQuestion> questionList = new FormEvaluator(this.form, this.symbolTable).getEvaluatedQuestions();

        rootPane.getChildren().clear();

        Label label = new Label(form.getId());
        label.setFont(new Font("Arial", 30));
        label.setPadding(new Insets(10,0,5,20));
        rootPane.add(label, 0, 0, 2, 1);

        rootPane.add(makeQuestionPane(questionList), 0, 1);
    }

    private ScrollPane makeQuestionPane(List<EvaluatedQuestion> questionList){
        ScrollPane questionPane = new ScrollPane();
        questionPane.setFitToWidth(true);

        questionsBox = new VBox();
        List<QuestionBox> questionBoxList = buildQuestionUiFromList(questionList);
        questionsBox.getChildren().addAll(questionBoxList);

        questionPane.setContent(questionsBox);
        return questionPane;
    }

    private void updatePreview(Widget widget){
        List<EvaluatedQuestion> questionList = new FormEvaluator(this.form, this.symbolTable).getEvaluatedQuestions();
        List<QuestionBox> questionBoxList = buildQuestionUiFromList(questionList);
        insertActiveQuestionControl(questionBoxList, widget);

        this.questionsBox.getChildren().clear();
        this.questionsBox.getChildren().addAll(questionBoxList);
    }

    private void insertActiveQuestionControl(List<QuestionBox> questionBoxList, Widget widget){
        for (QuestionBox questionBox : questionBoxList){
            if(widget.getQuestionName().equals(questionBox.getQuestionName())){
                questionBox.getChildren().set(1, widget.getUiElement());
            }
        }
    }

    private List<QuestionBox> buildQuestionUiFromList(List<EvaluatedQuestion> questions){
        List<QuestionBox> uiElements = new ArrayList<>();
        for (EvaluatedQuestion question : questions){
            uiElements.add(genQuestionUI(question));
        }
        return uiElements;
    }

    private QuestionBox genQuestionUI(EvaluatedQuestion q) {
        QuestionBox box = buildQuestionBox(q);

        Label label = new Label(q.getLabel());
        label.setWrapText(true);
        box.add(label, 0, 0);

        Value value = q.getValue();
        Widget valueWidget = value.accept(this, q);
        box.add(valueWidget.getUiElement(), 1, 0);

        return box;
    }

    private QuestionBox buildQuestionBox(EvaluatedQuestion q){
        QuestionBox box = new QuestionBox(q.getVarname().toString());
        box.setHgap(5);
        box.setPadding(new Insets(5,20,5,20));

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        col1.setHalignment(HPos.LEFT);
        col1.setPrefWidth(300);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.RIGHT);
        col2.setMinWidth(200);

        box.getColumnConstraints().addAll(col1, col2);
        return box;
    }

    @Override
    public Widget visit(Int val, EvaluatedQuestion parent) {
        NumberWidget widget = widgetFactory.getNumberWidget(parent);
        widget.setValue(val.getValue());
        widget.addListener((observable, oldValue, newValue) -> {
            try {
                widget.unSetInvalid();
                Value newExpr = new Int(Integer.parseInt(widget.getValue()));
                updateSymbolTable(widget.getParentQuestion(), newExpr);
                updatePreview(widget);
            }
            catch (Exception e) {
                widget.setInvalid();
            }
        });
        return widget;
    }

    @Override
    public Widget visit(Double val, EvaluatedQuestion parent) {
        NumberWidget widget = widgetFactory.getNumberWidget(parent);
        widget.setValue(val.getValue());
        widget.addListener((observable, oldValue, newValue) -> {
            try {
                widget.unSetInvalid();
                Value newExpr = new Double(java.lang.Double.parseDouble(widget.getValue()));
                updateSymbolTable(widget.getParentQuestion(), newExpr);
                updatePreview(widget);
            }
            catch (Exception e) {
                widget.setInvalid();
            }
        });
        return widget;
    }

    @Override
    public Widget visit(Bool val, EvaluatedQuestion parent) {
        BooleanWidget widget = widgetFactory.getBooleanWidget(parent);
        widget.setValue(val.getValue());
        widget.addListener((observable, oldValue, newValue) -> {
            try {
                widget.unSetInvalid();
                Value newExpr = new Bool(widget.isSelected());
                updateSymbolTable(widget.getParentQuestion(), newExpr);
                updatePreview(widget);
            }
            catch (Exception e) {
                widget.setInvalid();
            }

        });
        return widget;
    }

    @Override
    public Widget visit(Str val, EvaluatedQuestion parent) {
        StringWidget widget = widgetFactory.getStringWidget(parent);
        widget.setValue(val.getValue());
        widget.addListener((observable, oldValue, newValue) -> {
            java.lang.String fieldvalue = widget.getText();
            if(isLetterString(fieldvalue)){
                widget.unSetInvalid();
                Value newExpr = new Str(widget.getText());
                updateSymbolTable(widget.getParentQuestion(), newExpr);
                updatePreview(widget);
            }
            else{
                widget.setInvalid();
            }
        });
        return widget;
    }


    private void updateSymbolTable(EvaluatedQuestion changedQuestion, Value newValue){
        this.symbolTable.put(changedQuestion.getVarname(), newValue);
    }

    private boolean isLetterString(java.lang.String name) {
        return name.chars().allMatch(Character::isLetter);
    }

    public void showPreviewStage(){
        if(previewStage == null){
            Scene scene = new Scene(this.rootPane);
            scene.getStylesheets().add("customStylesheet.css");
            previewStage = new Stage();
            previewStage.setScene(scene);
            //previewStage.setOnCloseRequest(windowEvent -> previewStage = null);
            editor.updatePreview();
        }

        if(!previewStage.isShowing()){
            previewStage.show();
        }
    }
}
