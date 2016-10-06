package com.fp.datafeed;

import com.fp.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

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
        System.out.println("----------------------------Username-----------");
        String username =  env.getProperty("intrinio.username");
        String password =  env.getProperty("intrinio.password");
        String url = env.getProperty("intrinio.company.url") + "?page_size=10000";
        String creds = username + ":" + password;

        System.out.println(username);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + Base64Utils.encodeToString(creds.getBytes()));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        //CompanyResponse response = restTemplate.getForObject(url, CompanyResponse.class);

        for(int i=1;i<=33;i++){
            ResponseEntity<CompanyResponse>  response  =  restTemplate.exchange(url + "?page_number=" + i, HttpMethod.GET, request, CompanyResponse.class);
            System.out.println(response.getBody());
            CompanyResponse company =  (CompanyResponse)response.getBody();
            companyDao.createAll(company.getData());
        }


    }
}
