package storeservice.dao;

import storeservice.model.Client;

import java.util.List;

public interface ClientDAO {

    public Client findByClient(Client client);
    public Client findByCode(String code);
    public List<Client> findAll();
    public Client findById(int id);
    public void addClient(Client client);
    public void deleteClient(int id);
    public void updateClient(Client client);
}
