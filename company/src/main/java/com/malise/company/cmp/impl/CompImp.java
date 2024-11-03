package com.malise.company.cmp.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.malise.company.cmp.Company;
import com.malise.company.cmp.CompanyRepository;
import com.malise.company.cmp.CompanyService;

@Service
public class CompImp implements CompanyService{

     private final CompanyRepository companyRepository;

     public CompImp(CompanyRepository companyRepository) {
         this.companyRepository = companyRepository;
     }

    @Override
    public List<Company> getCompanyList() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Boolean updateCompany(Long id, Company company) {
        // companyRepository.findById(id).stream().map(c -> {
        //     c.setName(company.getName());
        //     c.setDescription(company.getDescription());
        //     c.setJobs(company.getJobs());
        //     return companyRepository.save(c);
        // });
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company updatedCompany = companyOptional.get();
            updatedCompany.setName(company.getName());
            updatedCompany.setDescription(company.getDescription());
            // updatedCompany.setJobs(company.getJobs());
            companyRepository.save(updatedCompany);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);

            return true;
        }

        return false;
        
    }
    
}
