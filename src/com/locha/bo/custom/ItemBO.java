package com.locha.bo.custom;

import com.locha.bo.SuperBO;
import com.locha.dto.ItemDTO;
import com.locha.entity.Item;

import java.util.List;

public interface ItemBO extends SuperBO {
    boolean addItem(ItemDTO item) throws Exception;

    String getLastId() throws Exception;

    List<ItemDTO> getAll() throws Exception;

    boolean updateItem(ItemDTO item) throws Exception;

    boolean deleteItem(ItemDTO item) throws Exception;
}
