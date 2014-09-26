package com.webscrapper.ui;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.ui.CheckListItem;
import com.webscapper.ui.CheckListRenderer;

@RunWith(JUnit4.class)
public class TestCheckListRenderer 
{
	@Test
	public void testGetListCellRendererComponent()
	{
		CheckListRenderer checkListRenderer = new CheckListRenderer();
		JList list = new JList(new CheckListItem[]{});		
		list.setCellRenderer(new CheckListRenderer());	
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		Object value = new CheckListItem("Test");
		int index = 0; 
	    boolean isSelected = false;
	    boolean hasFocus = false;
		checkListRenderer.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
		
		org.junit.Assert.assertEquals("Test", checkListRenderer.getText());		
	}
}
