package storeservice.service;

import org.springframework.stereotype.Service;
import storeservice.dao.ProductDAO;
import storeservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductServiceImpl implements AllService {

    @Autowired
    public ProductDAO productDAO;

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public void add(Object product) {
        Product product1 = (Product) product;
        if(product1.getBalance()>0){
            product1.setAvailable(true);
        }else product1.setAvailable(false);
        productDAO.addProduct( product1);
    }

    @Override
    public void delete(int id) {
        productDAO.deleteProduct(id);
    }


    @Override
    public void update(Object product) {
        Product product1 = (Product) product;
        if(product1.getBalance()>0){
            product1.setAvailable(true);
        }else product1.setAvailable(false);
        productDAO.updateProduct(product1);
    }
}
