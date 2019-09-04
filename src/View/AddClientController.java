/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package View;

import Model.AccessDB;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author micha
 */
public class AddClientController implements Initializable {

    @FXML
    private Button addClientButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField address2TextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField zipcodeTextField;
    @FXML
    private Button cancelAddClientButton;
    @FXML
    private Button clearAddClientButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void cancelAddClientHandler(ActionEvent event) throws IOException {
        String whereTo = AccessDB.comeFrom;
        Stage stage; 
        Parent root;
        stage=(Stage) addClientButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(whereTo));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();        
        
        
    }

    @FXML
    private void addClientHandler(ActionEvent event) throws SQLException {
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String address2 = address2TextField.getText();
        String phone = phoneTextField.getText();
        String zipcode = zipcodeTextField.getText();
        String city = cityTextField.getText();
        String country = countryTextField.getText();
        
        if(!Customer.isValidCustomer(name, address, phone, city, country, zipcode)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Add Client");
        alert.setContentText("Please fill out all fields");
        alert.showAndWait();
        return;
        }
        
        if (address2.equals("")) {
            address2 = "none";
        }
        
        Customer c = new Customer();
        c.setCustomerName(name);
        c.setCustomerAddress(address);
        c.setCustomerAddress2(address2);
        c.setCustomerPhone(phone);
        c.setCustomerZipcode(zipcode);
        c.setCustomerCity(city);
        c.setCustomerCountry(country);
        
        AccessDB.addCustomer(c);
 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Add User");
        alert.setContentText("User Has Been Added");
        alert.showAndWait();

        nameTextField.setText("");
        phoneTextField.setText("");
        addressTextField.setText("");
        address2TextField.setText("");
        cityTextField.setText("");
        countryTextField.setText("");
        zipcodeTextField.setText("");
     }

    @FXML
    private void clearAddClientHandler(ActionEvent event) {
        nameTextField.setText("");
        phoneTextField.setText("");
        addressTextField.setText("");
        address2TextField.setText("");
        cityTextField.setText("");
        countryTextField.setText("");
    }
    
}
