package eu.bankersen.kevin.ql.interpreter;

import java.util.LinkedHashMap;
import java.util.Map;

import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;

public class Environment {

    private final Map<String, QuestionData> environment;
    private Boolean active;
    private Boolean updates;

    public Environment() {
	this.environment = new LinkedHashMap<>();
	this.active = true;
	this.updates = false;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (String entry : environment.keySet()) {
	    QuestionData data = environment.get(entry);
	    if (data.active) {
		sb.append(String.format("%s = %s\n", entry, data.value));
	    }
	}
	return sb.toString();
    }

    public Boolean isUpdated() {
	if (updates) {
	    updates = false;
	    return true;
	} else {
	    return false;
	}
    }

    public Boolean isEnvironmentActive() {
	return active;
    }

    public void setEnvironmentActive(Boolean active) {
	this.active = active;
    }

    public void addQuestion(String name) {
	environment.put(name, new QuestionData(new UndifinedValue(), true));
    }

    public void updateQuestion(String name) {
	updateQuestion(name, getValue(name));
    }

    public void updateQuestion(String name, QLValue value) {

	if (!active) {
	    value = new UndifinedValue();
	}

	if (!getValue(name).equals(value)) {
	    updates = true;
	}
	environment.put(name, new QuestionData(value, active));
    }

    public QLValue getValue(String name) {
	return environment.get(name).value;
    }

    public Boolean getVisible(String name) {
	return environment.get(name).active;
    }

    private class QuestionData {
	private final Boolean active;
	private final QLValue value;

	QuestionData(QLValue value, Boolean active) {
	    this.active = active;
	    this.value = value;
	}
    }

}
