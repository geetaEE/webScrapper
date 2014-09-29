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

import com.webscrapper.constants.UIConstants;

// TODO: Auto-generated Javadoc
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
	  private JProgressBar progressBar = new JProgressBar(UIConstants.MIN, UIConstants.MAX);	 
	  
  	/** The status label. */
  	private JLabel statusLabel = new JLabel("Processing....", SwingConstants.CENTER);
	   
	  /**
  	 * Instantiates a new dialog display.
  	 */
  	public DialogDisplay() 
	  {		    
		    logger.info("Start showing processing wizard.");
		    
  			mainPanel.setBorder(new LineBorder(new Color(0, 0, 0)));    
		    
		    progressBar.setIndeterminate(true);	 
		    progressBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		    
		    mainPanel.add(statusLabel, BorderLayout.NORTH);
		    mainPanel.add(progressBar, BorderLayout.CENTER);
		     
		    final SwingWorker task = new SwingWorker<Void, Void>() {
		      private int value = 0;		       
		      @Override
		      protected Void doInBackground() throws InterruptedException {
		    for (int i = 0; i < UIConstants.DAILOG_COUNTER; i++) {
		      setProgress(UIConstants.DAILOG_MULTIPLY * i);
		      Thread.sleep(UIConstants.SLEEP);
		    }
		    setProgress(UIConstants.DAILOG_PROGRESS);
		    Thread.sleep(UIConstants.SLEEP);
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
  	public JComponent getComponent() 
  	{
  		logger.info("Returning main Panel.");
  		return mainPanel;
	 }
}
