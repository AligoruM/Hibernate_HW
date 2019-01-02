package com.netcracker.dao.book;

import com.netcracker.dao.BasicDAO;
import com.netcracker.model.Book;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("bookDAO")
@Transactional
public class BookDAOImpl extends BasicDAO implements IBookDAO {
    @Override
    public void saveBook(Book entity) {
        persist(entity);
    }

    @Override
    public List<Book> findAllBooks() {
        Criteria criteria = getSession().createCriteria((Book.class));
        return criteria.list();
    }

    @Override
    public Book findBookById(int id) {
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.add(Restrictions.eq("id",id));
        return (Book) criteria.uniqueResult();
    }

    @Override
    public void deleteBookById(int id) {
        Query query = getSession().createQuery("delete from Book where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    @Override
    public long rowCount() {
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.setProjection(Projections.rowCount());
        criteria.uniqueResult();
        return (long)criteria.uniqueResult();
    }

    @Override
    public List<Book> findBookByNameAndPrice(String name, int price) {
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.add(Restrictions.or(Restrictions.like("name", '%'+name+'%'), Restrictions.ge("cost", price)));
        return criteria.list();
    }
}
