package storeservice.dao;

import storeservice.model.Product;

import java.util.List;

public interface ProductDAO {

    public List<Product> findAll();
    public Product findById(int id);
    public void addProduct(Product product);
    public void deleteProduct(int id);
    public void updateProduct(Product product);
}
