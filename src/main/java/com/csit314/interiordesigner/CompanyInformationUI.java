package com.csit314.interiordesigner;

import javax.swing.*;
public class CompanyInformationUI {
    private final String designer; private final CompanyInformationController controller=new CompanyInformationController();
    public CompanyInformationUI(String designer){this.designer=designer;}
    public void displayMenu(){
        String[] choices={"Create Company Information","View Company Information","Update Company Information","Remove Company Information"};
        String option=(String)JOptionPane.showInputDialog(null,"Company Information","Interior Designer",JOptionPane.PLAIN_MESSAGE,null,choices,choices[0]);
        if(option==null)return;
        switch(option){case "Create Company Information"->createCompanyInfo();case "View Company Information"->viewCompanyInfo();case "Update Company Information"->updateCompanyInfo();case "Remove Company Information"->removeCompanyInfo();}
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
    private void show(boolean ok){if(ok)displaySuccess();else displayError();}
    public void displaySuccess(){JOptionPane.showMessageDialog(null,"Operation successful!");}
    public void displayError(){JOptionPane.showMessageDialog(null,"Operation failed or company not found.");}
}
