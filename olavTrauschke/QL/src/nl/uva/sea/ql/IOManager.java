package nl.uva.sea.ql;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import nl.uva.sea.ql.parser.ParserWrapper;

/**
 * Class to present various dialogs to the user.
 * 
 * @author Olav Trauschke
 * @version 10-mrt-2016
 */
public class IOManager {
    
    /**
     * Description of the type of file the user should select when running the program.
     */
    public static final String ACCEPTED_FILE_TYPES_DESCRIPTION
            = "Question Language questionnaire";
    
    /**
     * Extensions of files the user is allowed to select when running the program.
     */
    public static final String[] ACCEPTED_EXTENSIONS = {"ql"};
    
    /**
     * Extension of files the results of a questionnaire can be saved to.
     */
    public static final String SAVE_EXTENSION = "xml";
    
    /**
     * Description of the type of file results of a questionnaire can be saved to.
     */
    public static final String SAVE_FILE_TYPE_DESCRIPTION = "XML-file";
    
    /**
     * Error code representing an error while trying to read a file.
     */
    public static final int FILE_READING_ERROR = 1;
    
    /**
     * Error message presented to the user when an error occured while reading
     * the file he selected.
     */
    public static final String FILE_READING_ERROR_MESSAGE
            = "The selected file could not be read";
    
    /**
     * Title of the dialog a <code>FILE_READING_ERROR_MESSAGE</code> is displayed in.
     */
    public static final String FILE_READING_ERROR_TITLE
            = "Error reading file";
    
    /**
     * Error code representing failure to save the results of a questionnaire.
     */
    public static final int SAVING_ERROR = 4;
    
    /**
     * Error message presented to the user when an exception was thrown while
     * attempting to save the results of a questionnaire to a file the users
     * specified.
     */
    public static final String SAVING_ERROR_MESSAGE
            = "An error occured while saving your results. Please check wether "
            + "the results file is complete.";
    
    /**
     * Title of the dialog a <code>SAVING_ERROR_MESSAGE</code> is displayed in.
     */
    public static final String SAVING_ERROR_TITLE = "Error while saving";
    
    /**
     * Question displayed to the user to ask him to confirm wether he wants to
     * leave without saving.
     */
    public static final String LEAVE_WITHOUT_SAVING_MESSAGE
            = "Are you sure you want to leave without saving?";
    
    /**
     * Title for a dialog asking a user to confirm wether he wants to leave
     * without saving
     */
    public static final String LEAVE_WITHOUT_SAVING_TITLE
            = "Leave without saving";
    
    /**
     * Header predended when something is written to an xml-file.
     */
    public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    
    /**
     * Text used to introduce a list of errors (and possibly warnings) displayed
     * to the user.
     */
    public static final String SEMANTICS_ERROR_MESSAGE
            = "Errors occured:";
    
    /**
     * Title for dialog displaying warnings.
     */
    public static final String WARNING_TITLE = "Warnings";
    
    /**
     * Message to introduce warnings.
     */
    public static final String WARNINGS_MESSAGE
            = "Warning: problems found. Do you want to continue anyway?";
    
    /**
     * Text used to mark messages as warnings when errors and warnings are
     * presented to the user in one list.
     */
    public static final String WARNING_LABEL = " (warning)";
    
    /**
     * Ask the user to select a <code>File</code> to open.
     * 
     * @return the <code>File</code> the user selected, or <code>null</code> if
     *          he closed the dialog
     */
    public File selectFileToOpen() {
        JFileChooser fileChooser = new JFileChooser();
        FileFilter qlFilter = new FileNameExtensionFilter(ACCEPTED_FILE_TYPES_DESCRIPTION, ACCEPTED_EXTENSIONS);
        fileChooser.setFileFilter(qlFilter);
        fileChooser.showOpenDialog(null);
        return fileChooser.getSelectedFile();
    }
    
        /**
     * Try to read a given <code>File</code> and shutdown with an error if this
     * was not possible.
     * 
     * @param file the <code>File</code> to read
     * @return a <code>ParserWrapper</code> reading from the given <code>file</code>
     */
    public ParserWrapper read(File file) {
        try {
            ParserWrapper parser = new ParserWrapper(file);
            return parser;
        }
        catch (FileNotFoundException fnfe) {
            showErrorMessage(FILE_READING_ERROR_MESSAGE, FILE_READING_ERROR_TITLE);
            System.exit(FILE_READING_ERROR);
            return null; //never reached
        }
    }
    
    /**
     * Ask the user to select a location to save the result of a questionnaire.
     * 
     * @return a <code>String</code> containing the path where the user wants
     *          to save the result
     */
    public String selectSaveLocation() {
        JFileChooser fileChooser = new JFileChooser();
        FileFilter xmlFilter = new FileNameExtensionFilter(SAVE_FILE_TYPE_DESCRIPTION, SAVE_EXTENSION);
        fileChooser.setFileFilter(xmlFilter);
        fileChooser.showSaveDialog(null);
        File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile == null) {
            int leave = JOptionPane.showConfirmDialog(null, LEAVE_WITHOUT_SAVING_MESSAGE,
                    LEAVE_WITHOUT_SAVING_TITLE, JOptionPane.YES_NO_OPTION);
            if (leave == JOptionPane.YES_OPTION) {
                //User chose to leave without saving
                System.exit(0);
            }
            else {
                //User accidently cancelled saving
                return selectSaveLocation();
            }
        }
        String selectedPath = selectedFile.getPath();
        if (isValidSaveLocation(selectedPath)) {
            return selectedPath;
        }
        return selectedPath + "." + SAVE_EXTENSION;
    }
    
    /**
     * Determine wether a path is a valid location to save a file.
     * 
     * @param intendedSaveLocation a <code>String</code> containing the path to test
     * @return <code>true</code> if and only if <code>intendedSaveLocation</code>
     *          ends with <code>"." + </code>{@link #SAVE_EXTENSION SAVE_EXTENSION}
     *          (ignoring case)
     */
    private static boolean isValidSaveLocation(String intendedSaveLocation) {
        return intendedSaveLocation.toLowerCase().endsWith("." + SAVE_EXTENSION);
    }
    
    /**
     * Convert <code>toWrite</code> to xml and write the result to
     * <code>destination</code>. Displays an error and shutsdown if an exception
     * was thrown while attempting to write to the file specified by
     * <code>destination</code>.
     * 
     * @param toWrite an <code>Object</code> containing the contents to write
     * @param destination a <code>String</code> containing the path to write to
     */
    public void writeToXml(Object toWrite, String destination) {
        XStream xmlConverter = new XStream(new DomDriver());
        String answersXml = xmlConverter.toXML(toWrite);
        String systemNewLineCharacter = System.getProperty("line.separator");
        try (FileWriter writer = new FileWriter(destination)) {
            writer.write(XML_HEADER);
            writer.write(systemNewLineCharacter);
            writer.write(answersXml);
        }
        catch (IOException ioe) {
            showErrorMessage(SAVING_ERROR_MESSAGE, SAVING_ERROR_TITLE);
            System.exit(SAVING_ERROR);
        }
    }
    
    /**
     * Display errors and warnings (marked as warnings) to the user.
     * 
     * @param errors <code>String</code>s containing the <code>errors</code> to
     *                  display
     * @param warnings <code>String</code>s the <code>warnings</code> to display
     */
    public void showErrorsAndWarnings(List<String> errors, List<String> warnings) {
        assert !errors.isEmpty();
        warnings.replaceAll(w -> w + WARNING_LABEL);
        errors.addAll(warnings);
        showErrorMessage(toMultiLineString(errors), SEMANTICS_ERROR_MESSAGE);
    }
    
    /**
     * Displays warnings to the user and asks wether he wants to continue execution
     * despite these warnings.
     * 
     * @param warnings a <code>List</code> of <code>String</code>s representing
     *                  the <code>warnings</code> to display
     * @return wether the user has chosen to continue despite the displayed warnings
     */
    public boolean handleWarnings(List<String> warnings) {
        String warningsString = toMultiLineString(warnings);
        String message = WARNINGS_MESSAGE + "\n" + warningsString;
        int run = JOptionPane.showConfirmDialog(null, message, WARNING_TITLE,
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        return run == JOptionPane.YES_OPTION;
    }
    
    /**
     * Display an error to the user.
     * 
     * @param message an <code>Object</code> representing the message to display
     * @param title a <code>String</code> containing the tile for the dialog to
     *              display
     */
    public void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Get a multi-line <code>String</code> representation of a the elements of
     * an <code>Iterable</code>.
     * 
     * @param message the <code>Iterable</code> to convert
     * @return a <code>String</code> containing all elements of <code>message</code>
     *          in the order they are returned by its <code>iterator</code>,
     *          seperated by the new line character "\n"
     */
    private static String toMultiLineString(Iterable message) {
        Iterator iterator = message.iterator();
        String messageString = "";
        if (iterator.hasNext()) {
            messageString += iterator.next();
            while (iterator.hasNext()) {
                messageString += "\n";
                messageString += iterator.next();
            }
        }
        return messageString;
    }
    
}
