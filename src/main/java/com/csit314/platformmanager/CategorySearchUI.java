package com.csit314.platformmanager;
import javax.swing.*;import java.util.*;
public class CategorySearchUI {
    public void displaySearchField(){
        String q=JOptionPane.showInputDialog("Search category name or description:");if(q==null)return;
        try{List<IDPCategory> list=new SearchCategoryController().searchCategories(q);
            JTextArea area=new JTextArea(12,55);area.setEditable(false);
            if(list.isEmpty())area.setText("No matching categories.");else for(IDPCategory c:list)area.append(c+"\n");
            JOptionPane.showMessageDialog(null,new JScrollPane(area),"Search Results",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){JOptionPane.showMessageDialog(null,"Search failed: "+e.getMessage());}
    }
}
