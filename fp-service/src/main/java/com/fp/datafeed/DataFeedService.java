package com.fp.datafeed;

import com.fp.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by raj on 10/4/16.
 */
@Service
public class DataFeedService {

    @Autowired
    private Environment env;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CompanyDao companyDao;

    public void loadCompanies(){
        String username =  env.getProperty("intrinio.username");
        String password =  env.getProperty("intrinio.password");
        String url = env.getProperty("intrinio.company.url");
        restTemplate.
        CompanyResponse response = restTemplate.getForObject(url, CompanyResponse.class);

      //  ResponseEntity<CompanyResponse>  response  =  restTemplate.exchange(url, HttpMethod.GET, null, CompanyResponse.class);
        companyDao.createAll(response.b);

    }
}
