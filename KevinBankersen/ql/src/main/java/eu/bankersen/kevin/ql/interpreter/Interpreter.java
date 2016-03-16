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
	form.accept(new TopDownQuestionVisitor<Void>() {

	    @Override
	    public Void visit(NormalQuestion o, Void empty) {
		environment.addQuestion(o.name());
		return null;
	    }

	    @Override
	    public Void visit(ComputedQuestion o, Void empty) {
		environment.addQuestion(o.name());
		return null;
	    }
	}, null);
    }

    private void evalForm() {
	Environment previousEnv;

	do {
	    System.out.println("one");
	    previousEnv = environment;
	    form.evalForm(environment);
	} while (!previousEnv.equals(environment));

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
