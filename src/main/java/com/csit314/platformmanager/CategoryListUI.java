package com.csit314.platformmanager;
import javax.swing.*;import java.util.*;
public class CategoryListUI {
    public void displayCategoryList(){
        try{List<IDPCategory> list=new ViewCategoryController().getCategories();
            JTextArea area=new JTextArea(14,55);area.setEditable(false);
            if(list.isEmpty())area.setText("No categories found.");else for(IDPCategory c:list)area.append(c+"\n");
            JOptionPane.showMessageDialog(null,new JScrollPane(area),"IDP Categories",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){JOptionPane.showMessageDialog(null,"View failed: "+e.getMessage());}
    }
    public void displayCategoryDetails(){String id=JOptionPane.showInputDialog("Category ID:");if(id==null)return;
        try{IDPCategory c=new ViewCategoryController().getCategoryByID(id);JOptionPane.showMessageDialog(null,c==null?"Category not found.":c.toString());}
        catch(Exception e){JOptionPane.showMessageDialog(null,"View failed: "+e.getMessage());}}
}
