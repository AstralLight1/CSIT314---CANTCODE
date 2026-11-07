package com.csit314.platformmanager;
public class Session {
    private final String managerID;
    private boolean active=true;
    public Session(String managerID){this.managerID=managerID;}
    public String getManagerID(){return managerID;}
    public boolean getSessionStatus(){return active;}
    public boolean invalidate(){active=false;return true;}
}
