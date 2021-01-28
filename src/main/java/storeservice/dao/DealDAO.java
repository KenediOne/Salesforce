package storeservice.dao;

import storeservice.model.Client;
import storeservice.model.Deal;
import storeservice.model.Product;

import java.util.List;

public interface DealDAO {

    public Deal findByPerProd(int idPer, int idProd);
    public List<Deal> findAll();
    public Deal findById(int id);
    public void addDeal(Deal deal);
    public void deleteDeal(int id);
    public void updateDeal(Deal deal);
}
