package com.webscapper.ui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.webscrapper.constants.ContentType;

/** The Class WSUIControls. */
public class WSUIControls {

    /** The content pane. */
    private JPanel contentPane;

    /** The url text field. */
    private JTextField urlTextField;

    /** The title text field. */
    private JTextField titleTextField;

    /** The exit button. */
    private JButton exitButton;

    /** The extract data type combo box. */
    private JComboBox extractDataTypeComboBox;

    /** The un structed radio button. */
    private JRadioButton unStructedRadioButton;

    /** The structed radio button. */
    private JRadioButton structedRadioButton;

    /** The data type radio button group. */
    private ButtonGroup dataTypeRadioButtonGroup;

    /** The extract data type selectionpanel. */
    private JPanel extractDataTypeSelectionpanel;

    /** The scroll pane. */
    private JScrollPane scrollPane;

    /** The image list. */
    private JList imageList;

    /** The btn preview. */
    private JButton btnPreview;

    /** The preview run query panel. */
    private JPanel previewRunQueryPanel;

    /** The extract tocombo box. */
    private JComboBox extractTocomboBox;

    /** The lbl extract to. */
    private JLabel lblExtractTo;

    /** The btn reset. */
    private JButton btnReset;

    /** The mntm extract process. */
    private JMenuItem mntmExtractProcess;

    /** The mntm batch process. */
    private JMenuItem mntmBatchProcess;

    /** The batch process panel. */
    private JPanel batchProcessPanel;

    /** The button. */
    private JButton button;

    /** The button exit. */
    private JButton buttonExit;

    /** The lbl new label. */
    private JLabel lblNewLabel;

    /** The pathtext field. */
    private JTextField pathtextField;

    /** The batch process browse panel. */
    private JPanel batchProcessBrowsePanel;

    /** The extract process panel. */
    private JPanel extractProcessPanel;

    /** The btn process. */
    private JButton btnProcess;

    /** The header panel. */
    private JPanel headerPanel;

    /** The lbl extract process. */
    private JLabel lblExtractProcess;

    /** The query runner control box panel. */
    private JPanel queryRunnerControlBoxPanel;

    /** The btn run query. */
    private JButton btnRunQuery;

    /** The html control scroll panel. */
    private JScrollPane htmlControlScrollPanel;

    /** The html control list. */
    private JList htmlControlList;

    /** The extract button. */
    private JButton extractButton;

    /** The url. */
    private String url;

    /** The title. */
    private String title;

    /** The content type. */
    private ContentType contentType;

    /** Gets the content pane.
     * 
     * @return the content pane */
    public JPanel getContentPane() {
        return contentPane;
    }

    /** Sets the content pane.
     * 
     * @param contentPane
     *            the new content pane */
    public void setContentPane(JPanel contentPane) {
        this.contentPane = contentPane;
    }

    /** Gets the url text field.
     * 
     * @return the url text field */
    public JTextField getUrlTextField() {
        return urlTextField;
    }

    /** Sets the url text field.
     * 
     * @param urlTextField
     *            the new url text field */
    public void setUrlTextField(JTextField urlTextField) {
        this.urlTextField = urlTextField;
    }

    /** Gets the title text field.
     * 
     * @return the title text field */
    public JTextField getTitleTextField() {
        return titleTextField;
    }

    /** Sets the title text field.
     * 
     * @param titleTextField
     *            the new title text field */
    public void setTitleTextField(JTextField titleTextField) {
        this.titleTextField = titleTextField;
    }

    /** Gets the exit button.
     * 
     * @return the exit button */
    public JButton getExitButton() {
        return exitButton;
    }

    /** Sets the exit button.
     * 
     * @param exitButton
     *            the new exit button */
    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    /** Gets the extract data type combo box.
     * 
     * @return the extract data type combo box */
    public JComboBox getExtractDataTypeComboBox() {
        return extractDataTypeComboBox;
    }

    /** Sets the extract data type combo box.
     * 
     * @param extractDataTypeComboBox
     *            the new extract data type combo box */
    public void setExtractDataTypeComboBox(JComboBox extractDataTypeComboBox) {
        this.extractDataTypeComboBox = extractDataTypeComboBox;
    }

    /** Gets the un structed radio button.
     * 
     * @return the un structed radio button */
    public JRadioButton getUnStructedRadioButton() {
        return unStructedRadioButton;
    }

    /** Sets the un structed radio button.
     * 
     * @param unStructedRadioButton
     *            the new un structed radio button */
    public void setUnStructedRadioButton(JRadioButton unStructedRadioButton) {
        this.unStructedRadioButton = unStructedRadioButton;
    }

    /** Gets the structed radio button.
     * 
     * @return the structed radio button */
    public JRadioButton getStructedRadioButton() {
        return structedRadioButton;
    }

    /** Sets the structed radio button.
     * 
     * @param structedRadioButton
     *            the new structed radio button */
    public void setStructedRadioButton(JRadioButton structedRadioButton) {
        this.structedRadioButton = structedRadioButton;
    }

    /** Gets the data type radio button group.
     * 
     * @return the data type radio button group */
    public ButtonGroup getDataTypeRadioButtonGroup() {
        return dataTypeRadioButtonGroup;
    }

    /** Sets the data type radio button group.
     * 
     * @param dataTypeRadioButtonGroup
     *            the new data type radio button group */
    public void setDataTypeRadioButtonGroup(ButtonGroup dataTypeRadioButtonGroup) {
        this.dataTypeRadioButtonGroup = dataTypeRadioButtonGroup;
    }

    /** Gets the extract data type selectionpanel.
     * 
     * @return the extract data type selectionpanel */
    public JPanel getExtractDataTypeSelectionpanel() {
        return extractDataTypeSelectionpanel;
    }

    /** Sets the extract data type selectionpanel.
     * 
     * @param extractDataTypeSelectionpanel
     *            the new extract data type selectionpanel */
    public void setExtractDataTypeSelectionpanel(JPanel extractDataTypeSelectionpanel) {
        this.extractDataTypeSelectionpanel = extractDataTypeSelectionpanel;
    }

    /** Gets the scroll pane.
     * 
     * @return the scroll pane */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /** Sets the scroll pane.
     * 
     * @param scrollPane
     *            the new scroll pane */
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    /** Gets the image list.
     * 
     * @return the image list */
    public JList getImageList() {
        return imageList;
    }

    /** Sets the image list.
     * 
     * @param imageList
     *            the new image list */
    public void setImageList(JList imageList) {
        this.imageList = imageList;
    }

    /** Gets the btn preview.
     * 
     * @return the btn preview */
    public JButton getBtnPreview() {
        return btnPreview;
    }

    /** Sets the btn preview.
     * 
     * @param btnPreview
     *            the new btn preview */
    public void setBtnPreview(JButton btnPreview) {
        this.btnPreview = btnPreview;
    }

    /** Gets the preview run query panel.
     * 
     * @return the preview run query panel */
    public JPanel getPreviewRunQueryPanel() {
        return previewRunQueryPanel;
    }

    /** Sets the preview run query panel.
     * 
     * @param previewRunQueryPanel
     *            the new preview run query panel */
    public void setPreviewRunQueryPanel(JPanel previewRunQueryPanel) {
        this.previewRunQueryPanel = previewRunQueryPanel;
    }

    /** Gets the extract tocombo box.
     * 
     * @return the extract tocombo box */
    public JComboBox getExtractTocomboBox() {
        return extractTocomboBox;
    }

    /** Sets the extract tocombo box.
     * 
     * @param extractTocomboBox
     *            the new extract tocombo box */
    public void setExtractTocomboBox(JComboBox extractTocomboBox) {
        this.extractTocomboBox = extractTocomboBox;
    }

    /** Gets the lbl extract to.
     * 
     * @return the lbl extract to */
    public JLabel getLblExtractTo() {
        return lblExtractTo;
    }

    /** Sets the lbl extract to.
     * 
     * @param lblExtractTo
     *            the new lbl extract to */
    public void setLblExtractTo(JLabel lblExtractTo) {
        this.lblExtractTo = lblExtractTo;
    }

    /** Gets the btn reset.
     * 
     * @return the btn reset */
    public JButton getBtnReset() {
        return btnReset;
    }

    /** Sets the btn reset.
     * 
     * @param btnReset
     *            the new btn reset */
    public void setBtnReset(JButton btnReset) {
        this.btnReset = btnReset;
    }

    /** Gets the mntm extract process.
     * 
     * @return the mntm extract process */
    public JMenuItem getMntmExtractProcess() {
        return mntmExtractProcess;
    }

    /** Sets the mntm extract process.
     * 
     * @param mntmExtractProcess
     *            the new mntm extract process */
    public void setMntmExtractProcess(JMenuItem mntmExtractProcess) {
        this.mntmExtractProcess = mntmExtractProcess;
    }

    /** Gets the mntm batch process.
     * 
     * @return the mntm batch process */
    public JMenuItem getMntmBatchProcess() {
        return mntmBatchProcess;
    }

    /** Sets the mntm batch process.
     * 
     * @param mntmBatchProcess
     *            the new mntm batch process */
    public void setMntmBatchProcess(JMenuItem mntmBatchProcess) {
        this.mntmBatchProcess = mntmBatchProcess;
    }

    /** Gets the batch process panel.
     * 
     * @return the batch process panel */
    public JPanel getBatchProcessPanel() {
        return batchProcessPanel;
    }

    /** Sets the batch process panel.
     * 
     * @param batchProcessPanel
     *            the new batch process panel */
    public void setBatchProcessPanel(JPanel batchProcessPanel) {
        this.batchProcessPanel = batchProcessPanel;
    }

    /** Gets the button.
     * 
     * @return the button */
    public JButton getButton() {
        return button;
    }

    /** Sets the button.
     * 
     * @param button
     *            the new button */
    public void setButton(JButton button) {
        this.button = button;
    }

    /** Gets the button exit.
     * 
     * @return the button exit */
    public JButton getButtonExit() {
        return buttonExit;
    }

    /** Sets the button exit.
     * 
     * @param buttonExit
     *            the new button exit */
    public void setButtonExit(JButton buttonExit) {
        this.buttonExit = buttonExit;
    }

    /** Gets the lbl new label.
     * 
     * @return the lbl new label */
    public JLabel getLblNewLabel() {
        return lblNewLabel;
    }

    /** Sets the lbl new label.
     * 
     * @param lblNewLabel
     *            the new lbl new label */
    public void setLblNewLabel(JLabel lblNewLabel) {
        this.lblNewLabel = lblNewLabel;
    }

    /** Gets the pathtext field.
     * 
     * @return the pathtext field */
    public JTextField getPathtextField() {
        return pathtextField;
    }

    /** Sets the pathtext field.
     * 
     * @param pathtextField
     *            the new pathtext field */
    public void setPathtextField(JTextField pathtextField) {
        this.pathtextField = pathtextField;
    }

    /** Gets the batch process browse panel.
     * 
     * @return the batch process browse panel */
    public JPanel getBatchProcessBrowsePanel() {
        return batchProcessBrowsePanel;
    }

    /** Sets the batch process browse panel.
     * 
     * @param batchProcessBrowsePanel
     *            the new batch process browse panel */
    public void setBatchProcessBrowsePanel(JPanel batchProcessBrowsePanel) {
        this.batchProcessBrowsePanel = batchProcessBrowsePanel;
    }

    /** Gets the extract process panel.
     * 
     * @return the extract process panel */
    public JPanel getExtractProcessPanel() {
        return extractProcessPanel;
    }

    /** Sets the extract process panel.
     * 
     * @param extractProcessPanel
     *            the new extract process panel */
    public void setExtractProcessPanel(JPanel extractProcessPanel) {
        this.extractProcessPanel = extractProcessPanel;
    }

    /** Gets the btn process.
     * 
     * @return the btn process */
    public JButton getBtnProcess() {
        return btnProcess;
    }

    /** Sets the btn process.
     * 
     * @param btnProcess
     *            the new btn process */
    public void setBtnProcess(JButton btnProcess) {
        this.btnProcess = btnProcess;
    }

    /** Gets the header panel.
     * 
     * @return the header panel */
    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    /** Sets the header panel.
     * 
     * @param headerPanel
     *            the new header panel */
    public void setHeaderPanel(JPanel headerPanel) {
        this.headerPanel = headerPanel;
    }

    /** Gets the lbl extract process.
     * 
     * @return the lbl extract process */
    public JLabel getLblExtractProcess() {
        return lblExtractProcess;
    }

    /** Sets the lbl extract process.
     * 
     * @param lblExtractProcess
     *            the new lbl extract process */
    public void setLblExtractProcess(JLabel lblExtractProcess) {
        this.lblExtractProcess = lblExtractProcess;
    }

    /** Gets the query runner control box panel.
     * 
     * @return the query runner control box panel */
    public JPanel getQueryRunnerControlBoxPanel() {
        return queryRunnerControlBoxPanel;
    }

    /** Sets the query runner control box panel.
     * 
     * @param queryRunnerControlBoxPanel
     *            the new query runner control box panel */
    public void setQueryRunnerControlBoxPanel(JPanel queryRunnerControlBoxPanel) {
        this.queryRunnerControlBoxPanel = queryRunnerControlBoxPanel;
    }

    /** Gets the btn run query.
     * 
     * @return the btn run query */
    public JButton getBtnRunQuery() {
        return btnRunQuery;
    }

    /** Sets the btn run query.
     * 
     * @param btnRunQuery
     *            the new btn run query */
    public void setBtnRunQuery(JButton btnRunQuery) {
        this.btnRunQuery = btnRunQuery;
    }

    /** Gets the html control scroll panel.
     * 
     * @return the html control scroll panel */
    public JScrollPane getHtmlControlScrollPanel() {
        return htmlControlScrollPanel;
    }

    /** Sets the html control scroll panel.
     * 
     * @param htmlControlScrollPanel
     *            the new html control scroll panel */
    public void setHtmlControlScrollPanel(JScrollPane htmlControlScrollPanel) {
        this.htmlControlScrollPanel = htmlControlScrollPanel;
    }

    /** Gets the html control list.
     * 
     * @return the html control list */
    public JList getHtmlControlList() {
        return htmlControlList;
    }

    /** Sets the html control list.
     * 
     * @param htmlControlList
     *            the new html control list */
    public void setHtmlControlList(JList htmlControlList) {
        this.htmlControlList = htmlControlList;
    }

    /** Gets the extract button.
     * 
     * @return the extract button */
    public JButton getExtractButton() {
        return extractButton;
    }

    /** Sets the extract button.
     * 
     * @param extractButton
     *            the new extract button */
    public void setExtractButton(JButton extractButton) {
        this.extractButton = extractButton;
    }

    /** Gets the url.
     * 
     * @return the url */
    public String getUrl() {
        return url;
    }

    /** Sets the url.
     * 
     * @param url
     *            the new url */
    public void setUrl(String url) {
        this.url = url;
    }

    /** Gets the title.
     * 
     * @return the title */
    public String getTitle() {
        return title;
    }

    /** Sets the title.
     * 
     * @param title
     *            the new title */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Gets the content type.
     * 
     * @return the content type */
    public ContentType getContentType() {
        return contentType;
    }

    /** Sets the content type.
     * 
     * @param contentType
     *            the new content type */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

}
