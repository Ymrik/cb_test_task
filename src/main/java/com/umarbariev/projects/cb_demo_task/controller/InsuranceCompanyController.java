package com.umarbariev.projects.cb_demo_task.controller;

import com.umarbariev.projects.cb_demo_task.exception_handling.WrongParameterException;
import com.umarbariev.projects.cb_demo_task.model.InsuranceCompany;
import com.umarbariev.projects.cb_demo_task.service.InsuranceCompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InsuranceCompanyController {

    private InsuranceCompanyService insuranceCompanyService;

    @Autowired
    public InsuranceCompanyController(InsuranceCompanyService insuranceCompanyService) {
        this.insuranceCompanyService = insuranceCompanyService;
    }

    @GetMapping("/companies")
    @ApiOperation(value = "Finds all companies",
            notes = "Returns all insurance companies in the database\n" +
                    "You can also specify the order in which records are displayed.\n" +
                    "To do this, you can specify which field you want to sort by." +
                    " You can choose \"tin\" or \"psrn\".\nAnd you can also specify the sort order: \"asc\" or \"desc\"." +
                    " By default, the sort order is \"asc\".",
            response = List.class
    )
    public List<InsuranceCompany> getAllCompanies(@RequestParam(required = false, name = "field") String field,
                                                  @RequestParam(required = false, name = "order") String order) {
        if (field == null) {
            if (order != null) throw new WrongParameterException("Can't specify order without choosing a field");
            else return insuranceCompanyService.getAllCompanies();
        }

        if (!field.equals("tin") && !field.equals("psrn"))
            throw new WrongParameterException("Wrong value for parameter 'field'");

        if (order == null) order = "asc";

        if (!order.equals("asc") && !order.equals("desc"))
            throw new WrongParameterException("Wrong value for parameter 'order'");

        return insuranceCompanyService.getAllCompanies(field, order);
    }

    @GetMapping("/companies/{value}")
    @ApiOperation(value = "Finds companies by specific field",
            notes = "Specify the value that this field accepts. " +
                    "In the field parameter, specify the field by which the search will be performed. \n" +
                    " If value is not fully entered this method returns all " +
                    "companies whose value in the specific field starts with same symbols",
            response = List.class
    )
    public List<InsuranceCompany> getCompaniesByField(@PathVariable(name = "value") String value,
                                                      @RequestParam(name = "field") String field) {
        if (field.equals("tin")) return insuranceCompanyService.getCompaniesByTIN(value);
        if (field.equals("psrn")) return insuranceCompanyService.getCompaniesByPSRN(value);
        if (field.equals("full_name")) return insuranceCompanyService.getCompaniesByFullName(value);
        else throw new WrongParameterException("Wrong value for parameter 'field'");
    }

    @PostMapping("/companies")
    @ApiOperation(value = "Add new company to the database.", response = InsuranceCompany.class)
    public InsuranceCompany addCompany(@Valid @RequestBody InsuranceCompany insuranceCompany) {
        return insuranceCompanyService.addCompany(insuranceCompany);
    }

    @PutMapping("/companies")
    @ApiOperation(value = "Updates insurance company information from the database.", response = InsuranceCompany.class)
    public InsuranceCompany updateCompany(@Valid @RequestBody InsuranceCompany insuranceCompany) {
        return insuranceCompanyService.updateCompany(insuranceCompany);
    }

    @DeleteMapping("/companies/{tin}")
    @ApiOperation(value = "Deletes company from the database.",
            notes = "You must specify TIN number of company which you want to delete.\n" +
                    "This method returns company that was deleted.",
            response = InsuranceCompany.class)
    public InsuranceCompany deleteCompany(@PathVariable(name = "tin") String TIN) {
        if (!TIN.matches("[0-9]{10}")) {
            throw new WrongParameterException("TIN must consist of 10 digits");
        }
        return insuranceCompanyService.deleteCompany(TIN);
    }
}
