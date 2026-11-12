package com.csit314.customer;
import javax.swing.*;
import java.awt.*;
import com.csit314.ui.UiTheme;
/** Customer Boundary menu; existing controllers and entities are unchanged. */
public class CustomerMenu {
    public void displayMenu() {
        JFrame frame = new JFrame("CSIT314 | Customer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String[] names = {"Search Projects", "View Project Details", "Save Project to Favourites", "Search Favourites", "View Favourites", "Logout"};
        Runnable[] actions = {
            () -> { String q = JOptionPane.showInputDialog(frame, "Project keyword:"); if (q != null) new ProjectSearchUI().searchProjects(q); },
            () -> { String id = JOptionPane.showInputDialog(frame, "Project ID:"); if (id != null) new ProjectViewUI().viewProjectDetails(id.trim()); },
            () -> { String id = JOptionPane.showInputDialog(frame, "Project ID to save:"); if (id != null) new FavouriteUI().saveProject(id.trim()); },
            () -> { String q = JOptionPane.showInputDialog(frame, "Favourite keyword:"); if (q != null) new FavouriteSearchUI().searchFavouriteProjects(q); },
            () -> new FavouriteUI().viewFavouriteProjects(),
            () -> { if (new LogoutUI().logout()) { frame.dispose(); new com.csit314.UserLoginUI().displayLoginForm(); } }
        };
        JButton[] buttons = new JButton[names.length];
        for (int i = 0; i < names.length; i++) {
            JButton button = UiTheme.action(names[i]); final int index = i;
            button.addActionListener(e -> actions[index].run()); buttons[i] = button;
        }
        com.csit314.ui.ModernDashboard.show(frame, "Customer", "Discover and save interior design projects | " + CustomerSession.getUserID(), buttons);
    }
}
