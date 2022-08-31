package com.vvs.javaee.ui.company;

import com.vvs.javaee.dao.BranchDao;
import com.vvs.javaee.model.Branch;
import org.zkoss.bind.annotation.Init;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

import static java.util.Objects.isNull;

public class ListBranchesViewModel {
    @EJB
    private BranchDao branchDao;

    @Init
    public void init () {
        try {

            if (isNull(branchDao)) {
                InitialContext context = new InitialContext();
                this.branchDao = (BranchDao) context.lookup("java:global/wildfly_zk_demo/com.vvs.javaee-ejb-1.0-SNAPSHOT/BranchDaoBean");
            }
        } catch (NamingException e) {

        }
        listModel = branchDao.findAll();
    }

    public List<Branch> getListModel() {
        return listModel;
    }
    public void setListModel(List<Branch> listModel) { this.listModel = listModel; }
    private List<Branch> listModel;
}
