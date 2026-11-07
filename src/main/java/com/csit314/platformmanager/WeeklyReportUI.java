package com.csit314.platformmanager;
import javax.swing.*;import java.time.*;import java.util.*;
public class WeeklyReportUI {
    public void displayReportForm() {
        String input=JOptionPane.showInputDialog("Enter any date in the week (YYYY-MM-DD):");if(input==null)return;
        try{LocalDate date=LocalDate.parse(input);LocalDate start=date.with(java.time.DayOfWeek.MONDAY),end=start.plusDays(6);
            List<IDPProject> projects=new GenerateWeeklyReportController().generateWeeklyReport(start,end);
            JTextArea area=new JTextArea(14,65);area.setEditable(false);
            area.append("Weekly report: "+start+" to "+end+"\nTotal projects: "+projects.size()+"\n\n");
            for(IDPProject p:projects)area.append(p+"\n");
            JOptionPane.showMessageDialog(null,new JScrollPane(area),"Weekly Report",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){JOptionPane.showMessageDialog(null,"Report failed: "+e.getMessage());}
    }
}
