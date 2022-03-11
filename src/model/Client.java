package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public final class Client {

    private int idClient;
    private SimpleStringProperty clientName = new SimpleStringProperty();
    private SimpleStringProperty clientLastNames = new SimpleStringProperty();
    private SimpleStringProperty clientSex = new SimpleStringProperty();
    private byte[] picture;

    public Client(int idClient, SimpleStringProperty clientName, SimpleStringProperty clientLastNames, SimpleStringProperty clientSex, byte[] picture) {
        this.idClient= idClient;
        this.clientName= clientName;
        this.clientLastNames= clientLastNames;
        this.clientSex= clientSex;
        this.picture= picture;
    }
    
    public Client(int idClient, String clientName, String clientLastName, String clientSex,byte[] picture) {
        this.idClient= idClient;
        this.clientName.setValue(clientName);
        this.clientLastNames.setValue(clientLastName);
        this.clientSex.setValue(clientSex);
        this.picture= picture;
    }

    public int getIdClient() {
        return idClient;
    }

    public SimpleStringProperty getClientName() {
        return clientName;
    }

    public SimpleStringProperty getClientLastNames() {
        return clientLastNames;
    }

    public SimpleStringProperty getClientSex() {
        return clientSex;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setClientName(SimpleStringProperty clientName) {
        this.clientName = clientName;
    }

    public void setClientLastNames(SimpleStringProperty clientLastNames) {
        this.clientLastNames = clientLastNames;
    }

    public void setClientSex(SimpleStringProperty clientSex) {
        this.clientSex = clientSex;
    }
       
      
    
    public ObservableValue<String> clientNameProperty() {
            return this.clientName;
    }
    public ObservableValue<String> clientLastNamesProperty() {
            return this.clientLastNames;
    }
      
    public ObservableValue<String> clientSexProperty() {
            return this.clientSex;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
       
    
}
