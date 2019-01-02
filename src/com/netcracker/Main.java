package com.netcracker;

import com.netcracker.config.SpringConfig;
import com.netcracker.dao.book.IBookDAO;
import com.netcracker.dao.customer.ICustomerDAO;
import com.netcracker.dao.purchase.IPurchaseDAO;
import com.netcracker.dao.shop.IShopDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.format.datetime.DateFormatter;

import java.util.Locale;

public class Main {
    static AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    public static void main(final String[] args) {
        task3i();
    }

    static void task2a() {
        IBookDAO test = (IBookDAO) context.getBean("bookDAO");
        test.findAllBooks().forEach(e -> System.out.println("Name: " + e.getName() + "; price:" + e.getCost()));
    }

    static void task2b() {
        ICustomerDAO test = (ICustomerDAO) context.getBean("customerDAO");
        System.out.println(test.task2b());
    }

    static void task2c() {
        IPurchaseDAO test = (IPurchaseDAO) context.getBean("purchaseDAO");
        System.out.println(test.task2c());
    }

    static void task3a() {
        ICustomerDAO test = (ICustomerDAO) context.getBean("customerDAO");
        test.findCustomerByDistrict("Нижегородский").forEach(e -> System.out.println("Name: " + e.getName() + "; discount:" + e.getDiscount()));
    }

    static void task3b() {
        IShopDAO test = (IShopDAO) context.getBean("shopDAO");
        test.findShopByDistricts(new String[]{"Сормово", "Нижегородский"}).forEach(e -> System.out.println("Name: " + e.getName() + "; District: " + e.getDistrict()));
    }

    static void task3c() {
        IBookDAO test = (IBookDAO) context.getBean("bookDAO");
        test.findBookByNameAndPrice("Метро", 1000).forEach(e -> System.out.println("Name: " + e.getName() + "; Price: " + e.getCost()));
    }

    static void task3d() {
        IPurchaseDAO test = (IPurchaseDAO) context.getBean("purchaseDAO");
        test.findAllPurchases().forEach(e -> {
            System.out.println("Purchase id:" + e.getId() + "; Customer: " + e.getCustomer().getName() + "; Shop: " + e.getSeller().getName());
        });
    }

    static void task3e() {
        DateFormatter tmp = new DateFormatter("dd.MM.YYYY");
        IPurchaseDAO test = (IPurchaseDAO) context.getBean("purchaseDAO");
        test.findAllPurchases().forEach(e ->
                System.out.println("Purchase date: " + tmp.print(e.getDate(), Locale.ENGLISH) +
                        "; Customer: " + e.getCustomer().getName() +
                        "; Discount: " + e.getCustomer().getDiscount() +
                        "; Book: " + e.getBook().getName() +
                        "; Count: " + e.getCount())
        );
    }

    static void task3f() {
        DateFormatter tmp = new DateFormatter("dd.MM.YYYY");
        IPurchaseDAO test = (IPurchaseDAO) context.getBean("purchaseDAO");
        test.findPurchaseBySum(60000).forEach(e -> {
            System.out.println("Purchase id:" + e.getId() +
                    "; Customer: " + e.getCustomer().getName() +
                    "; Date: " + tmp.print(e.getDate(), Locale.ENGLISH));
        });
    }

    static void task3g() {
        DateFormatter tmp = new DateFormatter("dd.MM.YYYY");
        IPurchaseDAO test = (IPurchaseDAO) context.getBean("purchaseDAO");
        test.findPurchaseBySameDistrictAndSpecMouth("03").forEach(e ->
                System.out.println("Customer: " + e.getCustomer().getName() +
                        "; District: " + e.getCustomer().getDistrict() +
                        "; Date: " + tmp.print(e.getDate(), Locale.ENGLISH))
        );
    }

    static void task3h() {
        IPurchaseDAO test = (IPurchaseDAO) context.getBean("purchaseDAO");
        test.task3h().forEach(System.out::println);
    }

    static void task3i() {
        IPurchaseDAO test = (IPurchaseDAO) context.getBean("purchaseDAO");
        test.task3i().forEach(e -> {
            Object[] ob = (Object[]) e;
            System.out.println("Name: " + ob[0] + "; District: " + ob[1] + "; Stock:" + ob[2] + "; Price:" + ob[3]);
        });
    }
}