package storeservice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Time timeOfActivity;
    private Date dateOfActivity;
    private int idClient;
    private byte status;
    private int idProduct;

    public Interest(int id, Time timeOfActivity, Date dateOfActivity, int idClient, byte stage, int idProduct) {
        this.id = id;
        this.timeOfActivity = timeOfActivity;
        this.dateOfActivity = dateOfActivity;
        this.idClient = idClient;
        this.status = stage;
        this.idProduct = idProduct;
    }

    public Interest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTimeOfActivity() {
        return timeOfActivity;
    }

    public void setTimeOfActivity(Time timeOfActivity) {
        this.timeOfActivity = timeOfActivity;
    }

    public Date getDateOfActivity() {
        return dateOfActivity;
    }

    public void setDateOfActivity(Date dateOfActivity) {
        this.dateOfActivity = dateOfActivity;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
