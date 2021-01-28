package storeservice.mapper;


import org.springframework.jdbc.core.RowMapper;
import storeservice.model.Client;


import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientMapper implements RowMapper<Client> {

    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("id"));
        client.setName(resultSet.getString("name"));
        client.setEmail(resultSet.getString("email"));
        client.setPassword(resultSet.getString("password"));
        client.setPhone(resultSet.getString("phone"));
        client.setBirthday(resultSet.getString("birthday"));
        client.setActivateCode(resultSet.getString("activateCode"));
        return client;
    }

}
