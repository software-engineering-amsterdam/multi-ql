package uva.ql.gui.fields;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uva.ql.ast.variables.Variable;

public class DateSpinner extends JSpinner {
	
	public DateSpinner(final Variable var) {
		super(new SpinnerDateModel());
		
		final SimpleDateFormat format = ((JSpinner.DateEditor) this.getEditor()).getFormat();
        format.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        format.applyPattern("dd-MM-yyyy");
        
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(this, "dd-MM-yyyy");
        this.setName(var.getName());
        this.setEditor(dateEditor);
        this.setValue(new Date(-1));
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				Date date = (Date) ((JSpinner) e.getSource()).getValue();
                //var.setValue(format.format(date));
                //System.out.println(format.format(date));
			}
			
		});
	}
}
