package storeservice.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import storeservice.mapper.InterestMapper;
import storeservice.mapper.ProductMapper;
import storeservice.model.Interest;
import storeservice.model.Product;

import java.util.List;

public class InterestDAOImpl implements InterestDAO{

    final public JdbcTemplate jdbcTemplate;

    public InterestDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void deleteOfProduct(int idProduct) {
        String sql = "SELECT * FROM interest WHERE idProduct =?";
        List<Interest> select = jdbcTemplate.query(sql,new InterestMapper(),idProduct);
        String sqldel = "DELETE FROM interest WHERE id = ?";
        for(Interest interest:select){
            jdbcTemplate.update(sqldel,interest.getId());
        }
    }

    @Override
    public Interest findByPerProd(int idPer,int idProd) {
        String sql = "SELECT * FROM interest WHERE idClient = ? AND idProduct =?";
        List<Interest> select = jdbcTemplate.query(sql,new InterestMapper(),idPer,idProd);
        if(select.size()>0)
        return select.get(0);
        else
        return null;
    }

    @Override
    public List<Interest> findAll() {
        String sql = "SELECT * FROM interest ORDER BY timeOfActivity DESC , dateOfActivity DESC";
        return jdbcTemplate.query(sql,new InterestMapper());
    }

    @Override
    public Interest findById(int id) {
        String sql = "SELECT * FROM interest WHERE id = ?";
        List<Interest> select = jdbcTemplate.query(sql,new InterestMapper(),id);
        return select.get(0);
    }

    @Override
    public void addInterest(Interest interest) {
        String sql = "INSERT INTO interest (timeOfActivity,dateOfActivity,idClient,status,idProduct) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,interest.getTimeOfActivity(),interest.getDateOfActivity(),interest.getIdClient(),interest.getStatus(),interest.getIdProduct());
    }

    @Override
    public void deleteInterest(int id) {
        String sql = "DELETE FROM interest WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void updateInterest(Interest interest) {
        String sql = "UPDATE interest SET timeOfActivity=?,dateOfActivity=?,idClient=?,status=?,idProduct=? WHERE id=?";
        jdbcTemplate.update(sql,interest.getTimeOfActivity(),interest.getDateOfActivity(),interest.getIdClient(),interest.getStatus(),interest.getIdProduct(),interest.getId());
    }
}
