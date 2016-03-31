package sc.ql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sc.ql.SemanticMessage.Level;

public class SemanticErrors {

	private final Map<Level, List<SemanticMessage>> levelToMessages = new HashMap<>();

	public SemanticErrors() {

	}

	void add(SemanticMessage error) {
		List<SemanticMessage> messages;

		messages = levelToMessages.get(error.getLevel());
		if (messages == null) {
			messages = new ArrayList<>();
			levelToMessages.put(error.getLevel(), messages);
		}
		messages.add(error);
	}

	void addAll(SemanticErrors result) {
		result.getAllMessages().forEach(m -> add(m));
	}

	private List<SemanticMessage> getByLevel(Level level) {
		List<SemanticMessage> messages;

		messages = levelToMessages.get(level);
		if (messages == null) {
			return Collections.emptyList();
		}

		return Collections.unmodifiableList(messages);
	}

	public List<SemanticMessage> errors() {
		return getByLevel(Level.ERROR);
	}

	public List<SemanticMessage> warnings() {
		return getByLevel(Level.WARNING);
	}

	private List<SemanticMessage> getAllMessages() {
		List<SemanticMessage> allMessages;

		allMessages = new ArrayList<>();
		for (List<SemanticMessage> messages : levelToMessages.values()) {
			allMessages.addAll(messages);
		}

		return Collections.unmodifiableList(allMessages);
	}
}