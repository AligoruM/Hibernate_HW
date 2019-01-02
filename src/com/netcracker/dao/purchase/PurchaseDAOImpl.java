package com.netcracker.dao.purchase;

import com.netcracker.dao.BasicDAO;
import com.netcracker.model.Purchase;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository("purchaseDAO")
@Transactional
public class PurchaseDAOImpl extends BasicDAO implements IPurchaseDAO {
    @Override
    public void savePurchase(Purchase entity) {
        persist(entity);
    }

    @Override
    public List<Purchase> findAllPurchases() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Purchase> cr = cb.createQuery(Purchase.class);
        Root<Purchase> root = cr.from(Purchase.class);
        cr.select(root);
        TypedQuery<Purchase> query = getSession().createQuery(cr);
        return query.getResultList();
    }

    @Override
    public Purchase findPurchaseById(int id) {
        Criteria criteria = getSession().createCriteria(Purchase.class);
        criteria.add(Restrictions.eq("id", id));
        return (Purchase) criteria.uniqueResult();
    }

    @Override
    public void deletePurchaseById(int id) {
        Query query = getSession().createQuery("delete from Purchase where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    public long rowCount(){
        Criteria criteria = getSession().createCriteria(Purchase.class);
        criteria.setProjection(Projections.rowCount());
        criteria.uniqueResult();
        return (long)criteria.uniqueResult();
    }

    public List<String> task2c(){
        TypedQuery<String> query = getSession().createQuery("SELECT distinct date FROM Purchase");
        return query.getResultList();
        //Тут явный фейл, в sqlite нет нормального способа хранения даты(т.е. нет никаких реальных DATE/DATETIME),
        //поэтому я даже толком не могу выделить месяц... Надо было юзать что-то нормальное все-таки...
    }

    @Override
    public List<Purchase> findPurchaseBySum(int sum) {
        Criteria criteria = getSession().createCriteria(Purchase.class);
        criteria.add(Restrictions.ge("summary", sum));
        return criteria.list();
    }

    @Override
    public List<Purchase> findPurchaseBySameDistrictAndSpecMouth(String mouth) {
        Criteria criteria = getSession().createCriteria(Purchase.class);
        try {
            Date d =  new SimpleDateFormat("yyyy-MM-dd").parse("2018-"+ mouth +"-01");
            criteria.add(Restrictions.ge("date", d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        criteria.createAlias("customer", "cs").createAlias("seller", "sl");
        criteria.add(Restrictions.eqProperty("sl.district", "cs.district"));
        criteria.addOrder(Order.desc("sl.district"));


        return criteria.list();
    }

    @Override
    public List task3h() {
        Criteria criteria = getSession().createCriteria(Purchase.class);
        criteria.createAlias("seller", "sl").createAlias("customer", "cs");
        criteria.add(Restrictions.not(Restrictions.eq("sl.district", "Автозавод")));
        criteria.add(Restrictions.between("cs.discount", 10,15));
        ProjectionList p = Projections.projectionList();
        p.add(Projections.property("seller"));
        criteria.setProjection(p);
        return criteria.list();
    }

    @Override
    public List task3i() {
        Criteria criteria = getSession().createCriteria(Purchase.class);
        criteria.createAlias("seller", "sl").createAlias("book", "bk");
        criteria.add(Restrictions.eqProperty("sl.district", "bk.storage"));
        criteria.add(Restrictions.gt("bk.stock", 10));
        ProjectionList p = Projections.projectionList();
        p.add(Projections.property("bk.name"));
        p.add(Projections.property("bk.storage"));
        p.add(Projections.property("bk.stock"));
        p.add(Projections.property("bk.cost"), "price");
        //p.add(Projections.property("book"));
        criteria.setProjection(p);
        //criteria.addOrder(Order.asc("price"));

        return criteria.list();
    }


}
