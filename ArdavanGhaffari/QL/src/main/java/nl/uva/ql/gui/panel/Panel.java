package nl.uva.ql.gui.panel;

import java.awt.Component;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.evaluator.Evaluator;

public abstract class Panel extends JPanel{
	
	public Panel() {
		this.setLayout(new MigLayout("fillx, insets 2 0 0 0"));
	}
	
	public void addToPanel(Component component){
		add(component);
	}
	
	public void addToPanel(Component component, String layoutArgs) {
		add(component, layoutArgs);
	}
	
	public abstract void update(Evaluator evaluator, Identifier identifier);

}
