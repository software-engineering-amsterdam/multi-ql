package eu.bankersen.kevin.ql.interperter;

import java.util.Map;

import eu.bankersen.kevin.ql.form.ast.values.Value;

public interface DataListener {

    void dataUpdate(Map<String, Value> context);

}
