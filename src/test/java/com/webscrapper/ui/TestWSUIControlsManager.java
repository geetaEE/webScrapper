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
}
