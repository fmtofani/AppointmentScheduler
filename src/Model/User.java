/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author micha
 */
public class User {
    private final SimpleIntegerProperty userId;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty password;
    private final SimpleIntegerProperty active;


    public User(){   
        userId = new SimpleIntegerProperty();
        userName = new SimpleStringProperty();
        password = new SimpleStringProperty();
        active = new SimpleIntegerProperty();
    }

    
    /* 
    *
    *
    ************ Setters ************
    *
    *
    */
    
    public void setUserId(int userId) {
        this.userId.set(userId);
    }
    
    public void setUserName(String userName) {
        this.userName.set(userName);
    }
    
    public void setPassword(String password) {
        this.password.set(password);
    }
    
    public void setActive(int active) {
        this.active.set(active);
    }
    
    
    /* 
    *
    *
    ************ Getters ************
    *
    *
    */
    
    public int getUserId() {
        return this.userId.get();
    }
    
    public String getUserName() {
        return this.userName.get();
    }
    
    public String getPassword() {
        return this.password.get();
    }
    
    public int getActive() {
        return this.active.get();
    }
    

    /*
    *
    *
    *************** Properties ***************
    *
    *
    */

    public IntegerProperty userIdProperty() {
        return userId;
    }
    
    public StringProperty userNameProperty() {
        return userName;
    }
    
    public StringProperty passwordProperty() {
        return password;
    }
    
    public IntegerProperty activeProperty() {
        return active;
    }
    
}