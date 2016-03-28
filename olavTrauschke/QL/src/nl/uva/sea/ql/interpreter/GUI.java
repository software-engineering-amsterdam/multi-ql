package nl.uva.sea.ql.interpreter;

//java.awt cannot be imported in total because of a naming conflict with java.util
import java.awt.AWTEvent;
import java.awt.Container;
import java.awt.LayoutManager;
import java.util.*;
import java.util.function.Consumer;
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
    private final Map<BasicDisplayableQuestion,Boolean> isDisplayedPerQuestion;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param name a <code>String</code> containing the name of the questionnaire
     *              the constructed <code>GUI</code> will represent
     * @param theQuestions a <code>List</code> of the questions in the questionnaire
     *                      the constructed <code>GUI</code> will represent,
     *                      represented by <code>BasicDisplayableQuestion</code>s
     */
    public GUI(String name, List<BasicDisplayableQuestion> theQuestions) {
        frame = new JFrame(name);
        JPanel contentPane = new JPanel(true);
        LayoutManager layoutManager = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(layoutManager);
        frame.setContentPane(contentPane);
        
        isDisplayedPerQuestion = new LinkedHashMap<>(theQuestions.size());
        theQuestions.forEach((BasicDisplayableQuestion question) -> isDisplayedPerQuestion.put(question, false));
        theQuestions.forEach((BasicDisplayableQuestion question) -> question.addListener(this));
    }
    
    /**
     * Show <code>this GUI</code> to the user.
     * 
     * @param callback a <code>Consumer</code> of <code>AWTEvent</code>s to call
     *                  when the user has closed <code>this GUI</code>, should
     *                  at least properly shutdown the application
     */
    public void run(Consumer<AWTEvent> callback) {
        frame.addWindowListener((WindowClosingListener) callback::accept);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(callback::accept);
        frame.getContentPane().add(saveButton);
        
        addQuestionsToDisplay();
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Add all <code>DisplayableQuestions</code> from <code>theQuestions</code>
     * provided to the constructor when <code>this GUI</code> was constructed
     * that are to be displayed (for which
     * {@link nl.uva.sea.ql.interpreter.BasicDisplayableQuestion#isToDisplay() isToDisplay()}
     * returns <code>true</code>) to the <code>JFrame</code> that will be
     * displayed to the user when <code>this GUI</code> is
     * {@link nl.uva.sea.ql.interpreter.GUI#run() run}.
     */
    private void addQuestionsToDisplay() {
        Container contentPane = frame.getContentPane();
        isDisplayedPerQuestion.keySet().stream()
                .filter(BasicDisplayableQuestion::isToDisplay)
                .forEach((BasicDisplayableQuestion question) -> contentPane.add(question));
    }
    
    /**
     * Handle a <code>DisplayableQuestionChangeEvent</code> that was passed to
     * this method to signal a <code>BasicDisplayableQuestion</code>
     * (the <code>source</code> of the <code>event</code>) that was provided to
     * the constructor when <code>this GUI</code> was constructed was changed
     * and adapt <code>this GUI</code> to the specified change
     * 
     * @param event a <code>DisplayableQuestionChangeEvent</code> describing which
     *              <code>BasicDisplayableQuestion</code> changed (the source of the
     *              event) and whether it changed with respect to having to be
     *              displayed
     */
    @Override
    public void questionChanged(DisplayableQuestionChangeEvent event) {
        BasicDisplayableQuestion question = (BasicDisplayableQuestion) event.getSource();
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
     * Calculate the index at which a specified <code>BasicDisplayableQuestion</code>
     * should be added to <code>this GUI</code>'s <code>frame</code> to perceive
     * the order of the <code>questions</code> in which they were passed to the
     * constructor when <code>this GUI</code> was constructed.
     * 
     * @param question a <code>BasicDisplayableQuestion</code> to find an index for
     */
    private int calculateIndex(BasicDisplayableQuestion questionToIndex) {
        Set<BasicDisplayableQuestion> questions = isDisplayedPerQuestion.keySet();
        Iterator<BasicDisplayableQuestion> questionIterator = questions.iterator();
        BasicDisplayableQuestion currentQuestion = questionIterator.next();
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
