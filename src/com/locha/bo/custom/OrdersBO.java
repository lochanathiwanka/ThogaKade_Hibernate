package com.locha.bo.custom;

import com.locha.bo.SuperBO;
import com.locha.dao.CrudDAO;
import com.locha.dto.CustomerDTO;
import com.locha.dto.ItemDTO;
import com.locha.dto.OrderDetailDTO;
import com.locha.dto.OrdersDTO;

import java.util.List;

public interface OrdersBO extends SuperBO {
    String getLastId() throws Exception;

    boolean placeOrder(CustomerDTO customer, OrdersDTO order, List<OrderDetailDTO> orderDetalList, List<ItemDTO> itemList) throws Exception;
}
