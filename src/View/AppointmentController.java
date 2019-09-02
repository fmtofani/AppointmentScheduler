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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private void exitHandler(ActionEvent event) {
    }

    @FXML
    private void addHandler(ActionEvent event) {
    }

    @FXML
    private void editHandler(ActionEvent event) {
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
    private void detailsHandler(ActionEvent event) {
        selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
    }
    
}
