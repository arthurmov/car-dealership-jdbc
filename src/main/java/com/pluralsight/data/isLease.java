package com.pluralsight.data;

import com.pluralsight.models.LeaseContract;

import java.util.List;


public interface isLease {

    List<LeaseContract> getAllLeaseContracts();

    int createLease(LeaseContract lease);

    void deleteLease(int id);

}
