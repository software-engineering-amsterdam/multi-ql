package nl.nicasso.ql.semanticAnalysis;

import java.util.ArrayList;
import java.util.List;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.semanticAnalysis.messageHandling.MessageHandler;
import nl.nicasso.ql.semanticAnalysis.messageHandling.errors.CyclomaticDependency;
import nl.nicasso.ql.utils.Pair;

public class QuestionDependencies {

	private List<Pair<Identifier>> dependencies;
	private MessageHandler messageHandler;

	public QuestionDependencies(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;

		this.dependencies = new ArrayList<Pair<Identifier>>();
	}

	public void add(Pair<Identifier> pair) {
		dependencies.add(pair);
	}

	public void checkForCyclicDependencies() {
		transitiveClosureOnDependencyPairs(dependencies);

		int size = dependencies.size();
		for (int i = 0; i < size; i++) {
			Pair<Identifier> currentPair = new Pair<Identifier>(dependencies.get(i).getRight(),
					dependencies.get(i).getLeft());
			if (checkPairExistance(currentPair)) {
				messageHandler.addErrorMessage(new CyclomaticDependency(currentPair.getLeft().getLocation()));
			}
		}

	}

	private void transitiveClosureOnDependencyPairs(List<Pair<Identifier>> initialDependencies) {
		boolean keepRunning = true;

		while (keepRunning) {
			int amountOfDependencies = dependencies.size();

			for (int i = 0; i < amountOfDependencies; i++) {
				Pair<Identifier> transitivePair = findNewTransitiveClosurePair(dependencies.get(i));
				if (transitivePair != null) {
					dependencies.add(transitivePair);
				}
			}

			if (initialDependencies.size() == dependencies.size()) {
				keepRunning = false;
			} else {
				initialDependencies = dependencies;
			}
		}
	}

	private Pair<Identifier> findNewTransitiveClosurePair(Pair<Identifier> pair) {
		for (Pair<Identifier> currentPair : dependencies) {
			if (currentPair.getLeft().equals(pair.getRight())) {
				Pair<Identifier> possibleNewPair = new Pair<Identifier>(pair.getLeft(), currentPair.getRight());
				if (!checkPairExistance(possibleNewPair)) {
					return possibleNewPair;
				}
			}
		}
		return null;
	}

	private boolean checkPairExistance(Pair<Identifier> pair) {
		for (Pair<Identifier> currentPair : dependencies) {
			if (currentPair.equals(pair)) {
				return true;
			}
		}
		return false;
	}

}