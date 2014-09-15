package com.webscapper.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
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
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import com.webscapper.request.ExportRequest;
import com.webscapper.request.ExtractRequest;
import com.webscapper.response.ExportResponse;
import com.webscapper.response.ExtractResponse;
import com.webscrapper.constants.ContentType;
import com.webscrapper.constants.ExportType;
import com.webscrapper.constants.UIConstants;


public class WebScrapper extends JFrame 
{

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
	//JPanel htmlControlPanel;
	JCheckBox chckbxDiv;
	JCheckBox chckbxSpan;
	JCheckBox chckbxParagraph;
	//JCheckBox chckbxTable;
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
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    
		EventQueue.invokeLater(new Runnable() 
										{
											public void run() 
											{
												try 
												{
													frame = new WebScrapper();
													frame.setVisible(true);
													frame.setLocationRelativeTo( null );
													frame.resetAllExtractProcessPanel();													
																								
													String imgStr = "images/index.jpg";
													
													ClassLoader cl = getClass().getClassLoader();
													InputStream stream = cl.getResourceAsStream(imgStr);
													if( stream == null ) System.err.println( "resource not found" );
													BufferedImage image = ImageIO.read( stream );
													ImageIcon icon = new ImageIcon( image );												
													
													frame.setIconImage(icon.getImage());
												} 
												catch (Exception e) 
												{
													e.printStackTrace();
												}
											}
										}
								);		
	}

	/**
	 * Create the frame.
	 * @throws InterruptedException 
	 */
	public WebScrapper() throws Exception 
	{
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
	}
	
	public void createMenus()
	{
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 651, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Process   |");
		menuBar.add(mnNewMenu);
		
		mntmExtractProcess = new JMenuItem("Extract Process");
		mntmExtractProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)			
			{
				batchProcessPanel.setVisible(false);
				extractProcessPanel.setVisible(true);
				resetAllExtractProcessPanel();
			}
		});
		mnNewMenu.add(mntmExtractProcess);
		
		mntmBatchProcess = new JMenuItem("Batch Process");
		mntmBatchProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{			
				batchProcessPanel.setVisible(true);
				extractProcessPanel.setVisible(false);	
				resetBatchProcessPanel();
			}
		});
		mnNewMenu.add(mntmBatchProcess);
	}
	
	public void createExtrctProcessPanel() throws Exception
	{
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
			public void actionPerformed(ActionEvent event) 
			{				
				extractTocomboBox.setModel(new DefaultComboBoxModel(new String[]{}));
				populateHtmlControlList();
				btnRunQuery.setEnabled(false);
				btnPreview.setEnabled(true);
				htmlControlScrollPanel.setVisible(true);
			}
		});		
		
		unStructedRadioButton.setBounds(6, 5, 129, 23);	
		unStructedRadioButton.setEnabled(false);
		
		structedRadioButton = new JRadioButton("Tabular");
		structedRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				extractTocomboBox.setModel(new DefaultComboBoxModel(StructuredExtractDocType.values()));
				htmlControlList.setListData(new CheckListItem[] {});
				btnRunQuery.setEnabled(true);
				btnPreview.setEnabled(true);
				htmlControlScrollPanel.setVisible(false);
			}
		});		
		structedRadioButton.setBounds(6, 31, 92, 23);	
		structedRadioButton.setEnabled(false);
		dataTypeRadioButtonGroup.add(unStructedRadioButton);
		dataTypeRadioButtonGroup.add(structedRadioButton);
		
		extractDataTypeSelectionpanel.add(unStructedRadioButton);
		extractDataTypeSelectionpanel.add(structedRadioButton);
		
		
		
		imageList = new JList(new CheckListItem[]{});
		
		imageList.setCellRenderer(new CheckListRenderer());
		
		imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		imageList.addMouseListener(new MouseAdapter()
	      {
	         public void mouseClicked(MouseEvent event)
	         {
	            JList list = (JList) event.getSource();
	            
	            // Get index of item clicked
	            
	            int index = list.locationToIndex(event.getPoint());
	            CheckListItem item = (CheckListItem)
	               list.getModel().getElementAt(index);
	            
	            // Toggle selected state
	            
	            item.setSelected(! item.isSelected());
	            
	            // Repaint cell
	            
	            list.repaint(list.getCellBounds(index, index));
	            
	            List<CheckListItem> selectedList = getSelectedListItems(imageList);
	            
	            if(selectedList.size() <= 0)
	            {
	            	btnRunQuery.setEnabled(false);
	            }
	            else
	            {
	            	btnRunQuery.setEnabled(true);
	            }
	         }
	      });
		
		htmlControlList = new JList(new CheckListItem[]{});
		
		htmlControlList.setCellRenderer(new CheckListRenderer());
		
		htmlControlList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		htmlControlList.addMouseListener(new MouseAdapter()
	      {
	         public void mouseClicked(MouseEvent event)
	         {
	            JList list = (JList) event.getSource();
	            
	            // Get index of item clicked
	            
	            int index = list.locationToIndex(event.getPoint());
	            CheckListItem item = (CheckListItem)
	               list.getModel().getElementAt(index);
	            
	            // Toggle selected state
	            
	            item.setSelected(! item.isSelected());
	            
	            // Repaint cell
	            
	            list.repaint(list.getCellBounds(index, index));
	            
	            List<CheckListItem> selectedList = getSelectedListItems(htmlControlList);
	            
	            if(selectedList.size() <= 0)
	            {
	            	extractTocomboBox.setModel(new DefaultComboBoxModel(new String[]{}));
	            	btnRunQuery.setEnabled(false);
	            }
	            else
	            {
	            	extractTocomboBox.setModel(new DefaultComboBoxModel(UnStructuredExtractDocType.values()));
	            	btnRunQuery.setEnabled(true);	            	
	            }
	         }
	      });
		
		scrollPane = new JScrollPane(imageList);
		//scrollPane.setBounds(20, 11, 150, 124);
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
			public void actionPerformed(ActionEvent e) 
			{			
				String selectedOptionValue = extractDataTypeComboBox.getSelectedItem().toString();
				String extractToOptionValue = "";
				
				if(null != extractTocomboBox && null != extractTocomboBox.getSelectedItem())
				{	
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
				
				if(structedRadioButton.isSelected())
				{
					selectedTabularOption = "Tabular";
				}
				else
				{
					selectedTabularOption = "Non-Tabular";
				}
				
				if(selectedOptionValue.equals(ExtractDataType.IMAGE.getDescription()))
            	{
					queryTextField.setText(url + "," +title+","+selectedOptionValue+","+ getSelectedListItems(imageList).toString());
            	}
				else
				{
					if(structedRadioButton.isSelected())
					{	
						queryTextField.setText(url + "," +title+","+selectedOptionValue+","+selectedTabularOption+","+extractToOptionValue);						
					}
					else
					{
						queryTextField.setText(url + "," +title+","+selectedOptionValue+","+selectedTabularOption+","+getSelectedListItems(htmlControlList).toString()+","+extractToOptionValue);
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
                int returnvalue = JOptionPane.showOptionDialog(frame, message, "Web Scrapper", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, 
                        null, options, options[0]);  
                
                
                String optionValue = comboBox.getSelectedItem().toString();                
                
                if(returnvalue == 0)
                {	
	                if("Export".equals(optionValue))
	                {               	
	                	String msg = "All data exported successfully.";
	                	
	                	if(!"DB".equals(extractToOptionValue))
	                	{	
		                	fc = new JFileChooser();
							fc.setDialogTitle("Open");
							fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							int result = fc.showSaveDialog(WebScrapper.this);
							if (result == JFileChooser.APPROVE_OPTION) 
							{
							    File selectedFile = fc.getSelectedFile();
							    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
							    
							    
			                	
			                	if(selectedOptionValue.equals(ExtractDataType.IMAGE.getDescription()))
			                	{
			                		msg = "All Images exported successfully.";
			                	}
			                	
			                	ExportRequest exportRequest = frame.wsServiceProvider.buildExportRequest(frame.url, 
																											frame.title, 
																											frame.extractResponse, 
																											ExportType.getExportType(extractToOptionValue), 
																											null, 
																											selectedFile.getAbsolutePath());

			                	ExportResponse exportResponse = frame.wsServiceProvider.executeExportOperation(exportRequest);
			                	
			                	if(exportResponse.isSuccess())
		                		{
			                		JOptionPane.showMessageDialog(frame, msg, "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
		                		}
			                	else
			                	{
			                		msg = "Issue in data export operation, kinldy check settings.";
			                		JOptionPane.showMessageDialog(frame, msg, "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
			                		return;
			                	}
			                	
							}
							else
							{
								return;
							} 
	                	}
	                	else
	                	{
	                		//VivekYadav
	                		ExportRequest exportRequest = frame.wsServiceProvider.buildExportRequest(frame.url, 
	                																						frame.title, 
	                																						frame.extractResponse, 
	                																						ExportType.getExportType(extractToOptionValue), 
	                																						null, 
	                																						null);
	                		
	                		ExportResponse exportResponse = frame.wsServiceProvider.executeExportOperation(exportRequest);
	                		
	                		if(exportResponse.isSuccess())
	                		{	
	                			JOptionPane.showMessageDialog(frame, msg, "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
	                		}
	                		else
	                		{
	                			msg = "Database connection is not available, kindly check MongoDB connection on your machine and then choose export to DB option.";
	                			JOptionPane.showMessageDialog(frame, msg, "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
	                			return;
	                		}
	                	}
	                }
	                else
	                {
	                	fc = new JFileChooser();
						fc.setDialogTitle("Save");
						int result = fc.showSaveDialog(WebScrapper.this);
						if (result == JFileChooser.APPROVE_OPTION) 
						{
						    File selectedFile = fc.getSelectedFile();
						    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
						    
						    JOptionPane.showMessageDialog(frame, "Query Saved Successfuly for batch processing. For batch processing you need to select batch process menu.", "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							return;
						}
	                }
	                resetAllExtractProcessPanel();
                }    
			}
		});
		btnRunQuery.setEnabled(false);
		
		
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String slectedOptionValue = extractDataTypeComboBox.getSelectedItem().toString();
				
				if(slectedOptionValue.equals(ExtractDataType.IMAGE.getDescription()))
            	{
					String imgStr = "images/avatar-wallpaper.jpg";
					
					ClassLoader cl = getClass().getClassLoader();
					InputStream stream = cl.getResourceAsStream(imgStr);
					if( stream == null ) System.err.println( "resource not found" );
					BufferedImage bufferedImage = null;
					
					try 
					{
						bufferedImage = ImageIO.read( stream );
					} 
					catch (IOException e1) 
					{						
						e1.printStackTrace();
					}
					
					ImageIcon image = new ImageIcon( bufferedImage );					
					
					Image scaleImage = image.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
					
					image = new ImageIcon(scaleImage);
					
					JLabel lbl = new JLabel(image);
					
					JOptionPane.showMessageDialog(frame, lbl,"Image Preview", -1);					
            	}
				else
				{
					JScrollPane scrollPane = null;
					
					if(structedRadioButton.isSelected())
					{
						String columnNames[] = frame.wsServiceProvider.fetchColumnNameForPreview(frame.extractResponse);
						String dataValues[][] = frame.wsServiceProvider.fetchTabularPreviewData(frame.extractResponse);
						
						
						JTable table = new JTable( dataValues, columnNames );
						table.setTableHeader(null);
						table.setGridColor(Color.YELLOW);
				        table.setBackground(Color.CYAN);
				        table.setEnabled(false);
						
				        table.setPreferredScrollableViewportSize(new Dimension(200, 200));
				        
						scrollPane = new JScrollPane( table );
					}
					else
					{	
						String content = "Swing is the primary Java GUI widget toolkit. It is part of Oracle's Java Foundation Classes (JFC) � an API for providing a graphical user interface (GUI) for Java programs. " +
	
										" Swing was developed to provide a more sophisticated set of GUI components than the earlier Abstract Window Toolkit (AWT). Swing provides a native look and feel that emulates the look and feel of several platforms, and also supports a pluggable look and feel that allows applications to have a look and feel unrelated to the underlying platform. It has more powerful and flexible components than AWT. In addition to familiar components such as buttons, check boxes and labels, Swing provides several advanced components such as tabbed panel, scroll panes, trees, tables, and lists. "+
	
										" Unlike AWT components, Swing components are not implemented by platform-specific code. Instead they are written entirely in Java and therefore are platform-independent. The term lightweight is used to describe such an element.[1]";
						
						JTextArea textArea = new JTextArea(10, 25);
						textArea.setLineWrap(true);
					    textArea.setText(content);
					    textArea.setEditable(false);
					    
					    scrollPane = new JScrollPane(textArea);
					}							
					
					JOptionPane.showMessageDialog(frame, scrollPane,"Data Preview", -1);
				}
			}
		});
		
		btnPreview.setEnabled(false);
		
		previewRunQueryPanel = new JPanel();
		previewRunQueryPanel.setBounds(10, 405, 609, 54);
		extractProcessPanel.add(previewRunQueryPanel);
		previewRunQueryPanel.setLayout(null);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
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
			public void itemStateChanged(ItemEvent arg0) 
			{
				resetExtractProcessPanel();
			}
		});
		extractDataTypeComboBox.setModel(new DefaultComboBoxModel(ExtractDataType.values()));
		
		extractButton = new JButton("Extract");
		extractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//1 . Validation
				String url = urlTextField.getText().trim();
				String keyword = titleTextField.getText().trim();				
				
				if((null == url) || UIConstants.BLANK.equals(url) || (null == keyword) || UIConstants.BLANK.equals(keyword))						
				{
					JOptionPane.showMessageDialog(frame, "URL and title is required.", "Web Scrapper", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					if(url.startsWith("http:") || url.startsWith("https:"))
					{
						boolean isValidURL = isValidURL(url);
						if(!isValidURL)
						{
							JOptionPane.showMessageDialog(frame, "URL is invalid.", "Web Scrapper", JOptionPane.ERROR_MESSAGE);
							return;
						}						
					}
					else
					{
						url = "http://"+url;
						if(!isValidURL("http://"+url))
						{
							url = "https://"+url; 
							if(!isValidURL(url))
							{	
								JOptionPane.showMessageDialog(frame, "URL is invalid.", "Web Scrapper", JOptionPane.ERROR_MESSAGE);
								return;
							}						
						}					
					}
					
					boolean isValid = isValidURLForConnection(url);
					if(!isValid)
					{
						JOptionPane.showMessageDialog(frame, "URL is invalid.", "Web Scrapper", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				String slectedValue = extractDataTypeComboBox.getSelectedItem().toString();
				frame.url = url;
				frame.title = keyword;
				frame.contentType = ContentType.getContentType(slectedValue);
				
				//Construct ExtractRequest Object- VivekYadav
				frame.wsServiceProvider = new WSServiceProvider();
				frame.extractRequest = frame.wsServiceProvider.buildExtractRequest(frame.url, frame.contentType);
				frame.extractResponse = frame.wsServiceProvider.executeExtractOperation(frame.extractRequest);
				
				//3. show waiting dialog
				showWaitingDialog();
				
				
				//2. Populate Check Box List				
				if(slectedValue.equals(ExtractDataType.IMAGE.getDescription()))
				{				
					populateImageList();
					btnPreview.setEnabled(true);
				}						
						
				//3. Expand Panel Based On selection
				expandExtractProcessPanel();	
				
				//4. Disable the text box.
				disableHeaderArea();				
			}
		});
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
				exit();
			}
		});
		
		scrollPane.setVisible(false);	
	}
	
	public void showWaitingDialog()
	{
		DialogDisplay dlgDisplay = new DialogDisplay();		       
		
		JDialog dialog = new JDialog(frame, "Progress Dialog", ModalityType.APPLICATION_MODAL);
		
		JRootPane rootPane = ((JDialog) dialog).getRootPane();
		rootPane.setWindowDecorationStyle(JRootPane.NONE);
		
		dialog.getContentPane().add(dlgDisplay.getComponent());
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.dispose();
		dialog.setUndecorated(false);
	}
	
	private static boolean isValidURL(String url) 
	{
        URL u = null;
        try
        {
            u = new URL(url);
        } 
        catch (MalformedURLException e) 
        {        	
        	return false;
        }
        
        try 
        {
            u.toURI();
        } 
        catch (URISyntaxException e) 
        {        	
        	return false;
        }       
        return true;
    }
	
	private static boolean isValidURLForConnection(String url)
	{
		try 
		{		   
			URL webURL = new URL(url);
			URLConnection conn = webURL.openConnection();
		    conn.connect();
		}		
		catch (IOException ex) 
		{			
			return false;			 
		}
		return true;		
	}
	
	public void createBatchProcessPanel()
	{	
		batchProcessPanel = new JPanel();
		batchProcessPanel.setBounds(10, 542, 629, 211);
		contentPane.add(batchProcessPanel);
		batchProcessPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		batchProcessPanel.setLayout(null);
		
		button = new JButton("Reset");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				disableBatchProessPanelControls();
			}
		});
		button.setBounds(427, 183, 78, 28);
		batchProcessPanel.add(button);
		
		button_1 = new JButton("Exit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				exit();
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
		
		pathtextField.addKeyListener(new KeyAdapter() 
		{				
			@Override
			public void keyReleased(KeyEvent e) 
			{
				String value = pathtextField.getText();
				if(!"".equals(value.trim()))
				{	
					btnProcess.setEnabled(true);
				}
				else
				{
					btnProcess.setEnabled(false);
				}	
			}
		});
		
		pathtextField.setBounds(69, 19, 408, 28);
		batchProcessBrowsePanel.add(pathtextField);
		pathtextField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse..");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				fc = new JFileChooser();				
				int result = fc.showOpenDialog(WebScrapper.this);
				if (result == JFileChooser.APPROVE_OPTION) 
				{
				    File selectedFile = fc.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    pathtextField.setText(selectedFile.getAbsolutePath());
				    enableBatchProessPanelControls();
				}
			}
		});
		btnBrowse.setBounds(487, 19, 96, 28);
		batchProcessBrowsePanel.add(btnBrowse);
		
		btnProcess = new JButton("Process");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String fileName = pathtextField.getText();
				System.out.println("File Name : "+fileName);
				File f = new File(fileName);
				 
				if(f.exists())
				{
					JOptionPane.showMessageDialog(frame, "Batch Process exported data successfully.", "Web Scrapper", JOptionPane.INFORMATION_MESSAGE);
					disableBatchProessPanelControls();
				}
				else
				{
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
	
	public void disableBatchProessPanelControls()
	{
		btnProcess.setEnabled(false);	
		pathtextField.setText("");
	}
	
	public void resetBatchProcessPanel()
	{		
		batchProcessPanel.setBounds(10, 32, 629, 243);
		setBounds(10, 32, 657, 320);
		disableBatchProessPanelControls();
		frame.setLocationRelativeTo( null );
	}
	
	public void enableBatchProessPanelControls()
	{
		btnProcess.setEnabled(true);
		pathtextField.setEnabled(true);
	}
	
	public void resetAllExtractProcessPanel()
	{
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
	
	public void resetExtractProcessPanel()
	{		
		queryRunnerControlBoxPanel.setVisible(false);
		previewRunQueryPanel.setBounds(10, 180, 609, 54);
		extractProcessPanel.setBounds(10, 32, 629, 243);
		setBounds(10, 32, 657, 320);
		frame.setLocationRelativeTo( null );
		btnPreview.setEnabled(false);		
		dataTypeRadioButtonGroup.clearSelection();
	}
	
	public void expandExtractProcessPanel()
	{	
		setBounds(100, 100, 652, 541);
		queryRunnerControlBoxPanel.setVisible(true);
		previewRunQueryPanel.setVisible(true);
		previewRunQueryPanel.setBounds(10, 405, 609, 54);
		extractProcessPanel.setBounds(10, 32, 629, 470);
		frame.setLocationRelativeTo( null );
		
		String slectedValue = extractDataTypeComboBox.getSelectedItem().toString();
		if(slectedValue.equals(ExtractDataType.IMAGE.getDescription()))
		{					
			extractDataTypeSelectionpanel.setVisible(false);
			scrollPane.setVisible(true);						
			extractTocomboBox.setVisible(false);
			lblExtractTo.setVisible(false);
			htmlControlScrollPanel.setVisible(false);			
			btnRunQuery.setEnabled(false);
		}
		else
		{				
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
	
	public void populateImageList()
	{
		imageList.setListData(new CheckListItem[] {
	            new CheckListItem("aImg.jpg"), 
	            new CheckListItem("bImg.jpg"), 
	            new CheckListItem("cImg.jpg"), 
	            new CheckListItem("dImg.jpg"), 
	            new CheckListItem("eImg.jpg"),
	            new CheckListItem("fImg.jpg"),
	            new CheckListItem("gImg.jpg"),
	            new CheckListItem("hImg.jpg"),
	            new CheckListItem("iImg.jpg"),
	            new CheckListItem("jImg.jpg")
				});
	}
	
	public void populateHtmlControlList()
	{
		htmlControlList.setListData(new CheckListItem[] {
	            new CheckListItem("Div"), 
	            new CheckListItem("Paragraph"), 
	            new CheckListItem("Span"), 
	            new CheckListItem("Bold Text"), 
	            new CheckListItem("Hyperlink")
				});
	}
	
	public void resetAllExtractProcessPanelControlsValue()
	{
			
	}
	
	public void resetHeaderValuesValue()
	{		
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
		extractDataTypeComboBox.setModel(new DefaultComboBoxModel(ExtractDataType.values()));
		extractButton.setEnabled(true);
	}
	
	public void disableHeaderArea()
	{		
		urlTextField.setEditable(false);
		titleTextField.setEditable(false);
		extractDataTypeComboBox.setEnabled(false);
		extractButton.setEnabled(false);
	}
	
	public List<CheckListItem> getSelectedListItems(JList list)
	{	
		ListModel<JList> dlm = (ListModel<JList>) list.getModel();
		
		List<CheckListItem> selectedListItems = new ArrayList<CheckListItem>();
		for (int i = 0; i < dlm.getSize(); ++i) 
		{
			CheckListItem checkListItem = (CheckListItem)list.getModel().getElementAt(i);
		      
		      if (checkListItem.isSelected()) 
		      {	    	  
		    	  selectedListItems.add(checkListItem);
		      }		   
		 }		
		
		return selectedListItems;
	}
	
	public List<String> getSelectedListValues(JList list)
	{
		ListModel<JList> dlm = (ListModel<JList>) list.getModel();
		
		List<String> selectedListItems = new ArrayList<String>();
		for (int i = 0; i < dlm.getSize(); ++i) 
		{
			CheckListItem checkListItem = (CheckListItem)list.getModel().getElementAt(i);
		      
		      if (checkListItem.isSelected()) 
		      {	    	  
		    	  selectedListItems.add(checkListItem.toString());
		      }		   
		 }		
		
		return selectedListItems;
	}
	
	public void exit()
	{
		System.exit(1);
	}
	
	public enum ExtractDataType
	{ 
		TEXT("Text"), IMAGE("Image"); 
		private String description;	
		
		ExtractDataType(String description)
		{
		    this.description = description;		    
		}
		
		public String getDescription() {
		    return description;
		}

		public void setDescription(String description) {
		    this.description = description;
		}	

		@Override
		public String toString() 
		{
		    return description;
		}		
	}
	
	public enum StructuredExtractDocType
	{ 
		DB("DB"), CSV("CSV"); 
		private String description;	
		
		StructuredExtractDocType(String description)
		{
		    this.description = description;		    
		}
		
		public String getDescription() {
		    return description;
		}

		public void setDescription(String description) {
		    this.description = description;
		}	

		@Override
		public String toString() 
		{
		    return description;
		}		
	}
	
	public enum UnStructuredExtractDocType
	{ 
		DOC("Doc"), TEXT("Text"); 
		private String description;	
		
		UnStructuredExtractDocType(String description)
		{
		    this.description = description;		    
		}
		
		public String getDescription() {
		    return description;
		}

		public void setDescription(String description) {
		    this.description = description;
		}	

		@Override
		public String toString() 
		{
		    return description;
		}		
	}
}

class CheckListItem
{
   private String  label;
   private boolean isSelected = false;

   public CheckListItem(String label)
   {
      this.label = label;
   }

   public boolean isSelected()
   {
      return isSelected;
   }

   public void setSelected(boolean isSelected)
   {
      this.isSelected = isSelected;
   }

   public String toString()
   {
      return label;
   }
} 


class CheckListRenderer extends JCheckBox
   implements ListCellRenderer
{
   public Component getListCellRendererComponent(
         JList list, Object value, int index, 
         boolean isSelected, boolean hasFocus)
   {
      setEnabled(list.isEnabled());
      setSelected(((CheckListItem)value).isSelected());
      setFont(list.getFont());
      setBackground(list.getBackground());
      setForeground(list.getForeground());
      setText(value.toString());
      return this;
   }
}

class DialogDisplay 
{
	  private JPanel mainPanel = new JPanel();
	  private JProgressBar progressBar = new JProgressBar(0, 100);	 
	  private JLabel statusLabel = new JLabel("Extracting....", SwingConstants.CENTER);
	   
	  public DialogDisplay() 
	  {		    
		    mainPanel.setBorder(new LineBorder(new Color(0, 0, 0)));    
		    
		    progressBar.setIndeterminate(true);	 
		    progressBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		    
		    mainPanel.add(statusLabel, BorderLayout.NORTH);
		    mainPanel.add(progressBar, BorderLayout.CENTER);
		     
		    final SwingWorker task = new SwingWorker<Void, Void>() {
		      private int value = 0;
		       
		      @Override
		      protected Void doInBackground() throws Exception {
		    for (int i = 0; i < 5; i++) {
		      setProgress(10 * i);
		      Thread.sleep(1000);
		    }
		    setProgress(100);
		    Thread.sleep(1000);
		    return null;
		      }
		       
		      @Override
		      protected void done() {
		        Window win = SwingUtilities.getWindowAncestor(mainPanel);
		        win.dispose();
		      }
		       
		    };	    
		    task.execute();
	  }
	 
	  public JComponent getComponent() {
	    return mainPanel;
	  }
}