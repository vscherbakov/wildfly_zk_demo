package com.vvs.javaee.ui.address;

import com.vvs.javaee.dao.AddressDao;
import com.vvs.javaee.model.Address;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import static java.util.Objects.isNull;

public class newAddressViewModel {
    @EJB
    private AddressDao addressDao;

    @Init
    public void init() {
        address = new Address();
        if (isNull(addressDao)) {
            try {
                InitialContext context = new InitialContext();
                this.addressDao = (AddressDao) context.lookup("java:global/wildfly_zk_demo/com.vvs.javaee-ejb-1.0-SNAPSHOT/AddressDaoBean");
            } catch (NamingException e) {

            }
        }
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    private Address address;

    @Command
    @NotifyChange("address")
    public void save() {
        addressDao.create(address);
        address = new Address();
    }
}
