package com.locha.dao.custome.impl;

import com.locha.dao.custome.OrderDetailDAO;
import com.locha.entity.OrderDetail;
import com.locha.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    Session session;
    Transaction transaction;

    @Override
    public boolean add(OrderDetail orderDetail) throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        session.save(orderDetail);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(OrderDetail orderDetail) throws Exception {
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws Exception {
        return false;
    }

    @Override
    public OrderDetail search(String s) throws Exception {
        return null;
    }

    @Override
    public List<OrderDetail> getAll() throws Exception {
        return null;
    }
}
