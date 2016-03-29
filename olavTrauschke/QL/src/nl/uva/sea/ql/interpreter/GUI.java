package nl.uva.sea.ql.interpreter;

//java.awt cannot be imported in total because of a naming conflict with java.util
import nl.uva.sea.ql.interpreter.questionComponent.QuestionComponent;
import nl.uva.sea.ql.interpreter.questionComponent.BasicQuestionComponent;
import nl.uva.sea.ql.interpreter.questionComponent.QuestionComponentListener;
import nl.uva.sea.ql.interpreter.questionComponent.QuestionComponentChangeEvent;
import java.awt.AWTEvent;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.function.Consumer;
import javax.swing.*;

/**
 * Objects of this class are graphical representations of questionnaires.
 * 
 * @author Olav Trauschke
 * @version 29-mar-2016
 */
public class GUI implements QuestionComponentListener {
    
    private final JFrame frame;
    private final QuestionPanel contentPane;
    //Map from all QuestionComponents this GUI can display to whether or not
    //they are currently displayed
    private final Map<QuestionComponent,Boolean> isDisplayedPerQuestion;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param name a <code>String</code> containing the name of the questionnaire
     *              the constructed <code>GUI</code> will represent
     * @param theQuestions a <code>List</code> of the questions in the questionnaire
     *                      the constructed <code>GUI</code> will represent,
     *                      represented by <code>QuestionComponent</code>s
     */
    public GUI(String name, List<QuestionComponent> theQuestions) {
        assert theQuestions != null;
        frame = new JFrame(name);
        contentPane = new QuestionPanel();
        LayoutManager layoutManager = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(layoutManager);
        frame.setContentPane(contentPane);
        
        isDisplayedPerQuestion = new LinkedHashMap<>(theQuestions.size());
        theQuestions.forEach((QuestionComponent question) -> isDisplayedPerQuestion.put(question, false));
        theQuestions.forEach((QuestionComponent question) -> question.addListener(this));
    }
    
    /**
     * Show <code>this GUI</code> to the user.
     * 
     * @param callback a <code>Consumer</code> of <code>AWTEvent</code>s to call
     *                  when the user has closed <code>this GUI</code>, should
     *                  at least properly shutdown the application
     */
    public void run(Consumer<AWTEvent> callback) {
        assert callback != null;
        addQuestionsToDisplay();
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(callback::accept);
        contentPane.add(saveButton);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                callback.accept((AWTEvent) e);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Add all <code>QuestionComponent</code>s from <code>theQuestions</code>
     * provided to the constructor when <code>this GUI</code> was constructed
     * that are to be displayed (for which
     * {@link nl.uva.sea.ql.interpreter.QuestionComponent#isToDisplay()
     * isToDisplay()} returns <code>true</code>) to the <code>JFrame</code> that
     * will be displayed to the user when <code>this GUI</code> is
     * {@link nl.uva.sea.ql.interpreter.GUI#run() run}.
     */
    private void addQuestionsToDisplay() {
        isDisplayedPerQuestion.keySet().stream()
                .filter(QuestionComponent::isToDisplay)
                .forEach((QuestionComponent question) -> {
                    contentPane.add(question);
                    isDisplayedPerQuestion.put(question, true);
                });
    }
    
    /**
     * Handle a <code>QuestionComponentChangeEvent</code> that was passed to
     * this method to signal a <code>BasicQuestionComponent</code>
     * (the <code>source</code> of the <code>event</code>) that was provided to
     * the constructor when <code>this GUI</code> was constructed was changed
     * and adapt <code>this GUI</code> to the specified change
     * 
     * @param event a <code>QuestionComponentChangeEvent</code> describing which
     *              <code>BasicQuestionComponent</code> changed (the source of the
     *              event) and whether it changed with respect to having to be
     *              displayed
     */
    @Override
    public void questionChanged(QuestionComponentChangeEvent event) {
        assert event != null;
        BasicQuestionComponent basicQuestion
                = (BasicQuestionComponent) event.getSource();
        QuestionComponent question = basicQuestion.getConcreteQuestion();
        if (event.toDisplayChanged()) {
            if (isDisplayedPerQuestion.get(question)) {
                frame.getContentPane().remove((JComponent) question);
                isDisplayedPerQuestion.put(question, false);
            }
            else {
                int index = calculateIndex(question);
                frame.getContentPane().add((JComponent) question, index);
                isDisplayedPerQuestion.put(question, true);
            }
            frame.invalidate();
            frame.pack();
        }
        frame.repaint();
    }
    
    /**
     * Calculate the index at which a specified <code>QuestionComponent</code>
     * should be added to <code>this GUI</code>'s <code>frame</code> to perceive
     * the order of the <code>questions</code> in which they were passed to the
     * constructor when <code>this GUI</code> was constructed.
     * 
     * @param question a <code>QuestionComponent</code> to find an index for
     */
    private int calculateIndex(QuestionComponent questionToIndex) {
        assert questionToIndex != null;
        Set<QuestionComponent> questions = isDisplayedPerQuestion.keySet();
        Iterator<QuestionComponent> questionIterator = questions.iterator();
        QuestionComponent currentQuestion = questionIterator.next();
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
