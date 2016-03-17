package sc.qls.ui.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;

import sc.ql.eval.Environment;
import sc.ql.ui.widget.AbstractUIWidget;
import sc.ql.ui.widget.UIWidgetChoice;
import sc.ql.ui.widget.UIWidgetChoices;
import sc.ql.ui.widget.UIWidgetStyle;
import sc.ql.value.Value;

public class UISliderWidget extends AbstractUIWidget {

	private final Map<Integer, UIWidgetChoice> indexToChoiceMap;
	private final UIWidgetChoices choices;
	private final JPanel panel;
	private final JSlider slider;
	private final Hashtable<Integer, JLabel> labelTable;

	private final UIWidgetStyle style = new UIWidgetStyle(UIManager.getDefaults().getFont("JLabel.font"),
			new Dimension(140, 20), Color.BLACK);

	public UISliderWidget(Environment env, String variableName, UIWidgetChoices choices) {
		super(env, variableName, choices.defaultValue().getValue());

		this.choices = choices;

		slider = new JSlider(0, choices.values().size() - 1, choices.indexOf(choices.defaultValue()));
		slider.addChangeListener(e -> {
			// Change event will be fired when starting the drag, and stopping.
			// We want to capture the value when dragging the slider has
			// stopped.
			if (!slider.getValueIsAdjusting()) {
				setValue(getViewValue());
			}
		});

		indexToChoiceMap = new HashMap<>();
		labelTable = new Hashtable<>();
		for (UIWidgetChoice choice : choices.values()) {
			int index;

			index = choices.indexOf(choice);
			indexToChoiceMap.put(index, choice);
			labelTable.put(index, new JLabel(choice.getName()));
		}

		slider.setLabelTable(labelTable);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);

		panel = new JPanel(new BorderLayout());
		panel.add(slider, BorderLayout.CENTER);

		setStyle(style);
	}

	@Override
	public void setVisible(boolean visible) {
		slider.setVisible(visible);
	}

	@Override
	public void setEditable(boolean editable) {
		slider.setEnabled(editable);
	}

	@Override
	public void setStyle(UIWidgetStyle style) {
		panel.setPreferredSize(new Dimension(style.getWidth(), style.getHeight()));
		slider.setFont(style.getFont());

		for (JLabel label : labelTable.values()) {
			label.setForeground(style.getColor());
		}
	}

	@Override
	public UIWidgetStyle getStyle() {
		return style;
	}

	@Override
	public JComponent getComponent() {
		return panel;
	}

	private UIWidgetChoice getSelected() {
		return indexToChoiceMap.get(slider.getValue());
	}

	private void setSelected(UIWidgetChoice choice) {
		slider.setValue(choices.indexOf(choice));
	}

	@Override
	protected Value getViewValue() {
		return getSelected().getValue();
	}

	@Override
	protected void setViewValue(Value value) {
		setSelected(choices.getByValue(value));
	}
}
