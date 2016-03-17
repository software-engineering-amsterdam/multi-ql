package org.uva.sea.ql.evaluator;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.*;
import org.uva.sea.ql.ast.tree.atom.Literal;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.evaluator.value.Value;
import org.uva.sea.ql.parser.QLRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 09/03/16.
 */
public class FormEvaluatorTest {
    @Test public void FormEvaluatorTest1(){

        List<String> checkListVars = new ArrayList<>();
        checkListVars.add("Q1");
        checkListVars.add("Q2");

        Form f = parseFromPath("src/test/resources/gui1.ql");
        FormEvaluator fe = new FormEvaluator(f);
        List<EvaluatedQuestion> questions = fe.getEvaluatedQuestions();

        compareQuestionVars(questions, checkListVars);
    }

    @Test public void FormEvaluatorTest2(){

        List<String> checkListVars = new ArrayList<>();
        checkListVars.add("Q1");
        checkListVars.add("Q2");
        checkListVars.add("Q5_visible");

        Form f = parseFromPath("src/test/resources/gui2.ql");
        FormEvaluator fe = new FormEvaluator(f);
        List<EvaluatedQuestion> questions = fe.getEvaluatedQuestions();

        compareQuestionVars(questions, checkListVars);
    }

    @Test public void FormEvaluatorTest3(){

        List<String> checkListVars = new ArrayList<>();
        checkListVars.add("Q1");
        checkListVars.add("Q2");
        checkListVars.add("Q5_visible");

        Form f = parseFromPath("src/test/resources/gui3.ql");
        FormEvaluator fe = new FormEvaluator(f);
        List<EvaluatedQuestion> questions = fe.getEvaluatedQuestions();

        compareQuestionVars(questions, checkListVars);
    }

    @Test public void FormEvaluatorTest4(){

        List<String> checkListVars = new ArrayList<>();
        checkListVars.add("true");
        checkListVars.add("true");
        checkListVars.add("100");
        checkListVars.add("100");
        checkListVars.add("text");
        checkListVars.add("text");

        Form f = parseFromPath("src/test/resources/gui4.ql");
        FormEvaluator fe = new FormEvaluator(f);
        List<EvaluatedQuestion> questions = fe.getEvaluatedQuestions();

        compareQuestionVals(questions, checkListVars);
    }

    private void compareQuestionVars(List<EvaluatedQuestion> questions, List<String> checkList){

        List<String> testList = new ArrayList<>();
        for (EvaluatedQuestion q : questions)
            testList.add(q.getVarname().toString());

        Assert.assertThat(testList, IsIterableContainingInAnyOrder.containsInAnyOrder(checkList.toArray()));
    }

    private void compareQuestionVals(List<EvaluatedQuestion> questions, List<String> checkList){

        List<String> testList = new ArrayList<>();
        for (EvaluatedQuestion q : questions){
            Value v = q.getValue();
            testList.add(v.getValue().toString());
        }

        Assert.assertThat(testList, IsIterableContainingInAnyOrder.containsInAnyOrder(checkList.toArray()));
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
