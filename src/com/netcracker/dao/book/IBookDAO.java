package com.netcracker.dao.book;

import com.netcracker.model.Book;

import java.util.List;

public interface IBookDAO {
    void saveBook(Book entity);

    List<Book> findAllBooks();

    Book findBookById(int id);

    void deleteBookById(int id);

    long rowCount();

    List<Book> findBookByNameAndPrice(String name, int price);

}
