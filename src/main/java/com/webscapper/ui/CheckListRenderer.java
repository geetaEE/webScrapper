package com.webscapper.ui;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.apache.log4j.Logger;

/**
 * The Class CheckListRenderer.
 */
class CheckListRenderer extends JCheckBox implements ListCellRenderer
{
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(CheckListRenderer.class);
	
	/* (non-Javadoc)
	 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	public Component getListCellRendererComponent(
	      JList list, Object value, int index, 
	      boolean isSelected, boolean hasFocus)
	{
		logger.info("Entering getListCellRendererComponent()");
		
		setEnabled(list.isEnabled());
		setSelected(((CheckListItem)value).isSelected());
		setFont(list.getFont());
		setBackground(list.getBackground());
		setForeground(list.getForeground());
		setText(value.toString());
		
		logger.info("Exiting from getListCellRendererComponent()");
		
		return this;
	}
}
