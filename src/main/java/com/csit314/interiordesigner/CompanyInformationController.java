package com.csit314.interiordesigner;

public class CompanyInformationController {
    // #15
    public boolean createCompanyInfo(String designer,String name,String details){return CompanyInformation.saveCompanyInfo(designer,name,details);}
    // #16
    public CompanyInformation viewCompanyInformation(String designer){return CompanyInformation.getCompanyInformation(designer);}
    // #17
    public boolean updateCompanyInformation(int id,String designer,String name,String details){return CompanyInformation.updateCompanyInformation(id,designer,name,details);}
    // Search Company Information — Controller
    public java.util.List<CompanyInformation> searchCompanyInformation(String designerID,String keyword){
        if(!validateSearchKeyword(keyword))return java.util.Collections.emptyList();
        return CompanyInformation.searchCompanyInformation(designerID,keyword.trim());
    }
    public boolean validateSearchKeyword(String keyword){return keyword!=null && !keyword.isBlank();}
    // #19
    public boolean removeCompanyInformation(int id,String designer){return CompanyInformation.deleteCompanyInformation(id,designer);}
}
