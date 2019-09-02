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
public class Appointment {
    
    private final IntegerProperty appointmentId;
    private final IntegerProperty customerId;
    private final StringProperty customerName;
    private final IntegerProperty userId;
    private final StringProperty userName;
    private final StringProperty title;
    private final StringProperty description;
    private final StringProperty location;
    private final StringProperty contact;
    private final StringProperty type;
    private final StringProperty url;
    private final StringProperty date;
    private final StringProperty start;
    private final StringProperty end;
    
    public Appointment() {
        appointmentId = new SimpleIntegerProperty();
        customerId = new SimpleIntegerProperty();
        customerName = new SimpleStringProperty();
        userId = new SimpleIntegerProperty();
        userName = new SimpleStringProperty();
        title = new SimpleStringProperty();
        description = new SimpleStringProperty();
        location = new SimpleStringProperty();
        contact = new SimpleStringProperty();
        type = new SimpleStringProperty();
        url = new SimpleStringProperty();
        date = new SimpleStringProperty();
        start = new SimpleStringProperty();
        end = new SimpleStringProperty();
    }
    
    /*
    *
    *
    *************** Setters ***************
    *
    *
    */
    
    public void setAppointmentId(int appointmentId) {
        this.appointmentId.set(appointmentId);
    }
    
    public void setCustomerId (int customerId) {
        this.customerId.set(customerId);
    }
    
    public void setCustomerName (String customerName) {
        this.customerName.set(customerName);
    }
    
    public void setUserId(int userId) {
        this.userId.set(userId);
    }
    
    public void setUserName(String userName) {
        this.userName.set(userName);
    }
    public void setTitle(String title) {
        this.title.set(title);
    }
    
    public void setDescription(String description) {
        this.description.set(description);
    }
    
    public void setLocation(String location) {
        this.location.set(location);
    }
    
    public void setContact(String contact) {
        this.contact.set(contact);
    }
    
    public void setType(String type) {
        this.type.set(type);
    }
    
    public void setUrl(String url) {
        this.url.set(url);
    }
    
    public void setDate(String date) {
        this.date.set(date);
    }
    
    public void setStart(String start) {
        this.start.set(start);
    }
    
    public void setEnd(String end) {
        this.end.set(end);
    }
    

    /*
    *
    *
    *************** Getters ***************
    *
    *
    */
    
    public int getAppointmentId() {
        return this.appointmentId.get();
    }
    
    public int getCustomerId() {
        return this.customerId.get();
    }
    
    public String getCustomerName() {
        return this.customerName.get();
    }
    
    public int getUserId() {
        return this.userId.get();
    }
    
    public String getUserName() {
        return this.userName.get();
    }
    
    public String getTitle() {
        return this.title.get();
    }
    
    public String getDescription() {
        return this.description.get();
    }
    
    public String getLocation() {
        return this.location.get();
    }
    
    public String getContact() {
        return this.contact.get();
    }
       
    public String getType() {
        return this.type.get();
    }
    
    public String getUrl() {
        return this.url.get();
    }
    
    public String getDate() {
        return this.date.get();
    }
    
    public String getStart() {
        return this.start.get();
    }
    
    public String getEnd() {
        return this.end.get();
    }



    /*
    *
    *
    *************** Properties ***************
    *
    *
    */

    public IntegerProperty appointmentIdProperty() {
        return appointmentId;
    }
    
    public IntegerProperty customerIdProperty() {
        return customerId;
    }
    
    public StringProperty customerNameProperty() {
        return customerName;
    }
    
    public IntegerProperty userIdProperty() {
        return userId;
    }
    
    public StringProperty userNameProperty() {
        return userName;
    }
    
    public StringProperty titleProperty() {
        return title;
    }
    
    public StringProperty descriptionProperty() {
        return description;
    }
    
    public StringProperty locationProperty() {
        return location;
    }
    
    public StringProperty contactProperty() {
        return contact;
    }
        
    public StringProperty typeProperty() {
        return type;
    }
    
    public StringProperty urlProperty() {
        return url;
    }
    
    public StringProperty dateProperty() {
        return date;
    }
    
    public StringProperty startProperty() {
        return start;
    }
    
    public StringProperty endProperty() {
        return end;
    }
    
}
