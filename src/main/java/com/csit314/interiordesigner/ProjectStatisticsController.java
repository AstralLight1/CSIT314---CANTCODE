package com.csit314.interiordesigner;

import java.sql.SQLException;
// USER STORY #26: VIEW PROJECT STATISTICS — Controller
public class ProjectStatisticsController {
    public ProjectStatistics viewProjectStatistics(String designer)throws SQLException{return ProjectStatistics.getStatistics(designer);}
}
