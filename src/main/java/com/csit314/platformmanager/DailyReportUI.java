package com.csit314.platformmanager;
import javax.swing.*;import java.time.*;import java.util.*;
public class DailyReportUI {
    public void displayReportForm() {
        String input=JOptionPane.showInputDialog("Enter date (YYYY-MM-DD):");if(input==null)return;
        try{LocalDate date=LocalDate.parse(input);LocalDate start=date,end=date;
            List<IDPProject> projects=new GenerateDailyReportController().generateDailyReport(start,end);
            JTextArea area=new JTextArea(14,65);area.setEditable(false);
            area.append("Daily report: "+start+" to "+end+"\nTotal projects: "+projects.size()+"\n\n");
            for(IDPProject p:projects)area.append(p+"\n");
            JOptionPane.showMessageDialog(null,new JScrollPane(area),"Daily Report",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){JOptionPane.showMessageDialog(null,"Report failed: "+e.getMessage());}
    }
}
