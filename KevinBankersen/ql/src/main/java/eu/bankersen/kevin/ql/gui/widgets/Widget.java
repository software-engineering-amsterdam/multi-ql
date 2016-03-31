package eu.bankersen.kevin.ql.gui.widgets;

import eu.bankersen.kevin.ql.gui.ViewListener;
import eu.bankersen.kevin.ql.interperter.DataListener;
import javafx.scene.layout.Pane;

public interface Widget extends DataListener {

	Pane draw();

	void addViewListener(ViewListener listener);

}
