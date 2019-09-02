/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package Model;

import java.time.LocalDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author micha
 */
public class Country {
    
    private final IntegerProperty countryId;
    private final StringProperty country;


    public Country() {
        IntegerProperty newSimpleIntegerProperty;
        countryId = new SimpleIntegerProperty();
        country = new SimpleStringProperty();
    }
      
    
    /*
    *
    *
    *************** Getters ***************
    *
    *
    */
    
    public int getCountryId() {
        return this.countryId.get();
    }

    public String getCountry() {
        return this.country.get();
    }

    
    /*
    *
    *
    *************** Setters ***************
    *
    *
    */
    
    public void setCountryId(int countryId) {
        this.countryId.set(countryId);
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    
    /*
    *
    *
    *************** Properties ***************
    *
    *
    */
    
    public IntegerProperty countryIdProperty() {
        return countryId;
    }
    
    public StringProperty countryProperty() {
        return country;
    }
    
    
}
