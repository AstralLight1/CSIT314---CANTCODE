package com.csit314.interiordesigner;

import javax.swing.*;
import java.util.List;
// BOUNDARY: CURRENT PROJECT — Stories #20-24
public class CurrentProjectUI {
    private final String designer;
    private final CurrentProjectController controller=new CurrentProjectController();
    public CurrentProjectUI(String designer){this.designer=designer;}
    public void displayMenu(){
        String[] options={"Create Current Project","View Current Projects","Update Current Project","Remove Current Project","Search Current Projects"};
        String option=(String)JOptionPane.showInputDialog(null,"Current Projects","Interior Designer",JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
        if(option==null)return;
        switch(option){
            case "Create Current Project" -> createCurrentProject();
            case "View Current Projects" -> viewCurrentProjects();
            case "Update Current Project" -> updateCurrentProject();
            case "Remove Current Project" -> removeCurrentProject();
            case "Search Current Projects" -> searchCurrentProjects();
        }
    }
    private String ask(String text){return JOptionPane.showInputDialog(null,text);}
    private Integer askID(){
        String s=ask("Project ID:");if(s==null)return null;
        try{return Integer.parseInt(s.trim());}catch(NumberFormatException e){JOptionPane.showMessageDialog(null,"Enter a numeric project ID.");return null;}
    }
    private void show(boolean success,String message){JOptionPane.showMessageDialog(null,success?message:"Operation failed or project not found.");}
    private void display(List<CurrentInteriorDesignProject> list){
        if(list.isEmpty()){JOptionPane.showMessageDialog(null,"No current projects found.");return;}
        StringBuilder b=new StringBuilder();
        for(CurrentInteriorDesignProject p:list)b.append("ID: ").append(p.getProjectID()).append(" | ").append(p.getProjectName()).append("\n").append(p.getProjectDetails()).append("\n\n");
        JTextArea a=new JTextArea(b.toString(),15,45);a.setEditable(false);
        JOptionPane.showMessageDialog(null,new JScrollPane(a),"Current Projects",JOptionPane.INFORMATION_MESSAGE);
    }
    // USER STORY #20: CREATE CURRENT PROJECT — Boundary
    public void createCurrentProject(){
        String name=ask("Project name:");if(name==null)return;
        String details=ask("Project details:");if(details==null)return;
        if(name.isBlank()){JOptionPane.showMessageDialog(null,"Project name required.");return;}
        show(controller.createCurrentProject(designer,name,details),"Project created!");
    }
    // USER STORY #21: VIEW CURRENT PROJECTS — Boundary
    public void viewCurrentProjects(){display(controller.viewCurrentProjects(designer));}
    // USER STORY #22: UPDATE CURRENT PROJECT — Boundary
    public void updateCurrentProject(){
        Integer id=askID();if(id==null)return;
        String name=ask("New project name:");if(name==null)return;
        String details=ask("New project details:");if(details==null)return;
        if(name.isBlank()){JOptionPane.showMessageDialog(null,"Project name required.");return;}
        show(new UpdateCurrentProjectController().updateCurrentProject(designer,id,name,details),"Project updated!");
    }
    // USER STORY #23: REMOVE CURRENT PROJECT — Boundary
    public void removeCurrentProject(){
        Integer id=askID();if(id==null)return;
        if(JOptionPane.showConfirmDialog(null,"Remove project "+id+"?","Confirm",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        show(new RemoveCurrentProjectController().removeCurrentProject(designer,id),"Project removed!");
    }
    // USER STORY #24: SEARCH CURRENT PROJECTS — Boundary
    public void searchCurrentProjects(){String q=ask("Search keyword:");if(q!=null)display(new SearchCurrentProjectController().searchCurrentProjects(designer,q));}
}
