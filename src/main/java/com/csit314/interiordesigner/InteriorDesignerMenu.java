package com.csit314.interiordesigner;
import javax.swing.*;
import java.awt.*;
import com.csit314.ui.UiTheme;
// BOUNDARY: INTERIOR DESIGNER MAIN MENU
public class InteriorDesignerMenu {
    private final InteriorDesignerAccount account;
    public InteriorDesignerMenu(String userID) { this.account = new InteriorDesignerAccount(userID); }
    public void displayMenu() {
        JFrame frame = new JFrame("CSIT314 | Interior Designer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel page = UiTheme.page("Interior Designer", "Signed in as " + account.getUserID());
        JPanel grid = UiTheme.grid(5, 1);
        JButton company = UiTheme.action("Manage Company Information");
        JButton current = UiTheme.action("Manage Current Projects");
        JButton past = UiTheme.action("Manage Past Projects");
        JButton statistics = UiTheme.action("View Project Statistics");
        JButton logout = UiTheme.action("Logout");
        company.addActionListener(e -> new CompanyInformationUI(account.getUserID()).displayMenu());
        current.addActionListener(e -> new CurrentProjectUI(account.getUserID()).displayMenu());
        past.addActionListener(e -> new PastProjectUI(account.getUserID()).displayMenu());
        statistics.addActionListener(e -> new ProjectStatisticsUI(account.getUserID()).viewProjectStatistics());
        logout.addActionListener(e -> new LogoutUI().logout(frame, account));
        for (JButton button : new JButton[]{company, current, past, statistics, logout}) grid.add(button);
        page.add(grid, BorderLayout.CENTER);
        frame.add(page); frame.setSize(560, 530); frame.setLocationRelativeTo(null); frame.setVisible(true);
    }
}
