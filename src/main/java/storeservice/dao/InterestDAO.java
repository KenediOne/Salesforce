package storeservice.dao;

import storeservice.model.Deal;
import storeservice.model.Interest;

import java.util.List;

public interface InterestDAO {

    public void deleteOfProduct(int idProduct);
    public Interest findByPerProd(int idPer,int idProd);
    public List<Interest> findAll();
    public Interest findById(int id);
    public void addInterest(Interest interest);
    public void deleteInterest(int id);
    public void updateInterest(Interest interest);
}
