package com.vvs.javaee.ui.company;

import com.vvs.javaee.dao.AddressDao;
import com.vvs.javaee.dao.BranchDao;
import com.vvs.javaee.dao.CompanyDao;
import com.vvs.javaee.model.Address;
import com.vvs.javaee.model.Branch;
import com.vvs.javaee.model.Company;
import com.vvs.javaee.ui.address.AddressConverter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

public class NewCompanyViewModel {
    @EJB
    private BranchDao branchDao;

    @EJB
    private AddressDao addressDao;

    @EJB
    private CompanyDao companyDao;

    @Init
    public void init () {
        company = new Company();
        try {
            if (isNull(branchDao)) {
                InitialContext context = new InitialContext();
                this.branchDao = (BranchDao) context.lookup("java:global/wildfly_zk_demo/com.vvs.javaee-ejb-1.0-SNAPSHOT/BranchDaoBean");
            }
            if (isNull(addressDao)) {
                InitialContext context = new InitialContext();
                this.addressDao = (AddressDao) context.lookup("java:global/wildfly_zk_demo/com.vvs.javaee-ejb-1.0-SNAPSHOT/AddressDaoBean");
            }

            if (isNull(companyDao)) {
                InitialContext context = new InitialContext();
                this.companyDao = (CompanyDao) context.lookup("java:global/wildfly_zk_demo/com.vvs.javaee-ejb-1.0-SNAPSHOT/CompanyDaoBean");
            }
        } catch (NamingException e) {

        }
        List<Company> cm = companyDao.findAll();
        addressModel = addressDao.findAll();
        branchModel = branchDao.findAll();
        kindModel =  Arrays.asList("ООО","ИП","АО");

    }

    private Company company = new Company();
    public void setCompany(Company company) { this.company = company; }
    public Company getCompany() { return company; }

    public Address getSelectedAddress() {
        return selectedAddress;
    }
    public void setSelectedAddress(Address selectedAddress) {
        this.selectedAddress = selectedAddress;
    }
    private Address selectedAddress;

    private List<String> kindModel;
    public List<String> getKindModel () {
        return  kindModel;
    }
    public void setKindModel(List<String> kindModel) {
        this.kindModel = kindModel;
    }

    private List<Address> addressModel;
    public List<Address> getAddressModel () {
        return  addressModel;
    }
    public void setAddressModel(List<Address> addressModel) {
        this.addressModel = addressModel;
    }

    public List<Branch> getBranchModel() {
        return branchModel;
    }

    public void setBranchModel(List<Branch> branchModel) {
        this.branchModel = branchModel;
    }
    private List<Branch>  branchModel;

    public Set<Branch> getCompanyBranches() {
        return companyBranches;
    }
    public void setCompanyBranches(Set<Branch> companyBranches) {
        this.companyBranches = companyBranches;
    }
    private Set<Branch>  companyBranches = new HashSet<>();

    private AddressConverter addressConverter = new AddressConverter();
    public AddressConverter getAddressConverter(){
        return addressConverter;
    }

    public void setSelectedBranch(Branch selectedBranch) {
        this.selectedBranch = selectedBranch;
    }
    public Branch getSelectedBranch() {
        return selectedBranch;
    }
    private Branch selectedBranch;

    public Branch getSelectedInGrid() {
        return selectedInGrid;
    }
    public void setSelectedInGrid(Branch selectedInGrid) {
        this.selectedInGrid = selectedInGrid;
    }
    private Branch selectedInGrid;


    private String trash;

    @Command
    @NotifyChange({"companyBranches","selectedBranch"})
    public void addBranch() {
        companyBranches.add(selectedBranch);
        selectedBranch = null;
    }

    @Command
    @NotifyChange("companyBranches")
    public void deleteBranch() {
        companyBranches.remove(selectedInGrid);
    }

    @Command
    @NotifyChange({"company", "companyBranches", "selectedAddress"})
    public void save() {
        company.setAddress(selectedAddress);
        company.setBranches(companyBranches);
        companyDao.create(company);
        company = new Company();
        companyBranches = new HashSet<>();
        selectedAddress = null;
    }


}
