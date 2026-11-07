package com.csit314.platformmanager;
import java.sql.*;import java.time.*;import java.util.*;
public class IDPProject {
    private final String projectID,projectName,projectStatus;
    private final LocalDate createdAt;
    public IDPProject(String id,String name,String status,LocalDate createdAt){this.projectID=id;this.projectName=name;this.projectStatus=status;this.createdAt=createdAt;}
    public String toString(){return projectID+" | "+projectName+" | "+projectStatus+" | "+createdAt;}
    public static List<IDPProject> getProjectsByDateRange(LocalDate start,LocalDate end)throws SQLException{
        String sql="SELECT project_id,project_name,project_status,created_at FROM public.interior_design_projects WHERE created_at >= ? AND created_at < ? ORDER BY created_at,project_id";
        List<IDPProject> list=new ArrayList<>();
        try(Connection c=PlatformManagerDatabase.connect();PreparedStatement p=c.prepareStatement(sql)){
            p.setTimestamp(1,Timestamp.valueOf(start.atStartOfDay()));p.setTimestamp(2,Timestamp.valueOf(end.plusDays(1).atStartOfDay()));
            try(ResultSet r=p.executeQuery()){while(r.next())list.add(new IDPProject(r.getString("project_id"),r.getString("project_name"),r.getString("project_status"),r.getTimestamp("created_at").toLocalDateTime().toLocalDate()));}
        }return list;
    }
}
