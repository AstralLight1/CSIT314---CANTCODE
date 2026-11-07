package com.csit314.platformmanager;
import javax.swing.*;import java.time.*;import java.util.*;
public class MonthlyReportUI {
    public void displayReportForm() {
        String input=JOptionPane.showInputDialog("Enter any date in the month (YYYY-MM-DD):");if(input==null)return;
        try{LocalDate date=LocalDate.parse(input);LocalDate start=date.withDayOfMonth(1),end=date.withDayOfMonth(date.lengthOfMonth());
            List<IDPProject> projects=new GenerateMonthlyReportController().generateMonthlyReport(start,end);
            JTextArea area=new JTextArea(14,65);area.setEditable(false);
            area.append("Monthly report: "+start+" to "+end+"\nTotal projects: "+projects.size()+"\n\n");
            for(IDPProject p:projects)area.append(p+"\n");
            JOptionPane.showMessageDialog(null,new JScrollPane(area),"Monthly Report",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){JOptionPane.showMessageDialog(null,"Report failed: "+e.getMessage());}
    }
}
