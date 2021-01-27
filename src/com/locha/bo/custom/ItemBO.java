package com.locha.bo.custom;

import com.locha.bo.SuperBO;
import com.locha.dto.ItemDTO;
import com.locha.entity.Item;

import java.util.List;

public interface ItemBO extends SuperBO {
    void addItem(ItemDTO item) throws Exception;

    String getLastId() throws Exception;

    List<ItemDTO> getAll() throws Exception;

    void updateItem(ItemDTO item) throws Exception;

    void deleteItem(String id) throws Exception;
}
