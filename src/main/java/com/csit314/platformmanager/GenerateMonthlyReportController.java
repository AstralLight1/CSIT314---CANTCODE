package com.csit314.platformmanager;
import java.sql.*;import java.time.*;import java.util.*;
public class GenerateMonthlyReportController {
    public List<IDPProject> generateMonthlyReport(LocalDate start,LocalDate end)throws SQLException {return IDPProject.getProjectsByDateRange(start,end);}
}
