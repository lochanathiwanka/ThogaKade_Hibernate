package com.locha.dao.custome.impl;

import com.locha.dao.custome.ItemDAO;
import com.locha.entity.Item;
import com.locha.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private Session session;
    private Transaction transaction;

    @Override
    public boolean add(Item item) throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Item item) throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        session.delete(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Item item) throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        session.update(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Item search(String s) throws Exception {
        return null;
    }

    @Override
    public List<Item> getAll() throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Item");
        List<Item> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String getLastId() throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1");
        String id = sqlQuery.uniqueResult().toString();
        transaction.commit();
        session.close();
        return id;
    }
}
