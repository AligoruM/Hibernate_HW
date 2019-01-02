package com.netcracker.dao.shop;

import com.netcracker.model.Customer;
import com.netcracker.model.Shop;

import java.util.List;

public interface IShopDAO {
    void saveShop(Customer entity);

    List<Shop> findAllShops();

    Shop findShopById(int id);

    void deleteShopById(int id);

    long rowCount();

    List<Shop> findShopByDistricts(String[] districts);

}
