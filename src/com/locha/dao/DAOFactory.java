package com.locha.dao;

import com.locha.dao.custome.impl.CustomerDAOImpl;
import com.locha.dao.custome.impl.ItemDAOImpl;
import com.locha.dao.custome.impl.OrderDetailDAOImpl;
import com.locha.dao.custome.impl.OrdersDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType{
        CUSTOMER, ITEM, ORDERS, ORDERDETAIL
    }

    public <T extends SuperDAO> T getDAO(DAOType daoType) {
        switch (daoType) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case ORDERS:
                return (T) new OrdersDAOImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailDAOImpl();
            default:
                return null;
        }
    }
}
