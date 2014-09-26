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

import com.webscapper.ui.WSUIControlsManager;
import com.webscapper.ui.WebScrapper;
import com.webscrapper.constants.ContentType;

/**
 * The Class TestWebScrapper.
 */
@RunWith(JUnit4.class)
public class TestWSUIControlsManager 
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
		WSUIControlsManager WSUIControlsManager = new WSUIControlsManager(webScrapper);
		webScrapper.setFrame(webScrapper);
		
		WSUIControlsManager.disableBatchProessPanelControls();
		
		JTextField pathtextField = WSUIControlsManager.getWsUIControls().getPathtextField();
		
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
		WSUIControlsManager wsUIControlsManager = new WSUIControlsManager(webScrapper);
		webScrapper.setFrame(webScrapper);
		
		wsUIControlsManager.resetBatchProcessPanel();
		
		JTextField pathtextField = wsUIControlsManager.getWsUIControls().getPathtextField();
		
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
		WSUIControlsManager wsUIControlsManager = new WSUIControlsManager(webScrapper);		
		webScrapper.setFrame(webScrapper);
		
		wsUIControlsManager.enableBatchProessPanelControls();
		
		JTextField pathtextField = wsUIControlsManager.getWsUIControls().getPathtextField();
		
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
		WSUIControlsManager wsUIControlsManager = new WSUIControlsManager(webScrapper);		
		webScrapper.setFrame(webScrapper);
		
		wsUIControlsManager.resetAllExtractProcessPanel();
		
		JButton btnPreview = wsUIControlsManager.getWsUIControls().getBtnPreview();
		
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
		WSUIControlsManager wsUIControlsManager = new WSUIControlsManager(webScrapper);
		webScrapper.setFrame(webScrapper);
		
		wsUIControlsManager.resetExtractProcessPanel();
		
		JButton btnPreview = wsUIControlsManager.getWsUIControls().getBtnPreview();
		
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
		WSUIControlsManager wsUIControlsManager = new WSUIControlsManager(webScrapper);		
		webScrapper.setFrame(webScrapper);
		
		wsUIControlsManager.expandExtractProcessPanel();
		
		JPanel panel = wsUIControlsManager.getWsUIControls().getPreviewRunQueryPanel();
		
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
		WSUIControlsManager wsUIControlsManager = new WSUIControlsManager(webScrapper);
		webScrapper.setFrame(webScrapper);
		
		wsUIControlsManager.resetHeaderValuesValue();
		
		JTextField textField = wsUIControlsManager.getWsUIControls().getUrlTextField();
		
		Assert.assertEquals("", textField.getText());
		
		textField = wsUIControlsManager.getWsUIControls().getTitleTextField();
		
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
		WSUIControlsManager wsUIControlsManager = new WSUIControlsManager(webScrapper);
		webScrapper.setFrame(webScrapper);
		
		wsUIControlsManager.disableHeaderArea();
		
		JTextField textField = wsUIControlsManager.getWsUIControls().getUrlTextField();
		
		Assert.assertFalse(textField.isEditable());
		
		textField = wsUIControlsManager.getWsUIControls().getTitleTextField();
		
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
		
		WSUIControlsManager wsUIControlsManager = new WSUIControlsManager(webScrapper);
			
		webScrapper.getFrame().setWebScrapperUIControls(wsUIControlsManager);
		
		JTextField textField = wsUIControlsManager.getWsUIControls().getUrlTextField();		
		
		textField.setText("www.google.com");
		
		textField = wsUIControlsManager.getWsUIControls().getTitleTextField();
		
		textField.setText("TestTitle");
		
		JComboBox combo = wsUIControlsManager.getWsUIControls().getExtractDataTypeComboBox();
		
		combo.setModel(new DefaultComboBoxModel(ContentType.getContentArray()));
		
		webScrapper.executeExtractOpertion();
		
		textField = wsUIControlsManager.getWsUIControls().getUrlTextField();
		
		Assert.assertFalse(textField.isEditable());
		
		textField = wsUIControlsManager.getWsUIControls().getTitleTextField();
		
		Assert.assertFalse(textField.isEditable());
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
		
		WSUIControlsManager wsUIControlsManager = new WSUIControlsManager(webScrapper);				
		
		webScrapper.getFrame().setWebScrapperUIControls(wsUIControlsManager);
		
		JTextField textField = wsUIControlsManager.getWsUIControls().getUrlTextField();		
		
		textField.setText("http://www.w3schools.com/html/html_tables.asp");
		
		textField = wsUIControlsManager.getWsUIControls().getTitleTextField();
		
		textField.setText("TestTitle");
		
		JComboBox combo = wsUIControlsManager.getWsUIControls().getExtractDataTypeComboBox();
		
		combo.setModel(new DefaultComboBoxModel(ContentType.getContentArray()));
		
		combo.setSelectedIndex(0);
		
		webScrapper.executeExtractOpertion();
		
		wsUIControlsManager.populateHtmlControlList();
		
		JList imageList = wsUIControlsManager.getWsUIControls().getHtmlControlList();
		
		ListModel<JList> dlm = (ListModel<JList>) imageList.getModel();
		
		Assert.assertEquals(4, dlm.getSize());		
	}
}
