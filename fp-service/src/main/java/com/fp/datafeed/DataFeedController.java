package com.fp.datafeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by raj on 10/4/16.
 */
@Controller
@RequestMapping("/datafeed")
public class DataFeedController {

    @Autowired
    private DataFeedService dataFeedService;

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    @ResponseBody
    public String loadCompanies(){
        dataFeedService.loadCompanies();
        return "Successfully created";
    }

    @RequestMapping(value = "/companyDetails", method = RequestMethod.GET)
    @ResponseBody
    public String loadCompanyDetails(){
        dataFeedService.loadCompanyDetails();
        return "Successfully created";
    }


    @RequestMapping(value = "/indices", method = RequestMethod.GET)
    @ResponseBody
    public String loadIndices(){
        try {
            dataFeedService.loadIndices();


        }catch (Exception e){
            return "Failed!";

        }
        return "Successfully created";
    }

    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    @ResponseBody
    public String loadOwners(){

        try {
            dataFeedService.loadOwners();


        }catch (Exception e){
            return "Failed!";

        }
        return "Successfully created";
    }
}
