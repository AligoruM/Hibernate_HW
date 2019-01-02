package com.netcracker.dao.customer;

import com.netcracker.dao.BasicDAO;
import com.netcracker.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository("customerDAO")
@Transactional
public class CustomerDAOImpl extends BasicDAO implements ICustomerDAO {
    @Override
    public void saveCustomer(Customer entity) {
        persist(entity);
    }

    @Override
    public List<Customer> findAllCustomers() {
        Criteria criteria = getSession().createCriteria((Customer.class));
        return criteria.list();
    }

    @Override
    public Customer findCustomerById(int id) {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("id", id));
        return (Customer) criteria.uniqueResult();
    }

    @Override
    public void deleteCustomerById(int id) {
        Query query = getSession().createQuery("delete from Customer where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    @Override
    public long rowCount() {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.setProjection(Projections.rowCount());
        criteria.uniqueResult();
        return (long)criteria.uniqueResult();
    }

    @Override
    public List<String> task2b() {
        TypedQuery<String> query = getSession().createQuery("select distinct district from Customer");
        return query.getResultList();
    }

    @Override
    public List<Customer> findCustomerByDistrict(String district) {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("district", district));
        return criteria.list();
    }
}
