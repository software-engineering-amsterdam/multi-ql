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

        Form f1 = null;
        try {
            f1 = QLRunner.ParseFromPath("src/test/resources/undefined1.ql");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Checker chk = new Checker();
        List<Message> messages = chk.undefinedChecker(f1);
        List<String> testList = new ArrayList<>();

        for (Message m : messages)
            testList.add(m.getNode().toString());

        List<String> checkList = new ArrayList<>();
        checkList.add("Q4_Undefined");

        Assert.assertThat(testList, IsIterableContainingInAnyOrder.containsInAnyOrder(checkList.toArray()));
    }

    @Test public void DuplicateCheckerTest1(){

        Form f1 = null;
        try {
            f1 = QLRunner.ParseFromPath("src/test/resources/duplicates1.ql");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Checker chk = new Checker();
        List<Message> messages = chk.duplicateChecker(f1);
        List<String> testList = new ArrayList<>();

        for (Message m : messages) {
            Question q = (Question) m.getNode();
            testList.add(q.getVarname().toString());
        }

        List<String> checkList = new ArrayList<>();
        checkList.add("Q1_duplicate_error");
        checkList.add("Q2_duplicate_warning");

        Assert.assertThat(testList, IsIterableContainingInAnyOrder.containsInAnyOrder(checkList.toArray()));
    }

    @Test public void InvalidExpressionCheckerTest1(){

        Form f1 = null;
        try {
            f1 = QLRunner.ParseFromPath("src/test/resources/expressions1.ql");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Checker chk = new Checker();
        List<Message> messages = chk.invalidExpressionChecker(f1);
        List<String> testList = new ArrayList<>();

        for (Message m : messages)
            testList.add(m.getNode().toString());

        List<String> checkList = new ArrayList<>();
        checkList.add("true && 1");
        checkList.add("true + true");
        checkList.add("!1");
        checkList.add("-true");
        checkList.add("Q3_boolean + 1");
        checkList.add("Q1_money == true");
        checkList.add("Q1_money + Q3_boolean");
        checkList.add("Q2_money == Q4_boolean");

        Assert.assertThat(testList, IsIterableContainingInAnyOrder.containsInAnyOrder(checkList.toArray()));
    }

    @Test public void InvalidExpressionCheckerTest2(){

        Form f1 = null;
        try {
            f1 = QLRunner.ParseFromPath("src/test/resources/expressions2.ql");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Checker chk = new Checker();
        List<Message> messages = chk.invalidExpressionChecker(f1);
        List<String> testList = new ArrayList<>();

        for (Message m : messages)
            testList.add(m.getNode().toString());

        List<String> checkList = new ArrayList<>();
        checkList.add("1 + 1 || true && true");
        checkList.add("true || true || 1");
        checkList.add("Q2_money + Q2_money == Q3_boolean");
        checkList.add("Q1_boolean + 1");
        checkList.add("Q3_boolean != 1");

        Assert.assertThat(testList, IsIterableContainingInAnyOrder.containsInAnyOrder(checkList.toArray()));
    }

    @Test public void InvalidConditionCheckerTest1(){

        Form f1 = null;
        try {
            f1 = QLRunner.ParseFromPath("src/test/resources/conditions1.ql");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Checker chk = new Checker();
        List<Message> messages = chk.invalidConditionChecker(f1);
        List<String> testList = new ArrayList<>();

        for (Message m : messages)
            testList.add(m.getNode().toString());

        List<String> checkList = new ArrayList<>();
        checkList.add("1");
        checkList.add("Q1_money");
        checkList.add("Q3_null");

        Assert.assertThat(testList, IsIterableContainingInAnyOrder.containsInAnyOrder(checkList.toArray()));
    }


}
