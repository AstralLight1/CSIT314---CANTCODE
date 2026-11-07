package com.csit314.platformmanager;
import javax.swing.*;
public class CategoryForm {
    public void displayCreateForm(){
        JTextField name=new JTextField(),description=new JTextField();
        Object[] fields={"Category name",name,"Description",description};
        if(JOptionPane.showConfirmDialog(null,fields,"Create IDP Category",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
            try {boolean ok=new CreateCategoryController().createCategory(name.getText().trim(),description.getText().trim());
                JOptionPane.showMessageDialog(null,ok?"Category created.":"Category not created.");
            }catch(Exception e){JOptionPane.showMessageDialog(null,"Create failed: "+e.getMessage());}
        }
    }
}
