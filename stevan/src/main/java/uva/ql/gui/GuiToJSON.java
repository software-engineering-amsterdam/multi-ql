package uva.ql.gui;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.swing.JFrame;

public class GuiToJSON {

	private final List<List<String>> jsonData;
	StringWriter stWriter = new StringWriter();
	
	public GuiToJSON(JFrame frame) {
		
		ComponentsInPanel cip = new ComponentsInPanel();
		jsonData = cip.collectComponents(frame.getContentPane());
				
		Map<String, Boolean> config = new HashMap<>(0);
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonWriterFactory jwf = Json.createWriterFactory(config);
		
		JsonObjectBuilder model = Json.createObjectBuilder();
		JsonArrayBuilder questions = Json.createArrayBuilder();
		JsonObjectBuilder q = Json.createObjectBuilder();
		
		for(List<String> data : jsonData) {
			q.add(data.get(0), data.get(1));
			questions.add(q);
		}
		
		model.add("form", questions);
		
		JsonWriter jsonWriter = jwf.createWriter(stWriter);
		jsonWriter.writeObject(model.build());
		jsonWriter.close();
	}
	
	public String getJsonString() {
		return stWriter.toString();
	}
}
