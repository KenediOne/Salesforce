package storeservice.mapper;


import org.springframework.jdbc.core.RowMapper;
import storeservice.model.Admin;
import storeservice.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminMapper implements RowMapper<Admin> {

    public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
        Admin admin = new Admin();
        admin.setId(resultSet.getInt("id"));
        admin.setEmail(resultSet.getString("email"));
        admin.setPassword(resultSet.getString("password"));
        admin.setTimeOfAuthorization(resultSet.getTime("timeOfAuthorization"));
        admin.setDateOfAuthorization(resultSet.getDate("dateOfAuthorization"));
        admin.setActivateCode(resultSet.getString("activateCode"));
        return admin;
    }

}
