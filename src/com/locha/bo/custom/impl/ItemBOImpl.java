package com.locha.bo.custom.impl;

import com.locha.bo.custom.ItemBO;
import com.locha.dao.DAOFactory;
import com.locha.dao.custome.impl.ItemDAOImpl;
import com.locha.dto.ItemDTO;
import com.locha.entity.Item;
import com.locha.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAOImpl itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public void addItem(ItemDTO item) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        itemDAO.setSession(session);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            itemDAO.add(new Item(item.getCode(), item.getDescription(), item.getQty(), item.getPrice()));
            transaction.commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        itemDAO.setSession(session);
        Transaction transaction = null;
        String id = null;

        try {
            transaction = session.beginTransaction();
            id = itemDAO.getLastId();
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
    public List<ItemDTO> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        itemDAO.setSession(session);
        Transaction transaction = null;
        List<Item> all = null;

        try {
            transaction = session.beginTransaction();
            all = itemDAO.getAll();
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }

        List<ItemDTO> list = new ArrayList<>();
        for (Item item : all) {
            list.add(new ItemDTO(item.getCode(), item.getDescription(), item.getQty(), item.getPrice()));
        }
        return list;
    }

    @Override
    public void updateItem(ItemDTO item) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        itemDAO.setSession(session);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getQty(), item.getPrice()));
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteItem(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        itemDAO.setSession(session);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            itemDAO.delete(id);
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            session.close();
        }
    }
}
