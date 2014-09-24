package com.webscapper.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import com.webscapper.request.ExportRequest;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.ExportType;
import com.webscrapper.constants.StructuredExtractDocType;
import com.webscrapper.constants.TagType;
import com.webscrapper.constants.UIConstants;
import com.webscrapper.constants.UnStructuredExtractDocType;
public class WebScrapper extends JFrame {
	private static Logger logger = Logger.getLogger(WebScrapper.class);
	static {
        // Initialize for ssl communication.
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        } };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }
	
	private JPanel contentPane;
	static WebScrapper frame = null;
	static JProgressBar progressBar;
	private JTextField urlTextField;
	private JTextField titleTextField;
	private JButton exitButton;
	private JComboBox extractDataTypeComboBox;
	private JRadioButton unStructedRadioButton;
	private JRadioButton structedRadioButton;
	private ButtonGroup dataTypeRadioButtonGroup;
	private JPanel extractDataTypeSelectionpanel;
	String[] columnNames = {UIConstants.TYPE, UIConstants.FOUND_PLACES};
	String[][] data = {{UIConstants.TYPE, UIConstants.FOUND_PLACES},{"Structured", "0"},{"UnStructured", "0"}};
	private boolean isExtractDone = false;
	JFileChooser fc;
	private JScrollPane scrollPane;
	private JList imageList;
	private JButton btnPreview;	
	JCheckBox chckbxDiv;
	JCheckBox chckbxSpan;
	JCheckBox chckbxParagraph;	
	private JPanel previewRunQueryPanel;
	JComboBox extractTocomboBox;
	JLabel lblExtractTo;
	private JButton btnReset;
	private JCheckBox chckbxAll;
	private JMenuItem mntmExtractProcess;
	private JMenuItem mntmBatchProcess;
	private JPanel batchProcessPanel;
	private JButton button;
	private JButton button_1;
	private JLabel lblNewLabel;
	private JTextField pathtextField;
	private JPanel batchProcessBrowsePanel;
	private JPanel extractProcessPanel;
	private JButton btnProcess;
	private JPanel headerPanel;
	private JLabel lblExtractProcess;
	private JPanel queryRunnerControlBoxPanel ;	
	private JButton btnRunQuery;	
	private JScrollPane htmlControlScrollPanel;
	private JList htmlControlList;
	JButton extractButton;
	private String url;
	private String title;
	private ContentType contentType;
	private ExtractRequest extractRequest;
	private ExtractResponse extractResponse = null;
	private WSServiceProvider wsServiceProvider;
	
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		logger.info("Entring in main()");
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    
		EventQueue.invokeLater(new Runnable(){public void run(){
												try {
													frame = new WebScrapper();frame.setVisible(true);frame.setLocationRelativeTo( null );frame.resetAllExtractProcessPanel();				
												}catch (Exception e) 
												{
													logger.warn(e);													
													System.exit(1);
												}}});
		logger.info("Exit from main()");
	}


	/**
	 * Method for creating the UI components.
	 * @throws Exception
	 */
	public WebScrapper() throws Exception 
	{
		logger.info("Entering in WebScrapper()");		
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Web Scrapper");	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 792);
		contentPane = new JPanel();
		setContentPane(contentPane);
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
	}
	
	/**
	 * Method for creating extract process panel.
	 * @throws Exception
	 */
	public void createExtrctProcessPanel() throws Exception
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
	            if(selectedList.size() <= 0) btnRunQuery.setEnabled(false);
	            else btnRunQuery.setEnabled(true);
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
				executeRunQueryOperation();				
			}});
		btnRunQuery.setEnabled(false);
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){				
				try {
					executePreviewOperation();
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
				executeExtractOpertion();
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
		button_1 = new JButton("Exit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.exit(1);
			}
		});
		button_1.setBounds(515, 183, 78, 28);
		batchProcessPanel.add(button_1);		
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
				int result = fc.showOpenDialog(WebScrapper.this);
				if (result == JFileChooser.APPROVE_OPTION){
				    File selectedFile = fc.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
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
				System.out.println("File Name : "+fileName);
				File f = new File(fileName);				 
				if(f.exists()){
					JOptionPane.showMessageDialog(frame, "Batch Process exported data successfully.", "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
					disableBatchProessPanelControls();
				}else{
					JOptionPane.showMessageDialog(frame, "File path is incorrect.", "Web Scrapper", JOptionPane.ERROR_MESSAGE);
					return;
				}				
			}
		});
		btnProcess.setBounds(414, 80, 169, 30);
		batchProcessBrowsePanel.add(btnProcess);		
		disableBatchProessPanelControls();
		batchProcessPanel.setVisible(false);
	}
	
	/**
	 * Method for Disabling Batch Process Panel.
	 */
	public void disableBatchProessPanelControls(){
		logger.info("Entering in disableBatchProessPanelControls()");
		btnProcess.setEnabled(false);	
		pathtextField.setText("");
	}
	
	/**
	 * Method for reset Batch Process Panel.
	 */
	public void resetBatchProcessPanel(){		
		logger.info("Entering in resetBatchProcessPanel()");
		batchProcessPanel.setBounds(10, 32, 629, 243);
		setBounds(10, 32, 657, 320);
		disableBatchProessPanelControls();
		frame.setLocationRelativeTo( null );
	}
	
	/**
	 * Method for enabling batch process panel.
	 */
	public void enableBatchProessPanelControls(){
		logger.info("Entering in enableBatchProessPanelControls()");
		btnProcess.setEnabled(true);
		pathtextField.setEnabled(true);
	}
	
	/**
	 * Method for reset all controls of extract process panel.
	 */
	public void resetAllExtractProcessPanel(){
		logger.info("Entering in resetAllExtractProcessPanel()");
		resetHeaderValuesValue();
		queryRunnerControlBoxPanel.setVisible(false);
		previewRunQueryPanel.setBounds(10, 180, 609, 54);
		extractProcessPanel.setBounds(10, 32, 629, 243);
		setBounds(10, 32, 657, 320);
		frame.setLocationRelativeTo( null );
		btnPreview.setEnabled(false);
		unStructedRadioButton.setSelected(false);
		structedRadioButton.setSelected(false);
		htmlControlScrollPanel.setVisible(false);
	}
	
	/**
	 * Method for reset extract process panel.
	 */
	public void resetExtractProcessPanel(){		
		logger.info("Entering in resetExtractProcessPanel()");
		queryRunnerControlBoxPanel.setVisible(false);
		previewRunQueryPanel.setBounds(10, 180, 609, 54);
		extractProcessPanel.setBounds(10, 32, 629, 243);
		setBounds(10, 32, 657, 320);
		frame.setLocationRelativeTo( null );
		btnPreview.setEnabled(false);		
		dataTypeRadioButtonGroup.clearSelection();
	}
	
	/**
	 * Method for expand extract process panel
	 */
	public void expandExtractProcessPanel()	{	
		logger.info("Entering in expandExtractProcessPanel()");
		setBounds(100, 100, 652, 541);
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
	}
	
	/**
	 * Method for populate image list.
	 */
	public void populateImageList()	{
		logger.info("Entering in populateImageList()");
		Set<String> imageUrls = frame.extractResponse.getImageUrls();		
		List<String> imageURLList = new ArrayList<String>(imageUrls);		
		imageList.setListData(WebScrapperUtil.getCheckListItemArray(imageURLList));
	}
	
	/**
	 * Method for populate html control list.
	 */
	public void populateHtmlControlList(){
		logger.info("Entering in populateHtmlControlList()");
		Set<TagType> tagValues = frame.extractResponse.getTagDataMap().keySet();		
		List<String> tagList = new ArrayList<String>();		
		for(TagType tagType : tagValues){
			tagList.add(tagType.getDisplayName());
		}		
		htmlControlList.setListData(WebScrapperUtil.getCheckListItemArray(tagList));
	}	
	
	/**
	 * Method for reset Headers.
	 */
	public void resetHeaderValuesValue(){		
		logger.info("Entering in resetHeaderValuesValue()");
		frame.url = null;
		frame.title = null;
		frame.contentType = null;
		frame.extractRequest = null;
		frame.wsServiceProvider = null;
		frame.extractResponse = null;
		urlTextField.setText("");
		titleTextField.setText("");
		urlTextField.setEditable(true);
		titleTextField.setEditable(true);
		extractDataTypeComboBox.setEnabled(true);
		extractDataTypeComboBox.setModel(new DefaultComboBoxModel(ContentType.getContentArray()));
		extractButton.setEnabled(true);
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
	}
	
	/**
	 * Method for start the extract operation.
	 */
	public void executeExtractOpertion(){		
		logger.info("Entering in executeExtractOpertion()");
		String url = urlTextField.getText().trim();
		String keyword = titleTextField.getText().trim();		
		if((null == url) || UIConstants.BLANK.equals(url) || (null == keyword) || UIConstants.BLANK.equals(keyword)){
			JOptionPane.showMessageDialog(frame, "URL and title is required.", "Web Scrapper", JOptionPane.ERROR_MESSAGE);
			return;
		}else{
			if(url.startsWith("http:") || url.startsWith("https:")){
				boolean isValidURL = URLUtil.isValidURL(url);
				if(!isValidURL){
					JOptionPane.showMessageDialog(frame, "URL is invalid.", "Web Scrapper", JOptionPane.ERROR_MESSAGE);
					return;
				}						
			}else{
				url = "http://"+url;
				if(!URLUtil.isValidURL("http://"+url)){
					url = "https://"+url; 
					if(!URLUtil.isValidURL(url)){	
						JOptionPane.showMessageDialog(frame, "URL is invalid.", "Web Scrapper", JOptionPane.ERROR_MESSAGE);
						return;
					}						
				}					
			}
			
			boolean isValid = URLUtil.isValidURLForConnection(url);
			if(!isValid){
				JOptionPane.showMessageDialog(frame, "URL is invalid.", "Web Scrapper", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}		
		String slectedValue = extractDataTypeComboBox.getSelectedItem().toString();
		frame.url = url;
		frame.title = keyword;
		frame.contentType = ContentType.getContentType(slectedValue);		
		frame.wsServiceProvider = new WSServiceProvider();
		frame.extractRequest = frame.wsServiceProvider.buildExtractRequest(frame.url, frame.contentType);
		frame.extractResponse = frame.wsServiceProvider.executeExtractOperation(frame.extractRequest);		
		if(null == frame.extractResponse){
			JOptionPane.showMessageDialog(frame, "Selected Option Data is not available on the web page.", "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
			return;
		}		
		WebScrapperUtil.showWaitingDialog(frame);				
		if(slectedValue.equals(ContentType.IMAGE.getType())){				
			populateImageList();
			btnPreview.setEnabled(true);
		}
		expandExtractProcessPanel();
		disableHeaderArea();
	}
	
	/**
	 * Method for preview operation.
	 * @throws Exception
	 */
	public void executePreviewOperation() throws Exception{
		logger.info("Entering in executePreviewOperation()");
		String slectedOptionValue = extractDataTypeComboBox.getSelectedItem().toString();		
		if(slectedOptionValue.equals(ContentType.IMAGE.getType())){			
			List<CheckListItem> lists = WebScrapperUtil.getSelectedListItems(imageList);			
			if(lists.size() >0){	
				InputStream stream = frame.wsServiceProvider.fetchImagePreviewData(WebScrapperUtil.getSelectedListItems(imageList).get(0).toString());
				BufferedImage bufferedImage = null;
				try{
					bufferedImage = ImageIO.read( stream );
				}catch (IOException e1)	{}				
				ImageIcon image = new ImageIcon( bufferedImage );				
				Image scaleImage = image.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);				
				image = new ImageIcon(scaleImage);				
				JLabel lbl = new JLabel(image);				
				JOptionPane.showMessageDialog(frame, lbl,"Image Preview", -1);	
			}else{
				JOptionPane.showMessageDialog(frame, "No Image Preview available, kindly select image.", "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
    	}else{	
    		JScrollPane scrollPane = null;			
			if(structedRadioButton.isSelected()){
				String columnNames[] = frame.wsServiceProvider.fetchColumnNameForPreview(frame.extractResponse);
				String dataValues[][] = frame.wsServiceProvider.fetchTabularPreviewData(frame.extractResponse);				
				JTable table = new JTable( dataValues, columnNames );
				table.setTableHeader(null);
				table.setGridColor(Color.YELLOW);
		        table.setBackground(Color.CYAN);
		        table.setEnabled(false);				
		        table.setPreferredScrollableViewportSize(new Dimension(200, 200));		        
				scrollPane = new JScrollPane( table );
			}else{	
				List<String> selectedHTMLControlList = WebScrapperUtil.getSelectedListItemValues(htmlControlList);						
				if(selectedHTMLControlList.size() >0)				{	
					String content = frame.wsServiceProvider.fetchNonTabularPreviewData(frame.extractResponse, selectedHTMLControlList);					
					JTextArea textArea = new JTextArea(10, 25);
					textArea.setLineWrap(true);
				    textArea.setText(content);
				    textArea.setEditable(false);				    
				    scrollPane = new JScrollPane(textArea);
				}else{
					JOptionPane.showMessageDialog(frame, "No Data Preview available, kindly select HTML Control.", "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
			JOptionPane.showMessageDialog(frame, scrollPane,"Data Preview", -1);
		}	
	}
	
	/**
	 * Method for perform the run query operation.
	 */
	public void executeRunQueryOperation(){
		logger.info("Entering in executeRunQueryOperation()");
		String selectedOptionValue = extractDataTypeComboBox.getSelectedItem().toString();
		String extractToOptionValue = "";		
		if(null != extractTocomboBox && null != extractTocomboBox.getSelectedItem()){	
			extractToOptionValue = extractTocomboBox.getSelectedItem().toString();				
		}		
		JLabel queryLabel = new JLabel(" Result Query : ");		
		JTextField queryTextField = new JTextField();
		Font font = new Font("Verdana", Font.BOLD, 12);				
		queryTextField.setEditable(false);
		queryTextField.setFont(font);		
		String url = urlTextField.getText();
		String title = titleTextField.getText();
		String selectedTabularOption = "";		
		if(structedRadioButton.isSelected()) selectedTabularOption = "Tabular";
		else selectedTabularOption = "Non-Tabular";		
		if(selectedOptionValue.equals(ContentType.IMAGE.getType())){
			queryTextField.setText(url + "," +title+","+selectedOptionValue+","+ WebScrapperUtil.getSelectedListItems(imageList).toString());
    	}else{
			if(structedRadioButton.isSelected()) queryTextField.setText(url + "," +title+","+selectedOptionValue+","+selectedTabularOption+","+extractToOptionValue);						
			else queryTextField.setText(url + "," +title+","+selectedOptionValue+","+selectedTabularOption+","+WebScrapperUtil.getSelectedListItems(htmlControlList).toString()+","+extractToOptionValue);			
		}		
		queryTextField.setColumns(14);		
		JComboBox comboBox = new JComboBox();				
		comboBox.setModel(new DefaultComboBoxModel(new String[]{"Export","Save Query For Batch"}));		
		JPanel message = new JPanel();
        message.add(queryLabel);              
        message.add(queryTextField);
        message.add(comboBox);
        Object[] options = new String[]{"Process","Cancel"};
        int returnvalue = JOptionPane.showOptionDialog(frame, message, "Web Scrapper", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String optionValue = comboBox.getSelectedItem().toString();
        if(returnvalue == 0){	                
        	if("Export".equals(optionValue)){               	            	
        		List<String> selectedImageURLList = new ArrayList<String>();
        		List<String> selectedHTMLControlList = new ArrayList<String>();        		
        		if(selectedOptionValue.equals(ContentType.IMAGE.getType())) selectedImageURLList = WebScrapperUtil.getSelectedListItemValues(imageList);
				else selectedHTMLControlList = WebScrapperUtil.getSelectedListItemValues(htmlControlList);        		
        		boolean result = executeExportOperation(extractToOptionValue, selectedOptionValue, selectedImageURLList, selectedHTMLControlList);
        		if(!result) return;        			
            }else{
            	fc = new JFileChooser();
				fc.setDialogTitle("Save");
				int result = fc.showSaveDialog(WebScrapper.this);
				if (result == JFileChooser.APPROVE_OPTION){
				    File selectedFile = fc.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());				    
				    JOptionPane.showMessageDialog(frame, "Query Saved Successfuly for batch processing. For batch processing you need to select batch process menu.", "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
				}else return;				
            }
            resetAllExtractProcessPanel();
        }	
	}


	/**
	 * Method for export operation.
	 * @param extractToOptionValue
	 */
	private boolean executeExportOperation(String extractToOptionValue, String selectedOptionValue, List<String> selectedImageURLList, List<String> selectedHTMLControlList){
		logger.info("Entering in executeExportOperation()");
		String msg = "All data exported successfully.";    	
    	if(!"DB".equals(extractToOptionValue)){	
        	fc = new JFileChooser();
			fc.setDialogTitle("Open");
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = fc.showSaveDialog(WebScrapper.this);
			if (result == JFileChooser.APPROVE_OPTION){
			    File selectedFile = fc.getSelectedFile();
			    System.out.println("Selected file: " + selectedFile.getAbsolutePath()); 
            	if(selectedOptionValue.equals(ContentType.IMAGE.getType()))
            	{
            		msg = "All Images exported successfully.";
            	}            	
            	ExportRequest exportRequest = frame.wsServiceProvider.buildExportRequest(frame.url,frame.title,frame.extractResponse,ExportType.getExportType(extractToOptionValue),selectedHTMLControlList,selectedFile.getAbsolutePath(),selectedImageURLList);
            	ExportResponse exportResponse = frame.wsServiceProvider.executeExportOperation(exportRequest);            	
            	WebScrapperUtil.showWaitingDialog(frame);            	
            	if(exportResponse.isSuccess()){
            		JOptionPane.showMessageDialog(frame, msg, "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
            		return true;
        		}else{
            		msg = "Issue in data export operation, kinldy check settings.";
            		JOptionPane.showMessageDialog(frame, msg, "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
            		return false;
            	}           	
			}
			else
			{
				return false;
			} 
    	}else{    		
    		ExportRequest exportRequest = frame.wsServiceProvider.buildExportRequest(frame.url,frame.title,frame.extractResponse,ExportType.getExportType(extractToOptionValue),null,null,null);    		
    		ExportResponse exportResponse = frame.wsServiceProvider.executeExportOperation(exportRequest);    		
    		WebScrapperUtil.showWaitingDialog(frame);    		
    		if(exportResponse.isSuccess()){	
    			JOptionPane.showMessageDialog(frame, msg, "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
    			return true;
    		}else{
    			msg = "Database connection is not available, kindly check MongoDB connection on your machine and then choose export to DB option.";
    			JOptionPane.showMessageDialog(frame, msg, "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
    			return false;
    		}
    	}	
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
	 * @param frame
	 */
	public void setFrame(WebScrapper frame)
	{
		this.frame = frame;
	}
	
	/**
	 * 
	 * @return
	 */
	public WebScrapper getFrame(){
		return this.frame;
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
