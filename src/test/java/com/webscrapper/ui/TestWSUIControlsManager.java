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
}
