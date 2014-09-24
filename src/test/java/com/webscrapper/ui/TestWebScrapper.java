package com.webscrapper.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.webscapper.ui.CheckListItem;
import com.webscapper.ui.WebScrapper;
import com.webscrapper.constants.ContentType;

/**
 * The Class TestWebScrapper.
 */
@RunWith(JUnit4.class)
public class TestWebScrapper 
{
	
	/**
	 * Unit test for WebScrapper() method.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testWebScrapper() throws Exception
	{
		WebScrapper webScrapper = new WebScrapper(); 
		Assert.assertNotNull(webScrapper);
	}
	
	/**
	 * Unit Test for disableBatchProessPanelControls().
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDisableBatchProessPanelControls() throws Exception
	{
		WebScrapper webScrapper = new WebScrapper(); 
		webScrapper.disableBatchProessPanelControls();
		
		JTextField pathtextField = webScrapper.getPathtextField();
		
		Assert.assertEquals("", pathtextField.getText());
	}
	
	/**
	 * Unit Test for resetBatchProcessPanel().
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testResetBatchProcessPanel() throws Exception
	{
		WebScrapper webScrapper = new WebScrapper();
		
		webScrapper.setFrame(webScrapper);
		
		webScrapper.resetBatchProcessPanel();
		
		JTextField pathtextField = webScrapper.getPathtextField();
		
		Assert.assertEquals("", pathtextField.getText());
	}
	
	/**
	 * Unit Test for enableBatchProessPanelControls().
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEnableBatchProessPanelControls() throws Exception
	{
		WebScrapper webScrapper = new WebScrapper();
		
		webScrapper.setFrame(webScrapper);
		
		webScrapper.enableBatchProessPanelControls();
		
		JTextField pathtextField = webScrapper.getPathtextField();
		
		Assert.assertTrue(pathtextField.isEnabled());
	}
	
	/**
	 * Unit test for resetAllExtractProcessPanel().
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testResetAllExtractProcessPanel()throws Exception
	{
		WebScrapper webScrapper = new WebScrapper();
		
		webScrapper.setFrame(webScrapper);
		
		webScrapper.resetAllExtractProcessPanel();
		
		JButton btnPreview = webScrapper.getBtnPreview();
		
		Assert.assertFalse(btnPreview.isEnabled());
	}
	
	/**
	 * Unit test for resetExtractProcessPanel().
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testResetExtractProcessPanel()throws Exception
	{
		WebScrapper webScrapper = new WebScrapper();
		
		webScrapper.setFrame(webScrapper);
		
		webScrapper.resetExtractProcessPanel();
		
		JButton btnPreview = webScrapper.getBtnPreview();
		
		Assert.assertFalse(btnPreview.isEnabled());
	}
	
	/**
	 * Unit test for expandExtractProcessPanel().
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testExpandExtractProcessPanel()throws Exception
	{
		WebScrapper webScrapper = new WebScrapper();
		
		webScrapper.setFrame(webScrapper);
		
		webScrapper.expandExtractProcessPanel();
		
		JPanel panel = webScrapper.getPreviewRunQueryPanel();
		
		Assert.assertTrue(panel.isVisible());
	}
	
	/**
	 * Unit test for resetHeaderValuesValue().
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testResetHeaderValuesValue()throws Exception
	{
		WebScrapper webScrapper = new WebScrapper();
		
		webScrapper.setFrame(webScrapper);
		
		webScrapper.resetHeaderValuesValue();
		
		JTextField textField = webScrapper.getURLtextField();
		
		Assert.assertEquals("", textField.getText());
		
		textField = webScrapper.getTitletextField();
		
		Assert.assertEquals("", textField.getText());
	}
	
	/**
	 * Unit test for disableHeaderArea().
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDisableHeaderArea()throws Exception
	{
		WebScrapper webScrapper = new WebScrapper();
		
		webScrapper.setFrame(webScrapper);
		
		webScrapper.disableHeaderArea();
		
		JTextField textField = webScrapper.getURLtextField();
		
		Assert.assertFalse(textField.isEditable());
		
		textField = webScrapper.getTitletextField();
		
		Assert.assertFalse(textField.isEditable());
	}
	
	/**
	 * Unit test for executeExtractOpertion().
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testExecuteExtractOpertion()throws Exception
	{
		WebScrapper webScrapper = new WebScrapper();
		
		webScrapper.setFrame(webScrapper);		
		
		JTextField textField = webScrapper.getURLtextField();		
		
		textField.setText("www.google.com");
		
		textField = webScrapper.getTitletextField();
		
		textField.setText("TestTitle");
		
		JComboBox combo = webScrapper.getExtractDataTypeComboBox();
		
		combo.setModel(new DefaultComboBoxModel(ContentType.getContentArray()));
		
		webScrapper.executeExtractOpertion();
		
		textField = webScrapper.getURLtextField();
		
		Assert.assertFalse(textField.isEditable());
		
		textField = webScrapper.getTitletextField();
		
		Assert.assertFalse(textField.isEditable());
	}
	
	/**
	 * Unit for populateImageList().
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testpopulateImageList() throws Exception
	{
		WebScrapper webScrapper = new WebScrapper();
		
		webScrapper.setFrame(webScrapper);		
		
		JTextField textField = webScrapper.getURLtextField();		
		
		textField.setText("http://www.w3schools.com/html/html_tables.asp");
		
		textField = webScrapper.getTitletextField();
		
		textField.setText("TestTitle");
		
		JComboBox combo = webScrapper.getExtractDataTypeComboBox();
		
		combo.setModel(new DefaultComboBoxModel(ContentType.getContentArray()));
		
		combo.setSelectedIndex(1);
		
		webScrapper.executeExtractOpertion();
		
		webScrapper.populateImageList();
		
		JList imageList = webScrapper.getImageList();
		
		ListModel<JList> dlm = (ListModel<JList>) imageList.getModel();
		
		Assert.assertEquals(5, dlm.getSize());		
	}
	
	/**
	 * Unit for populateHtmlControlList().
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPopulateHtmlControlList() throws Exception
	{
		WebScrapper webScrapper = new WebScrapper();
		
		webScrapper.setFrame(webScrapper);		
		
		JTextField textField = webScrapper.getURLtextField();		
		
		textField.setText("http://www.w3schools.com/html/html_tables.asp");
		
		textField = webScrapper.getTitletextField();
		
		textField.setText("TestTitle");
		
		JComboBox combo = webScrapper.getExtractDataTypeComboBox();
		
		combo.setModel(new DefaultComboBoxModel(ContentType.getContentArray()));
		
		combo.setSelectedIndex(0);
		
		webScrapper.executeExtractOpertion();
		
		webScrapper.populateHtmlControlList();
		
		JList imageList = webScrapper.getHtmlControlList();
		
		ListModel<JList> dlm = (ListModel<JList>) imageList.getModel();
		
		Assert.assertEquals(4, dlm.getSize());		
	}
}
