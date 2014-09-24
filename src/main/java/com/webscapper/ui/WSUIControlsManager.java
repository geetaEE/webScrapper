package com.webscapper.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.StructuredExtractDocType;
import com.webscrapper.constants.TagType;
import com.webscrapper.constants.UIConstants;
import com.webscrapper.constants.UnStructuredExtractDocType;

public class WSUIControlsManager 
{
	private static Logger logger = Logger.getLogger(WSUIControlsManager.class);
	static JPanel contentPane;		
	static JTextField urlTextField;
	static JTextField titleTextField;
	static JButton exitButton;
	static JComboBox extractDataTypeComboBox;
	static JRadioButton unStructedRadioButton;
	static JRadioButton structedRadioButton;
	static ButtonGroup dataTypeRadioButtonGroup;
	static JPanel extractDataTypeSelectionpanel;	
	static JFileChooser fc;
	static JScrollPane scrollPane;
	static JList imageList;
	static JButton btnPreview;	
	static JPanel previewRunQueryPanel;
	static JComboBox extractTocomboBox;
	static JLabel lblExtractTo;
	static JButton btnReset;	
	static JMenuItem mntmExtractProcess;
	static JMenuItem mntmBatchProcess;
	static JPanel batchProcessPanel;
	static JButton button;
	static JButton buttonExit;
	static JLabel lblNewLabel;
	static JTextField pathtextField;
	static JPanel batchProcessBrowsePanel;
	static JPanel extractProcessPanel;
	static JButton btnProcess;
	static JPanel headerPanel;
	static JLabel lblExtractProcess;
	static JPanel queryRunnerControlBoxPanel ;	
	static JButton btnRunQuery;	
	static JScrollPane htmlControlScrollPanel;
	static JList htmlControlList;
	static JButton extractButton;
	static String url;
	static String title;
	static ContentType contentType;
	static ExtractRequest extractRequest;
	static ExtractResponse extractResponse = null;
	static WSServiceProvider wsServiceProvider;
	static WebScrapper frame = null;	
	
	/**
	 * Method for creating the UI components.
	 * @throws Exception
	 */
	public WSUIControlsManager(WebScrapper webScrapper)
	{
		logger.info("Entering in WebScrapper()");		
		this.frame = webScrapper;
		webScrapper.setAlwaysOnTop(true);
		webScrapper.setResizable(false);
		webScrapper.setTitle(UIConstants.WEB_SCRAPPER);	
		webScrapper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		webScrapper.setBounds(100, 100, 652, 792);
		contentPane = new JPanel();
		webScrapper.setContentPane(contentPane);
		contentPane.setLayout(null);		
		fc = new JFileChooser();		
		dataTypeRadioButtonGroup = new ButtonGroup();		
		createMenus();			
		createExtrctProcessPanel();		
		createBatchProcessPanel();
		logger.info("Exiting from WebScrapper()");
	}
	
	/**
	 * Method for crating the Menus.
	 */
	public void createMenus()
	{
		logger.info("Entering in createMenus()");
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 651, 21);
		contentPane.add(menuBar);		
		JMenu mnNewMenu = new JMenu("Process   |");
		menuBar.add(mnNewMenu);		
		mntmExtractProcess = new JMenuItem("Extract Process");
		mntmExtractProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				batchProcessPanel.setVisible(false);
				extractProcessPanel.setVisible(true);
				resetAllExtractProcessPanel();
			}});
		mnNewMenu.add(mntmExtractProcess);		
		mntmBatchProcess = new JMenuItem("Batch Process");
		mntmBatchProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){			
				batchProcessPanel.setVisible(true);
				extractProcessPanel.setVisible(false);	
				resetBatchProcessPanel();
			}});
		mnNewMenu.add(mntmBatchProcess);
		logger.info("Exiting from createMenus()");
	}
	
	/**
	 * Method for creating extract process panel.
	 * @throws Exception
	 */
	public void createExtrctProcessPanel()
	{
		logger.info("Entering in createExtrctProcessPanel()");
		extractProcessPanel = new JPanel();
		extractProcessPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		extractProcessPanel.setBounds(10, 32, 629, 470);
		contentPane.add(extractProcessPanel);
		extractProcessPanel.setLayout(null);		
		queryRunnerControlBoxPanel = new JPanel();
		queryRunnerControlBoxPanel.setBounds(10, 200, 609, 194);
		extractProcessPanel.add(queryRunnerControlBoxPanel);
		queryRunnerControlBoxPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		queryRunnerControlBoxPanel.setLayout(null);		
		extractDataTypeSelectionpanel = new JPanel();
		extractDataTypeSelectionpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		extractDataTypeSelectionpanel.setBounds(10, 11, 141, 61);
		queryRunnerControlBoxPanel.add(extractDataTypeSelectionpanel);
		extractDataTypeSelectionpanel.setLayout(null);		
		unStructedRadioButton = new JRadioButton("Non-Tabular");
		unStructedRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){				
				extractTocomboBox.setModel(new DefaultComboBoxModel(new String[]{}));
				populateHtmlControlList();
				btnRunQuery.setEnabled(false);
				btnPreview.setEnabled(true);
				htmlControlScrollPanel.setVisible(true);
			}});		
		unStructedRadioButton.setBounds(6, 5, 129, 23);	
		unStructedRadioButton.setEnabled(false);		
		structedRadioButton = new JRadioButton("Tabular");
		structedRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				extractTocomboBox.setModel(new DefaultComboBoxModel(StructuredExtractDocType.values()));
				htmlControlList.setListData(new CheckListItem[] {});
				btnRunQuery.setEnabled(true);
				btnPreview.setEnabled(true);
				htmlControlScrollPanel.setVisible(false);
			}});		
		structedRadioButton.setBounds(6, 31, 92, 23);	
		structedRadioButton.setEnabled(false);
		dataTypeRadioButtonGroup.add(unStructedRadioButton);
		dataTypeRadioButtonGroup.add(structedRadioButton);		
		extractDataTypeSelectionpanel.add(unStructedRadioButton);
		extractDataTypeSelectionpanel.add(structedRadioButton);		
		imageList = new JList(new CheckListItem[]{});		
		imageList.setCellRenderer(new CheckListRenderer());	
		imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		imageList.addMouseListener(new MouseAdapter(){
	         public void mouseClicked(MouseEvent event) {
	            JList list = (JList) event.getSource();
	            int index = list.locationToIndex(event.getPoint());
	            CheckListItem item = (CheckListItem)list.getModel().getElementAt(index);	            
	            item.setSelected(! item.isSelected());
	            list.repaint(list.getCellBounds(index, index));
	            List<CheckListItem> selectedList = WebScrapperUtil.getSelectedListItems(imageList);
	            if(selectedList.size() <= 0){	
	            	btnRunQuery.setEnabled(false);
	            }else{ 
	            	btnRunQuery.setEnabled(true);
	            }	
	         }});		
		htmlControlList = new JList(new CheckListItem[]{});		
		htmlControlList.setCellRenderer(new CheckListRenderer());		
		htmlControlList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		htmlControlList.addMouseListener(new MouseAdapter(){
	         public void mouseClicked(MouseEvent event){
	            JList list = (JList) event.getSource();	            
	            int index = list.locationToIndex(event.getPoint());
	            CheckListItem item = (CheckListItem)
	            list.getModel().getElementAt(index);
	            item.setSelected(! item.isSelected());
	            list.repaint(list.getCellBounds(index, index));	            
	            List<CheckListItem> selectedList = WebScrapperUtil.getSelectedListItems(htmlControlList);	            
	            if(selectedList.size() <= 0){
	            	extractTocomboBox.setModel(new DefaultComboBoxModel(new String[]{}));
	            	btnRunQuery.setEnabled(false);
	            }
	            else{	            	
	            	if(null != extractTocomboBox && null == extractTocomboBox.getSelectedItem()){	
	            		extractTocomboBox.setModel(new DefaultComboBoxModel(UnStructuredExtractDocType.values()));
	        		}	            	
	            	btnRunQuery.setEnabled(true);	            	
	            }
	         }});		
		scrollPane = new JScrollPane(imageList);		
		scrollPane.setBounds(210, 11, 200, 124);
		htmlControlScrollPanel = new JScrollPane(htmlControlList);
		htmlControlScrollPanel.setBounds(210, 11, 150, 124);
		queryRunnerControlBoxPanel.add(htmlControlScrollPanel);
		htmlControlScrollPanel.setVisible(false);
		queryRunnerControlBoxPanel.add(scrollPane);
		lblExtractTo = new JLabel("Export To : ");
		lblExtractTo.setBounds(412, 11, 71, 28);
		queryRunnerControlBoxPanel.add(lblExtractTo);
		extractTocomboBox = new JComboBox();
		extractTocomboBox.setBounds(480, 13, 99, 24);
		queryRunnerControlBoxPanel.add(extractTocomboBox);
		btnPreview = new JButton("Preview");
		btnPreview.setBounds(134, 146, 123, 28);
		queryRunnerControlBoxPanel.add(btnPreview);
		btnRunQuery = new JButton("Run Query");
		btnRunQuery.setBounds(322, 146, 123, 28);
		queryRunnerControlBoxPanel.add(btnRunQuery);
		btnRunQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){			
				frame.executeRunQueryOperation();				
			}});
		btnRunQuery.setEnabled(false);
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){				
				try {
					frame.executePreviewOperation();
				}catch (Exception e1) 
				{}
			}});		
		btnPreview.setEnabled(false);		
		previewRunQueryPanel = new JPanel();
		previewRunQueryPanel.setBounds(10, 405, 609, 54);
		extractProcessPanel.add(previewRunQueryPanel);
		previewRunQueryPanel.setLayout(null);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resetAllExtractProcessPanel();
			}
		});
		btnReset.setBounds(413, 11, 78, 28);
		previewRunQueryPanel.add(btnReset);		
		exitButton = new JButton("Exit");
		exitButton.setBounds(501, 11, 78, 28);
		previewRunQueryPanel.add(exitButton);		
		headerPanel = new JPanel();
		headerPanel.setBounds(10, 11, 609, 178);
		extractProcessPanel.add(headerPanel);
		headerPanel.setLayout(null);		
		urlTextField = new JTextField();
		urlTextField.setBounds(122, 29, 471, 28);
		headerPanel.add(urlTextField);
		urlTextField.setColumns(10);		
		JLabel lblUrl = new JLabel("URL*");
		lblUrl.setBounds(10, 29, 31, 28);
		headerPanel.add(lblUrl);		
		titleTextField = new JTextField();
		titleTextField.setBounds(122, 68, 471, 28);
		headerPanel.add(titleTextField);
		titleTextField.setColumns(10);		
		JLabel lblKeyword = new JLabel("Title*");
		lblKeyword.setBounds(10, 68, 31, 28);
		headerPanel.add(lblKeyword);		
		JLabel lblExtracatDataType = new JLabel("Extract Data Type");
		lblExtracatDataType.setBounds(10, 107, 123, 28);
		headerPanel.add(lblExtracatDataType);		
		extractDataTypeComboBox = new JComboBox();
		extractDataTypeComboBox.setBounds(122, 108, 78, 26);
		headerPanel.add(extractDataTypeComboBox);
		extractDataTypeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0){
				resetExtractProcessPanel();
			}});
		extractDataTypeComboBox.setModel(new DefaultComboBoxModel(ContentType.getContentArray()));
		
		extractButton = new JButton("Extract");
		extractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){			
				frame.executeExtractOpertion();
			}});
		extractButton.setBounds(256, 139, 123, 28);
		headerPanel.add(extractButton);		
		lblExtractProcess = new JLabel("Extract Process");
		lblExtractProcess.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExtractProcess.setBounds(10, 0, 123, 28);
		headerPanel.add(lblExtractProcess);		
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				System.exit(1);
			}
		});		
		scrollPane.setVisible(false);	
		logger.info("Exiting from createExtrctProcessPanel()");
	}	
	
	/**
	 * Method for crating the batch process panel.
	 */
	public void createBatchProcessPanel(){	
		logger.info("Entering in createBatchProcessPanel()");
		batchProcessPanel = new JPanel();
		batchProcessPanel.setBounds(10, 542, 629, 211);
		contentPane.add(batchProcessPanel);
		batchProcessPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		batchProcessPanel.setLayout(null);		
		button = new JButton("Reset");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				disableBatchProessPanelControls();
			}
		});
		button.setBounds(427, 183, 78, 28);
		batchProcessPanel.add(button);		
		buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.exit(1);
			}
		});
		buttonExit.setBounds(515, 183, 78, 28);
		batchProcessPanel.add(buttonExit);		
		lblNewLabel = new JLabel("Batch Process");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 102, 28);
		batchProcessPanel.add(lblNewLabel);		
		batchProcessBrowsePanel = new JPanel();
		batchProcessBrowsePanel.setBounds(10, 40, 609, 121);
		batchProcessPanel.add(batchProcessBrowsePanel);
		batchProcessBrowsePanel.setLayout(null);		
		JLabel lblOpen = new JLabel("Open : ");
		lblOpen.setBounds(10, 19, 49, 28);
		batchProcessBrowsePanel.add(lblOpen);
		pathtextField = new JTextField();
		pathtextField.addKeyListener(new KeyAdapter(){				
			@Override
			public void keyReleased(KeyEvent e) {
				String value = pathtextField.getText();
				if(!"".equals(value.trim())){	
					btnProcess.setEnabled(true);
				}else{
					btnProcess.setEnabled(false);
				}	
			}});		
		pathtextField.setBounds(69, 19, 408, 28);
		batchProcessBrowsePanel.add(pathtextField);
		pathtextField.setColumns(10);		
		JButton btnBrowse = new JButton("Browse..");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				fc = new JFileChooser();				
				int result = fc.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION){
				    File selectedFile = fc.getSelectedFile();				    
				    pathtextField.setText(selectedFile.getAbsolutePath());
				    enableBatchProessPanelControls();
				}
			}});
		btnBrowse.setBounds(487, 19, 96, 28);
		batchProcessBrowsePanel.add(btnBrowse);		
		btnProcess = new JButton("Process");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String fileName = pathtextField.getText();				
				File f = new File(fileName);				 
				if(f.exists()){
					JOptionPane.showMessageDialog(frame, "Batch Process exported data successfully.", UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
					disableBatchProessPanelControls();
				}else{
					JOptionPane.showMessageDialog(frame, "File path is incorrect.", UIConstants.WEB_SCRAPPER, JOptionPane.ERROR_MESSAGE);
					return;
				}				
			}
		});
		btnProcess.setBounds(414, 80, 169, 30);
		batchProcessBrowsePanel.add(btnProcess);		
		disableBatchProessPanelControls();
		batchProcessPanel.setVisible(false);
		logger.info("Exiting from createBatchProcessPanel()");
	}
	
	/**
	 * Method for Disabling Batch Process Panel.
	 */
	public void disableBatchProessPanelControls(){
		logger.info("Entering in disableBatchProessPanelControls()");
		btnProcess.setEnabled(false);	
		pathtextField.setText("");
		logger.info("Exiting from disableBatchProessPanelControls()");
	}
	
	/**
	 * Method for reset Batch Process Panel.
	 */
	public void resetBatchProcessPanel(){		
		logger.info("Entering in resetBatchProcessPanel()");
		batchProcessPanel.setBounds(10, 32, 629, 243);
		frame.setBounds(10, 32, 657, 320);
		disableBatchProessPanelControls();
		frame.setLocationRelativeTo( null );
		logger.info("Exiting from resetBatchProcessPanel()");
	}
	
	/**
	 * Method for enabling batch process panel.
	 */
	public void enableBatchProessPanelControls(){
		logger.info("Entering in enableBatchProessPanelControls()");
		btnProcess.setEnabled(true);
		pathtextField.setEnabled(true);
		logger.info("Exiting from enableBatchProessPanelControls()");
	}
	
	/**
	 * Method for reset all controls of extract process panel.
	 */
	public static void resetAllExtractProcessPanel(){
		logger.info("Entering in resetAllExtractProcessPanel()");
		resetHeaderValuesValue();
		queryRunnerControlBoxPanel.setVisible(false);
		previewRunQueryPanel.setBounds(10, 180, 609, 54);
		extractProcessPanel.setBounds(10, 32, 629, 243);
		frame.setBounds(10, 32, 657, 320);
		frame.setLocationRelativeTo( null );
		btnPreview.setEnabled(false);
		unStructedRadioButton.setSelected(false);
		structedRadioButton.setSelected(false);
		htmlControlScrollPanel.setVisible(false);
		logger.info("Exiting from resetAllExtractProcessPanel()");
	}
	
	/**
	 * Method for reset extract process panel.
	 */
	public void resetExtractProcessPanel(){		
		logger.info("Entering in resetExtractProcessPanel()");
		queryRunnerControlBoxPanel.setVisible(false);
		previewRunQueryPanel.setBounds(10, 180, 609, 54);
		extractProcessPanel.setBounds(10, 32, 629, 243);
		frame.setBounds(10, 32, 657, 320);
		frame.setLocationRelativeTo( null );
		btnPreview.setEnabled(false);		
		dataTypeRadioButtonGroup.clearSelection();
		logger.info("Exiting from resetExtractProcessPanel()");
	}
	
	/**
	 * Method for expand extract process panel
	 */
	public void expandExtractProcessPanel()	{	
		logger.info("Entering in expandExtractProcessPanel()");
		frame.setBounds(100, 100, 652, 541);
		queryRunnerControlBoxPanel.setVisible(true);
		previewRunQueryPanel.setVisible(true);
		previewRunQueryPanel.setBounds(10, 405, 609, 54);
		extractProcessPanel.setBounds(10, 32, 629, 470);
		frame.setLocationRelativeTo( null );		
		String slectedValue = extractDataTypeComboBox.getSelectedItem().toString();
		if(slectedValue.equals(ContentType.IMAGE.getType())){					
			extractDataTypeSelectionpanel.setVisible(false);
			scrollPane.setVisible(true);						
			extractTocomboBox.setVisible(false);
			lblExtractTo.setVisible(false);
			htmlControlScrollPanel.setVisible(false);			
			btnRunQuery.setEnabled(false);
		}else{				
			extractDataTypeSelectionpanel.setVisible(true);
			scrollPane.setVisible(false);			
			extractTocomboBox.setVisible(true);
			lblExtractTo.setVisible(true);
			htmlControlScrollPanel.setVisible(false);			
			structedRadioButton.setEnabled(true);
			unStructedRadioButton.setEnabled(true);
			btnPreview.setEnabled(false);
			dataTypeRadioButtonGroup.clearSelection();
			htmlControlList.setListData(new CheckListItem[] {});
			btnRunQuery.setEnabled(false);
			extractTocomboBox.setModel(new DefaultComboBoxModel(new String[]{}));			
		}
		logger.info("Exiting from expandExtractProcessPanel()");
	}
	
	/**
	 * Method for populate image list.
	 */
	public void populateImageList()	{
		logger.info("Entering in populateImageList()");
		Set<String> imageUrls = extractResponse.getImageUrls();		
		List<String> imageURLList = new ArrayList<String>(imageUrls);		
		imageList.setListData(WebScrapperUtil.getCheckListItemArray(imageURLList));
		logger.info("Exiting from populateImageList()");
	}
	
	/**
	 * Method for populate html control list.
	 */
	public void populateHtmlControlList(){
		logger.info("Entering in populateHtmlControlList()");
		Set<TagType> tagValues = extractResponse.getTagDataMap().keySet();		
		List<String> tagList = new ArrayList<String>();		
		for(TagType tagType : tagValues){
			tagList.add(tagType.getDisplayName());
		}		
		htmlControlList.setListData(WebScrapperUtil.getCheckListItemArray(tagList));
		logger.info("Exiting from populateHtmlControlList()");
	}	
	
	/**
	 * Method for reset Headers.
	 */
	public static void resetHeaderValuesValue(){		
		logger.info("Entering in resetHeaderValuesValue()");
		url = null;
		title = null;
		contentType = null;
		extractRequest = null;
		wsServiceProvider = null;
		extractResponse = null;
		urlTextField.setText("");
		titleTextField.setText("");
		urlTextField.setEditable(true);
		titleTextField.setEditable(true);
		extractDataTypeComboBox.setEnabled(true);
		extractDataTypeComboBox.setModel(new DefaultComboBoxModel(ContentType.getContentArray()));
		extractButton.setEnabled(true);
		logger.info("Exiting from resetHeaderValuesValue()");
	}
	
	/**
	 * Method for disable header ares.
	 */
	public void disableHeaderArea()	{		
		logger.info("Entering in disableHeaderArea()");
		urlTextField.setEditable(false);
		titleTextField.setEditable(false);
		extractDataTypeComboBox.setEnabled(false);
		extractButton.setEnabled(false);
		logger.info("Exiting from disableHeaderArea()");
	}
	
	/**
	 * 
	 * @return
	 */
	public JTextField getPathtextField(){
		return this.pathtextField;
	}
	
	/**
	 * 
	 * @return
	 */
	public JTextField getURLtextField(){
		return this.urlTextField;
	}
	
	/**
	 * 
	 * @return
	 */
	public JTextField getTitletextField(){
		return this.titleTextField;
	}
	
	/**
	 * 
	 * @return
	 */
	public JButton getBtnPreview(){
		return this.btnPreview;	
	}
	
	/**
	 * 
	 * @return
	 */
	public JPanel getPreviewRunQueryPanel(){
		return this.previewRunQueryPanel;
	}
	
	/**
	 * 
	 * @return
	 */
	public JComboBox getExtractDataTypeComboBox(){
		return this.extractDataTypeComboBox;
	}
	
	/**
	 * 
	 * @return
	 */
	public JList getImageList(){
		return this.imageList;
	}
	
	/**
	 * 
	 * @return
	 */
	public JList getHtmlControlList(){
		return this.htmlControlList;
	}
}
