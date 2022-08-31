package com.vvs.javaee.ui.branch;

import com.vvs.javaee.dao.AddressDao;
import com.vvs.javaee.dao.BranchDao;
import com.vvs.javaee.model.Address;
import com.vvs.javaee.model.Branch;
import com.vvs.javaee.ui.address.AddressConverter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.List;

import static java.util.Objects.isNull;

public class NewBranchViewModel {
    @EJB
    private BranchDao branchDao;

    @EJB
    private AddressDao addressDao;

    @Init
    public void init() {
        branch = new Branch();
        if (isNull(branchDao)) {
            try {
                InitialContext context = new InitialContext();
                this.branchDao = (BranchDao)  context.lookup("java:global/wildfly_zk_demo/com.vvs.javaee-ejb-1.0-SNAPSHOT/BranchDaoBean");
            } catch (NamingException e) {

            }
        }
        if (isNull(addressDao)) {
            try {
                InitialContext context = new InitialContext();
                this.addressDao = (AddressDao) context.lookup("java:global/wildfly_zk_demo/com.vvs.javaee-ejb-1.0-SNAPSHOT/AddressDaoBean");
            } catch (NamingException e) {

            }
        }
        addressModel = addressDao.findAll();
    }

    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    private Branch branch;

    public Address getSelectedAddress() {
        return selectedAddress;
    }
    public void setSelectedAddress(Address selectedAddress) {
        this.selectedAddress = selectedAddress;
    }
    private Address selectedAddress;

    private List<Address> addressModel;
    public List<Address> getAddressModel () {
        return  addressModel;
    }
    public void setAddressModel(List<Address> addressModel) {
        this.addressModel = addressModel;
    }

    private AddressConverter addressConverter = new AddressConverter();
    public AddressConverter getAddressConverter(){
        return addressConverter;
    }

    @Command
    @NotifyChange({"branch", "selectedAddress"})
    public void save() {
        branch.setAddress(selectedAddress);
        branchDao.create(branch);
        branch = new Branch();
        selectedAddress = null;

    }
}
