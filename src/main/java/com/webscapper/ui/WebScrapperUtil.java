package com.webscapper.ui;

import java.awt.Dialog.ModalityType;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JRootPane;
import javax.swing.ListModel;

public class WebScrapperUtil 
{
	/**
	 * 
	 * @param frame
	 */
	public static void showWaitingDialog(WebScrapper frame)
	{
		DialogDisplay dlgDisplay = new DialogDisplay();		       
		
		JDialog dialog = new JDialog(frame, "Progress Dialog", ModalityType.APPLICATION_MODAL);
		
		JRootPane rootPane = ((JDialog) dialog).getRootPane();
		rootPane.setWindowDecorationStyle(JRootPane.NONE);
		
		dialog.getContentPane().add(dlgDisplay.getComponent());
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.dispose();
		dialog.setUndecorated(false);
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static List<CheckListItem> getSelectedListItems(JList list)
	{	
		ListModel<JList> dlm = (ListModel<JList>) list.getModel();
		
		List<CheckListItem> selectedListItems = new ArrayList<CheckListItem>();
		for (int i = 0; i < dlm.getSize(); ++i) 
		{
			CheckListItem checkListItem = (CheckListItem)list.getModel().getElementAt(i);
		      
		      if (checkListItem.isSelected()) 
		      {	    	  
		    	  selectedListItems.add(checkListItem);
		      }		   
		 }		
		
		return selectedListItems;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<String> getSelectedListItemValues(JList list)
	{
		ListModel<JList> dlm = (ListModel<JList>) list.getModel();
		
		List<String> selectedListItems = new ArrayList<String>();
		for (int i = 0; i < dlm.getSize(); ++i) 
		{
			CheckListItem checkListItem = (CheckListItem)list.getModel().getElementAt(i);
		      
		      if (checkListItem.isSelected()) 
		      {	    	  
		    	  selectedListItems.add(checkListItem.toString());
		      }		   
		 }		
		
		return selectedListItems;
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> getSelectedListValues(JList list)
	{
		ListModel<JList> dlm = (ListModel<JList>) list.getModel();
		
		List<String> selectedListItems = new ArrayList<String>();
		for (int i = 0; i < dlm.getSize(); ++i) 
		{
			CheckListItem checkListItem = (CheckListItem)list.getModel().getElementAt(i);
		      
		      if (checkListItem.isSelected()) 
		      {	    	  
		    	  selectedListItems.add(checkListItem.toString());
		      }		   
		 }		
		
		return selectedListItems;
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static CheckListItem[] getCheckListItemArray(List<String> list)
	{
		CheckListItem[] checkListItems = new CheckListItem[list.size()];
		
		if(list.size() > 0)
		{	
			for(int i = 0 ; i < list.size() ; i++)
			{
				checkListItems[i] = new CheckListItem(list.get(i));
			}
		}	
		
		return checkListItems;
	}	
}
