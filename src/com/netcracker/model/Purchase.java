package com.netcracker.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @Column(name="purchase_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "date")
    //@Temporal(TemporalType.TIMESTAMP)
    private Date date;
    //@Column(name = "seller")
    @ManyToOne
    @JoinColumn(name = "seller", referencedColumnName = "shop_id")
    private Shop seller;
    //@Column(name = "customer")
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
    private Customer customer;
    //@Column(name = "book")
    @ManyToOne
    @JoinColumn(name = "book", referencedColumnName = "book_id")
    private Book book;

    @Column(name = "count")
    private int count;
    @Column(name = "summary")
    private int summary;

    public Purchase() {
    }

    public Purchase(Date date, Shop seller, Customer customer, Book book, int count, int summary) {
        this.date = date;
        this.seller = seller;
        this.customer = customer;
        this.book = book;
        this.count = count;
        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Shop getSeller() {
        return seller;
    }

    public void setSeller(Shop seller) {
        this.seller = seller;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSummary() {
        return summary;
    }

    public void setSummary(int summa) {
        this.summary = summa;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", date=" + date +
                ", seller=" + seller +
                ", customer=" + customer +
                ", book=" + book +
                ", count=" + count +
                ", summa=" + summary +
                '}';
    }
}
