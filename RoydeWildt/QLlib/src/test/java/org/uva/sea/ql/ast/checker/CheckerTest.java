package org.uva.sea.ql.ast.checker;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.*;
import org.uva.sea.ql.checker.Checker;
import org.uva.sea.ql.checker.message.Message;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.parser.QLRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 12/02/16.
 */
public class CheckerTest {

    @Test public void UndefinedVarsCheckerTest1(){

        List<String> checkList = new ArrayList<>();
        checkList.add("Q4_Undefined");
        checkList.add("Q7_after");

        Form f = parseFromPath("src/test/resources/undefined1.ql");
        List<Message> messages = (new Checker()).undefinedChecker(f);

        compareMessages(messages, checkList);
    }

    @Test public void DuplicateCheckerTest1(){

        List<String> checkList = new ArrayList<>();
        checkList.add("Q1_duplicate_error");
        checkList.add("Q2_duplicate_warning");

        Form f = parseFromPath("src/test/resources/duplicates1.ql");
        List<Message> messages = (new Checker()).duplicateChecker(f);

        compareMessages(messages, checkList);
    }

    @Test public void InvalidExpressionCheckerTest1(){

        List<String> checkList = new ArrayList<>();
        checkList.add("true && 1");
        checkList.add("true + true");
        checkList.add("!1");
        checkList.add("-true");
        checkList.add("Q3_boolean + 1");
        checkList.add("Q1_money == true");
        checkList.add("Q1_money + Q3_boolean");
        checkList.add("Q2_money == Q4_boolean");

        Form f = parseFromPath("src/test/resources/expressions1.ql");
        List<Message> messages = (new Checker()).invalidExpressionChecker(f);

        compareMessages(messages, checkList);
    }

    @Test public void InvalidExpressionCheckerTest2(){

        List<String> checkList = new ArrayList<>();
        checkList.add("1 + 1 || true && true");
        checkList.add("true || true || 1");
        checkList.add("Q2_money + Q2_money == Q3_boolean");
        checkList.add("Q1_boolean + 1");
        checkList.add("Q3_boolean != 1");

        Form f = parseFromPath("src/test/resources/expressions2.ql");
        List<Message> messages = (new Checker()).invalidExpressionChecker(f);

        compareMessages(messages, checkList);
    }

    @Test public void InvalidConditionCheckerTest1(){

        List<String> checkList = new ArrayList<>();
        checkList.add("1");
        checkList.add("Q1_money");
        checkList.add("Q3_null");

        Form f = parseFromPath("src/test/resources/conditions1.ql");
        List<Message> messages = (new Checker()).invalidConditionChecker(f);

        compareMessages(messages,checkList);
    }

    private void compareMessages(List<Message> messages, List<String> checkList){

        List<String> testList = new ArrayList<>();
        for (Message m : messages)
            testList.add(m.getNode().toString());

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
