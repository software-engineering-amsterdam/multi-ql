package sc.ql.check;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sc.ql.check.SemanticMessage.Level;

public class SemanticResult {

	private final Map<Level, List<SemanticMessage>> levelToMessages = new HashMap<>();

	public SemanticResult() {

	}

	void add(SemanticMessage msg) {
		List<SemanticMessage> messages;

		messages = levelToMessages.get(msg.level());
		if (messages == null) {
			messages = new ArrayList<>();
			levelToMessages.put(msg.level(), messages);
		}
		messages.add(msg);
	}

	void addAll(List<SemanticMessage> messages) {
		messages.forEach(m -> add(m));
	}

	private List<SemanticMessage> get(Level level) {
		List<SemanticMessage> messages;

		messages = levelToMessages.get(level);
		if (messages == null) {
			return Collections.emptyList();
		}

		return Collections.unmodifiableList(messages);
	}

	public List<SemanticMessage> errors() {
		return get(Level.ERROR);
	}

	public List<SemanticMessage> warnings() {
		return get(Level.WARNING);
	}

	public List<SemanticMessage> messages() {
		List<SemanticMessage> allMessages;

		allMessages = new ArrayList<>();
		for (List<SemanticMessage> messages : levelToMessages.values()) {
			allMessages.addAll(messages);
		}

		return Collections.unmodifiableList(allMessages);
	}
}