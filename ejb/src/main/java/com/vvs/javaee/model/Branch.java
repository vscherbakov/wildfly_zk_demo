package com.vvs.javaee.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
        @NamedQuery(name = Branch.Query.FIND_ALL, query = "SELECT b FROM Branch b ORDER BY b.name"),
})
public class Branch extends BaseEntity {
    private static final long serialVersionUID = 2533429675272268521L;

    public static class Query {
        public static final String FIND_ALL = "Branch.findAll";
    }

    @NotNull
    @Size(min = 1, max = 64)
    private String name;
    public String getName () { return name; };
    public void setName (String name ) { this.name = name; }

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    @ManyToOne
    @JoinColumn(name="company_id", nullable=true)
    private Company company;
    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}
