package com.vvs.javaee.ui.company;


import com.vvs.javaee.dao.CompanyDao;
import com.vvs.javaee.model.Company;
import org.zkoss.bind.annotation.Init;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class ListCompaniesViewModel {
    @EJB
    private CompanyDao companyDao;

    @Init
    public void init () {
        try {

            if (isNull(companyDao)) {
                InitialContext context = new InitialContext();
                this.companyDao = (CompanyDao) context.lookup("java:global/wildfly_zk_demo/com.vvs.javaee-ejb-1.0-SNAPSHOT/CompanyDaoBean");
            }
        } catch (NamingException e) {

        }
        listModel = companyDao.findAll();
    }

    public List<Company> getListModel() {
        return listModel;
    }
    public void setListModel(List<Company> listModel) { this.listModel = listModel; }
    private List<Company> listModel;
}

