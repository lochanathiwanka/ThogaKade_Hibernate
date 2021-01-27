package com.locha.dao.custome.impl;
import com.locha.dao.CrudDAOImpl;
import com.locha.dao.custome.ItemDAO;
import com.locha.entity.Item;
import com.locha.util.FactoryConfiguration;
import org.hibernate.query.NativeQuery;

public class ItemDAOImpl extends CrudDAOImpl <Item, String> implements ItemDAO {

    @Override
    public String getLastId() throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1");
        String id = sqlQuery.uniqueResult().toString();
        return id;
    }
}
