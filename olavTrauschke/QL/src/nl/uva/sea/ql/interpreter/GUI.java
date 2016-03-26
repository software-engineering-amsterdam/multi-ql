package nl.uva.sea.ql.interpreter;

import java.awt.Container;
import java.awt.LayoutManager;
import java.util.*;
import javax.swing.*;
import nl.uva.sea.ql.interpreter.listener.*;

/**
 * TODO document
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
     * TODO document
     * 
     * @param name
     * @param theQuestions
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
        //TODO make questions notify on change
        
        //TODO add save button and close operation
    }
    
    /**
     * TODO document
     */
    public void run() {
        addQuestionsToDisplay();
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * TODO document
     */
    private void addQuestionsToDisplay() {
        Container contentPane = frame.getContentPane();
        isDisplayedPerQuestion.keySet().stream()
                .filter((DisplayableQuestion question) -> question.isToDisplay())
                .forEach((DisplayableQuestion question) -> contentPane.add(question));
    }
    
    /**
     * TODO document
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
     * TODO document
     * 
     * @param question
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
