package com.fp.dao;

import com.fp.models.Company;
import com.fp.models.CompanyDetails;
import com.fp.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by raj on 10/4/16.
 */
@Repository
@Transactional
public class CompanyDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Company company) {
        entityManager.persist(company);
        return;
    }

    public Collection<Company> getCompnanies() {
        Query query = entityManager.createQuery("SELECT c FROM Company c");
        return (Collection<Company>)query.getResultList();
    }

    public void addDetails(CompanyDetails companyDetails) {
        if(companyDetails!=null){
            entityManager.persist(companyDetails);

        }
        return;
    }


    public void createAll(List<Company> companies) {
        for (Company company : companies) {
            entityManager.persist(company);
        }
        return;
    }

}
