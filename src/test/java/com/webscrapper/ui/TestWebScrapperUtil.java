package com.webscrapper.ui;

import com.webscapper.ui.CheckListItem;
import com.webscapper.ui.WebScrapperUtil;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.junit.runners.JUnit4;

import java.awt.Point;

import java.util.Arrays;
import java.util.List;

import javax.swing.JList;

/** The Class TestWebScrapperUtil. */
@RunWith(JUnit4.class)
public class TestWebScrapperUtil {
    // ~ Methods ------------------------------------------------------------------------------------------------------

    /** Unit Test for getSelectedListItems(). */
    @Test
    public void testGetSelectedListItems() {
        JList imageList = new JList(new CheckListItem[] {});

        List<String> imagesList = Arrays.asList(new String[] {"Test1", "Test2"});

        imageList.setListData(WebScrapperUtil.getCheckListItemArray(imagesList));

        // Scenario 1: When none of item is selecated.
        List<CheckListItem> selectedItemList = WebScrapperUtil.getSelectedListItems(imageList);

        Assert.assertEquals("Selected Item length should be zero.", 0, selectedItemList.size());

        // Scenario 2: When single item is selected.
        int index = imageList.locationToIndex(new Point(0, 0));
        CheckListItem item = (CheckListItem) imageList.getModel().getElementAt(index);

        // Toggle selected state
        item.setSelected(!item.isSelected());

        // Repaint cell
        imageList.repaint(imageList.getCellBounds(index, index));

        selectedItemList = WebScrapperUtil.getSelectedListItems(imageList);
        Assert.assertEquals("Selected Item length should be one.", 1, selectedItemList.size());
    }

    /** Unit Test for getSelectedListItemValues(). */
    @Test
    public void testGetSelectedListItemValues() {
        JList imageList = new JList(new CheckListItem[] {});

        List<String> imagesList = Arrays.asList(new String[] {"Test1", "Test2"});

        imageList.setListData(WebScrapperUtil.getCheckListItemArray(imagesList));

        // Scenario 1: When none of item is selecated.
        List<String> selectedValues = WebScrapperUtil.getSelectedListItemValues(imageList);
        Assert.assertEquals("Selected Item length should be zero.", 0, selectedValues.size());

        // Scenario 2: When single item is selected.
        int index = imageList.locationToIndex(new Point(0, 0));
        CheckListItem item = (CheckListItem) imageList.getModel().getElementAt(index);
        item.setSelected(!item.isSelected());
        imageList.repaint(imageList.getCellBounds(index, index));

        selectedValues = WebScrapperUtil.getSelectedListItemValues(imageList);
        Assert.assertEquals("Selected Item length should be one.", 1, selectedValues.size());
    }

    /** Unit Test for getCheckListItemArray(). */
    @Test
    public void testGetCheckListItemArray() {
        List<String> imagesList = Arrays.asList(new String[] {"Test1", "Test2"});

        CheckListItem[] checkListItems = WebScrapperUtil.getCheckListItemArray(imagesList);

        Assert.assertEquals("Item count should be two.", 2, checkListItems.length);
    }
}
