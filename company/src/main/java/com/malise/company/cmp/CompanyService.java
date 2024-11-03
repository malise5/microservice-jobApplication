package com.malise.company.cmp;

import java.util.List;


public interface CompanyService {

    List<Company> getCompanyList();

    Company getCompanyById(Long id);

    Company createCompany(Company company);

    Boolean updateCompany(Long id ,Company company);

    Boolean deleteCompany(Long id);

    
}