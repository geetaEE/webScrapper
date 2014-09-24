package com.webscapper.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

/**
 * The Class DialogDisplay.
 */
public class DialogDisplay 
{
	  
  	/** The logger. */
  	private static Logger logger = Logger.getLogger(DialogDisplay.class);
	  
  	/** The main panel. */
  	private JPanel mainPanel = new JPanel();
	  
  	/** The progress bar. */
  	private JProgressBar progressBar = new JProgressBar(0, 100);	 
	  
  	/** The status label. */
  	private JLabel statusLabel = new JLabel("Processing....", SwingConstants.CENTER);
	   
	  /**
  	 * Instantiates a new dialog display.
  	 */
  	public DialogDisplay() 
	  {		    
		    mainPanel.setBorder(new LineBorder(new Color(0, 0, 0)));    
		    
		    progressBar.setIndeterminate(true);	 
		    progressBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		    
		    mainPanel.add(statusLabel, BorderLayout.NORTH);
		    mainPanel.add(progressBar, BorderLayout.CENTER);
		     
		    final SwingWorker task = new SwingWorker<Void, Void>() {
		      private int value = 0;
		       
		      @Override
		      protected Void doInBackground() throws Exception {
		    for (int i = 0; i < 5; i++) {
		      setProgress(10 * i);
		      Thread.sleep(1000);
		    }
		    setProgress(100);
		    Thread.sleep(1000);
		    return null;
		      }
		       
		      @Override
		      protected void done() {
		        Window win = SwingUtilities.getWindowAncestor(mainPanel);
		        win.dispose();
		      }
		       
		    };	    
		    task.execute();
	  }
	 
	  /**
  	 * Gets the component.
  	 *
  	 * @return the component
  	 */
  	public JComponent getComponent() {
	    return mainPanel;
	  }
}
