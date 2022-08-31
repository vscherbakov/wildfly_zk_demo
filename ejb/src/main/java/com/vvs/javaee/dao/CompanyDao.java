package com.vvs.javaee.dao;

import com.vvs.javaee.model.Company;

import java.util.List;

public interface CompanyDao {
    List<Company> findAll();
    void create(Company branch);
}
