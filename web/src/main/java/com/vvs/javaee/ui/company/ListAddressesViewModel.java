package com.vvs.javaee.ui.company;

import com.vvs.javaee.dao.AddressDao;
import com.vvs.javaee.model.Address;
import com.vvs.javaee.model.Branch;
import org.zkoss.bind.annotation.Init;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

import static java.util.Objects.isNull;

public class ListAddressesViewModel {
    @EJB
    private AddressDao addressDao;

    @Init
    public void init () {
        try {

            if (isNull(addressDao)) {
                InitialContext context = new InitialContext();
                this.addressDao = (AddressDao) context.lookup("java:global/wildfly_zk_demo/com.vvs.javaee-ejb-1.0-SNAPSHOT/AddressDaoBean");
            }
        } catch (NamingException e) {

        }
        listModel = addressDao.findAll();
    }

    public List<Address> getListModel() {
        return listModel;
    }
    public void setListModel(List<Address> listModel) { this.listModel = listModel; }
    private List<Address> listModel;
}
