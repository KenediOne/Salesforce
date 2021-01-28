package storeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storeservice.dao.AdminDAO;
import storeservice.dao.InterestDAO;
import storeservice.model.Admin;
import storeservice.model.Interest;

import java.util.List;

@Service
public class InterestServiceImpl implements AllService{

    @Autowired
    public InterestDAO interestDAO;

    public void deleteOfProduct(int idProduct){interestDAO.deleteOfProduct(idProduct);}

    public Interest findByPerProd(int idPer,int idProd){return interestDAO.findByPerProd(idPer, idProd);}

    public List<Interest> findAll() {
        return interestDAO.findAll();
    }

    @Override
    public Object findById(int id) {
        return interestDAO.findById(id);
    }

    @Override
    public void add(Object object) {
        interestDAO.addInterest((Interest) object);
    }

    @Override
    public void delete(int id) {
        interestDAO.deleteInterest(id);
    }

    @Override
    public void update(Object object) {
        interestDAO.updateInterest((Interest) object);
    }
}
