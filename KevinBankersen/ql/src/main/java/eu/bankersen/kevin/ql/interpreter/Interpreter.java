package eu.bankersen.kevin.ql.interpreter;

import java.util.ArrayList;
import java.util.List;

import eu.bankersen.kevin.ql.ast.TopDownQuestionVisitor;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.gui.ViewListener;

public class Interpreter implements ViewListener {

    private final Form form;
    private final Environment environment;
    private List<DataListener> dataListeners;

    public Interpreter(Form form) {
	this.form = form;
	this.dataListeners = new ArrayList<>();
	this.environment = new Environment();

	// Construct the environment
	form.accept(new TopDownQuestionVisitor<Environment>() {

	    @Override
	    public void visit(NormalQuestion o, Environment environment) {
		environment.addQuestion(o.name());
	    }

	    @Override
	    public void visit(ComputedQuestion o, Environment environment) {
		environment.addQuestion(o.name());
	    }
	}, environment);
    }

    private void evalForm() {

	do {
	    form.evalForm(environment);
	} while (environment.isUpdated());

	System.out.println(environment);
	dataUpdate();
    }

    public void addDataListener(DataListener listener) {
	dataListeners.add(listener);
	this.evalForm();
    }

    private void dataUpdate() {
	dataListeners.forEach(listener -> listener.dataUpdate(environment));
    }

    @Override
    public void viewUpdate(String name, QLValue value) {
	environment.updateQuestion(name, value);
	this.evalForm();
    }
}
