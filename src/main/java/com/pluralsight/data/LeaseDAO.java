package com.pluralsight.data;

import java.util.List;

import com.pluralsight.models.LeaseContract;
import org.apache.commons.dbcp2.BasicDataSource;


public class LeaseDAO implements isLease {

    private BasicDataSource dataSource;

    public LeaseDAO(BasicDataSource basicDataSource) {
        this.dataSource = basicDataSource;
    }

    @Override
    public List<LeaseContract> getAllLeaseContracts() {
        return List.of();
    }

    @Override
    public int createLease(LeaseContract lease) {
        return 0;
    }

    @Override
    public void deleteLease(int id) {

    }
}