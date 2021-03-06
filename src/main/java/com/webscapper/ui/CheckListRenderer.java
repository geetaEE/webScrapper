package com.webscapper.ui;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.apache.log4j.Logger;

/** The Class CheckListRenderer. */
public class CheckListRenderer extends JCheckBox implements ListCellRenderer {

    /** The logger. */
    private static Logger logger = Logger.getLogger(CheckListRenderer.class);

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
        logger.info("Entering getListCellRendererComponent()");

        setEnabled(list.isEnabled());
        setSelected(((CheckListItem) value).isSelected());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setText(value.toString());

        logger.info("Exiting from getListCellRendererComponent()");

        return this;
    }
}
