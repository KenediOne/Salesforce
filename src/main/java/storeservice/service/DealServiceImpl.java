package storeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storeservice.dao.AdminDAO;
import storeservice.dao.DealDAO;
import storeservice.model.Admin;
import storeservice.model.Deal;
import storeservice.model.Interest;

import java.util.List;

@Service
public class DealServiceImpl implements AllService{

    @Autowired
    public DealDAO dealDAO;

    public Deal findByPerProd(int idPer, int idProd){return dealDAO.findByPerProd(idPer, idProd);}

    public List<Deal> findAll() {
        return dealDAO.findAll();
    }

    @Override
    public Object findById(int id) {
        return dealDAO.findById(id);
    }

    @Override
    public void add(Object object) {
        dealDAO.addDeal((Deal) object);
    }

    @Override
    public void delete(int id) {
        dealDAO.deleteDeal(id);
    }

    @Override
    public void update(Object object) {
        dealDAO.updateDeal((Deal) object);
    }
}
