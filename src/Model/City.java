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
public class City {
    
    private final SimpleIntegerProperty cityId;
    private final SimpleStringProperty city;
//    private Country country;


    public City() {
        cityId = new SimpleIntegerProperty();
        city = new SimpleStringProperty();
//        this.country = country;
    }

    
    /*
    *
    *
    ***************Getters ***************
    *
    *
    */
    
    public int getCityId() {
        return this.cityId.get();
    }

    public String getCity() {
        return this.city.get();
    }
/*
    public Country getCountry() {
        return country;
    }
*/
    
        
    /*
    *
    *
    *************** Setters ***************
    *
    *
    */
    
    
    public void setCityId(int cityId) {
        this.cityId.set(cityId);
    }

    public void setCity(String city) {
        this.city.set(city);
    }
/*
    public void setCountry(Country country) {
        this.country = country;
    }
*/  
    

    /*
    *
    *
    *************** Properties ***************
    *
    *
    */

        public IntegerProperty cityIdProperty() {
            return cityId;
        }
        
        public StringProperty cityProperty() {
            return city;
        }
}
