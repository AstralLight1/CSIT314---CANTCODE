package com.csit314.interiordesigner;

import javax.swing.*;
import java.awt.*;
// BOUNDARY: INTERIOR DESIGNER MAIN MENU
public class InteriorDesignerMenu {
    private final InteriorDesignerAccount account;
    public InteriorDesignerMenu(String userID){this.account=new InteriorDesignerAccount(userID);}
    public void displayMenu(){
        JFrame frame=new JFrame("Interior Designer - "+account.getUserID());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel=new JPanel(new GridLayout(5,1,12,12));
        panel.setBorder(BorderFactory.createEmptyBorder(25,35,25,35));
        JButton company=new JButton("Manage Company Information");
        JButton current=new JButton("Manage Current Projects");
        JButton past=new JButton("Manage Past Projects");
        JButton statistics=new JButton("View Project Statistics");
        JButton logout=new JButton("Logout");
        company.addActionListener(e->new CompanyInformationUI(account.getUserID()).displayMenu());
        current.addActionListener(e->new CurrentProjectUI(account.getUserID()).displayMenu());
        past.addActionListener(e->new PastProjectUI(account.getUserID()).displayMenu());
        statistics.addActionListener(e->new ProjectStatisticsUI(account.getUserID()).viewProjectStatistics());
        logout.addActionListener(e->new LogoutUI().logout(frame,account));
        for(JButton b:new JButton[]{company,current,past,statistics,logout})panel.add(b);
        frame.add(panel);frame.setSize(450,365);frame.setLocationRelativeTo(null);frame.setVisible(true);
    }
}
