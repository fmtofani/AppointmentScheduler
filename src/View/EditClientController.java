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
public class EditClientController implements Initializable {

    @FXML
    private Button modifyClientButton;
    @FXML
    private Button cancelModifyClientButton;
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

    //Capture original customerId to edit properly
    int originalId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Customer sel = ClientController.selectedCustomer;
        if (sel.getCustomerAddress2().equals("none")) address2TextField.setText("");
        nameTextField.setText(sel.getCustomerName());
        addressTextField.setText(sel.getCustomerAddress());
        address2TextField.setText(sel.getCustomerAddress2());
        phoneTextField.setText(sel.getCustomerPhone());
        cityTextField.setText(sel.getCustomerCity());
        countryTextField.setText(sel.getCustomerCountry());
        zipcodeTextField.setText(sel.getCustomerZipcode());
        originalId = sel.getCustomerId();
    }    

    @FXML
    private void modifyClientHandler(ActionEvent event) throws IOException {
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String address2 = address2TextField.getText();
        String phone = phoneTextField.getText();
        String city = cityTextField.getText();
        String country = countryTextField.getText();
        String zip = zipcodeTextField.getText();
       
        if (!Customer.isValidCustomer(name, address, phone, city, country, zip)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Modify Client");
            alert.setContentText("Please complete all fields");
            alert.showAndWait();
            return;
        }
        
        if(address2.equals("")) {
            address2 = "none";
        }

        Customer c = new Customer();
        c.setCustomerId(originalId);
        c.setCustomerName(name);
        c.setCustomerAddress(address);
        c.setCustomerAddress2(address2);
        c.setCustomerCity(city);
        c.setCustomerCountry(country);
        c.setCustomerPhone(phone);
        c.setCustomerZipcode(zip);
        
        AccessDB.modifyCustomer(c);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Modify User");
        alert.setContentText("User Has Been Modified");
        alert.showAndWait();
        
        Stage stage; 
        Parent root;
        stage=(Stage) modifyClientButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Client.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void cancelModifyClientHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("CANCEL");
        alert.setContentText("Are you sure that you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {      
            Stage stage; 
            Parent root;
            stage=(Stage) modifyClientButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();     
        }    
    }
    
    
}
