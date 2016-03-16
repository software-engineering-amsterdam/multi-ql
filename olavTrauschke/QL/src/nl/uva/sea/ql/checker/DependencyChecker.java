package nl.uva.sea.ql.checker;

import java.util.*;
import nl.uva.sea.ql.QuestionIdentCollector;
import nl.uva.sea.ql.ast.ConditionalStatement;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.ast.question.Question;
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 * Visitor to check the dependencies between <code>Question</code>s in an ast.
 * 
 * @author Olav Trauschke
 * @version 10-mar-2016
 */
public class DependencyChecker extends GeneralizedASTVisitor {
    
    /**
     * Error presented to the user when a reference to an undefined question was
     * found.
     */
    public static final String REFERENCE_TO_UNDEFINED_QUESTION_ERROR
            = "A reference to the following undefined Question was found: ";
    
    /**
     * Error presented to the user when one or more cyclic dependencies between
     * questions were found.
     */
    public static final String CYCLIC_DEPENDENCIES_ERROR
            = "Cyclic dependencies were found between the following questions: ";
    
    /**
     * <code>String</code> used to seperator <code>Ident</code>s in error messages
     * presented to the user.
     */
    public static final String IDENT_SEPERATOR = ", ";
    
    private final List<String> errors;
    private final DirectedGraph<Ident,DefaultEdge> dependencyGraph;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param identifiers an <code>Iterable</code> of the <code>Ident</code>s of
     *                      <code>Question</code>s that exist in the ast to
     *                      <code>visit</code>.
     */
    public DependencyChecker(Iterable<Ident> identifiers) {
        errors = new ArrayList<>();
        dependencyGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
        addVertrices(identifiers);
    }
    
    /**
     * Add edges from the <code>identifier</code> of <code>question</code> to the
     * <code>identifier</code>s of <code>Question</code>s <code>question</code>
     * depends on to <code>this DependencyChecker</code>'s
     * <code>dependencyGraph</code> and add an error if a <code>Question</code>
     * was found that depends on an undefined <code>Question</code>.
     * 
     * @param question the <code>Question</code> to check
     */
    @Override
    public void visit(Question question) {
        Ident startIdentifier = question.getIdentifier();
        assert dependencyGraph.containsVertex(startIdentifier);
        IdentCollector collector = new IdentCollector();
        if (question.isComputed()) {
            question.calculationAccept(collector);
            Iterable<Ident> identifiersInCalculation = collector.getIdentifiers();
            addEdges(startIdentifier, identifiersInCalculation);
        }
    }
    
    /**
     * Add edges from the <code>identifier</code>s of all <code>Question</code>s
     * in a <code>ConditionalStatement</code> to all <code>Ident</code>s used
     * in its <code>condition</code> and add an error if a
     * <code>ConditionalStatement</code> was found which <code>condition</code>
     * refers to an undefined <code>Question</code>.
     * 
     * @param statement the <code>ConditionalStatement</code> to check
     */
    @Override
    public void visit(ConditionalStatement statement) {
        IdentCollector dependenciesCollector = new IdentCollector();
        statement.conditionAccept(dependenciesCollector);
        Iterable<Ident> dependencies = dependenciesCollector.getIdentifiers();
        
        QuestionIdentCollector containedQuestionsCollector = new QuestionIdentCollector();
        statement.accept(containedQuestionsCollector);
        Iterable<Ident> containedQuestions = containedQuestionsCollector.obtainIdentifiers();
        
        for(Ident startIdentifier : containedQuestions) {
            addEdges(startIdentifier, dependencies);
        }
    }
    
    /**
     * Add an error stating which <code>Ident</code>s were involved if
     * cyclic dependencies were found in <code>this DependencyChecker</code>'s
     * <code>DependencyGraph</code>.
     */
    public void detectCyclicDependencies() {
        CycleDetector detector = new CycleDetector(dependencyGraph);
        Set<Ident> identifiersContributingToCycles = detector.findCycles();
        if (!identifiersContributingToCycles.isEmpty()) {
            String error = CYCLIC_DEPENDENCIES_ERROR;
            Iterator iterator = identifiersContributingToCycles.iterator();
            error += iterator.next();
            while (iterator.hasNext()) {
                error += IDENT_SEPERATOR + iterator.next();
            }
            errors.add(error);
        }
    }
    
    /**
     * @return a <code>List</code> of the errors generated during the
     *          <code>visit</code>s <code>this DependencyChecker</code> has
     *          performed and while it searched for cyclic dependencies between
     *          the visited <code>Question</code>s.
     */
    public List<String> getErrors() {
        return errors;
    }
    
    /**
     * Add all <code>Ident</code>s in <code>identifiers</code> to
     * <code>this DependencyChecker</code>'s <code>dependencyGraph</code>.
     * 
     * @param identifiers an <code>Iterable</code> containing the
     *                      <code>Ident</code>s to add
     */
    private void addVertrices(Iterable<Ident> identifiers) {
        for (Ident identifier : identifiers) {
            dependencyGraph.addVertex(identifier);
        }
    }
    
    /**
     * Add an edge from the <code>startIdent</code> to each of the
     * <code>Ident</code>s in <code>endIdents</code> to
     * <code>this DependencyChecker</code>'s <code>dependencyGraph</code>
     * 
     * @param startIdent the <code>Ident</code> all added edges should start at
     * @param endIdents an <code>Iterable</code> over the <code>Ident</code>s
     *                  the added edges should end at
     */
    private void addEdges(Ident startIdent, Iterable<Ident> endIdents) {
        for (Ident endIdent : endIdents) {
            if (dependencyGraph.containsVertex(endIdent)) {
                dependencyGraph.addEdge(startIdent, endIdent);
            }
            else {
                errors.add(REFERENCE_TO_UNDEFINED_QUESTION_ERROR + endIdent);
            }
        }
    }
    
}
