package com.netcracker.dao.purchase;


import com.netcracker.model.Purchase;

import java.util.List;

public interface IPurchaseDAO {
    void savePurchase(Purchase entity);

    List<Purchase> findAllPurchases();

    Purchase findPurchaseById(int id);

    void deletePurchaseById(int id);

    long rowCount();

    List<String> task2c();

    List<Purchase> findPurchaseBySum(int sum);

    List<Purchase> findPurchaseBySameDistrictAndSpecMouth(String mouth);

    List task3h();

    List task3i();
}
