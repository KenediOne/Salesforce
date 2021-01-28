package storeservice.dao;

import storeservice.model.Admin;
import storeservice.model.Client;

import java.util.List;

public interface AdminDAO {

    public Admin findByAdmin(Admin admin);
    public Admin findByCode(String code);
    public List<Admin> findAll();
    public Admin findById(int id);
    public void addAdmin(Admin admin);
    public void deleteAdmin(int id);
    public void updateAdmin(Admin admin);
}
