/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author micha
 */
public class DetailsAppointmentController implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private TableView<Appointment> detailsTableView;
    @FXML
    private TableColumn<Appointment, String> dateColumn;
    @FXML
    private TableColumn<Appointment, String> clientColumn;
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
    @FXML
    private TableColumn<Appointment, String> startColumn;
    @FXML
    private TableColumn<Appointment, String> endColumn;
    @FXML
    private TableColumn<Appointment, String> titleColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Appointment sel = AppointmentController.selectedAppointment;
        sel.getAppointmentId();
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        startColumn.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        endColumn.setCellValueFactory(cellData -> cellData.getValue().endProperty());
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        detailsTableView.setItems(AccessDB.selectedAppointment(sel));
    }    

    @FXML
    private void exitHandler(ActionEvent event) {
    }
    
}
