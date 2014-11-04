package com.webscapper.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.webscapper.request.ExportRequest;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.ExportType;
import com.webscrapper.constants.UIConstants;
// TODO: Auto-generated Javadoc

/**
 * The Class WebScrapper.
 */
public class WebScrapper extends JFrame {
	
    /** Generated serialVersionUID. */
    private static final long serialVersionUID = 2993601246664970663L;

    /** The logger. */
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
        	logger.warn(e);
        } catch (NoSuchAlgorithmException e) {
        	logger.warn(e);
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
	
	/** The frame. */
	private static WebScrapper frame = null;
	
	/** The extract request. */
	private ExtractRequest extractRequest;
	
	/** The extract response. */
	private ExtractResponse extractResponse = null;
	
	/** The ws service provider. */
	private static  WSServiceProvider wsServiceProvider;
	
	/** The web scrapper ui controls. */
	private static WSUIControlsManager wsUIControlsManager;
	
	/**
	 * Sets the frame.
	 *
	 * @param frame the new frame
	 */
	public void setFrame(WebScrapper frame)
	{
		this.frame = frame;
	}
	
	/**
	 * Gets the frame.
	 *
	 * @return the frame
	 */
	public WebScrapper getFrame(){
		return this.frame;
	}
	
	/**
	 * Gets the web scrapper ui controls.
	 *
	 * @return the web scrapper ui controls
	 */
	public static WSUIControlsManager getWebScrapperUIControls() {
		return wsUIControlsManager;
	}

	/**
	 * Sets the web scrapper ui controls.
	 *
	 * @param wsUIControlsManager the new web scrapper ui controls
	 */
	public static void setWebScrapperUIControls(
			WSUIControlsManager wsUIControlsManager) {
		WebScrapper.wsUIControlsManager = wsUIControlsManager;
	}
	
	

	/**
	 * Gets the extract request.
	 *
	 * @return the extract request
	 */
	public ExtractRequest getExtractRequest() {
		return extractRequest;
	}

	/**
	 * Sets the extract request.
	 *
	 * @param extractRequest the new extract request
	 */
	public void setExtractRequest(ExtractRequest extractRequest) {
		this.extractRequest = extractRequest;
	}

	/**
	 * Gets the extract response.
	 *
	 * @return the extract response
	 */
	public ExtractResponse getExtractResponse() {
		return extractResponse;
	}

	/**
	 * Sets the extract response.
	 *
	 * @param extractResponse the new extract response
	 */
	public void setExtractResponse(ExtractResponse extractResponse) {
		this.extractResponse = extractResponse;
	}

	/**
	 * Gets the ws service provider.
	 *
	 * @return the ws service provider
	 */
	public static WSServiceProvider getWsServiceProvider() {
		return wsServiceProvider;
	}

	/**
	 * Sets the ws service provider.
	 *
	 * @param wsServiceProvider the new ws service provider
	 */
	public static void setWsServiceProvider(WSServiceProvider wsServiceProvider) {
		WebScrapper.wsServiceProvider = wsServiceProvider;
	}

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		logger.info("Entring in main()");
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    
		EventQueue.invokeLater(new Runnable(){public void run(){
												try {
													frame = new WebScrapper();frame.setVisible(true);frame.setLocationRelativeTo( null );
													wsUIControlsManager = new WSUIControlsManager(frame);
													populateDetailArea();			
																
												}catch (Exception e) 
												{
													logger.warn(e);													
													System.exit(1);
												}}});
		logger.info("Exit from main()");
	}

	/**
	 * Populate detail area.
	 */
	public static void populateDetailArea()
	{
		wsUIControlsManager.createMenus();
		wsUIControlsManager.createExtrctProcessPanel();		
		wsUIControlsManager.createBatchProcessPanel();
		wsUIControlsManager.resetAllExtractProcessPanel();	
	}
	
	
	/**
	 * Method for start the extract operation.
	 */
	public void executeExtractOpertion(){		
		logger.info("Entering in executeExtractOpertion()");
		WSUIControls wsUIControls = wsUIControlsManager.getWsUIControls();
		String url = wsUIControls.getUrlTextField().getText().trim();
		String keyword = wsUIControls.getTitleTextField().getText().trim();		
		if((null == url) || UIConstants.BLANK.equals(url) || (null == keyword) || UIConstants.BLANK.equals(keyword)){
			JOptionPane.showMessageDialog(frame, "URL and title is required.", UIConstants.WEB_SCRAPPER, JOptionPane.ERROR_MESSAGE);
			return;
		}
		else
		{
			boolean isValid = validateUrl(url);
			if(!isValid)
			{
				JOptionPane.showMessageDialog(frame, "URL is invalid.", UIConstants.WEB_SCRAPPER, JOptionPane.ERROR_MESSAGE);
				return;
			}			
		}		
		String slectedValue = wsUIControls.getExtractDataTypeComboBox().getSelectedItem().toString();
		wsUIControls.setUrl(url);
		wsUIControls.setTitle(keyword);
		wsUIControls.setContentType(ContentType.getContentType(slectedValue));
			
		wsServiceProvider = new WSServiceProvider();
		extractRequest = wsServiceProvider.buildExtractRequest(wsUIControls.getUrl(), wsUIControls.getContentType());
		extractResponse = wsServiceProvider.executeExtractOperation(extractRequest);		
		if(null == extractResponse){
			JOptionPane.showMessageDialog(frame, "Selected Option Data is not available on the web page.", UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
			return;
		}		
		WebScrapperUtil.showWaitingDialog(frame);				
		if(slectedValue.equals(ContentType.IMAGE.getType())){				
			wsUIControlsManager.populateImageList();
			wsUIControls.getBtnPreview().setEnabled(true);
		}
		wsUIControlsManager.expandExtractProcessPanel();
		wsUIControlsManager.disableHeaderArea();
		logger.info("Exiting from executeExtractOpertion()");
	}
	
	/**
	 * Validate url.
	 *
	 * @param url the url
	 * @return true, if successful
	 */
	public boolean validateUrl(String url)
	{
		boolean isValidURL = false;
		String completeUrl = "";
		if(url.startsWith("http:") || url.startsWith("https:"))
		{
			isValidURL = URLUtil.isValidURL(url);
			completeUrl = completeUrl+url;
		}
		else
		{
			completeUrl = "http://"+url;
			isValidURL = URLUtil.isValidURL(completeUrl);
			if(!isValidURL)
			{
				completeUrl = "https://"+url;
				isValidURL = URLUtil.isValidURL(completeUrl);			
			}													
		}
		
		if(isValidURL)
		{	
			isValidURL = URLUtil.isValidURLForConnection(completeUrl);		
		}	
		
		return isValidURL;
	}	
	
	
	/**
	 * Method for preview operation.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void executePreviewOperation() throws IOException{
		logger.info("Entering in executePreviewOperation()");
		WSUIControls wsUIControls = wsUIControlsManager.getWsUIControls();
		String slectedOptionValue = wsUIControls.getExtractDataTypeComboBox().getSelectedItem().toString();		
		if(slectedOptionValue.equals(ContentType.IMAGE.getType())){			
			List<CheckListItem> lists = WebScrapperUtil.getSelectedListItems(wsUIControls.getImageList());			
			if(lists.size() >0){	
				InputStream stream = wsServiceProvider.fetchImagePreviewData(WebScrapperUtil.getSelectedListItems(wsUIControls.getImageList()).get(0).toString());
				BufferedImage bufferedImage = null;
				try{
					bufferedImage = ImageIO.read( stream );
				}catch (IOException e1)	{}				
				ImageIcon image = new ImageIcon( bufferedImage );				
				Image scaleImage = image.getImage().getScaledInstance(UIConstants.WS_IMAGE_WIDTH, UIConstants.WS_IMAGE_HEIGHT, Image.SCALE_DEFAULT);				
				image = new ImageIcon(scaleImage);				
				JLabel lbl = new JLabel(image);				
				JOptionPane.showMessageDialog(frame, lbl,"Image Preview", -1);	
			}else{
				JOptionPane.showMessageDialog(frame, "No Image Preview available, kindly select image.", UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
    	}else{	
    		JScrollPane scrollPane = null;			
			if(wsUIControls.getStructedRadioButton().isSelected()){
				String columnNames[] = wsServiceProvider.fetchColumnNameForPreview(extractResponse);
				String dataValues[][] = wsServiceProvider.fetchTabularPreviewData(extractResponse);				
				JTable table = new JTable( dataValues, columnNames );
				table.setTableHeader(null);				
		        table.setEnabled(false);				
		        table.setPreferredScrollableViewportSize(new Dimension(UIConstants.WS_IMAGE_WIDTH, UIConstants.WS_IMAGE_HEIGHT));		        
				scrollPane = new JScrollPane( table );
			}else{	
				List<String> selectedHTMLControlList = WebScrapperUtil.getSelectedListItemValues(wsUIControls.getHtmlControlList());						
				if(selectedHTMLControlList.size() >0)				{	
					String content = wsServiceProvider.fetchNonTabularPreviewData(extractResponse, selectedHTMLControlList);					
					JTextArea textArea = new JTextArea(UIConstants.WS_TEXT_PREVIEW_ROW, UIConstants.WS_TEXT_PREVIEW_COLUMN);
					textArea.setLineWrap(true);
				    textArea.setText(content);
				    textArea.setEditable(false);				    
				    scrollPane = new JScrollPane(textArea);
				}else{
					JOptionPane.showMessageDialog(frame, "No Data Preview available, kindly select HTML Control.", UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
			JOptionPane.showMessageDialog(frame, scrollPane,"Data Preview", -1);
		}
		logger.info("Exiting from executePreviewOperation()");
	}
	
	/**
	 * Method for perform the run query operation.
	 */
	public void executeRunQueryOperation(){		
		logger.info("Entering in executeRunQueryOperation()");
		WSUIControls wsUIControls = wsUIControlsManager.getWsUIControls();
		String selectedOptionValue = wsUIControls.getExtractDataTypeComboBox().getSelectedItem().toString();
		String extractToOptionValue = "";		
		if(null != wsUIControls.getExtractTocomboBox() && null != wsUIControls.getExtractTocomboBox().getSelectedItem()){	
			extractToOptionValue = wsUIControls.getExtractTocomboBox().getSelectedItem().toString();				
		}		
		JLabel queryLabel = new JLabel(" Result Query : ");		
		JTextField queryTextField = new JTextField();
		Font font = new Font("Verdana", Font.BOLD, UIConstants.WS_FONT_SIZE);				
		queryTextField.setEditable(false);
		queryTextField.setFont(font);		
		String url = wsUIControls.getUrlTextField().getText();
		String title = wsUIControls.getTitleTextField().getText();
		String selectedTabularOption = "";		
		if(wsUIControls.getStructedRadioButton().isSelected()){ 
			selectedTabularOption = "Tabular";
		}else{
			selectedTabularOption = "Non-Tabular";		
		}
		if(selectedOptionValue.equals(ContentType.IMAGE.getType())){
			queryTextField.setText(url + "," +title+","+selectedOptionValue+","+ WebScrapperUtil.getSelectedListItems(wsUIControls.getImageList()).toString());
    	}else{
			if(wsUIControls.getStructedRadioButton().isSelected()) {
				queryTextField.setText(url + "," +title+","+selectedOptionValue+","+selectedTabularOption+","+extractToOptionValue);						
			}else{
				queryTextField.setText(url + "," +title+","+selectedOptionValue+","+selectedTabularOption+","+WebScrapperUtil.getSelectedListItems(wsUIControls.getHtmlControlList()).toString()+","+extractToOptionValue);			
			}
		}		
		queryTextField.setColumns(UIConstants.WS_COLUMN);		
		JComboBox comboBox = new JComboBox();				
		comboBox.setModel(new DefaultComboBoxModel(new String[]{"Export","Save Query For Batch"}));		
		JPanel message = new JPanel();
        message.add(queryLabel);              
        message.add(queryTextField);
        message.add(comboBox);
        Object[] options = new String[]{"Process","Cancel"};
        int returnvalue = JOptionPane.showOptionDialog(frame, message, UIConstants.WEB_SCRAPPER, JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String optionValue = comboBox.getSelectedItem().toString();       
        if(returnvalue == 0)
        {	                
        	 performExportOperation(optionValue, extractToOptionValue, selectedOptionValue);
        }
        logger.info("Exiting from executeRunQueryOperation()");
	}
	
	/**
	 * Perform export operation.
	 *
	 * @param optionValue the option value
	 * @param extractToOptionValue the extract to option value
	 * @param selectedOptionValue the selected option value
	 */
	public void performExportOperation(String optionValue, String extractToOptionValue, String selectedOptionValue)
	{
		if("Export".equals(optionValue))
    	{        		
    		boolean result = executeExportOperation(extractToOptionValue, selectedOptionValue);
    		if(!result){
    			return;        			
    		}
        }
    	else
    	{
        	JFileChooser fc = new JFileChooser();
        	fc.setDialogTitle("Save");
			int result = fc.showSaveDialog(WebScrapper.this);
			if (result == JFileChooser.APPROVE_OPTION){
			    JOptionPane.showMessageDialog(frame, "Query Saved Successfuly for batch processing. For batch processing you need to select batch process menu.", UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
			}else{
				return;				
			}
        }
    	wsUIControlsManager.resetAllExtractProcessPanel();
	}


	/**
	 * Method for export operation.
	 *
	 * @param extractToOptionValue the extract to option value
	 * @param selectedOptionValue the selected option value
	 * @return true, if successful
	 */
	private boolean executeExportOperation(String extractToOptionValue, String selectedOptionValue){
		logger.info("Entering in executeExportOperation()");
		WSUIControls wsUIControls = wsUIControlsManager.getWsUIControls();
		
		List<String> selectedImageURLList = new ArrayList<String>();
		List<String> selectedHTMLControlList = new ArrayList<String>();        		
		if(selectedOptionValue.equals(ContentType.IMAGE.getType())){ 
			selectedImageURLList = WebScrapperUtil.getSelectedListItemValues(wsUIControls.getImageList());
		}else{
			selectedHTMLControlList = WebScrapperUtil.getSelectedListItemValues(wsUIControls.getHtmlControlList());        		
		}
		
		String msg = "All data exported successfully.";    	
    	if(!"DB".equals(extractToOptionValue)){	
    		JFileChooser fc = new JFileChooser();
    		fc.setDialogTitle("Open");
    		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = fc.showSaveDialog(WebScrapper.this);
			if (result == JFileChooser.APPROVE_OPTION){
			    File selectedFile = fc.getSelectedFile();			     
            	if(selectedOptionValue.equals(ContentType.IMAGE.getType()))
            	{
            		msg = "All Images exported successfully.";           		
            	}     	
            	
            	ExportRequest exportRequest = null;
            	if(selectedOptionValue.equals(ContentType.IMAGE.getType()))
            	{	
            		exportRequest = wsServiceProvider.buildExportRequest(wsUIControls.getUrl(),wsUIControls.getTitle(),extractResponse,ExportType.IMAGE,selectedHTMLControlList,selectedFile.getAbsolutePath(),selectedImageURLList);
            	}
            	else
            	{
            		exportRequest = wsServiceProvider.buildExportRequest(wsUIControls.getUrl(),wsUIControls.getTitle(),extractResponse,ExportType.getExportType(extractToOptionValue),selectedHTMLControlList,selectedFile.getAbsolutePath(),selectedImageURLList);
            	}
            	ExportResponse exportResponse = wsServiceProvider.executeExportOperation(exportRequest);            	
            	WebScrapperUtil.showWaitingDialog(frame);            	
            	if(exportResponse.isSuccess()){
            		JOptionPane.showMessageDialog(frame, msg, UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
            		return true;
        		}else{
            		msg = "Issue in data export operation, kinldy check settings.";
            		JOptionPane.showMessageDialog(frame, msg, UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
            		return false;
            	}           	
			}
			else
			{
				return false;
			} 
    	}else{    		
    		ExportRequest exportRequest = wsServiceProvider.buildExportRequest(wsUIControls.getUrl(),wsUIControls.getTitle(),extractResponse,ExportType.getExportType(extractToOptionValue),null,null,null);    		
    		ExportResponse exportResponse = wsServiceProvider.executeExportOperation(exportRequest);    		
    		WebScrapperUtil.showWaitingDialog(frame);    		
    		if(exportResponse.isSuccess()){	
    			JOptionPane.showMessageDialog(frame, msg, UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
    			return true;
    		}else{
    			msg = "Database connection is not available, kindly check MongoDB connection on your machine and then choose export to DB option.";
    			JOptionPane.showMessageDialog(frame, msg, UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
    			return false;
    		}
    	}	
	}	
}
