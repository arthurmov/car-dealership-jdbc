package com.pluralsight.data;

import com.pluralsight.models.SalesContract;

import java.util.List;

public interface isSale {


    List<SalesContract> getAllSales();

    int createSale(SalesContract sale);

    void deleteSale(int id);
}