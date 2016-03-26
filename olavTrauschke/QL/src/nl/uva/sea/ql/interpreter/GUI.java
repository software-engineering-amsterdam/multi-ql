package nl.uva.sea.ql.interpreter;

import java.awt.Container;
import java.awt.LayoutManager;
import java.util.*;
import javax.swing.*;
import nl.uva.sea.ql.interpreter.listener.*;

/**
 * Objects of this class are graphical representations of questionnaires.
 * 
 * @author Olav Trauschke
 * @version 26-mrt-2016
 */
public class GUI implements DisplayableQuestionListener {
    
    private final JFrame frame;
    //Map from all DisplayableQuestions this GUI can display to whether or not
    //they are currently displayed
    private final Map<DisplayableQuestion,Boolean> isDisplayedPerQuestion;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param name a <code>String</code> containing the name of the questionnaire
     *              the constructed <code>GUI</code> will represent
     * @param theQuestions a <code>List</code> of the questions in the questionnaire
     *                      the constructed <code>GUI</code> will represent,
     *                      represented by <code>DisplayableQuestion</code>s
     */
    public GUI(String name, List<DisplayableQuestion> theQuestions) {
        frame = new JFrame(name);
        JPanel contentPane = new JPanel(true);
        LayoutManager layoutManager = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(layoutManager);
        frame.setContentPane(contentPane);
        
        isDisplayedPerQuestion = new LinkedHashMap<>(theQuestions.size());
        theQuestions.forEach((DisplayableQuestion question) -> isDisplayedPerQuestion.put(question, false));
        theQuestions.forEach((DisplayableQuestion question) -> question.addListener(this));
        
        //TODO add save button and close operation
    }
    
    /**
     * Show <code>this GUI</code> to the user.
     */
    public void run() {
        addQuestionsToDisplay();
        frame.pack();
        frame.setVisible(true);
        
        //TODO wait until questionnaire was finished
    }
    
    /**
     * Add all <code>DisplayableQuestions</code> from <code>theQuestions</code>
     * provided to the constructor when <code>this GUI</code> was constructed
     * that are to be displayed (for which
     * {@link nl.uva.sea.ql.interpreter.DisplayableQuestion#isToDisplay() isToDisplay()}
     * returns <code>true</code>) to the <code>JFrame</code> that will be
     * displayed to the user when <code>this GUI</code> is
     * {@link nl.uva.sea.ql.interpreter.GUI#run() run}.
     */
    private void addQuestionsToDisplay() {
        Container contentPane = frame.getContentPane();
        isDisplayedPerQuestion.keySet().stream()
                .filter((DisplayableQuestion question) -> question.isToDisplay())
                .forEach((DisplayableQuestion question) -> contentPane.add(question));
    }
    
    /**
     * Handle a <code>DisplayableQuestionChangeEvent</code> that was passed to
     * this method to signal a <code>DisplayableQuestion</code>
     * (the <code>source</code> of the <code>event</code>) that was provided to
     * the constructor when <code>this GUI</code> was constructed was changed
     * and adapt <code>this GUI</code> to the specified change
     * 
     * @param event a <code>DisplayableQuestionChangeEvent</code> describing which
     *              <code>DisplayableQuestion</code> changed (the source of the
     *              event) and whether it changed with respect to having to be
     *              displayed
     */
    @Override
    public void questionChanged(DisplayableQuestionChangeEvent event) {
        DisplayableQuestion question = (DisplayableQuestion) event.getSource();
        if (event.toDisplayChanged()) {
            if (isDisplayedPerQuestion.get(question)) {
                frame.getContentPane().remove(question);
                isDisplayedPerQuestion.put(question, false);
            }
            else {
                int index = calculateIndex(question);
                frame.getContentPane().add(question, index);
                isDisplayedPerQuestion.put(question, true);
            }
            frame.revalidate();
        }
        frame.repaint();
    }
    
    /**
     * Calculate the index at which a specified <code>DisplayableQuestion</code>
     * should be added to <code>this GUI</code>'s <code>frame</code> to perceive
     * the order of the <code>questions</code> in which they were passed to the
     * constructor when <code>this GUI</code> was constructed.
     * 
     * @param question a <code>DisplayableQuestion</code> to find an index for
     */
    private int calculateIndex(DisplayableQuestion questionToIndex) {
        Set<DisplayableQuestion> questions = isDisplayedPerQuestion.keySet();
        Iterator<DisplayableQuestion> questionIterator = questions.iterator();
        DisplayableQuestion currentQuestion = questionIterator.next();
        int index = 0;
        while (!questionToIndex.equals(currentQuestion)) {
            if (isDisplayedPerQuestion.get(currentQuestion)) {
                index++;
            }
            currentQuestion = questionIterator.next();
        }
        return index;
    }
    
}
