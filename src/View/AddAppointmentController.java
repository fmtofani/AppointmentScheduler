/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package View;

import Model.AccessDB;
import Model.Address;
import Model.Appointment;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AddAppointmentController implements Initializable {

    @FXML
    private Button cancelAddAppointmentButton;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private Label appointmentTypeLabel;
    @FXML
    private TextField startTF;
    @FXML
    private TextField clientTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private TableView<Customer> clientTableView;
    @FXML
    private TextField searchTF;
    @FXML
    private Button addClient;
    @FXML
    private Button exitButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox locationComboBox;
    @FXML
    private ComboBox typeComboBox;
    @FXML
    private ComboBox durationComboBox;
    @FXML
    private RadioButton amRadioButton;
    @FXML
    private RadioButton pmRadioButton;
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> clientColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;
    @FXML
    private Button searchButton;
    @FXML
    private Button resetButton;
    @FXML
    private TextField clientPhoneTF;

    public static Customer selectedClient;
    /*
    To get the selected item write: 
?
1
String selectedChoice = choiceBox.getSelectionModel().getSelectedItem();

To set the selected item write: 
?
1
choiceBox.getSelectionModel().setSelectedItem("oranges");
*/
    
    private final ObservableList<String>  locationList = FXCollections.observableArrayList("Phoenix", "New York", "London");
    private final ObservableList<String> typeList = FXCollections.observableArrayList("Status", "Information", "Decision", "Problem", "Innovation", "Team");
    private final ObservableList<String> durationList = FXCollections.observableArrayList("30 minutes", "60 minutes", "90 minutes", "120 minutes");
    private boolean isAM;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Fill Table
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().customerPhoneProperty());
        clientTableView.setItems(AccessDB.addClientAppointment());
        //Fill Combo Boxes
        locationComboBox.setItems(locationList);
        typeComboBox.setItems(typeList);
        durationComboBox.setItems(durationList); 
        locationComboBox.getSelectionModel().select(0);
        typeComboBox.getSelectionModel().select(0);
        durationComboBox.getSelectionModel().select(0);
        clientPhoneTF.setVisible(false);
        clientTF.setDisable(true);
        
        /*
        if(AppointmentController.versionAdd.equals("edit")) {
        Appointment sel = AppointmentController.selectedAppointment;
        startTF.setText(sel.getStart());
        clientTF.setText(sel.getCustomerName());
        descriptionTF.setText(sel.getDescription());
        locationComboBox.getSelectionModel().select("New York");
        typeComboBox.getSelectionModel().select("New York");
        durationComboBox.getSelectionModel().select("30 minutes");
//If statement to see if am or pm        
        amRadioButton.setSelected(true);
        pmRadioButton.setSelected(false);
        }
 */
        

    }    

    @FXML
    private void addAppointmentHandler(ActionEvent event) {
    
    }

    @FXML
    private void amHandler(ActionEvent event) {
        isAM = true;
        pmRadioButton.setSelected(false);
    }

    @FXML
    private void pmHandler(ActionEvent event) {
        isAM = false;
        amRadioButton.setSelected(false);
    }
     
//The purpose of this method is to add the selected client to the appointment client field
    @FXML
    private void addClientHandler(ActionEvent event) throws IOException {
       AccessDB.comeFrom = "Appointment.fxml";
       Stage stage; 
       Parent root;
       stage=(Stage) addAppointmentButton.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AddClient.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();             
    }

    
    //Redundant method to get back to the main schedule stage. I included it so that users would not be confused when they decided to not save the information that they entered
    @FXML
    private void cancelAddAppointmentHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("CANCEL");
        alert.setContentText("Are you sure that you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {      
            Stage stage; 
            Parent root;
            stage=(Stage) addAppointmentButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Appointment.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();        
        }
    }

   @FXML
    private void exitHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("CANCEL");
        alert.setContentText("Are you sure that you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {      
            Stage stage; 
            Parent root;
            stage=(Stage) addAppointmentButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Appointment.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();        
        }   
    }
    
    @FXML
    private void searchHandler(ActionEvent event) throws IOException {
        String str = searchTF.getText();  
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().customerPhoneProperty());
        AccessDB.addClientAppointment(str);
    }

    @FXML
    private void resetHandler(ActionEvent event) {
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().customerPhoneProperty());
        AccessDB.addClientAppointment();
    }

    @FXML
    private void addClientToApptHandler(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Customer addClientToAppt =  clientTableView.getSelectionModel().getSelectedItem();
            clientTF.setText(addClientToAppt.getCustomerName());
            clientPhoneTF.setText(addClientToAppt.getCustomerName());
        }
            
    }

    
//End Class
}
