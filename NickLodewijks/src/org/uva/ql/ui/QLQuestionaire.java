package org.uva.ql.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Context.ContextListener;

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

		context.addContextListener(new ContextListener() {

			@Override
			public void contextChanged(Context context) {
				jframe.pack();
			}
		});

		jframe.setContentPane(forms.get(0).getComponent());
		jframe.pack();
		jframe.setVisible(true);
	}

}
