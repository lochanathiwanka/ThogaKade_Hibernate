package com.locha.dao.custome.impl;

import com.locha.dao.custome.OrdersDAO;
import com.locha.entity.Orders;
import com.locha.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {
    Session session;
    Transaction transaction;

    @Override
    public boolean add(Orders orders) throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        session.save(orders);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Orders orders) throws Exception {
        return false;
    }

    @Override
    public boolean update(Orders orders) throws Exception {
        return false;
    }

    @Override
    public Orders search(String s) throws Exception {
        return null;
    }

    @Override
    public List<Orders> getAll() throws Exception {
        return null;
    }

    @Override
    public String getLastId() throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT oid FROM Orders ORDER BY oid DESC LIMIT 1");
        String id = sqlQuery.uniqueResult().toString();
        transaction.commit();
        session.close();
        return id;
    }
}
