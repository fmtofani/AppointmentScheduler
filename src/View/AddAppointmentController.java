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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//3 specific locations
/**
 * FXML Controller class
 *
 * @author micha
 */
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
    private ComboBox<?> locationComboBox;
    @FXML
    private ComboBox<?> typeComboBox;
    @FXML
    private ComboBox<?> durationComboBox;
    @FXML
    private RadioButton amRadioButton;
    @FXML
    private RadioButton pmRadioButton;
    @FXML
    private TableColumn<Customer, String> clientColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
    }    

    @FXML
    private void addAppointmentHandler(ActionEvent event) {
    
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
    
}
