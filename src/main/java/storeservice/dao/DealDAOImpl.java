package storeservice.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import storeservice.mapper.DealMapper;
import storeservice.mapper.InterestMapper;
import storeservice.mapper.ProductMapper;
import storeservice.model.Deal;
import storeservice.model.Interest;
import storeservice.model.Product;

import java.util.List;

public class DealDAOImpl implements DealDAO{

    final public JdbcTemplate jdbcTemplate;

    public DealDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Deal findByPerProd(int idPer, int idProd) {
        String sql = "SELECT * FROM deal WHERE idClient = ? AND idProduct =?";
        List<Deal> select = jdbcTemplate.query(sql,new DealMapper(),idPer,idProd);
        if(select.size()>0)
            return select.get(0);
        else
            return null;
    }

    @Override
    public List<Deal> findAll() {
        String sql = "SELECT * FROM deal ORDER BY timeOfActivity DESC , dateOfActivity DESC";
        return jdbcTemplate.query(sql,new DealMapper());
    }

    @Override
    public Deal findById(int id) {
        String sql = "SELECT * FROM deal WHERE id = ?";
        List<Deal> select = jdbcTemplate.query(sql,new DealMapper(),id);
        return select.get(0);
    }

    @Override
    public void addDeal(Deal deal) {
        String sql = "INSERT INTO deal (timeOfActivity,dateOfActivity,idClient,stage,idProduct) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,deal.getTimeOfActivity(),deal.getDateOfActivity(),deal.getIdClient(),deal.getStage(),deal.getIdProduct());
    }

    @Override
    public void deleteDeal(int id) {
        String sql = "DELETE FROM deal WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void updateDeal(Deal deal) {
        String sql = "UPDATE deal SET timeOfActivity=?,dateOfActivity=?,idClient=?,stage=?,idProduct=? WHERE id=?";
        jdbcTemplate.update(sql,deal.getTimeOfActivity(),deal.getDateOfActivity(),deal.getIdClient(),deal.getStage(),deal.getIdProduct(),deal.getId());
    }
}
