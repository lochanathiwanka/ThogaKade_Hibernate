package com.locha.bo.custom.impl;

import com.locha.bo.custom.OrdersBO;
import com.locha.dao.DAOFactory;
import com.locha.dao.custome.impl.CustomerDAOImpl;
import com.locha.dao.custome.impl.ItemDAOImpl;
import com.locha.dao.custome.impl.OrderDetailDAOImpl;
import com.locha.dao.custome.impl.OrdersDAOImpl;
import com.locha.dto.CustomerDTO;
import com.locha.dto.ItemDTO;
import com.locha.dto.OrderDetailDTO;
import com.locha.dto.OrdersDTO;
import com.locha.entity.*;

import java.util.ArrayList;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {
    CustomerDAOImpl customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    OrdersDAOImpl ordersDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    OrderDetailDAOImpl orderDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAIL);
    ItemDAOImpl itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public String getLastId() throws Exception {
        return ordersDAO.getLastId();
    }

    @Override
    public boolean placeOrder(CustomerDTO c, OrdersDTO o, List<OrderDetailDTO> odList, List<ItemDTO> iList) throws Exception {
        Customer customer = new Customer();
        customer.setCid(c.getCid());
        customer.setName(c.getName());
        customer.setAddress(c.getAddress());

        Orders order = new Orders();
        order.setOid(o.getOid());
        order.setDate(o.getDate());

        order.setCustomer(customer);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();

        order.setOrderDetailList(orderDetailList);
        customerDAO.add(customer);
        ordersDAO.add(order);

        Item item = new Item();
        for (int i = 0; i < odList.size(); i++) {
            CompositeKey pk = new CompositeKey();
            pk.setOid(odList.get(i).getOid());
            pk.setCode(odList.get(i).getCode());

            item = new Item(iList.get(i).getCode(), iList.get(i).getDescription(), iList.get(i).getQty(), iList.get(i).getPrice(), orderDetailList);

            orderDetail.setCompositeKey(pk);
            orderDetail.setOrders(order);
            orderDetail.setItem(item);
            orderDetail.setQty(odList.get(i).getQty());
            orderDetail.setPrice(odList.get(i).getPrice());

            orderDetailDAO.add(orderDetail);
            itemDAO.update(item);
        }
        return true;
    }
}
