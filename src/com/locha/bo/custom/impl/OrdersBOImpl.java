package com.locha.bo.custom.impl;

import com.locha.bo.custom.OrdersBO;
import com.locha.dao.DAOFactory;
import com.locha.dao.custome.CustomerDAO;
import com.locha.dao.custome.ItemDAO;
import com.locha.dao.custome.OrderDetailDAO;
import com.locha.dao.custome.OrdersDAO;
import com.locha.dto.CustomerDTO;
import com.locha.dto.ItemDTO;
import com.locha.dto.OrderDetailDTO;
import com.locha.dto.OrdersDTO;
import com.locha.entity.Customer;
import com.locha.entity.Item;
import com.locha.entity.OrderDetail;
import com.locha.entity.Orders;
import com.locha.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.List;

public class OrdersBOImpl implements OrdersBO {
    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    OrdersDAO ordersDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAIL);
    ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public String getLastId() throws Exception {
        return ordersDAO.getLastId();
    }

    @Override
    public void placeOrder(CustomerDTO c, OrdersDTO o, List<OrderDetailDTO> odList, List<ItemDTO> iList) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        customerDAO.setSession(session);
        ordersDAO.setSession(session);
        orderDetailDAO.setSession(session);
        itemDAO.setSession(session);

        try {
            session.getTransaction().begin();
            Customer customer = new Customer(c.getCid(), c.getName(), c.getAddress());
            customerDAO.add(customer);

            ordersDAO.add(new Orders(o.getOid(), o.getDate(), customer));

            for (OrderDetailDTO od : odList) {
                orderDetailDAO.add(new OrderDetail(od.getOid(), od.getCode(), od.getQty(), od.getPrice()));
                Item item = itemDAO.search(od.getCode());
                item.setQty(item.getQty() - od.getQty());
            }
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
    }
}
