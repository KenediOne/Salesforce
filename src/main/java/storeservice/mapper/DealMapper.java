package storeservice.mapper;


import org.springframework.jdbc.core.RowMapper;
import storeservice.model.Client;
import storeservice.model.Deal;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DealMapper implements RowMapper<Deal> {

    public Deal mapRow(ResultSet resultSet, int i) throws SQLException {
        Deal deal = new Deal();
        deal.setId(resultSet.getInt("id"));
        deal.setTimeOfActivity(resultSet.getTime("timeOfActivity"));
        deal.setDateOfActivity(resultSet.getDate("dateOfActivity"));
        deal.setIdClient(resultSet.getInt("idClient"));
        deal.setStage( resultSet.getByte("stage"));
        deal.setIdProduct(resultSet.getInt("idProduct"));
        return deal;
    }

}
