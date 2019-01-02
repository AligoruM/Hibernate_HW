package com.netcracker.model;


import javax.persistence.*;

@Entity
@Table(name="shop")
public class Shop {
    @Id
    @Column(name="shop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "district")
    private String district;
    @Column(name = "commission")
    private int commission;

    public Shop() {
    }

    public Shop(String name, String district, int commission) {
        this.name = name;
        this.district = district;
        this.commission = commission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", district='" + district + '\'' +
                ", commission=" + commission +
                '}';
    }
}
