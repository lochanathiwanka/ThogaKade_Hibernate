package com.locha.dao;
import java.io.Serializable;
import java.util.List;

public interface CrudDAO <Entity, ID extends Serializable> extends SuperDAO {
    boolean add (Entity entity) throws Exception;
    boolean delete (Entity entity) throws Exception;
    boolean update (Entity entity) throws Exception;
    Entity search (ID id) throws Exception;
    List<Entity> getAll () throws Exception;
}
