package storeservice.service;

import storeservice.model.Product;

import java.util.List;

public interface AllService {


    public Object findById(int id);
    public void add(Object object);
    public void delete(int id);
    public void update(Object object);
}
