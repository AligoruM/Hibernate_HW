package com.netcracker.dao.shop;

import com.netcracker.dao.BasicDAO;
import com.netcracker.model.Customer;
import com.netcracker.model.Shop;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("shopDAO")
@Transactional
public class ShopDAOImpl extends BasicDAO implements IShopDAO {
    @Override
    public void saveShop(Customer entity) {
        persist(entity);
    }

    @Override
    public List<Shop> findAllShops() {
        Criteria criteria = getSession().createCriteria(Shop.class);
        return criteria.list();
    }

    @Override
    public Shop findShopById(int id) {
        Criteria criteria = getSession().createCriteria(Shop.class);
        criteria.add(Restrictions.eq("id", id));
        return (Shop) criteria.uniqueResult();
    }

    @Override
    public void deleteShopById(int id) {
        Query query = getSession().createQuery("delete from Shop where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    @Override
    public long rowCount() {
        Criteria criteria = getSession().createCriteria(Shop.class);
        criteria.setProjection(Projections.rowCount());
        criteria.uniqueResult();
        return (long)criteria.uniqueResult();
    }

    @Override
    public List<Shop> findShopByDistricts(String... districts) {
        Criteria criteria = getSession().createCriteria(Shop.class);
        Junction conditionGroup = Restrictions.disjunction();
        for (String district : districts) {
            conditionGroup.add(Restrictions.eq("district", district));
        }
        criteria.add(conditionGroup);
        return criteria.list();
    }




}
