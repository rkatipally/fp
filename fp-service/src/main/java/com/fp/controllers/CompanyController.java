package com.fp.controllers;

import com.fp.dao.CompanyDao;
import com.fp.models.Company;
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


    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Company company){
        try{
            companyDao.create(company);
            return "Successfully created";

        }catch (Exception e){
            return "Creation Failed";
        }
    }

    @RequestMapping(value = "createAll", method = RequestMethod.POST)
    @ResponseBody
    public String createAll(@RequestBody List<Company> companies){
        try{
            companyDao.createAll(companies);
            return "Successfully created";

        }catch (Exception e){
            return "Creation Failed";

        }
    }

}
