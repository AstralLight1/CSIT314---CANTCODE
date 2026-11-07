package com.csit314.platformmanager;
import java.sql.*;import java.time.*;import java.util.*;
public class GenerateWeeklyReportController {
    public List<IDPProject> generateWeeklyReport(LocalDate start,LocalDate end)throws SQLException {return IDPProject.getProjectsByDateRange(start,end);}
}
