package org.uva.ql.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.uva.ql.ast.expr.Context;

public class QLQuestionaire {

	private List<QLForm> forms = new ArrayList<QLForm>();

	public QLQuestionaire() {

	}

	public void addForm(QLForm form) {
		forms.add(form);
	}

	public void show() {
		JFrame jframe;
		Context context;

		context = new Context();

		for (QLForm form : forms) {
			form.setContext(context);
		}

		jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocationRelativeTo(null);

		jframe.setContentPane(forms.get(0).getComponent());
		jframe.setSize(400, 300);
		jframe.setVisible(true);
	}

}
