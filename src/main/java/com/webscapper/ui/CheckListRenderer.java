package com.webscapper.ui;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.apache.log4j.Logger;

class CheckListRenderer extends JCheckBox implements ListCellRenderer
{
	private static Logger logger = Logger.getLogger(CheckListRenderer.class);
	
	/**
	 * 
	 */
	public Component getListCellRendererComponent(
	      JList list, Object value, int index, 
	      boolean isSelected, boolean hasFocus)
	{
	   setEnabled(list.isEnabled());
	   setSelected(((CheckListItem)value).isSelected());
	   setFont(list.getFont());
	   setBackground(list.getBackground());
	   setForeground(list.getForeground());
	   setText(value.toString());
	   return this;
	}
}
