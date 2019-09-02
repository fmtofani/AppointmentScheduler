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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author micha
 */
public class ClientController implements Initializable {

    @FXML
    private Button exitClientButton;
    @FXML
    private TableColumn<Customer, String> clientNameColumn;
    @FXML
    private TableColumn<Customer, String> clientPhoneColumn;
    @FXML
    private TableColumn<Customer, String> clientAddressColumn;
    @FXML
    private TableColumn<Customer, String> clientAddress2Column;
    @FXML
    private TableColumn<Customer, String> clientZipcodeColumn;
    @FXML
    private TableColumn<Customer, String> clientCityColumn;
    @FXML
    private TableColumn<Customer, String> clientCountryColumn;
    @FXML
    private Button modifyClientButton;
    @FXML
    private Button addClientButton;
    @FXML
    private Button deleteClientButton;
    @FXML
    private TableView<Customer> clientTableView;
    
    public static Customer selectedCustomer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        clientPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().customerPhoneProperty());
        clientAddressColumn.setCellValueFactory(cellData -> cellData.getValue().customerAddressProperty());
        clientAddress2Column.setCellValueFactory(cellData -> cellData.getValue().customerAddress2Property());
        clientCityColumn.setCellValueFactory(cellData -> cellData.getValue().customerCityProperty());
        clientCountryColumn.setCellValueFactory(cellData -> cellData.getValue().customerCountryProperty());
        clientZipcodeColumn.setCellValueFactory(cellData -> cellData.getValue().customerZipcodeProperty());
        clientTableView.setItems(AccessDB.allCustomers());
    }    

    @FXML
    private void exitClientHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("CANCEL");
        alert.setContentText("Are you sure that you want to exit this module?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {      
            Stage stage; 
            Parent root;
            stage=(Stage) deleteClientButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();        
        }
        
    }

    @FXML
    private void modifyClientHandler(ActionEvent event) throws IOException {
        selectedCustomer = clientTableView.getSelectionModel().getSelectedItem();
        Stage stage; 
        Parent root;
        stage=(Stage) modifyClientButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("EditClient.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();       
    }

    @FXML
    private void deleteClientHandler(ActionEvent event) {
        selectedCustomer = clientTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("DELETE USER");
        alert.setContentText("Are you sure that you want to delete user " + selectedCustomer.getCustomerName());
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
              AccessDB.deleteCustomer(selectedCustomer);
              clientTableView.setItems(AccessDB.allCustomers());
        }        
    }

    @FXML
    private void addClientHandler(ActionEvent event) throws IOException {
        System.out.println("adding client");
        Stage stage; 
        Parent root;
        stage=(Stage) addClientButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddClient.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   

    }



    
}
