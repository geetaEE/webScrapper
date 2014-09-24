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
	
	private static WebScrapper frame = null;
	private static WSUIControlsManager webScrapperUIControls;
	
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
	public static WSUIControlsManager getWebScrapperUIControls() {
		return webScrapperUIControls;
	}

	/**
	 * 
	 * @param webScrapperUIControls
	 */
	public static void setWebScrapperUIControls(
			WSUIControlsManager webScrapperUIControls) {
		WebScrapper.webScrapperUIControls = webScrapperUIControls;
	}

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
													frame = new WebScrapper();frame.setVisible(true);frame.setLocationRelativeTo( null );
													webScrapperUIControls = new WSUIControlsManager(frame);
													webScrapperUIControls.resetAllExtractProcessPanel();				
												}catch (Exception e) 
												{
													logger.warn(e);													
													System.exit(1);
												}}});
		logger.info("Exit from main()");
	}


	
	
	/**
	 * Method for start the extract operation.
	 */
	public void executeExtractOpertion(){		
		logger.info("Entering in executeExtractOpertion()");
		String url = webScrapperUIControls.urlTextField.getText().trim();
		String keyword = webScrapperUIControls.titleTextField.getText().trim();		
		if((null == url) || UIConstants.BLANK.equals(url) || (null == keyword) || UIConstants.BLANK.equals(keyword)){
			JOptionPane.showMessageDialog(frame, "URL and title is required.", UIConstants.WEB_SCRAPPER, JOptionPane.ERROR_MESSAGE);
			return;
		}else{
			if(url.startsWith("http:") || url.startsWith("https:")){
				boolean isValidURL = URLUtil.isValidURL(url);
				if(!isValidURL){
					JOptionPane.showMessageDialog(frame, "URL is invalid.", UIConstants.WEB_SCRAPPER, JOptionPane.ERROR_MESSAGE);
					return;
				}						
			}else{
				url = "http://"+url;
				if(!URLUtil.isValidURL("http://"+url)){
					url = "https://"+url; 
					if(!URLUtil.isValidURL(url)){	
						JOptionPane.showMessageDialog(frame, "URL is invalid.", UIConstants.WEB_SCRAPPER, JOptionPane.ERROR_MESSAGE);
						return;
					}						
				}					
			}
			
			boolean isValid = URLUtil.isValidURLForConnection(url);
			if(!isValid){
				JOptionPane.showMessageDialog(frame, "URL is invalid.", UIConstants.WEB_SCRAPPER, JOptionPane.ERROR_MESSAGE);
				return;
			}
		}		
		String slectedValue = webScrapperUIControls.extractDataTypeComboBox.getSelectedItem().toString();
		webScrapperUIControls.url = url;
		webScrapperUIControls.title = keyword;
		webScrapperUIControls.contentType = ContentType.getContentType(slectedValue);		
		webScrapperUIControls.wsServiceProvider = new WSServiceProvider();
		webScrapperUIControls.extractRequest = webScrapperUIControls.wsServiceProvider.buildExtractRequest(webScrapperUIControls.url, webScrapperUIControls.contentType);
		webScrapperUIControls.extractResponse = webScrapperUIControls.wsServiceProvider.executeExtractOperation(webScrapperUIControls.extractRequest);		
		if(null == webScrapperUIControls.extractResponse){
			JOptionPane.showMessageDialog(frame, "Selected Option Data is not available on the web page.", UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
			return;
		}		
		WebScrapperUtil.showWaitingDialog(frame);				
		if(slectedValue.equals(ContentType.IMAGE.getType())){				
			webScrapperUIControls.populateImageList();
			webScrapperUIControls.btnPreview.setEnabled(true);
		}
		webScrapperUIControls.expandExtractProcessPanel();
		webScrapperUIControls.disableHeaderArea();
		logger.info("Exiting from executeExtractOpertion()");
	}
	
	/**
	 * Method for preview operation.
	 * @throws IOException 
	 * @throws Exception
	 */
	public void executePreviewOperation() throws IOException{
		logger.info("Entering in executePreviewOperation()");
		String slectedOptionValue = webScrapperUIControls.extractDataTypeComboBox.getSelectedItem().toString();		
		if(slectedOptionValue.equals(ContentType.IMAGE.getType())){			
			List<CheckListItem> lists = WebScrapperUtil.getSelectedListItems(webScrapperUIControls.imageList);			
			if(lists.size() >0){	
				InputStream stream = webScrapperUIControls.wsServiceProvider.fetchImagePreviewData(WebScrapperUtil.getSelectedListItems(webScrapperUIControls.imageList).get(0).toString());
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
				JOptionPane.showMessageDialog(frame, "No Image Preview available, kindly select image.", UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
    	}else{	
    		JScrollPane scrollPane = null;			
			if(webScrapperUIControls.structedRadioButton.isSelected()){
				String columnNames[] = webScrapperUIControls.wsServiceProvider.fetchColumnNameForPreview(webScrapperUIControls.extractResponse);
				String dataValues[][] = webScrapperUIControls.wsServiceProvider.fetchTabularPreviewData(webScrapperUIControls.extractResponse);				
				JTable table = new JTable( dataValues, columnNames );
				table.setTableHeader(null);
				table.setGridColor(Color.YELLOW);
		        table.setBackground(Color.CYAN);
		        table.setEnabled(false);				
		        table.setPreferredScrollableViewportSize(new Dimension(200, 200));		        
				scrollPane = new JScrollPane( table );
			}else{	
				List<String> selectedHTMLControlList = WebScrapperUtil.getSelectedListItemValues(webScrapperUIControls.htmlControlList);						
				if(selectedHTMLControlList.size() >0)				{	
					String content = webScrapperUIControls.wsServiceProvider.fetchNonTabularPreviewData(webScrapperUIControls.extractResponse, selectedHTMLControlList);					
					JTextArea textArea = new JTextArea(10, 25);
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
		String selectedOptionValue = webScrapperUIControls.extractDataTypeComboBox.getSelectedItem().toString();
		String extractToOptionValue = "";		
		if(null != webScrapperUIControls.extractTocomboBox && null != webScrapperUIControls.extractTocomboBox.getSelectedItem()){	
			extractToOptionValue = webScrapperUIControls.extractTocomboBox.getSelectedItem().toString();				
		}		
		JLabel queryLabel = new JLabel(" Result Query : ");		
		JTextField queryTextField = new JTextField();
		Font font = new Font("Verdana", Font.BOLD, 12);				
		queryTextField.setEditable(false);
		queryTextField.setFont(font);		
		String url = webScrapperUIControls.urlTextField.getText();
		String title = webScrapperUIControls.titleTextField.getText();
		String selectedTabularOption = "";		
		if(webScrapperUIControls.structedRadioButton.isSelected()){ 
			selectedTabularOption = "Tabular";
		}else{
			selectedTabularOption = "Non-Tabular";		
		}
		if(selectedOptionValue.equals(ContentType.IMAGE.getType())){
			queryTextField.setText(url + "," +title+","+selectedOptionValue+","+ WebScrapperUtil.getSelectedListItems(webScrapperUIControls.imageList).toString());
    	}else{
			if(webScrapperUIControls.structedRadioButton.isSelected()) {
				queryTextField.setText(url + "," +title+","+selectedOptionValue+","+selectedTabularOption+","+extractToOptionValue);						
			}else{
				queryTextField.setText(url + "," +title+","+selectedOptionValue+","+selectedTabularOption+","+WebScrapperUtil.getSelectedListItems(webScrapperUIControls.htmlControlList).toString()+","+extractToOptionValue);			
			}
		}		
		queryTextField.setColumns(14);		
		JComboBox comboBox = new JComboBox();				
		comboBox.setModel(new DefaultComboBoxModel(new String[]{"Export","Save Query For Batch"}));		
		JPanel message = new JPanel();
        message.add(queryLabel);              
        message.add(queryTextField);
        message.add(comboBox);
        Object[] options = new String[]{"Process","Cancel"};
        int returnvalue = JOptionPane.showOptionDialog(frame, message, UIConstants.WEB_SCRAPPER, JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String optionValue = comboBox.getSelectedItem().toString();
        if(returnvalue == 0){	                
        	if("Export".equals(optionValue)){               	            	
        		List<String> selectedImageURLList = new ArrayList<String>();
        		List<String> selectedHTMLControlList = new ArrayList<String>();        		
        		if(selectedOptionValue.equals(ContentType.IMAGE.getType())){ 
        			selectedImageURLList = WebScrapperUtil.getSelectedListItemValues(webScrapperUIControls.imageList);
        		}else{
        			selectedHTMLControlList = WebScrapperUtil.getSelectedListItemValues(webScrapperUIControls.htmlControlList);        		
        		}
        		boolean result = executeExportOperation(extractToOptionValue, selectedOptionValue, selectedImageURLList, selectedHTMLControlList);
        		if(!result){
        			return;        			
        		}
            }else{
            	webScrapperUIControls.fc = new JFileChooser();
            	webScrapperUIControls.fc.setDialogTitle("Save");
				int result = webScrapperUIControls.fc.showSaveDialog(WebScrapper.this);
				if (result == JFileChooser.APPROVE_OPTION){
				    File selectedFile = webScrapperUIControls.fc.getSelectedFile();				    				    
				    JOptionPane.showMessageDialog(frame, "Query Saved Successfuly for batch processing. For batch processing you need to select batch process menu.", UIConstants.WEB_SCRAPPER, JOptionPane.INFORMATION_MESSAGE);
				}else{
					return;				
				}
            }
        	webScrapperUIControls.resetAllExtractProcessPanel();
        }
        logger.info("Exiting from executeRunQueryOperation()");
	}


	/**
	 * Method for export operation.
	 * @param extractToOptionValue
	 */
	private boolean executeExportOperation(String extractToOptionValue, String selectedOptionValue, List<String> selectedImageURLList, List<String> selectedHTMLControlList){
		logger.info("Entering in executeExportOperation()");
		String msg = "All data exported successfully.";    	
    	if(!"DB".equals(extractToOptionValue)){	
    		webScrapperUIControls.fc = new JFileChooser();
    		webScrapperUIControls.fc.setDialogTitle("Open");
    		webScrapperUIControls.fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = webScrapperUIControls.fc.showSaveDialog(WebScrapper.this);
			if (result == JFileChooser.APPROVE_OPTION){
			    File selectedFile = webScrapperUIControls.fc.getSelectedFile();			     
            	if(selectedOptionValue.equals(ContentType.IMAGE.getType()))
            	{
            		msg = "All Images exported successfully.";
            	}            	
            	ExportRequest exportRequest = webScrapperUIControls.wsServiceProvider.buildExportRequest(webScrapperUIControls.url,webScrapperUIControls.title,webScrapperUIControls.extractResponse,ExportType.getExportType(extractToOptionValue),selectedHTMLControlList,selectedFile.getAbsolutePath(),selectedImageURLList);
            	ExportResponse exportResponse = webScrapperUIControls.wsServiceProvider.executeExportOperation(exportRequest);            	
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
    		ExportRequest exportRequest = webScrapperUIControls.wsServiceProvider.buildExportRequest(webScrapperUIControls.url,webScrapperUIControls.title,webScrapperUIControls.extractResponse,ExportType.getExportType(extractToOptionValue),null,null,null);    		
    		ExportResponse exportResponse = webScrapperUIControls.wsServiceProvider.executeExportOperation(exportRequest);    		
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
