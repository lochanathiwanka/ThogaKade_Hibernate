package com.locha.bo.custom.impl;

import com.locha.bo.custom.CustomerBO;
import com.locha.dao.DAOFactory;
import com.locha.dao.custome.impl.CustomerDAOImpl;
import com.locha.dto.CustomerDTO;
import com.locha.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAOImpl customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public String getLastId() throws Exception {
        return customerDAO.getLastId();
    }

    @Override
    public List<CustomerDTO> getAll() throws Exception {
        List<Customer> all = customerDAO.getAll();
        List<CustomerDTO> list = new ArrayList<>();
        for (Customer c : all) {
            list.add(new CustomerDTO(c.getCid(), c.getName(), c.getAddress()));
        }
        return list;
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws Exception {
        Customer customer = new Customer();
        customer.setCid(customerDTO.getCid());
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        return customerDAO.update(customer);
    }

    @Override
    public CustomerDTO searchCustomer(String value) throws Exception {
        Customer cust = customerDAO.search(value);
        return new CustomerDTO(cust.getCid(), cust.getName(), cust.getAddress());
    }
}
