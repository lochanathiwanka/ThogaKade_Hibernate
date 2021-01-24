package com.locha.bo.custom.impl;

import com.locha.bo.custom.ItemBO;
import com.locha.dao.DAOFactory;
import com.locha.dao.custome.impl.ItemDAOImpl;
import com.locha.dto.ItemDTO;
import com.locha.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAOImpl itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public boolean addItem(ItemDTO item) throws Exception {
        Item i = new Item();
        i.setCode(item.getCode());
        i.setDescription(item.getDescription());
        i.setQty(item.getQty());
        i.setPrice(item.getPrice());
        return itemDAO.add(i);
    }

    @Override
    public String getLastId() throws Exception {
        return itemDAO.getLastId();
    }

    @Override
    public List<ItemDTO> getAll() throws Exception {
        List<Item> all = itemDAO.getAll();
        List<ItemDTO> list = new ArrayList<>();
        for (Item item : all) {
            list.add(new ItemDTO(item.getCode(), item.getDescription(), item.getQty(), item.getPrice()));
        }
        return list;
    }

    @Override
    public boolean updateItem(ItemDTO item) throws Exception {
        Item i = new Item();
        i.setCode(item.getCode());
        i.setDescription(item.getDescription());
        i.setQty(item.getQty());
        i.setPrice(item.getPrice());
        itemDAO.update(i);
        return true;
    }

    @Override
    public boolean deleteItem(ItemDTO item) throws Exception {
        Item i = new Item();
        i.setCode(item.getCode());
        i.setDescription(item.getDescription());
        i.setQty(item.getQty());
        i.setPrice(item.getPrice());
        itemDAO.delete(i);
        return true;
    }
}
