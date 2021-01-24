package com.locha.dao.custome.impl;

import com.locha.dao.custome.CustomerDAO;
import com.locha.entity.Customer;
import com.locha.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    Session session;
    Transaction transaction;

    @Override
    public String getLastId() throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT cid FROM Customer ORDER BY cid DESC LIMIT 1");
        String id = sqlQuery.uniqueResult().toString();
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public boolean add(Customer customer) throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Customer customer) throws Exception {
        return false;
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Customer search(String value) throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Customer WHERE cid = ?1 OR name = ?2");
        query.setParameter(1, value);
        query.setParameter(2, value);
        Customer customer = (Customer) query.uniqueResult();
        transaction.commit();
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAll() throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Customer");
        List<Customer> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
