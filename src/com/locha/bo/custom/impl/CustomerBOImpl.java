package com.locha.bo.custom.impl;

import com.locha.bo.custom.CustomerBO;
import com.locha.dao.DAOFactory;
import com.locha.dao.custome.impl.CustomerDAOImpl;
import com.locha.dto.CustomerDTO;
import com.locha.entity.Customer;
import com.locha.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAOImpl customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public String getLastId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        customerDAO.setSession(session);
        Transaction transaction = null;
        String id = null;

        try {
            transaction = session.beginTransaction();
            id = customerDAO.getLastId();
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }

        return id;
    }

    @Override
    public List<CustomerDTO> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        customerDAO.setSession(session);
        Transaction transaction = null;
        List<Customer> all = null;

        try {
            transaction = session.beginTransaction();
            all = customerDAO.getAll();
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }

        List<CustomerDTO> list = new ArrayList<>();
        for (Customer c : all) {
            list.add(new CustomerDTO(c.getCid(), c.getName(), c.getAddress()));
        }
        return list;
    }

    @Override
    public void update(CustomerDTO c) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        customerDAO.setSession(session);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            customerDAO.update(new Customer(c.getCid(), c.getName(), c.getAddress()));
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public CustomerDTO searchCustomer(String value) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        customerDAO.setSession(session);
        Transaction transaction = null;
        Customer cust = null;

        try {
            transaction = session.beginTransaction();
            cust = customerDAO.search(value);
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }

        return new CustomerDTO(cust.getCid(), cust.getName(), cust.getAddress());
    }
}
