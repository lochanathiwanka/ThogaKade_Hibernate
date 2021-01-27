package com.locha.dao.custome.impl;

import com.locha.dao.CrudDAOImpl;
import com.locha.dao.custome.OrdersDAO;
import com.locha.entity.Orders;
import com.locha.util.FactoryConfiguration;
import org.hibernate.query.NativeQuery;

public class OrdersDAOImpl extends CrudDAOImpl <Orders, String> implements OrdersDAO {

    @Override
    public String getLastId() throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT oid FROM Orders ORDER BY oid DESC LIMIT 1");
        String id = sqlQuery.uniqueResult().toString();
        return id;
    }
}
