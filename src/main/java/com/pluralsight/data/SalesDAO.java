package com.pluralsight.data;

import java.util.List;

import com.pluralsight.models.SalesContract;

import org.apache.commons.dbcp2.BasicDataSource;


public class SalesDAO implements isSale {
    private BasicDataSource dataSource;

    public SalesDAO(BasicDataSource basicDataSource) {
        this.dataSource = basicDataSource;
    }

    @Override
    public List<SalesContract> getAllSales() {
        return List.of();
    }

    @Override
    public int createSale(SalesContract sale) {
        return 0;
    }

    @Override
    public void deleteSale(int id) {

    }
}