/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Report {
    
    private final StringProperty date;
    private final StringProperty start;
    private final StringProperty userName;
    private final StringProperty customerName;
    private final StringProperty type;
    private final StringProperty time;
    
    public Report() {
        date = new SimpleStringProperty();
        start = new SimpleStringProperty();
        userName = new SimpleStringProperty();
        customerName = new SimpleStringProperty();
        type = new SimpleStringProperty();
        time = new SimpleStringProperty();
    }
    
    /*
    *
    *
    *************** Getters ***************
    *
    *
    */

    public String getDate() {
        return this.date.get();
    }
    
    public String getStart() {
        return this.start.get();
    }
    
    public String getUserName() {
        return this.userName.get();
    }
    
    public String getCustomerName() {
        return this.customerName.get();
    }
    
    public String getType() {
        return this.type.get();
    }
    
    public String getTime() {
        return this.time.get();
    }
        
    /*
    *
    *
    *************** Setters ***************
    *
    *
    */

    public void setDate(String date) {
        this.date.set(date);
    }
    
    public void setStart(String start) {
        this.start.set(start);
    }
    
    public void setUserName(String userName) {
        this.userName.set(userName);
    }
    
    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }
    
    public void setType(String type) {
        this.type.set(type);
    }
    
    public void setTime(String time) {
        this.time.set(time);
    }
    
    /*
    *
    *
    *************** Properties ***************
    *
    *
    */
    
    public StringProperty dateProperty() {
        return date;
    }
    
    public StringProperty startProperty() {
        return start;
    }
    
    public StringProperty userNameProperty() {
        return userName;
    }
    
    public StringProperty customerNameProperty() {
        return customerName;
    }
    
    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty timeProperty() {
        return time;
    }
    
    
//End Class    
}
