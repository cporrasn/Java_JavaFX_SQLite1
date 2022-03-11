package model;

import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Year {
	private int idYear;
	private SimpleIntegerProperty year= new SimpleIntegerProperty();
	
	public Year(int idYear, SimpleIntegerProperty year) {
		
		this.idYear = idYear;
		this.year = year;
	}
        public Year(int idYear, int year) {
		
		this.idYear = idYear;
		this.year.setValue(year);
	}

	public int getIdYear() {
		return idYear;
	}

	public void setIdYear(int idYear) {
		this.idYear = idYear;
	}

	public SimpleIntegerProperty getYear() {
		return year;
	}

	public void setYear(SimpleIntegerProperty year) {
		this.year = year;
	}
        
        public ObservableValue<Number> idYearProperty() throws SQLException, SQLException, ClassNotFoundException{
            return Controller.getController().getYear(idYear).getYear();
    }
	
}
