package com.locha.dao.custome;

import com.locha.dao.CrudDAO;
import com.locha.entity.Item;

public interface ItemDAO extends CrudDAO <Item, String> {

    String getLastId() throws Exception;
}
