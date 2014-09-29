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
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.StructuredExtractDocType;
import com.webscrapper.constants.TagType;
import com.webscrapper.constants.UIConstants;
import com.webscrapper.constants.UnStructuredExtractDocType;

// TODO: Auto-generated Javadoc
/**
 * The Class WSUIControlsManager.
 */
public class WSUIControlsManager 
{
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(WSUIControlsManager.class);	
	
	/** The frame. */
	private WebScrapper frame = null;
	
	/** The ws ui controls. */
	private WSUIControls wsUIControls = null;
	
	/**
	 * Method for creating the UI components.
	 *
	 * @param webScrapper the web scrapper
	 */
	public WSUIControlsManager(WebScrapper webScrapper)
	{
		logger.info("Entering in WebScrapper()");		
		this.frame = webScrapper;
		this.wsUIControls = new WSUIControls();		
		webScrapper.setAlwaysOnTop(true);
		webScrapper.setResizable(false);
		webScrapper.setTitle(UIConstants.WEB_SCRAPPER);	
		webScrapper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		webScrapper.setBounds(UIConstants.WSUM_X_1, UIConstants.WSUM_Y_1, UIConstants.WSUM_WIDTH_1, UIConstants.WSUM_HEIGHT_1);		
		JPanel contentPane = new JPanel();
		webScrapper.setContentPane(contentPane);
		contentPane.setLayout(null);
		wsUIControls.setContentPane(contentPane);		
		ButtonGroup dataTypeRadioButtonGroup = new ButtonGroup();
		wsUIControls.setDataTypeRadioButtonGroup(dataTypeRadioButtonGroup);				
		logger.info("Exiting from WebScrapper()");
	}
	
	/**
	 * Method for crating the Menus.
	 */
	public void createMenus()
	{
		logger.info("Entering in createMenus()");
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(UIConstants.WSUM_X_2, UIConstants.WSUM_Y_2, UIConstants.WSUM_WIDTH_2, UIConstants.WSUM_HEIGHT_2);				
		wsUIControls.getContentPane().add(menuBar);		
		JMenu mnNewMenu = new JMenu("Process   |");
		menuBar.add(mnNewMenu);			
		JMenuItem mntmExtractProcess = new JMenuItem("Extract Process");
		mntmExtractProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				wsUIControls.getBatchProcessBrowsePanel().setVisible(false);
				wsUIControls.getExtractProcessPanel().setVisible(true);
				resetAllExtractProcessPanel();
			}});
		wsUIControls.setMntmExtractProcess(mntmExtractProcess);		
		mnNewMenu.add(mntmExtractProcess);			
		JMenuItem mntmBatchProcess = new JMenuItem("Batch Process");
		mntmBatchProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){			
				wsUIControls.getBatchProcessBrowsePanel().setVisible(true);
				wsUIControls.getExtractProcessPanel().setVisible(false);	
				resetBatchProcessPanel();
			}});
		wsUIControls.setMntmBatchProcess(mntmBatchProcess);
		mnNewMenu.add(mntmBatchProcess);
		logger.info("Exiting from createMenus()");
	}
	
	/**
	 * Method for creating extract process panel.
	 */
	public void createExtrctProcessPanel()
	{
		logger.info("Entering in createExtrctProcessPanel()");
		JPanel extractProcessPanel = new JPanel();
		extractProcessPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		extractProcessPanel.setBounds(UIConstants.WSUM_X_3, UIConstants.WSUM_Y_3, UIConstants.WSUM_WIDTH_3, UIConstants.WSUM_HEIGHT_3);
		extractProcessPanel.setLayout(null);
		wsUIControls.setExtractProcessPanel(extractProcessPanel);		
		wsUIControls.getContentPane().add(extractProcessPanel);		
		JPanel queryRunnerControlBoxPanel = new JPanel();
		queryRunnerControlBoxPanel.setBounds(UIConstants.WSUM_X_4, UIConstants.WSUM_Y_4, UIConstants.WSUM_WIDTH_4, UIConstants.WSUM_HEIGHT_4);
		extractProcessPanel.add(queryRunnerControlBoxPanel);		
		queryRunnerControlBoxPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		queryRunnerControlBoxPanel.setLayout(null);	
		wsUIControls.setQueryRunnerControlBoxPanel(queryRunnerControlBoxPanel);		
		JPanel extractDataTypeSelectionpanel = new JPanel();
		extractDataTypeSelectionpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		extractDataTypeSelectionpanel.setBounds(UIConstants.WSUM_X_5, UIConstants.WSUM_Y_5, UIConstants.WSUM_WIDTH_5, UIConstants.WSUM_HEIGHT_5);
		queryRunnerControlBoxPanel.add(extractDataTypeSelectionpanel);
		extractDataTypeSelectionpanel.setLayout(null);
		wsUIControls.setExtractDataTypeSelectionpanel(extractDataTypeSelectionpanel);		
		JRadioButton unStructedRadioButton = new JRadioButton("Non-Tabular");
		unStructedRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){				
				wsUIControls.getExtractTocomboBox().setModel(new DefaultComboBoxModel(new String[]{}));
				populateHtmlControlList();
				wsUIControls.getBtnRunQuery().setEnabled(false);
				wsUIControls.getBtnPreview().setEnabled(true);
				wsUIControls.getHtmlControlScrollPanel().setVisible(true);
			}});		
		unStructedRadioButton.setBounds(UIConstants.WSUM_X_6, UIConstants.WSUM_Y_6, UIConstants.WSUM_WIDTH_6, UIConstants.WSUM_HEIGHT_6);	
		unStructedRadioButton.setEnabled(false);
		wsUIControls.setUnStructedRadioButton(unStructedRadioButton);		
		JRadioButton structedRadioButton = new JRadioButton("Tabular");
		structedRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				wsUIControls.getExtractTocomboBox().setModel(new DefaultComboBoxModel(StructuredExtractDocType.values()));
				wsUIControls.getHtmlControlList().setListData(new CheckListItem[] {});
				wsUIControls.getBtnRunQuery().setEnabled(true);
				wsUIControls.getBtnPreview().setEnabled(true);
				wsUIControls.getHtmlControlScrollPanel().setVisible(false);
			}});		
		structedRadioButton.setBounds(UIConstants.WSUM_X_7, UIConstants.WSUM_Y_7, UIConstants.WSUM_WIDTH_7, UIConstants.WSUM_HEIGHT_7);	
		structedRadioButton.setEnabled(false);
		wsUIControls.setStructedRadioButton(structedRadioButton);		
		wsUIControls.getDataTypeRadioButtonGroup().add(unStructedRadioButton);
		wsUIControls.getDataTypeRadioButtonGroup().add(structedRadioButton);		
		extractDataTypeSelectionpanel.add(unStructedRadioButton);
		extractDataTypeSelectionpanel.add(structedRadioButton);			
		JList imageList = new JList(new CheckListItem[]{});		
		imageList.setCellRenderer(new CheckListRenderer());	
		imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		imageList.addMouseListener(new MouseAdapter(){
	         public void mouseClicked(MouseEvent event) {
	            JList list = (JList) event.getSource();
	            int index = list.locationToIndex(event.getPoint());
	            CheckListItem item = (CheckListItem)list.getModel().getElementAt(index);	            
	            item.setSelected(! item.isSelected());
	            list.repaint(list.getCellBounds(index, index));
	            List<CheckListItem> selectedList = WebScrapperUtil.getSelectedListItems(wsUIControls.getImageList());
	            if(selectedList.size() <= 0){	
	            	wsUIControls.getBtnRunQuery().setEnabled(false);
	            }else{ 
	            	wsUIControls.getBtnRunQuery().setEnabled(true);
	            }	
	         }});	
		wsUIControls.setImageList(imageList);		
		createExtrctProcessPanelPhaseTwo(imageList, queryRunnerControlBoxPanel, extractProcessPanel);	
		logger.info("Exiting from createExtrctProcessPanel()");
	}	
	
	
	/**
	 * Creates the extrct process panel phase two.
	 *
	 * @param imageList the image list
	 * @param queryRunnerControlBoxPanel the query runner control box panel
	 * @param extractProcessPanel the extract process panel
	 */
	public void createExtrctProcessPanelPhaseTwo(JList imageList, JPanel queryRunnerControlBoxPanel, JPanel extractProcessPanel)
	{
		JList htmlControlList = new JList(new CheckListItem[]{});		
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
	            List<CheckListItem> selectedList = WebScrapperUtil.getSelectedListItems(wsUIControls.getHtmlControlList());	            
	            if(selectedList.size() <= 0){
	            	wsUIControls.getExtractTocomboBox().setModel(new DefaultComboBoxModel(new String[]{}));
	            	wsUIControls.getBtnRunQuery().setEnabled(false);
	            }
	            else{	            	
	            	if(null != wsUIControls.getExtractTocomboBox() && null == wsUIControls.getExtractTocomboBox().getSelectedItem()){	
	            		wsUIControls.getExtractTocomboBox().setModel(new DefaultComboBoxModel(UnStructuredExtractDocType.values()));
	        		}	            	
	            	wsUIControls.getBtnRunQuery().setEnabled(true);	            	
	            }
	         }});	
		wsUIControls.setHtmlControlList(htmlControlList);		
		JScrollPane scrollPane = new JScrollPane(imageList);		
		scrollPane.setBounds(UIConstants.WSUM_X_8, UIConstants.WSUM_Y_8, UIConstants.WSUM_WIDTH_8, UIConstants.WSUM_HEIGHT_8);
		wsUIControls.setScrollPane(scrollPane);		
		JScrollPane htmlControlScrollPanel = new JScrollPane(htmlControlList);
		htmlControlScrollPanel.setBounds(UIConstants.WSUM_X_9, UIConstants.WSUM_Y_9, UIConstants.WSUM_WIDTH_9, UIConstants.WSUM_HEIGHT_9);
		queryRunnerControlBoxPanel.add(htmlControlScrollPanel);
		htmlControlScrollPanel.setVisible(false);
		wsUIControls.setHtmlControlScrollPanel(htmlControlScrollPanel);		
		queryRunnerControlBoxPanel.add(scrollPane);		
		JLabel lblExtractTo = new JLabel("Export To : ");
		lblExtractTo.setBounds(UIConstants.WSUM_X_10, UIConstants.WSUM_Y_10, UIConstants.WSUM_WIDTH_10, UIConstants.WSUM_HEIGHT_10);
		queryRunnerControlBoxPanel.add(lblExtractTo);
		wsUIControls.setLblExtractTo(lblExtractTo);		
		JComboBox extractTocomboBox = new JComboBox();
		extractTocomboBox.setBounds(UIConstants.WSUM_X_11, UIConstants.WSUM_Y_11, UIConstants.WSUM_WIDTH_11, UIConstants.WSUM_HEIGHT_11);
		queryRunnerControlBoxPanel.add(extractTocomboBox);
		wsUIControls.setExtractTocomboBox(extractTocomboBox);		
		JButton btnPreview = new JButton("Preview");
		btnPreview.setBounds(UIConstants.WSUM_X_12, UIConstants.WSUM_Y_12, UIConstants.WSUM_WIDTH_12, UIConstants.WSUM_HEIGHT_12);
		wsUIControls.setBtnPreview(btnPreview);
		queryRunnerControlBoxPanel.add(btnPreview);		
		JButton btnRunQuery = new JButton("Run Query");
		btnRunQuery.setBounds(UIConstants.WSUM_X_13, UIConstants.WSUM_Y_13, UIConstants.WSUM_WIDTH_13, UIConstants.WSUM_HEIGHT_13);
		queryRunnerControlBoxPanel.add(btnRunQuery);
		btnRunQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){			
				frame.executeRunQueryOperation();				
			}});
		btnRunQuery.setEnabled(false);
		wsUIControls.setBtnRunQuery(btnRunQuery);
		
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){				
				try {
					frame.executePreviewOperation();
				}catch (Exception e1) 
				{}
			}});		
		btnPreview.setEnabled(false);
		wsUIControls.setBtnPreview(btnPreview);		
		JPanel previewRunQueryPanel = new JPanel();
		previewRunQueryPanel.setBounds(UIConstants.WSUM_X_14, UIConstants.WSUM_Y_14, UIConstants.WSUM_WIDTH_14, UIConstants.WSUM_HEIGHT_14);
		extractProcessPanel.add(previewRunQueryPanel);
		previewRunQueryPanel.setLayout(null);
		wsUIControls.setPreviewRunQueryPanel(previewRunQueryPanel);		
		createExtrctProcessPanelPhaseThree(previewRunQueryPanel, extractProcessPanel, scrollPane);
	}
	
	/**
	 * Creates the extrct process panel phase three.
	 *
	 * @param previewRunQueryPanel the preview run query panel
	 * @param extractProcessPanel the extract process panel
	 * @param scrollPane the scroll pane
	 */
	public void createExtrctProcessPanelPhaseThree(JPanel previewRunQueryPanel, JPanel extractProcessPanel, JScrollPane scrollPane)
	{
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resetAllExtractProcessPanel();
			}
		});
		btnReset.setBounds(UIConstants.WSUM_X_15, UIConstants.WSUM_Y_15, UIConstants.WSUM_WIDTH_15, UIConstants.WSUM_HEIGHT_15);
		previewRunQueryPanel.add(btnReset);
		wsUIControls.setPreviewRunQueryPanel(previewRunQueryPanel);		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(UIConstants.WSUM_X_16, UIConstants.WSUM_Y_16, UIConstants.WSUM_WIDTH_16, UIConstants.WSUM_HEIGHT_16);
		previewRunQueryPanel.add(exitButton);		
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(UIConstants.WSUM_X_17, UIConstants.WSUM_Y_17, UIConstants.WSUM_WIDTH_17, UIConstants.WSUM_HEIGHT_17);
		extractProcessPanel.add(headerPanel);
		headerPanel.setLayout(null);
		wsUIControls.setHeaderPanel(headerPanel);		
		JTextField urlTextField = new JTextField();
		urlTextField.setBounds(UIConstants.WSUM_X_18, UIConstants.WSUM_Y_18, UIConstants.WSUM_WIDTH_18, UIConstants.WSUM_HEIGHT_18);
		headerPanel.add(urlTextField);
		urlTextField.setColumns(10);
		wsUIControls.setUrlTextField(urlTextField);		
		createExtrctProcessPanelPhaseFour(headerPanel, exitButton, scrollPane);
	}
	
	
	/**
	 * Creates the extrct process panel phase four.
	 *
	 * @param headerPanel the header panel
	 * @param exitButton the exit button
	 * @param scrollPane the scroll pane
	 */
	public void createExtrctProcessPanelPhaseFour(JPanel headerPanel, JButton exitButton, JScrollPane scrollPane)
	{
		JLabel lblUrl = new JLabel("URL*");
		lblUrl.setBounds(UIConstants.WSUM_X_19, UIConstants.WSUM_Y_19, UIConstants.WSUM_WIDTH_19, UIConstants.WSUM_HEIGHT_19);
		wsUIControls.getHeaderPanel().add(lblUrl);		
		JTextField titleTextField = new JTextField();
		titleTextField.setBounds(UIConstants.WSUM_X_20, UIConstants.WSUM_Y_20, UIConstants.WSUM_WIDTH_20, UIConstants.WSUM_HEIGHT_20);
		headerPanel.add(titleTextField);
		titleTextField.setColumns(10);	
		wsUIControls.setTitleTextField(titleTextField);		
		JLabel lblKeyword = new JLabel("Title*");
		lblKeyword.setBounds(UIConstants.WSUM_X_21, UIConstants.WSUM_Y_21, UIConstants.WSUM_WIDTH_21, UIConstants.WSUM_HEIGHT_21);
		headerPanel.add(lblKeyword);		
		JLabel lblExtracatDataType = new JLabel("Extract Data Type");
		lblExtracatDataType.setBounds(UIConstants.WSUM_X_22, UIConstants.WSUM_Y_22, UIConstants.WSUM_WIDTH_22, UIConstants.WSUM_HEIGHT_22);
		headerPanel.add(lblExtracatDataType);		
		JComboBox extractDataTypeComboBox = new JComboBox();
		extractDataTypeComboBox.setBounds(UIConstants.WSUM_X_23, UIConstants.WSUM_Y_23, UIConstants.WSUM_WIDTH_23, UIConstants.WSUM_HEIGHT_23);
		headerPanel.add(extractDataTypeComboBox);
		extractDataTypeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0){
				resetExtractProcessPanel();
			}});
		extractDataTypeComboBox.setModel(new DefaultComboBoxModel(ContentType.getContentArray()));
		wsUIControls.setExtractDataTypeComboBox(extractDataTypeComboBox);		
		JButton extractButton = new JButton("Extract");
		extractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){			
				frame.executeExtractOpertion();
			}});
		extractButton.setBounds(UIConstants.WSUM_X_24, UIConstants.WSUM_Y_24, UIConstants.WSUM_WIDTH_24, UIConstants.WSUM_HEIGHT_24);
		headerPanel.add(extractButton);	
		wsUIControls.setExtractButton(extractButton);		
		JLabel lblExtractProcess = new JLabel("Extract Process");
		lblExtractProcess.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExtractProcess.setBounds(UIConstants.WSUM_X_25, UIConstants.WSUM_Y_25, UIConstants.WSUM_WIDTH_25, UIConstants.WSUM_HEIGHT_25);
		headerPanel.add(lblExtractProcess);		
		wsUIControls.setLblExtractProcess(lblExtractProcess);		
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				System.exit(1);
			}
		});		
		scrollPane.setVisible(false);
	}
	
	/**
	 * Method for crating the batch process panel.
	 */
	public void createBatchProcessPanel(){	
		logger.info("Entering in createBatchProcessPanel()");		
		JPanel batchProcessPanel = new JPanel();
		batchProcessPanel.setBounds(UIConstants.WSUM_X_26, UIConstants.WSUM_Y_26, UIConstants.WSUM_WIDTH_26, UIConstants.WSUM_HEIGHT_26);
		wsUIControls.setBatchProcessPanel(batchProcessPanel);		
		wsUIControls.getContentPane().add(batchProcessPanel);
		batchProcessPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		batchProcessPanel.setLayout(null);	
		JButton button = new JButton("Reset");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				disableBatchProessPanelControls();
			}
		});
		button.setBounds(UIConstants.WSUM_X_27, UIConstants.WSUM_Y_27, UIConstants.WSUM_WIDTH_27, UIConstants.WSUM_HEIGHT_27);
		batchProcessPanel.add(button);	
		wsUIControls.setButton(button);		
		JButton buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.exit(1);
			}
		});
		buttonExit.setBounds(UIConstants.WSUM_X_28, UIConstants.WSUM_Y_28, UIConstants.WSUM_WIDTH_28, UIConstants.WSUM_HEIGHT_28);
		batchProcessPanel.add(buttonExit);
		wsUIControls.setButtonExit(buttonExit);		
		JLabel lblNewLabel = new JLabel("Batch Process");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(UIConstants.WSUM_X_29, UIConstants.WSUM_Y_29, UIConstants.WSUM_WIDTH_29, UIConstants.WSUM_HEIGHT_29);
		batchProcessPanel.add(lblNewLabel);		
		wsUIControls.setLblNewLabel(lblNewLabel);		
		JPanel batchProcessBrowsePanel = new JPanel();
		batchProcessBrowsePanel.setBounds(UIConstants.WSUM_X_30, UIConstants.WSUM_Y_30, UIConstants.WSUM_WIDTH_30, UIConstants.WSUM_HEIGHT_30);
		batchProcessPanel.add(batchProcessBrowsePanel);
		batchProcessBrowsePanel.setLayout(null);		
		JLabel lblOpen = new JLabel("Open : ");
		lblOpen.setBounds(UIConstants.WSUM_X_31, UIConstants.WSUM_Y_31, UIConstants.WSUM_WIDTH_31, UIConstants.WSUM_HEIGHT_31);
		batchProcessBrowsePanel.add(lblOpen);
		wsUIControls.setBatchProcessBrowsePanel(batchProcessBrowsePanel);		
		JTextField pathtextField = new JTextField();
		pathtextField.addKeyListener(new KeyAdapter(){				
			@Override
			public void keyReleased(KeyEvent e) {
				String value = wsUIControls.getPathtextField().getText();
				if(!"".equals(value.trim())){	
					wsUIControls.getBtnProcess().setEnabled(true);
				}else{
					wsUIControls.getBtnProcess().setEnabled(false);
				}	
			}});		
		pathtextField.setBounds(UIConstants.WSUM_X_32, UIConstants.WSUM_Y_32, UIConstants.WSUM_WIDTH_32, UIConstants.WSUM_HEIGHT_32);
		batchProcessBrowsePanel.add(pathtextField);
		pathtextField.setColumns(10);
		wsUIControls.setPathtextField(pathtextField);		
		JButton btnBrowse = new JButton("Browse..");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JFileChooser fc = new JFileChooser();				
				int result = fc.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION){
				    File selectedFile = fc.getSelectedFile();				    
				    wsUIControls.getPathtextField().setText(selectedFile.getAbsolutePath());
				    enableBatchProessPanelControls();
				}
			}});
		btnBrowse.setBounds(UIConstants.WSUM_X_33, UIConstants.WSUM_Y_33, UIConstants.WSUM_WIDTH_33, UIConstants.WSUM_HEIGHT_33);
		batchProcessBrowsePanel.add(btnBrowse);			
		JButton btnProcess = new JButton("Process");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String fileName = wsUIControls.getPathtextField().getText();				
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
		btnProcess.setBounds(UIConstants.WSUM_X_34, UIConstants.WSUM_Y_34, UIConstants.WSUM_WIDTH_34, UIConstants.WSUM_HEIGHT_34);
		batchProcessBrowsePanel.add(btnProcess);
		wsUIControls.setBtnProcess(btnProcess);		
		disableBatchProessPanelControls();
		batchProcessPanel.setVisible(false);
		logger.info("Exiting from createBatchProcessPanel()");
	}
	
	/**
	 * Method for Disabling Batch Process Panel.
	 */
	public void disableBatchProessPanelControls(){
		logger.info("Entering in disableBatchProessPanelControls()");
		wsUIControls.getBtnProcess().setEnabled(false);	
		wsUIControls.getPathtextField().setText("");
		logger.info("Exiting from disableBatchProessPanelControls()");
	}
	
	/**
	 * Method for reset Batch Process Panel.
	 */
	public void resetBatchProcessPanel(){		
		logger.info("Entering in resetBatchProcessPanel()");
		wsUIControls.getBatchProcessPanel().setBounds(UIConstants.WSUM_X_35, UIConstants.WSUM_Y_35, UIConstants.WSUM_WIDTH_35, UIConstants.WSUM_HEIGHT_35);
		frame.setBounds(UIConstants.WSUM_X_36, UIConstants.WSUM_Y_36, UIConstants.WSUM_WIDTH_36, UIConstants.WSUM_HEIGHT_36);
		disableBatchProessPanelControls();
		frame.setLocationRelativeTo( null );
		logger.info("Exiting from resetBatchProcessPanel()");
	}
	
	/**
	 * Method for enabling batch process panel.
	 */
	public void enableBatchProessPanelControls(){
		logger.info("Entering in enableBatchProessPanelControls()");
		wsUIControls.getBtnProcess().setEnabled(true);
		wsUIControls.getPathtextField().setEnabled(true);
		logger.info("Exiting from enableBatchProessPanelControls()");
	}
	
	/**
	 * Method for reset all controls of extract process panel.
	 */
	public void resetAllExtractProcessPanel(){
		logger.info("Entering in resetAllExtractProcessPanel()");
		resetHeaderValuesValue();
		wsUIControls.getQueryRunnerControlBoxPanel().setVisible(false);
		wsUIControls.getPreviewRunQueryPanel().setBounds(UIConstants.WSUM_X_37, UIConstants.WSUM_Y_37, UIConstants.WSUM_WIDTH_37, UIConstants.WSUM_HEIGHT_37);
		wsUIControls.getExtractProcessPanel().setBounds(UIConstants.WSUM_X_38, UIConstants.WSUM_Y_38, UIConstants.WSUM_WIDTH_38, UIConstants.WSUM_HEIGHT_38);
		frame.setBounds(UIConstants.WSUM_X_39, UIConstants.WSUM_Y_39, UIConstants.WSUM_WIDTH_39, UIConstants.WSUM_HEIGHT_39);
		frame.setLocationRelativeTo( null );
		wsUIControls.getBtnPreview().setEnabled(false);
		wsUIControls.getUnStructedRadioButton().setSelected(false);
		wsUIControls.getStructedRadioButton().setSelected(false);
		wsUIControls.getHtmlControlScrollPanel().setVisible(false);
		logger.info("Exiting from resetAllExtractProcessPanel()");
	}
	
	/**
	 * Method for reset extract process panel.
	 */
	public void resetExtractProcessPanel(){		
		logger.info("Entering in resetExtractProcessPanel()");
		wsUIControls.getQueryRunnerControlBoxPanel().setVisible(false);
		wsUIControls.getPreviewRunQueryPanel().setBounds(UIConstants.WSUM_X_40, UIConstants.WSUM_Y_40, UIConstants.WSUM_WIDTH_40, UIConstants.WSUM_HEIGHT_40);
		wsUIControls.getExtractProcessPanel().setBounds(UIConstants.WSUM_X_41, UIConstants.WSUM_Y_41, UIConstants.WSUM_WIDTH_41, UIConstants.WSUM_HEIGHT_41);
		frame.setBounds(UIConstants.WSUM_X_42, UIConstants.WSUM_Y_42, UIConstants.WSUM_WIDTH_42, UIConstants.WSUM_HEIGHT_42);
		frame.setLocationRelativeTo( null );
		wsUIControls.getBtnPreview().setEnabled(false);		
		wsUIControls.getDataTypeRadioButtonGroup().clearSelection();
		logger.info("Exiting from resetExtractProcessPanel()");
	}
	
	/**
	 * Method for expand extract process panel.
	 */
	public void expandExtractProcessPanel()	{	
		logger.info("Entering in expandExtractProcessPanel()");
		frame.setBounds(UIConstants.WSUM_X_43, UIConstants.WSUM_Y_43, UIConstants.WSUM_WIDTH_43, UIConstants.WSUM_HEIGHT_43);
		wsUIControls.getQueryRunnerControlBoxPanel().setVisible(true);
		wsUIControls.getPreviewRunQueryPanel().setVisible(true);
		wsUIControls.getPreviewRunQueryPanel().setBounds(UIConstants.WSUM_X_44, UIConstants.WSUM_Y_44, UIConstants.WSUM_WIDTH_44, UIConstants.WSUM_HEIGHT_44);
		wsUIControls.getExtractProcessPanel().setBounds(UIConstants.WSUM_X_45, UIConstants.WSUM_Y_45, UIConstants.WSUM_WIDTH_45, UIConstants.WSUM_HEIGHT_45);
		frame.setLocationRelativeTo( null );		
		String slectedValue = wsUIControls.getExtractDataTypeComboBox().getSelectedItem().toString();
		if(slectedValue.equals(ContentType.IMAGE.getType())){					
			wsUIControls.getExtractDataTypeSelectionpanel().setVisible(false);
			wsUIControls.getScrollPane().setVisible(true);						
			wsUIControls.getExtractTocomboBox().setVisible(false);
			wsUIControls.getLblExtractTo().setVisible(false);
			wsUIControls.getHtmlControlScrollPanel().setVisible(false);			
			wsUIControls.getBtnRunQuery().setEnabled(false);
		}else{				
			wsUIControls.getExtractDataTypeSelectionpanel().setVisible(true);
			wsUIControls.getScrollPane().setVisible(false);			
			wsUIControls.getExtractTocomboBox().setVisible(true);
			wsUIControls.getLblExtractTo().setVisible(true);
			wsUIControls.getHtmlControlScrollPanel().setVisible(false);			
			wsUIControls.getStructedRadioButton().setEnabled(true);
			wsUIControls.getUnStructedRadioButton().setEnabled(true);
			wsUIControls.getBtnPreview().setEnabled(false);
			wsUIControls.getDataTypeRadioButtonGroup().clearSelection();
			wsUIControls.getHtmlControlList().setListData(new CheckListItem[] {});
			wsUIControls.getBtnRunQuery().setEnabled(false);
			wsUIControls.getExtractTocomboBox().setModel(new DefaultComboBoxModel(new String[]{}));			
		}
		logger.info("Exiting from expandExtractProcessPanel()");
	}
	
	/**
	 * Method for populate image list.
	 */
	public void populateImageList()	{
		logger.info("Entering in populateImageList()");
		Set<String> imageUrls = frame.getExtractResponse().getImageUrls();		
		List<String> imageURLList = new ArrayList<String>(imageUrls);		
		wsUIControls.getImageList().setListData(WebScrapperUtil.getCheckListItemArray(imageURLList));
		logger.info("Exiting from populateImageList()");
	}
	
	/**
	 * Method for populate html control list.
	 */
	public void populateHtmlControlList(){
		logger.info("Entering in populateHtmlControlList()");
		Set<TagType> tagValues = frame.getExtractResponse().getTagDataMap().keySet();		
		List<String> tagList = new ArrayList<String>();		
		for(TagType tagType : tagValues){
			tagList.add(tagType.getDisplayName());
		}		
		wsUIControls.getHtmlControlList().setListData(WebScrapperUtil.getCheckListItemArray(tagList));
		logger.info("Exiting from populateHtmlControlList()");
	}	
	
	/**
	 * Method for reset Headers.
	 */
	/**
	 * 
	 */
	public void resetHeaderValuesValue(){		
		logger.info("Entering in resetHeaderValuesValue()");
		wsUIControls.setUrl(null);
		wsUIControls.setTitle(null);
		wsUIControls.setContentType(null);
		frame.setExtractRequest(null);
		frame.setWsServiceProvider(null);
		frame.setExtractResponse(null);		
		wsUIControls.getUrlTextField().setText("");
		wsUIControls.getTitleTextField().setText("");
		wsUIControls.getUrlTextField().setEditable(true);
		wsUIControls.getTitleTextField().setEditable(true);
		wsUIControls.getExtractDataTypeComboBox().setEnabled(true);
		wsUIControls.getExtractDataTypeComboBox().setModel(new DefaultComboBoxModel(ContentType.getContentArray()));
		wsUIControls.getExtractButton().setEnabled(true);
		logger.info("Exiting from resetHeaderValuesValue()");
	}
	
	/**
	 * Method for disable header ares.
	 */
	public void disableHeaderArea()	{		
		logger.info("Entering in disableHeaderArea()");
		wsUIControls.getUrlTextField().setEditable(false);
		wsUIControls.getTitleTextField().setEditable(false);
		wsUIControls.getExtractDataTypeComboBox().setEnabled(false);
		wsUIControls.getExtractButton().setEnabled(false);
		logger.info("Exiting from disableHeaderArea()");
	}

	/**
	 * Gets the frame.
	 *
	 * @return the frame
	 */
	public WebScrapper getFrame() {
		return frame;
	}

	/**
	 * Sets the frame.
	 *
	 * @param frame the new frame
	 */
	public void setFrame(WebScrapper frame) {
		this.frame = frame;
	}

	/**
	 * Gets the ws ui controls.
	 *
	 * @return the ws ui controls
	 */
	public WSUIControls getWsUIControls() {
		return wsUIControls;
	}

	/**
	 * Sets the ws ui controls.
	 *
	 * @param wsUIControls the new ws ui controls
	 */
	public void setWsUIControls(WSUIControls wsUIControls) {
		this.wsUIControls = wsUIControls;
	}
	
	
}
