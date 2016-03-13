package uva.ql.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import uva.ql.ast.Form;

public class GUI{

	private final JFrame frame;
	private JPanel panel;
	private final JFileChooser fcLoad = new JFileChooser();
	private final JFileChooser fcSave = new JFileChooser();
	private final Form node;
	
	public GUI(Form node) {
		this.node = node;
		this.panel = new JPanel();
		
		FileFilter ftLoad = new FileNameExtensionFilter("QL Files", "ql");
		FileFilter ftSave = new FileNameExtensionFilter("QL File Result", "json");
		fcLoad.setFileFilter(ftLoad);
		fcSave.setFileFilter(ftSave);
		
		//create frame
		frame = new JFrame( "QL" );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setPreferredSize( new Dimension(400, 600) );
		frame.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		
		//show frame
		frame.pack();
		frame.setVisible(true);
		
		//set frame position on screen, center
		frame.setLocationRelativeTo(null);
		
		frame.addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
    				
					@Override
					public void run() {
						panel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
						panel.revalidate();
					}
    			});
			}
		});
		
		addMenu();
		addPanel();
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
	public GUI resetFrame () {
		frame.getContentPane().removeAll();
		addMenu();
		addPanel();
		
		return this;
	}
	
	private void addMenu() {
		//create menu
		JMenuBar menuBar = new JMenuBar();
		
		//File
		JMenu menu = new JMenu( "File" );
		menu.setMnemonic( KeyEvent.VK_F );
		menu.getAccessibleContext().setAccessibleDescription( "File menu" );
		menuBar.add(menu);
		
		//Load File
		JMenuItem menuItemLoad = new JMenuItem( "Load File" );
		menuItemLoad.setMnemonic(KeyEvent.VK_L);
		menuItemLoad.getAccessibleContext().setAccessibleDescription( "Load a new QL file" );
		menu.add(menuItemLoad);
		
		//Save File
		JMenuItem menuItemSave = new JMenuItem( "Save File" );
		menuItemSave.setMnemonic(KeyEvent.VK_S);
		menuItemSave.getAccessibleContext().setAccessibleDescription( "Save QL file" );
		menu.add(menuItemSave);
		
		frame.setJMenuBar(menuBar);

		menuItemLoad.addActionListener(new LoadMenu(fcLoad, frame, this));
		menuItemSave.addActionListener(new SaveMenu(fcSave, frame, node));
	}

	public void addPanel() {
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		panel.setBackground(new Color(0, 200, 0));
		frame.add(panel, BorderLayout.CENTER);
		
		frame.pack();
		frame.revalidate();
	}
}
