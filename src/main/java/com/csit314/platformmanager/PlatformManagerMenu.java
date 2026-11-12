package com.csit314.platformmanager;
import javax.swing.*;
import java.awt.*;
import com.csit314.ui.UiTheme;
public class PlatformManagerMenu {
    private final PlatformManager manager;
    public PlatformManagerMenu(PlatformManager manager) { this.manager = manager; }
    public void displayMenu() {
        JFrame frame = new JFrame("CSIT314 | Platform Manager");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String[] names = {"Create Category", "View Categories", "View Category Details", "Update Category", "Deactivate Category", "Search Categories", "Daily Report", "Weekly Report", "Monthly Report", "Logout"};
        Runnable[] actions = {() -> new CategoryForm().displayCreateForm(), () -> new CategoryListUI().displayCategoryList(), () -> new CategoryListUI().displayCategoryDetails(), () -> new EditCategoryForm().displayEditForm(), () -> new DeactivateCategoryUI().requestConfirmation(), () -> new CategorySearchUI().displaySearchField(), () -> new DailyReportUI().displayReportForm(), () -> new WeeklyReportUI().displayReportForm(), () -> new MonthlyReportUI().displayReportForm(), () -> new LogoutUI().submitLogout(frame, manager)};
        JButton[] buttons = new JButton[names.length];
        for (int i = 0; i < names.length; i++) {
            JButton button = UiTheme.action(names[i]);
            final int index = i;
            button.addActionListener(e -> actions[index].run());
            buttons[i] = button;
        }
        com.csit314.ui.ModernDashboard.show(frame, "Platform Manager", "Categories and project activity reports | " + manager.getUserID(), buttons);
    }
}
