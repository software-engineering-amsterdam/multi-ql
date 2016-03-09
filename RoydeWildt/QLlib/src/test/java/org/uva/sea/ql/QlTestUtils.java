package org.uva.sea.ql;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.checker.message.Message;
import org.uva.sea.ql.parser.QLRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 09/03/16.
 */
public class QlTestUtils {
    public static void compareMessages(List<Message> messages, List<String> checkList){

        List<String> testList = new ArrayList<>();
        for (Message m : messages)
            testList.add(m.getNode().toString());

        Assert.assertThat(testList, IsIterableContainingInAnyOrder.containsInAnyOrder(checkList.toArray()));
    }

    public static Form parseFromPath(String path){
        try {
            return QLRunner.ParseFromPath(path);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
