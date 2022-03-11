package model;

import javafx.beans.property.SimpleStringProperty;

public class Month {
	private int idMonth;
	private SimpleStringProperty month= new SimpleStringProperty();
	
	public Month(int idMonth, SimpleStringProperty month) {
		
		this.idMonth = idMonth;
		this.month = month;
	}
        public Month(int idMonth, String month) {
		
		this.idMonth = idMonth;
		this.month.setValue(month);
	}
	
	public int getIdMonth() {
		return idMonth;
	}
	public void setIdMonth(int idMonth) {
		this.idMonth = idMonth;
	}
	public SimpleStringProperty getMonth() {
		return month;
	}
	public void setMonth(SimpleStringProperty month) {
		this.month = month;
	}
		
}
