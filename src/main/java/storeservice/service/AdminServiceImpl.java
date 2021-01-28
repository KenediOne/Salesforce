package storeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import storeservice.dao.AdminDAO;
import storeservice.model.Admin;
import storeservice.model.Client;

import java.util.List;

@Service
public class AdminServiceImpl implements AllService{

    @Autowired
    public AdminDAO adminDAO;

    public Admin findByAdmin(Admin admin){return adminDAO.findByAdmin(admin);}

    public Admin findByCode(String code){
        return adminDAO.findByCode(code);
    }

    public List<Admin> findAll() {
        return adminDAO.findAll();
    }

    @Override
    public Admin findById(int id) {
        return adminDAO.findById(id);
    }

    @Override
    public void add(Object object) {
        adminDAO.addAdmin((Admin) object);
    }

    @Override
    public void delete(int id) {
        adminDAO.deleteAdmin(id);
    }

    @Override
    public void update(Object object) {
        adminDAO.updateAdmin((Admin) object);
    }
}
