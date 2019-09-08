/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package Model;

import Util.DatabaseConnect;
import Util.TimeUtil;
import View.AppointmentController;
import View.LoginController;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author micha
 */
public class AccessDB {
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static ObservableList<Customer> searchClient = FXCollections.observableArrayList();
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<City> allCities = FXCollections.observableArrayList();
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> selectedAppointment = FXCollections.observableArrayList();
    private static ObservableList<Appointment> weekAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> monthAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> dailyAppointments = FXCollections.observableArrayList();
 //   private static ObservableList<Appointment> alertAppointment = FXCollections.observableArrayList();
    private static ObservableList<Customer> addClientAppointment = FXCollections.observableArrayList();
    private static ObservableList<Report> report1 = FXCollections.observableArrayList();
    private static ObservableList<Report> report2 = FXCollections.observableArrayList();
    private static ObservableList<Report> report3 = FXCollections.observableArrayList();

    //This will be used to make sure that doubles are not added to the database. I realize that it doesn't need to be observable. It was just easy to implement.
    public static ObservableList<City> allCities() {
        try {
            allCities.clear();
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT cityId, city " + 
                                                       "FROM city");
            while(results.next()) {
                City c = new City();
                     c.setCityId(results.getInt("cityId"));
                     c.setCity(results.getString("city"));
                allCities.add(c);
            }
            statement.close();
            return allCities;
        } catch (SQLException ex) {
            System.out.println("Error building User List \n Error: " + ex.getMessage());
            return null;
        }
    }
    
    //This will be used to make sure doubles are not added to the database.=
    public static ObservableList<Country> allCountries() {
        try {
            allCountries.clear();
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT countryId, country " + 
                                                       "FROM country");
            while(results.next()) {
                Country c = new Country();
                     c.setCountryId(results.getInt("countryId"));
                     c.setCountry(results.getString("country"));
                allCountries.add(c);
            }
            statement.close();
            return allCountries;
        } catch (SQLException ex) {
            System.out.println("Error building User List \n Error: " + ex.getMessage());
            return null;
        }
    }

    //This String is used to go back to the proper module when adding a client
    public static String comeFrom;

    
    /*
    *
    *
    *********** USER METHODS ***********
    *
    *
    */
    
   
    public static ObservableList<User> allUsers() {
        try {
            allUsers.clear();
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT userId, userName, password " + 
                                                       "FROM user");
            while(results.next()) {
                User u = new User();
                     u.setUserId(results.getInt("userId"));
                     u.setUserName(results.getString("userName"));
                     u.setPassword(results.getString("password"));
                allUsers.add(u);
            }
            statement.close();
            return allUsers;
        } catch (SQLException ex) {
            System.out.println("Error building User List \n Error: " + ex.getMessage());
            return null;
        }
    }
   
    public static void addUser(User u) {
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            statement.executeUpdate("INSERT INTO user VALUES (null, '" + u.getUserName() + "','" + u.getPassword() + "', '1', '" + TimeUtil.getUTCTime() + "', 'test', '" + TimeUtil.getUTCTime() + "', 'test')");
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Add user failed \n Error: " + ex.getMessage());
        }
    }    
    
    public static void modifyUser(User u) {
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            statement.executeUpdate("UPDATE user SET userName='" + u.getUserName() + "',password='" + u.getPassword() + "' WHERE userId='" + u.getUserId() + "'");
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Modify user failed \n Error: " + ex.getMessage());
        }
    }
    
    public static void deleteUser(User u) {
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            statement.executeUpdate("DELETE FROM user WHERE userId= '" + u.getUserId() + "'"); 
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Delete user failed \n Error: " + ex.getMessage());
        }
    }
    
    
    
    
    /*
    *
    *
    ***********  CUSTOMER METHODS ***********
    *
    *
    */
    
    
    public static ObservableList<Customer> allCustomers() {
        allCustomers.clear();
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT customer.customerId, customer.customerName, address.phone, address.address, address.address2, address.postalCode, city.city, country.country " +
                                                       "FROM customer, address, city, country " +
                                                       "WHERE customer.addressId = address.addressId AND address.cityId = city.cityId AND city.countryId = country.countryId;");
            while(results.next()) {
                Customer c = new Customer();
                    c.setCustomerId(results.getInt("customer.customerId"));
                    c.setCustomerName(results.getString("customer.customerName"));
                    c.setCustomerPhone(results.getString("address.phone"));
                    c.setCustomerAddress(results.getString("address.address"));
                    c.setCustomerAddress2(results.getString("address.address2"));
                    c.setCustomerZipcode(results.getString("address.postalCode"));
                    c.setCustomerCity(results.getString("city.city"));
                    c.setCustomerCountry(results.getString("country.country"));
                allCustomers.add(c);
            }
            statement.close();
            return allCustomers;
        } catch (SQLException ex) {
            System.out.println("Error building Customer List \n Error: " + ex.getMessage());
            return null;
        }
    }

    public static ObservableList<Customer> searchClient(Customer client) {
        try {
            searchClient.clear();
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT customerName FROM customer WHERE customerName='" + client.getCustomerName() + "';");
            while (results.next()) {
                Customer c = new Customer();
                c.setCustomerId(results.getInt("customerId"));
                c.setCustomerName(results.getString("customerName"));
                searchClient.add(c);
            }
            statement.close();
            return searchClient;
        } catch (SQLException ex) {
            System.out.println("Error finding client \n Error:" + ex.getMessage());
            return null;
        }
    }

    //Check to see if country exists to prevent doubles in database
    public static int countryExists(String name) {
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT countryID FROM country WHERE country = '" + name + "';");
            while(results.next()) {
                return results.getInt("countryId");
            }
            statement.close();
            return -1;
        } catch (SQLException ex) {
            System.out.println("Error checking for country \n Error: " + ex.getMessage());
            return -1; 
        }
    }
    
    //Check to see if city exists to prevent doubles in database
    public static int cityExists(String name) {
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT cityId FROM city WHERE city = '" + name + "';");
            while (results.next()) {
                return results.getInt("cityId");
            }
            statement.close();
            return -1;
        } catch (SQLException ex) {
            System.out.println("Error checking for city \n Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public static int addressIdExists(int id) {
        //This method will check to see if any other customers are using the same addressId as it is possible to have the same address and phone number as other customers. 
        //A better field to have in the customer table would be an email address as this would more than likely be unique to each customer. This method is for customerDelete()
        System.out.println("addressIdExists Method");
        try {
            int counter = 0;
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT addressId FROM customer WHERE addressId = '" + id + "';");
            while(results.next()) {
                if(results.getInt("addressId") == id){
                    counter += 1;
                }                
            }
            statement.close();
            return counter;
        } catch (SQLException ex) {
            System.out.println("Error checking for addressExists \n Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public static int addressIdFind(String address, String address2, String phone) {
        //This method will check to see if any other customers are using the same addressId as it is possible to have the same address and phone number as other customers. 
        //A better field to have in the customer table would be an email address as this would more than likely be unique to each customer. This method is for customerAdd()
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM address WHERE address='" + address + "' AND address2='" + address2 + "' AND phone ='" + phone +"';");
            while(results.next()) {
                if (results.getString("address").equals("") && results.getString("address2").equals("") && results.getString("phone").equals("")) {
                  return -1;
                } else {
                    return results.getInt("addressId");
                }
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error checking for addressIdFind \n Error: " + ex.getMessage());
        }
        return -1;
    }
    
    public static void modifyCustomer(Customer c) {

        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            int i = countryExists(c.getCustomerCountry());
            int i2 = cityExists(c.getCustomerCity());
            int i3 = addressIdFind(c.getCustomerAddress(), c.getCustomerAddress2(), c.getCustomerPhone());
            
            if(i == -1) {
                statement.executeUpdate("INSERT INTO country VALUES (null, '" + c.getCustomerCountry() + "','" + 
                                         TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "','" + TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "');");
                i = countryExists(c.getCustomerCountry());
            }
            if(i2 == -1) {
                statement.executeUpdate("INSERT INTO city VALUES (null, '" + c.getCustomerCity() + "','" + i + "','" +
                                         TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "','" + TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "');");
                i2 = cityExists(c.getCustomerCity());
            }
            if(i3 == -1) {
                statement.executeUpdate("INSERT INTO address VALUES (null, '" + c.getCustomerAddress() + "','" + c.getCustomerAddress2() + "','" + i2 + "','" + c.getCustomerZipcode() + "','" + c.getCustomerPhone() 
                                        + "','" + TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "','" + TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "');");
                i3 = addressIdFind(c.getCustomerAddress(), c.getCustomerAddress2(), c.getCustomerPhone());
            }
            
            statement.executeUpdate("UPDATE customer SET customerName='" + c.getCustomerName() + "',addressId='" + i3 + "',lastUpdate='"+ TimeUtil.getUTCTime() + "',lastUpdateBy='" + LoginController.getCurrentUser() + 
                                    "' WHERE customerId=" + c.getCustomerId() + ";");
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error adding customer \n Error: " + ex.getMessage());
        }        
    }
    
    public static void deleteCustomer(Customer c) {
        //make sure no one else is using addressId
        try {
            System.out.println("Deleting Customer: " + c.getCustomerName());
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            statement.executeUpdate("DELETE FROM customer WHERE customer.customerId= '" + c.getCustomerId() + "'");
            //Check to be sure this is the only use of the address in the table
            if (addressIdExists(c.getCustomerAddressId()) == 1) {
                statement.executeUpdate("DELETE FROM address WHERE addressId='" + c.getCustomerAddressId() + "';");
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Delete user failed \n Error: " + ex.getMessage());
        }
    }
    
    public static void addCustomer(Customer c) throws SQLException {
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            int i = countryExists(c.getCustomerCountry());
            int i2 = cityExists(c.getCustomerCity());
            int i3 = addressIdFind(c.getCustomerAddress(), c.getCustomerAddress2(), c.getCustomerPhone());

            if(i == -1) {
                statement.executeUpdate("INSERT INTO country VALUES (null, '" + c.getCustomerCountry() + "','" + 
                                         TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "','" + TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "');");
                i = countryExists(c.getCustomerCountry());
            }
            if(i2 == -1) {
                statement.executeUpdate("INSERT INTO city VALUES (null, '" + c.getCustomerCity() + "','" + i + "','" +
                                         TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "','" + TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "');");
                i2 = cityExists(c.getCustomerCity());
            }
            if(i3 == -1) {
                statement.executeUpdate("INSERT INTO address VALUES (null, '" + c.getCustomerAddress() + "','" + c.getCustomerAddress2() + "','" + i2 + "','" + c.getCustomerZipcode() + "','" + c.getCustomerPhone() 
                                        + "','" + TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "','" + TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "');");
                i3 = addressIdFind(c.getCustomerAddress(), c.getCustomerAddress2(), c.getCustomerPhone());
            }
            
            statement.executeUpdate("INSERT INTO customer VALUES (null,'" + c.getCustomerName() + "','" + i3 + "','1','" +
                                     TimeUtil.getUTCTime() + "','"  + LoginController.getCurrentUser() + "','" + TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "');");
                
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error adding customer \n Error: " + ex.getMessage());
        }
        
    }
    
  
    
    /*
    *
    *
    *********** APPOINTMENT METHODS ***********
    *
    *
    */
        
    public static void addAppointment(Appointment a) throws SQLException {
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            statement.executeUpdate("INSERT INTO appointment VALUES (null, + " + a.getCustomerId() + "," + a.getUserId() + 
                                    ",'" + a.getTitle() + "','" + a.getDescription() + "','" + a.getLocation() + "', 'not needed', '" + 
                                    a.getType() + "', 'not needed', '" + a.getStart() + "','" + a.getEnd() + "','" + TimeUtil.getUTCTime() +
                                    "','" + LoginController.getCurrentUser() + "','" + TimeUtil.getUTCTime() + "','" + LoginController.getCurrentUser() + "');");
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error adding appointment \n Error: " + ex.getMessage());
        }
    }
    
    public static void alertAppointment() {
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT appointment.start FROM appointment WHERE appointment.userId = " + LoginController.getcurrentUserId()+" ORDER BY appointment.start ASC;");
            LocalDateTime now = LocalDateTime.now();
            now = now.minusMinutes(TimeUtil.getOffset());
            while(results.next()) {
                Appointment a = new Appointment();
                a.setStart(results.getString("appointment.start"));
                LocalDateTime check = LocalDateTime.parse(a.getStart().substring(0, 10).concat("T").concat(a.getStart().substring(11,19)));
                //Check for 15 minutes in advance
                for(int i=0; i <= 15; i++) {
                    if(now.equals(check.plusMinutes(i))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Appointment Reminder");
                        alert.setHeaderText("Appointment Reminder");
                        alert.setContentText("You have an appointment within 15 minutes"); 
                        alert.showAndWait();
                        break;
                    }
                }
             }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error building Appointment List \n Error: " + ex.getMessage());
        }
    }

    public static ObservableList<Appointment> weekAppointments(LocalDate dateWeek) {
        weekAppointments.clear();
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT appointment.appointmentId, appointment.customerId, customer.customerName, appointment.userId, user.userName, appointment.title, appointment.description, appointment.location," +
                                                       " appointment.contact, appointment.type, appointment.url, appointment.start, appointment.end" +
                                                       " FROM appointment, customer, user" +
                                                       " WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId"
                                                       + " ORDER BY appointment.start ASC;");
            while(results.next()) {
                Appointment a = new Appointment();
                    String startTime = TimeUtil.stringToString(results.getString("appointment.start"), "time");
                    LocalTime ltStart = LocalTime.parse(startTime).minusMinutes(TimeUtil.getOffset());
                    startTime = ltStart.toString();
                    String endTime = TimeUtil.stringToString(results.getString("appointment.end"), "time"); 
                    LocalTime ltEnd = LocalTime.parse(endTime).minusMinutes(TimeUtil.getOffset());
                    endTime = ltEnd.toString();
                    String date = TimeUtil.stringToString(results.getString("appointment.start"), "date");
                    LocalDate ld = LocalDate.parse(date);
                    if(ld.equals(dateWeek) ||  ld.equals(dateWeek.plusDays(1)) || ld.equals(dateWeek.plusDays(2)) ||  ld.equals(dateWeek.plusDays(3)) ||  ld.equals(dateWeek.plusDays(4)) ||  ld.equals(dateWeek.plusDays(5)) ||  ld.equals(dateWeek.plusDays(6))) {
System.out.println("accessdb date: " + ld);                        a.setAppointmentId(results.getInt("appointment.appointmentId"));
                        a.setCustomerId(results.getInt("appointment.customerId"));
                        a.setCustomerName(results.getString("customer.customerName"));
                        a.setUserId(results.getInt("appointment.userId"));
                        a.setUserName(results.getString("user.userName"));
                        a.setTitle(results.getString("appointment.title"));
                        a.setDescription(results.getString("appointment.description"));
                        a.setLocation(results.getString("appointment.location"));
                        a.setContact(results.getString("appointment.contact"));
                        a.setType(results.getString("appointment.type"));
                        a.setUrl(results.getString("appointment.url"));
                        a.setDate(date);
                        a.setStart(startTime);
                        a.setEnd(endTime);
                        weekAppointments.add(a);
                    }
            }
            statement.close();
            return weekAppointments;
        } catch (SQLException ex) {
            System.out.println("Error building Appointment List \n Error: " + ex.getMessage());
            return null;
        }
    }
    
        public static ObservableList<Appointment> allAppointments() {
        allAppointments.clear();
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT appointment.appointmentId, appointment.customerId, customer.customerName, appointment.userId, user.userName, appointment.title, appointment.description, appointment.location," +
                                                       " appointment.contact, appointment.type, appointment.url, appointment.start, appointment.end" +
                                                       " FROM appointment, customer, user" +
                                                       " WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId "
                                                       + "ORDER BY appointment.start ASC;");
            while(results.next()) {
                Appointment a = new Appointment();
                    String date = TimeUtil.stringToString(results.getString("appointment.start"), "date");
                    String startTime = TimeUtil.stringToString(results.getString("appointment.start"), "time");
                    LocalTime ltStart = LocalTime.parse(startTime).minusMinutes(TimeUtil.getOffset());
                    startTime = ltStart.toString();
                    String endTime = TimeUtil.stringToString(results.getString("appointment.end"), "time"); 
                    LocalTime ltEnd = LocalTime.parse(endTime).minusMinutes(TimeUtil.getOffset());
                    endTime = ltEnd.toString();
                    a.setAppointmentId(results.getInt("appointment.appointmentId"));
                    a.setCustomerId(results.getInt("appointment.customerId"));
                    a.setCustomerName(results.getString("customer.customerName"));
                    a.setUserId(results.getInt("appointment.userId"));
                    a.setUserName(results.getString("user.userName"));
                    a.setTitle(results.getString("appointment.title"));
                    a.setDescription(results.getString("appointment.description"));
                    a.setLocation(results.getString("appointment.location"));
                    a.setContact(results.getString("appointment.contact"));
                    a.setType(results.getString("appointment.type"));
                    a.setUrl(results.getString("appointment.url"));
                    a.setDate(date);
                    a.setStart(startTime);
                    a.setEnd(endTime);
                    allAppointments.add(a);
            }
            statement.close();
            return allAppointments;
        } catch (SQLException ex) {
            System.out.println("Error building Appointment List \n Error: " + ex.getMessage());
            return null;
        }
    }

    public static ObservableList<Appointment> monthAppointments(LocalDate dateMonth) {
        monthAppointments.clear();
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT appointment.appointmentId, appointment.customerId, customer.customerName, appointment.userId, user.userName, appointment.title, appointment.description, appointment.location," +
                                                       " appointment.contact, appointment.type, appointment.url, appointment.start, appointment.end" +
                                                       " FROM appointment, customer, user" +
                                                       " WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId "
                                                       + "ORDER BY appointment.start ASC;");
            while(results.next()) {
                Appointment a = new Appointment();
                    String startTime = TimeUtil.stringToString(results.getString("appointment.start"), "time");
                    LocalTime ltStart = LocalTime.parse(startTime).minusMinutes(TimeUtil.getOffset());
                    startTime = ltStart.toString();
                    String endTime = TimeUtil.stringToString(results.getString("appointment.end"), "time"); 
                    LocalTime ltEnd = LocalTime.parse(endTime).minusMinutes(TimeUtil.getOffset());
                    endTime = ltEnd.toString();
                    String date = TimeUtil.stringToString(results.getString("appointment.start"), "date");
                    LocalDate ld = LocalDate.parse(date);
                    if(ld.getMonth() == dateMonth.getMonth()) {
                        a.setAppointmentId(results.getInt("appointment.appointmentId"));
                        a.setCustomerId(results.getInt("appointment.customerId"));
                        a.setCustomerName(results.getString("customer.customerName"));
                        a.setUserId(results.getInt("appointment.userId"));
                        a.setUserName(results.getString("user.userName"));
                        a.setTitle(results.getString("appointment.title"));
                        a.setDescription(results.getString("appointment.description"));
                        a.setLocation(results.getString("appointment.location"));
                        a.setContact(results.getString("appointment.contact"));
                        a.setType(results.getString("appointment.type"));
                        a.setUrl(results.getString("appointment.url"));
                        a.setDate(date);
                        a.setStart(startTime);
                        a.setEnd(endTime);
                        monthAppointments.add(a);
                    }
            }
            statement.close();
            return monthAppointments;
        } catch (SQLException ex) {
            System.out.println("Error building Appointment List \n Error: " + ex.getMessage());
            return null;
        }
    }

    public static ObservableList<Appointment> dailyAppointments(String dateDay) {
        dailyAppointments.clear();
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT appointment.appointmentId, appointment.customerId, customer.customerName, appointment.userId, user.userName, appointment.title, appointment.description, appointment.location," +
                                                       " appointment.contact, appointment.type, appointment.url, appointment.start, appointment.end" +
                                                       " FROM appointment, customer, user" +
                                                       " WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId"
                                                       + " ORDER BY appointment.start ASC;");
            while(results.next()) {
                Appointment a = new Appointment();
                    String startTime = TimeUtil.stringToString(results.getString("appointment.start"), "time");
                    LocalTime ltStart = LocalTime.parse(startTime).minusMinutes(TimeUtil.getOffset());
                    startTime = ltStart.toString();
                    String endTime = TimeUtil.stringToString(results.getString("appointment.end"), "time"); 
                    LocalTime ltEnd = LocalTime.parse(endTime).minusMinutes(TimeUtil.getOffset());
                    endTime = ltEnd.toString();
                    String date = TimeUtil.stringToString(results.getString("appointment.start"), "date");
                    if(date.equals(dateDay)) {
                        a.setAppointmentId(results.getInt("appointment.appointmentId"));
                        a.setCustomerId(results.getInt("appointment.customerId"));
                        a.setCustomerName(results.getString("customer.customerName"));
                        a.setUserId(results.getInt("appointment.userId"));
                        a.setUserName(results.getString("user.userName"));
                        a.setTitle(results.getString("appointment.title"));
                        a.setDescription(results.getString("appointment.description"));
                        a.setLocation(results.getString("appointment.location"));
                        a.setContact(results.getString("appointment.contact"));
                        a.setType(results.getString("appointment.type"));
                        a.setUrl(results.getString("appointment.url"));
                        a.setDate(date);
                        a.setStart(startTime);
                        a.setEnd(endTime);
                        dailyAppointments.add(a);
                    }
            }
            statement.close();
            return dailyAppointments;
        } catch (SQLException ex) {
            System.out.println("Error building Appointment List \n Error: " + ex.getMessage());
            return null;
        }
    }

    public static ObservableList<Appointment> selectedAppointment() {
        selectedAppointment.clear();
        int apptId = AppointmentController.selectedAppointment.getAppointmentId();
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT appointment.appointmentId, appointment.customerId, customer.customerName, appointment.userId, user.userName, appointment.title, appointment.description, appointment.location," +
                                                       " appointment.contact, appointment.type, appointment.url, appointment.start, appointment.end" +
                                                       " FROM appointment, customer, user" +
                                                       " WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId AND appointment.appointmentId=" + apptId +";");
     System.out.println(apptId);
            while(results.next()) {
                Appointment a = new Appointment();
                    String date = TimeUtil.stringToString(results.getString("appointment.start"), "date");
                    String startTime = TimeUtil.stringToString(results.getString("appointment.start"), "time");
                    LocalTime ltStart = LocalTime.parse(startTime).minusMinutes(TimeUtil.getOffset());
                    startTime = ltStart.toString();
                    String endTime = TimeUtil.stringToString(results.getString("appointment.end"), "time"); 
                    LocalTime ltEnd = LocalTime.parse(endTime).minusMinutes(TimeUtil.getOffset());
                    endTime = ltEnd.toString();
                    a.setAppointmentId(results.getInt("appointment.appointmentId"));
                    a.setCustomerId(results.getInt("appointment.customerId"));
                    a.setCustomerName(results.getString("customer.customerName"));
                    a.setUserId(results.getInt("appointment.userId"));
                    a.setUserName(results.getString("user.userName"));
                    a.setTitle(results.getString("appointment.title"));
                    a.setDescription(results.getString("appointment.description"));
                    a.setLocation(results.getString("appointment.location"));
                    a.setContact(results.getString("appointment.contact"));
                    a.setType(results.getString("appointment.type"));
                    a.setUrl(results.getString("appointment.url"));
                    a.setDate(date);
                    a.setStart(startTime);
                    a.setEnd(endTime);
                    selectedAppointment.add(a);
            }
            statement.close();
            return selectedAppointment;
        } catch (SQLException ex) {
            System.out.println("Error building Appointment List \n Error: " + ex.getMessage());
            return null;
        }
    }    
    
    public static void deleteAppointment(Appointment a) {
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            statement.executeUpdate("DELETE FROM appointment WHERE appointmentId= '" + a.getAppointmentId() + "'"); 
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Delete appointment failed \n Error: " + ex.getMessage());
        }
    }

/*
*
***** Try with resources used here for third type of exemption controls *****
*
*/
    public static ObservableList<Customer> addClientAppointment() {
        addClientAppointment.clear();
        try (Statement statement = DatabaseConnect.getDbConnection().createStatement()) {
            ResultSet results = statement.executeQuery("SELECT customer.customerName, address.phone, customerId FROM customer, address " +
                                    "WHERE customer.addressId = address.addressId;");
            while(results.next()) {
                Customer c = new Customer();
                     c.setCustomerId(results.getInt("customerId"));
                     c.setCustomerName(results.getString("customerName"));
                     c.setCustomerPhone(results.getString("phone"));
                addClientAppointment.add(c);
            }
            statement.close();
            return addClientAppointment;
        } catch (SQLException ex) {
            System.out.println("Select client failed \n Error: " + ex.getMessage());
            return null;
        }
    }
    
    //Overloaded method to search
    public static ObservableList<Customer> addClientAppointment(String str) {
        addClientAppointment.clear();
        try (Statement statement = DatabaseConnect.getDbConnection().createStatement()) {
            ResultSet results = statement.executeQuery("SELECT customer.customerName, address.phone FROM customer, address " +
                                    "WHERE customer.addressId = address.addressId AND customer.customerName = " + str + ";");
            while(results.next()) {
                Customer c = new Customer();
                 //    c.setCustomerId(results.getInt("customerId"));
                     c.setCustomerName(results.getString("customerName"));
                     c.setCustomerPhone(results.getString("phone"));
                addClientAppointment.add(c);
            }
            statement.close();
            return addClientAppointment;
        } catch (SQLException ex) {
            System.out.println("Select client failed \n Error: " + ex.getMessage());
            return null;
        }
    }

    /*
    *
    *
    *********** REPORT METHODS ***********
    *
    *
    */

    public static ObservableList<Report> report1(LocalDate dateMonth) {
        report1.clear();
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT appointment.appointmentId, appointment.customerId, customer.customerName, appointment.userId, user.userName, appointment.title, appointment.description, appointment.location," +
                                                       " appointment.contact, appointment.type, appointment.url, appointment.start, appointment.end" +
                                                       " FROM appointment, customer, user" +
                                                       " WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId" +
                                                       " ORDER BY user.userName, appointment.start ASC;");
           
            while(results.next()) {
                Report a = new Report();
                String startTime = TimeUtil.stringToString(results.getString("appointment.start"), "time");
                LocalTime ltStart = LocalTime.parse(startTime).minusMinutes(TimeUtil.getOffset());
                startTime = ltStart.toString();
                String date = TimeUtil.stringToString(results.getString("appointment.start"), "date");
                LocalDate ld = LocalDate.parse(date);
                if(ld.getMonth() == dateMonth.getMonth()) {
                    a.setCustomerName(results.getString("customer.customerName"));
                    a.setUserName(results.getString("user.userName"));
                    a.setType(results.getString("appointment.type"));
                    a.setDate(date);
                    a.setTime(startTime);
                    report1.add(a);
                    }
            }
            statement.close();
            return report1;
        } catch (SQLException ex) {
            System.out.println("Error building Report 1 List \n Error: " + ex.getMessage());
            return null;
        }
    }
    
    public static ObservableList<Report> report2() {
        report2.clear();
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT appointment.appointmentId, appointment.customerId, customer.customerName, appointment.userId, user.userName, appointment.title, appointment.description, appointment.location," +
                                                       " appointment.contact, appointment.type, appointment.url, appointment.start, appointment.end" +
                                                       " FROM appointment, customer, user" +
                                                       " WHERE appointment.customerId = customer.customerId AND appointment.userId = user.userId" +
                                                       " ORDER BY appointment.type, user.userName, appointment.start ASC;");
            while(results.next()) {
                Report a = new Report();
                    String date = TimeUtil.stringToString(results.getString("appointment.start"), "date");
                    String startTime = TimeUtil.stringToString(results.getString("appointment.start"), "time");
                    LocalTime ltStart = LocalTime.parse(startTime).minusMinutes(TimeUtil.getOffset());
                    startTime = ltStart.toString();
                    a.setCustomerName(results.getString("customer.customerName"));
                    a.setUserName(results.getString("user.userName"));
                    a.setType(results.getString("appointment.type"));
                    a.setDate(date);
                    a.setTime(startTime);
                    report2.add(a);
            }
            statement.close();
            return report2;
        } catch (SQLException ex) {
            System.out.println("Error building Report 2 List \n Error: " + ex.getMessage());
            return null;
        }
    }
    
    public static ObservableList<Report> report3() {
        report3.clear();
        try {
            Statement statement = DatabaseConnect.getDbConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT customer.customerName, user.userName, appointment.type, appointment.start" +
                                                       " FROM ((appointment " +
                                                       "INNER JOIN customer ON appointment.customerId = customer.customerId)" +
                                                       "INNER JOIN user ON appointment.userId = user.userId)" +
                                                       " ORDER BY user.userName, customer.customerName ASC;");
            while(results.next()) {
                Report a = new Report();
                    String date = TimeUtil.stringToString(results.getString("appointment.start"), "date");
                    String startTime = TimeUtil.stringToString(results.getString("appointment.start"), "time");
                    LocalTime ltStart = LocalTime.parse(startTime).minusMinutes(TimeUtil.getOffset());
                    startTime = ltStart.toString();
                    a.setCustomerName(results.getString("customer.customerName"));
                    a.setUserName(results.getString("user.userName"));
                    a.setType(results.getString("appointment.type"));
                    a.setDate(date);
                    a.setTime(startTime);
                    report3.add(a);
            }
            statement.close();
            return report3;
        } catch (SQLException ex) {
            System.out.println("Error building Report 3 List \n Error: " + ex.getMessage());
            return null;
        }
    }

    
//End Class
}

