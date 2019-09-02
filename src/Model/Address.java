/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author micha
 */
public class Address {

    private final IntegerProperty addressId;
    private final StringProperty address;
    private final StringProperty address2;
//    private City city;
    private final StringProperty postalCode;
    private final StringProperty phone;

    public Address() {
        addressId = new SimpleIntegerProperty();
        address = new SimpleStringProperty();
        address2 = new SimpleStringProperty();
//enable        this.city = city;
        postalCode = new SimpleStringProperty();
        phone = new SimpleStringProperty();
     }
    
    /*
    *
    *
    *************** Getters ***************
    *
    *
    */
    
    public int getAddressId() {
        return this.addressId.get();
    }

    public String getAddress() {
        return this.address.get();
    }

    public String getAddress2() {
        return this.address2.get();
    }
/*
    public City getCity() {
        return this.city;
    }
*/
    public String getPostalCode() {
        return this.postalCode.get();
    }

    public String getPhone() {
        return this.phone.get();
    }

    
    /*
    *
    *
    *************** Setters ***************
    *
    *
    */
    
    
    public void setAddressId(int addressId) {
        this.addressId.set(addressId);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setAddress2(String address2) {
        this.address2.set(address2);
    }

/*
    public void setCity(City city) {
        this.city = city;
    }
*/
    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    

    /*
    *
    *
    *************** Properties ***************
    *
    *
    */
   
    
    public IntegerProperty addressIdProperty() {
        return addressId;
    }
    
    public StringProperty addressProperty() {
        return address;
    }
    
    public StringProperty address2Property() {
        return address2;
    }
    
    public StringProperty postalCodeProperty() {
        return postalCode;
    }
    
    public StringProperty phoneProperty() {
        return phone;
    }
    
}
