package com.fp.datafeed;

import com.fp.dao.CompanyDao;
import com.fp.dao.IndexDao;
import com.fp.dao.OwnerDao;
import com.fp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

    @Autowired
    private IndexDao indexDao;

    @Autowired
    private OwnerDao ownerDao;

    private HttpHeaders headers;

    @Value("${intrinio.company.totalcount}")
    private String companyDetailsEndPropKey;

    @Value("${intrinio.company.chunksize}")
    private String companyDetailsStartPropKey;

    @Autowired
    private PropertyService propertyService;

    public DataFeedService(){

    }

    public void loadCompanies(){
        String username =  env.getProperty("intrinio.username");
        String password =  env.getProperty("intrinio.password");

        String creds = username + ":" + password;

        System.out.println(username);
        headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + Base64Utils.encodeToString(creds.getBytes()));
        String url = env.getProperty("intrinio.company.url");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        //IntrinioListResponse response = restTemplate.getForObject(url, IntrinioListResponse.class);

        for(int i=1;i<=33;i++){
            ResponseEntity<IntrinioListResponse<Company>>  response  =  restTemplate.exchange(url + "?page_number=" + i, HttpMethod.GET, request, new ParameterizedTypeReference<IntrinioListResponse<Company>>() {});
            System.out.println(response.getBody());
            IntrinioListResponse<Company> company =  (IntrinioListResponse<Company>)response.getBody();
            companyDao.createAll(company.getData());
        }

    }

    public void loadCompanyDetails(){
        String username =  env.getProperty("intrinio.username");
        String password =  env.getProperty("intrinio.password");

        String creds = username + ":" + password;

        System.out.println(username);
        headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + Base64Utils.encodeToString(creds.getBytes()));
        List<Company> companies= (List<Company>)companyDao.getCompnanies();

        Property property = propertyService.getProperty(companyDetailsEndPropKey);
        int COMPANY_START = Integer.parseInt(property.getValue());
        Property sizeProperty = propertyService.getProperty(companyDetailsStartPropKey);

        int COMPANY_END = Integer.parseInt(sizeProperty.getValue());
        for(int i = COMPANY_START;i<=companies.size() && i<=COMPANY_START + COMPANY_END;i++ ){
            Company company = companies.get(i);
            HttpEntity<String> request = new HttpEntity<String>(headers);
            //IntrinioListResponse response = restTemplate.getForObject(url, IntrinioListResponse.class);
            String url = env.getProperty("intrinio.company.url") + "?identifier="+ company.getTicker();
            ResponseEntity<CompanyDetails>  response  =  restTemplate.exchange(url, HttpMethod.GET, request, CompanyDetails.class);
            System.out.println(response.getBody());
            System.out.println(url);
            companyDao.addDetails(response.getBody());
        }

        propertyService.updateProperty(companyDetailsEndPropKey,""+Constants.COMPANY_START +  Constants.COMPANY_END);

    }


    public void loadIndices() throws URISyntaxException{
        String username =  env.getProperty("intrinio.username");
        String password =  env.getProperty("intrinio.password");

        String creds = username + ":" + password;

        String url = env.getProperty("intrinio.index.master.url");
        URI uri = new URI(url);

        System.out.println(username);
        headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + Base64Utils.encodeToString(creds.getBytes()));
        RequestEntity<String> request = new RequestEntity<String>(headers, HttpMethod.GET, uri);
        //
        ResponseEntity<IntrinioListResponse<Index>>  response  =  restTemplate.exchange(request, new ParameterizedTypeReference<IntrinioListResponse<Index>>(){});

        for(Index index: (List<Index>)response.getBody().getData()){
            indexDao.addIndex(index);
        }

    }

    public void loadOwners() throws URISyntaxException{
        String username =  env.getProperty("intrinio.username");
        String password =  env.getProperty("intrinio.password");

        String creds = username + ":" + password;

        System.out.println(username);
        headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + Base64Utils.encodeToString(creds.getBytes()));
        String url = env.getProperty("intrinio.owner.master.url");
        URI uri = new URI(url);
        RequestEntity<String> request = new RequestEntity<String>(headers, HttpMethod.GET, uri);

        ResponseEntity<IntrinioListResponse<Owner>>  response  =  restTemplate.exchange(request, new ParameterizedTypeReference<IntrinioListResponse<Owner>>(){});

        for(Owner owner: (List<Owner>)response.getBody()){
            ownerDao.addOwner(owner);
        }

    }




}
