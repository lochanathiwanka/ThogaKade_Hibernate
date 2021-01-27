package com.locha.dao.custome.impl;

import com.locha.dao.CrudDAOImpl;
import com.locha.dao.custome.CustomerDAO;
import com.locha.entity.Customer;
import com.locha.util.FactoryConfiguration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class CustomerDAOImpl extends CrudDAOImpl <Customer, String> implements CustomerDAO {

    @Override
    public String getLastId() throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT cid FROM Customer ORDER BY cid DESC LIMIT 1");
        String id = sqlQuery.uniqueResult().toString();
        return id;
    }

    public Customer search(String value) throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("FROM Customer WHERE cid = ?1 OR name = ?2");
        query.setParameter(1, value);
        query.setParameter(2, value);
        return (Customer) query.uniqueResult();
    }
}
