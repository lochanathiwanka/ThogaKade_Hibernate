package com.locha.dao.custome;

import com.locha.dao.CrudDAO;
import com.locha.entity.Customer;

public interface CustomerDAO extends CrudDAO <Customer, String> {
    String getLastId() throws Exception;
}
