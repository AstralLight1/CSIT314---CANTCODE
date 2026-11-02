package com.csit314.customer;

import javax.swing.JOptionPane;

/** Customer menu; calls the Boundary classes from the diagrams. */
public class CustomerMenu {
    public void displayMenu(){
        String[] choices={"Search Projects","View Project Details","Save Project to Favourites","Search Favourites","View Favourites","Logout"};
        while(CustomerSession.getUserID()!=null){
            int option=JOptionPane.showOptionDialog(null,"Logged in: "+CustomerSession.getUserID(),"Customer Menu",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,choices,choices[0]);
            if(option==0){String q=JOptionPane.showInputDialog("Project keyword:");if(q!=null)new ProjectSearchUI().searchProjects(q);}
            else if(option==1){String id=JOptionPane.showInputDialog("Project ID:");if(id!=null)new ProjectViewUI().viewProjectDetails(id.trim());}
            else if(option==2){String id=JOptionPane.showInputDialog("Project ID to save:");if(id!=null)new FavouriteUI().saveProject(id.trim());}
            else if(option==3){String q=JOptionPane.showInputDialog("Favourite keyword:");if(q!=null)new FavouriteSearchUI().searchFavouriteProjects(q);}
            else if(option==4)new FavouriteUI().viewFavouriteProjects();
            else if (option == 5) {
             if (new LogoutUI().logout()) {
        new com.csit314.UserLoginUI().displayLoginForm();
        break;
    }
}
            else break;
        }
    }
}
