package com.webscapper.ui;

/**
 * The Class CheckListItem.
 */
public class CheckListItem 
{
	
	/** The label. */
	private String  label;
	
	/** The is selected. */
	private boolean isSelected = false;

   /**
    * Instantiates a new check list item.
    *
    * @param label the label
    */
   public CheckListItem(String label)
   {
      this.label = label;
   }

   /**
    * Checks if is selected.
    *
    * @return true, if is selected
    */
   public boolean isSelected()
   {
      return isSelected;
   }

   /**
    * Sets the selected.
    *
    * @param isSelected the new selected
    */
   public void setSelected(boolean isSelected)
   {
      this.isSelected = isSelected;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
      return label;
   }

}
