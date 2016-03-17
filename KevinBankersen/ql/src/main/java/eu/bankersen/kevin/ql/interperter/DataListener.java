package eu.bankersen.kevin.ql.interperter;

import java.util.Map;

import eu.bankersen.kevin.ql.ast.values.QLValue;

public interface DataListener {

    void dataUpdate(Map<String, QLValue> context);

}
