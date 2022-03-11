package model;

import java.sql.SQLException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class ClientAccount {
	private int idClientAccount;
	private int idClient;
	private int idMonth;
	private int idYear;
	private SimpleStringProperty assistance= new SimpleStringProperty();
	private SimpleStringProperty payment=new SimpleStringProperty();

    public ClientAccount(int idClientAccount, int idClient, int idMonth, int idYear, SimpleStringProperty assistance, SimpleStringProperty payment) {
        this.idClientAccount = idClientAccount;
        this.idClient = idClient;
        this.idMonth = idMonth;
        this.idYear = idYear;
        this.assistance= assistance;
        this.payment= payment;
    }
    public ClientAccount(int idClientAccount, int idClient, int idMonth, int idYear, String assistance, String payment) {
        this.idClientAccount = idClientAccount;
        this.idClient = idClient;
        this.idMonth = idMonth;
        this.idYear = idYear;
        this.assistance.setValue(assistance);
        this.payment.setValue(payment);
    }

    public int getIdClientAccount() {
        return idClientAccount;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdMonth() {
        return idMonth;
    }

    public int getIdYear() {
        return idYear;
    }

    public SimpleStringProperty getAssistance() {
        return assistance;
    }

    public SimpleStringProperty getPayment() {
        return payment;
    }

    public void setIdClientAccount(int idClientAccount) {
        this.idClientAccount = idClientAccount;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdMonth(int idMonth) {
        this.idMonth = idMonth;
    }

    public void setIdYear(int idYear) {
        this.idYear = idYear;
    }

    public void setAssistance(SimpleStringProperty assistance) {
        this.assistance = assistance;
    }

    public void setPayment(SimpleStringProperty payment) {
        this.payment = payment;
    }
    
    public ObservableValue<String> assistanceProperty() {
            return this.assistance;
    }
    public ObservableValue<String> paymentProperty() {
            return this.payment;
    }
    
    public ObservableValue<Number> idYearProperty() throws SQLException, ClassNotFoundException{
            return Controller.getController().getYear(idYear).getYear();
    }
    public ObservableValue<String> idMonthProperty() throws SQLException, ClassNotFoundException{
            return Controller.getController().getMonth(idMonth).getMonth();
    }
    
		
}
