package storeservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import storeservice.mapper.ClientMapper;
import storeservice.model.Client;

import java.util.List;

public class ClientDAOImpl implements ClientDAO{

    final public JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Client findByClient(Client client) {
        String sql = "SELECT * FROM client WHERE email=? AND password=?";
        List<Client> select = jdbcTemplate.query(sql,new ClientMapper(),client.getEmail(),client.getPassword());
        if(select.size()>0)
            return select.get(0);
        else
            return null;
    }

    @Override
    public Client findByCode(String code) {
        String sql = "SELECT * FROM client WHERE activateCode = ?";
        List<Client> select = jdbcTemplate.query(sql,new ClientMapper(),code);
        if(select.size()>0)
            return select.get(0);
        else
            return null;
    }

    @Override
    public List<Client> findAll() {
        String sql = "SELECT * FROM client";
        return jdbcTemplate.query(sql,new ClientMapper());
    }

    @Override
    public Client findById(int id) {
        String sql = "SELECT * FROM client WHERE id = ?";
        List<Client> select = jdbcTemplate.query(sql,new ClientMapper(),id);
        return select.get(0);
    }

    @Override
    public void addClient(Client client) {
        String sql = "INSERT INTO client (name,email,password,phone,birthday,activateCode) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql,client.getName(),client.getEmail(),client.getPassword(),client.getPhone(),client.getBirthday(),client.getActivateCode());
    }

    @Override
    public void deleteClient(int id) {
        String sql = "DELETE FROM client WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void updateClient(Client client) {
        String sql = "UPDATE client SET name=?,email=?,password=?,phone=?,birthday=?,activateCode=? WHERE id=?";
        jdbcTemplate.update(sql,client.getName(),client.getEmail(),client.getPassword(),client.getPhone(),client.getBirthday(),client.getActivateCode(),client.getId());
    }
}
