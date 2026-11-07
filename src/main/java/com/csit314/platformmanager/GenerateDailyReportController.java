package com.csit314.platformmanager;
import java.sql.*;import java.time.*;import java.util.*;
public class GenerateDailyReportController {
    public List<IDPProject> generateDailyReport(LocalDate start,LocalDate end)throws SQLException {return IDPProject.getProjectsByDateRange(start,end);}
}
