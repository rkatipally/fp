package com.fp.datafeed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by raj on 10/4/16.
 */
@Controller
@RequestMapping("/datafeed")
public class DataFeedController {

    public String loadCompanies(){
        return "Successfully created";
    }
}
