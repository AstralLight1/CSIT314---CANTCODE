package com.csit314.platformmanager;
import javax.swing.*;
public class DeactivateCategoryUI {
    public void displayCategoryList(){new CategoryListUI().displayCategoryList();}
    public void requestConfirmation(){
        String id=JOptionPane.showInputDialog("Category ID to deactivate:");if(id==null)return;
        try{IDPCategory c=new DeactivateCategoryController().findCategory(id);
            if(c==null){JOptionPane.showMessageDialog(null,"Category not found.");return;}
            if(JOptionPane.showConfirmDialog(null,"Deactivate "+c.getCategoryName()+"?","Confirm",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                boolean ok=new DeactivateCategoryController().deactivateCategory(id);
                JOptionPane.showMessageDialog(null,ok?"Category deactivated.":"Deactivation failed.");
            }
        }catch(Exception e){JOptionPane.showMessageDialog(null,"Deactivate failed: "+e.getMessage());}
    }
}
