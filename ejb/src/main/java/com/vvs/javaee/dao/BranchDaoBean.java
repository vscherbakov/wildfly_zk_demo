package com.vvs.javaee.dao;

import com.vvs.javaee.model.Branch;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BranchDaoBean implements BranchDao{
    @PersistenceContext(unitName = "wildfly-jpa")
    private EntityManager em;

    @Override
    public List<Branch> findAll() {
        return em.createNamedQuery(Branch.Query.FIND_ALL, Branch.class).getResultList();
    }

    @Override
    public void create(Branch branch) {
        em.persist(branch);
    }
}
