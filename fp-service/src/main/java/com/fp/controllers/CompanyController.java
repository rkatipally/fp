package com.fp.controllers;

import com.fp.dao.CompanyDao;
import com.fp.models.Company;
import com.fp.models.CompanyDetails;
import com.fp.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by raj on 10/4/16.
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    // Wire the UserDao used inside this controller.
    @Autowired
    private CompanyDao companyDao;


    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String createCompany(@RequestBody Company company){
        try{
            companyDao.create(company);
            return "Successfully created";

        }catch (Exception e){
            return "Creation Failed";
        }
    }


    @RequestMapping(value = "addDetails", method = RequestMethod.POST)
    @ResponseBody
    public String addDetails(@RequestBody CompanyDetails companyDetails){
        try{
            companyDao.addDetails(companyDetails);
            return "Successfully created";

        }catch (Exception e){
            return "Creation Failed";
        }
    }

    @RequestMapping(value = "addAll", method = RequestMethod.POST)
    @ResponseBody
    public String createCompanyAll(@RequestBody List<Company> companies){
        try{
            companyDao.createAll(companies);
            return "Successfully created";

        }catch (Exception e){
            return "Creation Failed";

        }
    }

}
