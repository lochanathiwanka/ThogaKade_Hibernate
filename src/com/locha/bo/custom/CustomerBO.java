package com.locha.bo.custom;

import com.locha.bo.SuperBO;
import com.locha.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    String getLastId() throws Exception;

    List<CustomerDTO> getAll() throws Exception;

    boolean update(CustomerDTO customerDTO) throws Exception;

    CustomerDTO searchCustomer(String value) throws Exception;
}
