package com.vvs.javaee.dao;

import com.vvs.javaee.model.Branch;
import com.vvs.javaee.model.Company;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CompanyDaoBean implements CompanyDao{
    @PersistenceContext(unitName = "wildfly-jpa")
    private EntityManager em;

    @Override
    public List<Company> findAll()  {
        return em.createNamedQuery(Company.Query.FIND_ALL, Company.class).getResultList();
    }

    @Override
    public void create(Company company)  {
        em.persist(company);
        for(Branch b : company.getBranches()) {
            b.setCompany(company);
            em.merge(b);
        }
    }
}
