package com.csit314.interiordesigner;

import javax.swing.*;
import java.awt.*;
import com.csit314.ui.UiTheme;
public class CompanyInformationUI {
    private final String designer; private final CompanyInformationController controller=new CompanyInformationController();
    public CompanyInformationUI(String designer){this.designer=designer;}
    public void displayMenu(){
        JFrame frame = new JFrame("CSIT314 | Company Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel page = UiTheme.page("Company Information", "Manage your company profile  |  " + designer);
        JPanel actions = UiTheme.grid(3, 2);
        String[] labels = {"Create Company", "View Company", "Update Company", "Remove Company", "Search Company"};
        Runnable[] operations = {this::createCompanyInfo, this::viewCompanyInfo,
            this::updateCompanyInfo, this::removeCompanyInfo, this::searchCompanyInfo};
        for(int i=0;i<labels.length;i++){
            JButton button = UiTheme.action(labels[i]);
            final int index=i;
            button.addActionListener(e -> operations[index].run());
            actions.add(button);
        }
        JButton close = UiTheme.action("Back to Dashboard");
        close.addActionListener(e -> frame.dispose());
        actions.add(close);
        page.add(actions, BorderLayout.CENTER);
        frame.setContentPane(page);
        frame.setSize(760, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private String ask(String label){return JOptionPane.showInputDialog(null,label);}
    // #15
    public void createCompanyInfo(){String name=ask("Company name:");if(name==null)return;String details=ask("Company details:");if(details==null)return;
        if(name.isBlank()){displayError();return;}show(controller.createCompanyInfo(designer,name,details));}
    // #16
    public void viewCompanyInfo(){CompanyInformation c=controller.viewCompanyInformation(designer);
        if(c==null){displayError();return;}
        JOptionPane.showMessageDialog(null,"Company ID: "+c.getCompanyID()+"\nName: "+c.getCompanyName()+"\nDetails: "+c.getCompanyDetails());}
    // #17
    public void updateCompanyInfo(){CompanyInformation c=controller.viewCompanyInformation(designer);if(c==null){displayError();return;}
        String name=ask("Company name (current: "+c.getCompanyName()+"):");if(name==null)return;
        String details=ask("Company details:");if(details==null)return;
        if(name.isBlank()){displayError();return;}show(controller.updateCompanyInformation(c.getCompanyID(),designer,name,details));}
    // #19
    public void removeCompanyInfo(){CompanyInformation c=controller.viewCompanyInformation(designer);if(c==null){displayError();return;}
        if(JOptionPane.showConfirmDialog(null,"Remove company: "+c.getCompanyName()+"?","Confirm",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            show(controller.removeCompanyInformation(c.getCompanyID(),designer));}
    // Search Company Information — Boundary
    public void searchCompanyInfo(){
        String keyword=ask("Search company information (name or details):");
        if(keyword==null)return;
        java.util.List<CompanyInformation> results=controller.searchCompanyInformation(designer,keyword);
        if(results.isEmpty()){
            JOptionPane.showMessageDialog(null,"No matching company information found.");
            return;
        }
        StringBuilder message=new StringBuilder();
        for(CompanyInformation c:results){
            if(message.length()>0)message.append("\n\n");
            message.append("Company ID: ").append(c.getCompanyID())
                .append("\nName: ").append(c.getCompanyName())
                .append("\nDetails: ").append(c.getCompanyDetails());
        }
        JOptionPane.showMessageDialog(null,message.toString(),"Search Results",JOptionPane.INFORMATION_MESSAGE);
    }
    private void show(boolean ok){if(ok)displaySuccess();else displayError();}
    public void displaySuccess(){JOptionPane.showMessageDialog(null,"Operation successful!");}
    public void displayError(){JOptionPane.showMessageDialog(null,"Operation failed or company not found.");}
}
