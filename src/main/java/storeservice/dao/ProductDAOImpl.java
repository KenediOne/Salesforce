package storeservice.dao;

import storeservice.mapper.ProductMapper;
import storeservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProductDAOImpl implements ProductDAO{

    final public JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM product WHERE available=1";
        return jdbcTemplate.query(sql,new ProductMapper());
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        List<Product> select = jdbcTemplate.query(sql,new ProductMapper(),id);
        return select.get(0);
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO product (name,description,price,balance,available) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,product.getName(),product.getDescription(),product.getPrice(),product.getBalance(),product.getAvailable());
    }

    @Override
    public void deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE product SET name=?,description=?,price=?,balance=?,available=? WHERE id=?";
        jdbcTemplate.update(sql,product.getName(),product.getDescription(),product.getPrice(),product.getBalance(),product.getAvailable(),product.getId());
    }
}
