package nl.uva.ql.gui.panel;

import java.util.List;

import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.evaluator.Evaluator;

public class BoxPanel extends Panel{
	
	private List<Panel> panels;
	
	public BoxPanel(List<Panel> panels) {
		this.panels = panels;
		// This layout is for components being added to this box panel (every component in a new line)
		// this.setLayout(new MigLayout("insets 2 0 0 0, wrap, fill"));
		addToPanel(panels);
	}
	
	private void addToPanel(List<Panel> panels){
		for(Panel panel: panels) {
			// hidemode 3 is for not showing ifpanel in the box panel, in case it's visibility is false
			// wrap is for adding every component in a new line
			addToPanel(panel, "hidemode 3, growx, wrap");
		}
	}

	@Override
	public void update(Evaluator evaluator, Identifier identifier) {
		for(Panel panel: panels) {
			panel.update(evaluator, identifier);
		}
	}

}
