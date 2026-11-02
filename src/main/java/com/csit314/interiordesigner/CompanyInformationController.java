package com.csit314.interiordesigner;

public class CompanyInformationController {
    // #15
    public boolean createCompanyInfo(String designer,String name,String details){return CompanyInformation.saveCompanyInfo(designer,name,details);}
    // #16
    public CompanyInformation viewCompanyInformation(String designer){return CompanyInformation.getCompanyInformation(designer);}
    // #17
    public boolean updateCompanyInformation(int id,String designer,String name,String details){return CompanyInformation.updateCompanyInformation(id,designer,name,details);}
    // #19
    public boolean removeCompanyInformation(int id,String designer){return CompanyInformation.deleteCompanyInformation(id,designer);}
}
