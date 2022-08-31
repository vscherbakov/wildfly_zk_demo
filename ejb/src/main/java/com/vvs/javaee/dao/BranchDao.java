package com.vvs.javaee.dao;

import com.vvs.javaee.model.Branch;

import java.util.List;

public interface BranchDao {
    List<Branch> findAll();
    void create(Branch branch);
}
