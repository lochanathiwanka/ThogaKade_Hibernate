package com.locha.bo;

import com.locha.bo.custom.impl.CustomerBOImpl;
import com.locha.bo.custom.impl.ItemBOImpl;
import com.locha.bo.custom.impl.OrderDetailBOImpl;
import com.locha.bo.custom.impl.OrdersBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory () {

    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM, ORDERS, ORDERDETAIL
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDERS:
                return (T) new OrdersBOImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailBOImpl();
            default:
                return null;
        }
    }
}
