package storeservice.mapper;


import org.springframework.jdbc.core.RowMapper;
import storeservice.model.Deal;
import storeservice.model.Interest;

import java.sql.ResultSet;
import java.sql.SQLException;


public class InterestMapper implements RowMapper<Interest> {

    public Interest mapRow(ResultSet resultSet, int i) throws SQLException {
        Interest interest = new Interest();
        interest.setId(resultSet.getInt("id"));
        interest.setTimeOfActivity(resultSet.getTime("timeOfActivity"));
        interest.setDateOfActivity(resultSet.getDate("dateOfActivity"));
        interest.setIdClient(resultSet.getInt("idClient"));
        interest.setStatus( resultSet.getByte("status"));
        interest.setIdProduct(resultSet.getInt("idProduct"));
        return interest;
    }

}
