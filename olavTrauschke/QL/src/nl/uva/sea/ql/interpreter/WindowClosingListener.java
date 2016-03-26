package nl.uva.sea.ql.interpreter;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Functional interface extending {@link java.awt.event.WindowListener} and
 * leaving only
 * {@link java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
 * windowClosing(WindowEvent)} abstract to make this a functional interface.
 * 
 * @author Olav Trauschke
 * @version 26-mrt-2016
 */
@FunctionalInterface
public interface WindowClosingListener extends WindowListener {
    
    @Override
    default void windowActivated(WindowEvent e) {}
    
    @Override
    default void windowClosed(WindowEvent e) {}
    
    @Override
    default void windowDeactivated(WindowEvent e) {}
    
    @Override
    default void windowDeiconified(WindowEvent e) {}
    
    @Override
    default void windowIconified(WindowEvent e) {}
    
    @Override
    default void windowOpened(WindowEvent e) {}
    
}
