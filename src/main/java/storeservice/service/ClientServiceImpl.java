package storeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storeservice.dao.ClientDAO;
import storeservice.model.Admin;
import storeservice.model.Client;
import storeservice.model.Product;

import java.util.List;

@Service
public class ClientServiceImpl implements AllService{

    @Autowired
    public ClientDAO clientDAO;

    public Client findByClient(Client client){return clientDAO.findByClient(client);}

    public Client findByCode(String code){
        return clientDAO.findByCode(code);
    };

    public List<Client> findAll() {
        return clientDAO.findAll();
    }


    public Client findById(int id) {
        return clientDAO.findById(id);
    }

    @Override
    public void add(Object object) {
        clientDAO.addClient((Client) object);
    }

    @Override
    public void delete(int id) {
        clientDAO.deleteClient(id);
    }

    @Override
    public void update(Object object) {
        clientDAO.updateClient((Client) object);
    }
}
