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
import Util.DatabaseConnect;
import java.awt.Desktop;
import java.io.File;
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
public class HomeController implements Initializable {

    @FXML
    private Button exitAppButton;
    @FXML
    private Button reportsButton;
    @FXML
    private Button scheduleButton;
    @FXML
    private Button viewLogButton;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private Button deleteAppointmentButton;
    @FXML
    private Button userAccessButton;
    @FXML
    private Button clientButton;
    @FXML
    private TableColumn<Appointment, String> timeColumn;
    @FXML
    private TableColumn<Appointment, String> clientColumn;
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    @FXML
    private TableColumn<Appointment, String> titleColumn;
    @FXML
    private TableView<Appointment> appointmentTableView;
    
    public static Appointment selectedAppointment;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        appointmentTableView.setItems(AccessDB.allAppointments());
    }    


    /*
    *
    *
    *********** APPOINTMENT METHODS ***********
    *
    *
    */

    
    @FXML
    private void scheduleHandler(ActionEvent event) throws IOException {
        Stage stage; 
        Parent root;
        stage=(Stage) addAppointmentButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Appointment.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();                  
    }    
    
    @FXML
    private void addAppointmentHandler(ActionEvent event) throws IOException {
        Stage stage; 
        Parent root;
        stage=(Stage) addAppointmentButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();          
    }

    @FXML
    private void deleteAppointmentHandler(ActionEvent event) {
        selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("DELETE Appointment");
        alert.setContentText("Are you sure that you want to delete this appointment?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
              AccessDB.deleteAppointment(selectedAppointment);
              appointmentTableView.setItems(AccessDB.allAppointments());
        }        
    }



    /*
    *
    *
    *********** ADMIN METHODS ***********
    *
    *
    */


    @FXML
    private void userAccessHandler(ActionEvent event) throws IOException {
        Stage stage; 
        Parent root;
        stage=(Stage) userAccessButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("User.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();          
    }

    @FXML
    private void clientHandler(ActionEvent event) throws IOException {
        Stage stage; 
        Parent root;
        stage=(Stage) clientButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Client.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();          
    }
    
    @FXML
    private void exitMainHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("EXIT");
        alert.setContentText("Are you sure that you want to exit the program?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            DatabaseConnect.closeDbConnection();
            System.exit(0);
        }        
    }

 @FXML
    private void viewLogHandler(ActionEvent action) throws IOException {
        File file = new File("C195_log.txt");        
        //Make sure Desktop is supported
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }       
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);            
    }
    
    @FXML
    private void reportsHandler(ActionEvent event) throws IOException {
                Stage stage; 
                Parent root;
                stage=(Stage) reportsButton.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Reports.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();        
    }    
   
//End Class    
}
