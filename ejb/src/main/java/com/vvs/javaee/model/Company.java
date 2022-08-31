package com.vvs.javaee.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = Company.Query.FIND_ALL, query = "SELECT c FROM Company c ORDER BY c.name"),
})
public class Company extends BaseEntity{

    private static final long serialVersionUID = -8047749858311867252L;

    public static class Query {
        public static final String FIND_ALL = "Company.findAll";
    }

    @NotNull
    @Size(min = 1, max = 64)
    private String name;
    public String getName () { return name; };
    public void setName (String name ) { this.name = name; }

    @NotNull
    @Size(min = 1, max = 8)
    private String kind;
    public String getKind() { return kind; }
    public void setKind(String kind) { this.kind = kind; }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id")
    private Address address;
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    @OneToMany(mappedBy="company")
    private Set<Branch> branches = new HashSet<>();
    public Set<Branch> getBranches() { return branches; }
    public void setBranches(Set<Branch> branches) { this.branches = branches; }
}
