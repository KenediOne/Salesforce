package storeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String password;
    private Time timeOfAuthorization;
    private Date dateOfAuthorization;
    private String activateCode;

    public Admin(int id, String email, String password, Time timeOfAuthorization, Date dateOfAuthorization,String activateCode) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.timeOfAuthorization = timeOfAuthorization;
        this.dateOfAuthorization = dateOfAuthorization;
        this.activateCode = activateCode;
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Time getTimeOfAuthorization() {
        return timeOfAuthorization;
    }

    public void setTimeOfAuthorization(Time timeOfAuthorization) {
        this.timeOfAuthorization = timeOfAuthorization;
    }

    public Date getDateOfAuthorization() {
        return dateOfAuthorization;
    }

    public void setDateOfAuthorization(Date dateOfAuthorization) {
        this.dateOfAuthorization = dateOfAuthorization;
    }

    public String getActivateCode() {
        return activateCode;
    }

    public void setActivateCode(String activateCode) {
        this.activateCode = activateCode;
    }
}
