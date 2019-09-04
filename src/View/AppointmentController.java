/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package View;

import Model.AccessDB;
import Model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author micha
 */
public class AppointmentController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button forwardButton;
    @FXML
    private Label calenderView;
    @FXML
    private Button exitButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button monthButton;
    @FXML
    private Button weekButton;
    @FXML
    private Button detailsButton;
    @FXML
    private TableColumn<Appointment, String> dateColumn;
    @FXML
    private TableColumn<Appointment, String> startColumn;
    @FXML
    private TableColumn<Appointment, String> endColumn;
    @FXML
    private TableColumn<Appointment, String> clientColumn;
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    @FXML
    private TableColumn<Appointment, String> titleColumn;
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    @FXML
    private TableView<Appointment> appointmentTableView;

    public static Appointment selectedAppointment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        startColumn.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        endColumn.setCellValueFactory(cellData -> cellData.getValue().endProperty());
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        appointmentTableView.setItems(AccessDB.allAppointments());
    }    

    @FXML
    private void backHandler(ActionEvent event) {
    }

    @FXML
    private void forwardHandler(ActionEvent event) {
    }

    @FXML
    private void exitHandler(ActionEvent event) throws IOException {
       Stage stage; 
       Parent root;
       stage=(Stage) exitButton.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("Home.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();             
        
    }

    @FXML
    private void addHandler(ActionEvent event) throws IOException {
       Stage stage; 
       Parent root;
       stage=(Stage) addButton.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();             

    }

    @FXML
    private void editHandler(ActionEvent event) throws IOException {
       Stage stage; 
       Parent root;
       stage=(Stage) editButton.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("EditAppointment.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();             

    }

    @FXML
    private void deleteHandler(ActionEvent event) {
    }

    @FXML
    private void monthHandler(ActionEvent event) {
    }

    @FXML
    private void weekHandler(ActionEvent event) {
    }

    @FXML
    private void detailsHandler(ActionEvent event) throws IOException {
       selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
       Stage stage; 
       Parent root;
       stage=(Stage) editButton.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("detailsAppointment.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();      
    }    
}
