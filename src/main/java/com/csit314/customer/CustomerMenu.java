package com.csit314.customer;
import javax.swing.*;
import java.awt.*;
import com.csit314.ui.UiTheme;
/** Customer Boundary menu; existing controllers and entities are unchanged. */
public class CustomerMenu {
    public void displayMenu() {
        JFrame frame = new JFrame("CSIT314 | Customer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel page = UiTheme.page("Customer", "Discover and save interior design projects | " + CustomerSession.getUserID());
        JPanel grid = UiTheme.grid(3, 2);
        String[] names = {"Search Projects", "View Project Details", "Save Project to Favourites", "Search Favourites", "View Favourites", "Logout"};
        Runnable[] actions = {
            () -> { String q = JOptionPane.showInputDialog(frame, "Project keyword:"); if (q != null) new ProjectSearchUI().searchProjects(q); },
            () -> { String id = JOptionPane.showInputDialog(frame, "Project ID:"); if (id != null) new ProjectViewUI().viewProjectDetails(id.trim()); },
            () -> { String id = JOptionPane.showInputDialog(frame, "Project ID to save:"); if (id != null) new FavouriteUI().saveProject(id.trim()); },
            () -> { String q = JOptionPane.showInputDialog(frame, "Favourite keyword:"); if (q != null) new FavouriteSearchUI().searchFavouriteProjects(q); },
            () -> new FavouriteUI().viewFavouriteProjects(),
            () -> { if (new LogoutUI().logout()) { frame.dispose(); new com.csit314.UserLoginUI().displayLoginForm(); } }
        };
        for (int i = 0; i < names.length; i++) {
            JButton button = UiTheme.action(names[i]); final int index = i;
            button.addActionListener(e -> actions[index].run()); grid.add(button);
        }
        page.add(grid, BorderLayout.CENTER);
        frame.add(page); frame.setSize(760, 480); frame.setLocationRelativeTo(null); frame.setVisible(true);
    }
}
