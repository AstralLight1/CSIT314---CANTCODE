package com.csit314.platformmanager;
import javax.swing.*;
public class EditCategoryForm {
    public void displayEditForm(){
        String id=JOptionPane.showInputDialog("Category ID to update:");if(id==null)return;
        JTextField name=new JTextField(),description=new JTextField();
        try{IDPCategory old=new ViewCategoryController().getCategoryByID(id);
            if(old==null){JOptionPane.showMessageDialog(null,"Category not found.");return;}
            name.setText(old.getCategoryName());description.setText(old.getDescription());
            Object[] fields={"Category name",name,"Description",description};
            if(JOptionPane.showConfirmDialog(null,fields,"Update Category",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
                boolean ok=new UpdateCategoryController().updateCategory(id,name.getText().trim(),description.getText().trim());
                JOptionPane.showMessageDialog(null,ok?"Category updated.":"Update failed.");
            }
        }catch(Exception e){JOptionPane.showMessageDialog(null,"Update failed: "+e.getMessage());}
    }
}
