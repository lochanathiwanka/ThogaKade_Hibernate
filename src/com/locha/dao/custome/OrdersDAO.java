package com.locha.dao.custome;

import com.locha.dao.CrudDAO;
import com.locha.entity.Orders;

public interface OrdersDAO extends CrudDAO <Orders, String> {

    String getLastId() throws Exception;
}
