package com.vvs.javaee.dao;

import com.vvs.javaee.model.Address;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AddressDao {
    List<Address> findAll();

    void create(Address address);
}
