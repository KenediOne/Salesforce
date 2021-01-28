package storeservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import storeservice.mapper.AdminMapper;
import storeservice.mapper.ClientMapper;
import storeservice.mapper.ProductMapper;
import storeservice.model.Admin;
import storeservice.model.Client;
import storeservice.model.Product;

import java.util.List;

public class AdminDAOImpl implements AdminDAO{

    final public JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Admin findByAdmin(Admin admin) {
        String sql = "SELECT * FROM admin WHERE email=? AND password=?";
        List<Admin> select = jdbcTemplate.query(sql,new AdminMapper(),admin.getEmail(),admin.getPassword());
        if(select.size()>0)
            return select.get(0);
        else
            return null;
    }

    @Override
    public Admin findByCode(String code) {
        String sql = "SELECT * FROM admin WHERE activateCode = ?";
        List<Admin> select = jdbcTemplate.query(sql,new AdminMapper(),code);
        return select.get(0);
    }

    @Override
    public List<Admin> findAll() {
        String sql = "SELECT * FROM admin";
        List<Admin> select = jdbcTemplate.query(sql,new AdminMapper());
        return select;
    }

    @Override
    public Admin findById(int id) {
        String sql = "SELECT * FROM admin WHERE id = ?";
        List<Admin> select = jdbcTemplate.query(sql,new AdminMapper(),id);
        return select.get(0);
    }

    @Override
    public void addAdmin(Admin admin) {
        String sql = "INSERT INTO admin (email,password,timeOfAuthorization,dateOfAuthorization,activateCode) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,admin.getEmail(),admin.getPassword(),admin.getTimeOfAuthorization(),admin.getDateOfAuthorization(),admin.getActivateCode());
    }

    @Override
    public void deleteAdmin(int id) {
        String sql = "DELETE FROM admin WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void updateAdmin(Admin admin) {
        String sql = "UPDATE admin SET email=?,password=?,timeOfAuthorization=?,dateOfAuthorization=?,activateCode=? WHERE id=?";
        jdbcTemplate.update(sql,admin.getEmail(),admin.getPassword(),admin.getTimeOfAuthorization(),admin.getDateOfAuthorization(),admin.getActivateCode(),admin.getId());
    }
}
