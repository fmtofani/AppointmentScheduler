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
public class Customer {
    
    private final SimpleIntegerProperty customerId;
    private final SimpleIntegerProperty customerAddressId;
    private final SimpleStringProperty customerName;
    private final SimpleIntegerProperty customerActive;
    private final SimpleStringProperty customerPhone;
    private final SimpleStringProperty customerAddress;
    private final SimpleStringProperty customerAddress2;
    private final SimpleStringProperty customerZipcode;
    private final SimpleStringProperty customerCity;
    private final SimpleStringProperty customerCountry;


    public Customer() {
        customerId = new SimpleIntegerProperty();
        customerAddressId = new SimpleIntegerProperty();
        customerName = new SimpleStringProperty();
        customerActive = new SimpleIntegerProperty();
        customerPhone = new SimpleStringProperty();
        customerAddress = new SimpleStringProperty();
        customerAddress2 = new SimpleStringProperty();
        customerZipcode = new SimpleStringProperty();
        customerCity = new SimpleStringProperty();
        customerCountry = new SimpleStringProperty();
    }   

    
    /*
    *
    *
    *************** Getters ***************
    *
    *
    */
    
    public int getCustomerId() {
        return customerId.get();
    }

    public int getCustomerAddressId() {
        return customerAddressId.get();
    }
    public String getCustomerName() {
        return customerName.get();
    }

    public int getCustomerActive() {
        return customerActive.get();
    }

    public String getCustomerPhone() {
        return customerPhone.get();
    }
    
    public String getCustomerAddress() {
        return customerAddress.get();
    }
    
    public String getCustomerAddress2() {
        return customerAddress2.get();
    }
    
    public String getCustomerZipcode() {
        return customerZipcode.get();
    }
    
    public String getCustomerCity() {
        return customerCity.get();
    }
    
    public String getCustomerCountry() {
        return customerCountry.get();
    }
    
    /*
    *
    *
    *************** Setters ***************
    *
    *
    */
    
    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }
    
    public void setCustomerAddressId(int customerAddressId) {
        this.customerAddressId.set(customerAddressId);
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public void setCustomerActive(int active) {
        this.customerActive.set(active);
    }
    
    public void setCustomerPhone(String phone) {
        this.customerPhone.set(phone);
    }
    
    public void setCustomerAddress(String address) {
        this.customerAddress.set(address);
    }
    
    public void setCustomerAddress2(String address2) {
        this.customerAddress2.set(address2);
    }

    public void setCustomerZipcode(String zipcode) {
        this.customerZipcode.set(zipcode);
    }
    
    public void setCustomerCity(String city) {
        this.customerCity.set(city);
    }
    
    public void setCustomerCountry(String country) {
        this.customerCountry.set(country);
    }
    
    /*
    *
    *
    *************** Properties ***************
    *
    *
    */

    public IntegerProperty customerIdProperty() {
        return customerId;
    }
    
    public IntegerProperty customerAddressIdProperty() {
            return customerAddressId;
    }
    
    public StringProperty customerNameProperty() {
        return customerName;
    }
    
    public IntegerProperty customerActiveProperty() {
        return customerActive;
    }
    
    public StringProperty customerPhoneProperty() {
        return customerPhone;
    }
    
    public StringProperty customerAddressProperty() {
        return customerAddress;
    }
    
    public StringProperty customerAddress2Property() {
        return customerAddress2;
    }
    
    public StringProperty customerZipcodeProperty() {
        return customerZipcode;
    }
    
    public StringProperty customerCityProperty() {
        return customerCity;
    }
    
    public StringProperty customerCountryProperty() {
        return customerCountry;
    }
    
    //Methods to verify proper construction
    public static boolean isValidCustomer(String name, String address, String phone, String city, String country, String zip) {
        if(name.equals("") || address.equals("") || phone.equals("") || city.equals("") || country.equals("") || zip.equals("")) {
            return false;
        }
        return true;
    }
    
    
}
