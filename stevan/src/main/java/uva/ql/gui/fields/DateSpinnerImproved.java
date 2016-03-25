package uva.ql.gui.fields;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

import uva.ql.ast.variables.Variable;
import uva.ql.gui.fields.documentfilters.DateFilter;

public class DateSpinnerImproved extends JSpinner {
	/*
	 * Not really improved as of yet
	 */
	public DateSpinnerImproved(final Variable var) {
		super(new SpinnerDateModel());
		
		this.setName(var.getName());
    	PlainDocument doc = new PlainDocument();
    	doc.setDocumentFilter(new DateFilter(var, "^[0-31][-][0-12][-][0-4000]$"));
    	
    	JTextComponent txt = ((JSpinner.DefaultEditor) this.getEditor()).getTextField();
        txt.setDocument(doc);
    	
    	final SimpleDateFormat format = ((JSpinner.DateEditor) this.getEditor()).getFormat();
        format.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        format.applyPattern("dd-MM-yyyy");
        
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(this, "dd-MM-yyyy");
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
