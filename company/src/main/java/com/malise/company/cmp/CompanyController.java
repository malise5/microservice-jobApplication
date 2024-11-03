package com.malise.company.cmp;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malise.company.cmp.impl.CompImp;






@RestController
@RequestMapping("/api")
public class CompanyController {
    
    private final CompImp companyImpl;

    public CompanyController(CompImp companyImpl) {
        this.companyImpl = companyImpl;
    }

    // Add your company-related API endpoints here

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompany(){
        // Implementation to fetch all company details
        // return companyImpl.getCompanyList().stream().map(Company::getName).collect(Collectors.toList());
        List<Company> companies = companyImpl.getCompanyList();

        return ResponseEntity.status(HttpStatus.OK).body(companies);

    }

    @GetMapping("companies/{id}")
    public ResponseEntity<Company> getMethodName(@PathVariable Long id) {
        
        // Implementation to fetch company details by id
        // return companyImpl.getCompanyById(id).getName();
        Company company = companyImpl.getCompanyById(id);
        if (company!= null) {
            return ResponseEntity.status(HttpStatus.OK).body(company);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @PostMapping("/companies")
    public ResponseEntity<String> postMethodName(@RequestBody Company company) {
        // Implementation to create a new company
        // companyImpl.createCompany(company);
        Company savedCompany = companyImpl.createCompany(company);
        if (savedCompany!= null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Company created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create company");
        }

    }
    

    @PutMapping("companies/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        // companyImpl.updateCompany(id, company);
        Boolean updated = companyImpl.updateCompany(id, company);
        if (updated) {
            return ResponseEntity.status(HttpStatus.OK).body("Company updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company Id Not Found");
        }
        
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        Boolean deleted = companyImpl.deleteCompany(id);

        if (deleted) {

        return ResponseEntity.status(HttpStatus.OK).body("Company Delete Successfully");
            
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company Not Deleted Successfully");

   
    }
    
}
