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
}
