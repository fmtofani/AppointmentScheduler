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
import Util.TimeUtil;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
    private Button exitButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button detailsButton;
    @FXML
    private RadioButton monthRB;
    @FXML
    private RadioButton weekRB;
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
    @FXML
    private Label weekOfLabel;
    @FXML
    private Label fillLabel;
 
    private static Appointment selectedAppointment;
    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }
    
    private static boolean isEdit = false;
    public static boolean getIsEdit() {
        return isEdit;
    }
    public static void setIsEdit(boolean b) {
        isEdit = b;
    }
    private static String versionAdd = "";
    public static String getVersionAdd() {
        return versionAdd;
    }
    public static void setVersionAdd(String s) {
        versionAdd = s;
    }
    
    boolean isWeek = false;
    
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
        //appointmentTableView.setItems(AccessDB.monthAppointments(TimeUtil.getNowMonth(), TimeUtil.getNowYear()));
        appointmentTableView.setItems(AccessDB.monthAppointments(LocalDate.now()));
        weekRB.setSelected(false);
        monthRB.setSelected(true);
        weekOfLabel.setText("Month Of");
        fillLabel.setText(TimeUtil.thisMonth(LocalDate.now()));
        isEdit = false;
    }    

    int backCounter = 1;
    int forwardCounter = 1;
    @FXML
    private void backHandler(ActionEvent event) {
        LocalDate ld = LocalDate.parse(TimeUtil.getNowDate());
        if(isWeek) {
            appointmentTableView.setItems(AccessDB.weekAppointments(ld.minusDays(backCounter * 7)));
            fillLabel.setText(TimeUtil.thisWeek(ld.minusDays(backCounter * 7)));
        } else {
            appointmentTableView.setItems(AccessDB.monthAppointments(ld.minusMonths(backCounter)));            
            fillLabel.setText(TimeUtil.thisMonth(ld.minusMonths(backCounter)));
        }
        backCounter++;
        forwardCounter--;
    }
    @FXML
    private void forwardHandler(ActionEvent event) {
        LocalDate ld = LocalDate.parse(TimeUtil.getNowDate());
        if(isWeek) {
            appointmentTableView.setItems(AccessDB.weekAppointments(ld.plusDays(forwardCounter * 7)));
            fillLabel.setText(TimeUtil.thisWeek(ld.plusDays(forwardCounter * 7)));
       } else {
            appointmentTableView.setItems(AccessDB.monthAppointments(ld.plusMonths(forwardCounter)));
            fillLabel.setText(TimeUtil.thisMonth(ld.plusMonths(forwardCounter)));
        }
        forwardCounter++;
        backCounter--;
    }

    @FXML
    private void monthHandler(ActionEvent event) {
        LocalDate ld = LocalDate.now();
        backCounter = 1;
        forwardCounter = 1;
        isWeek = false;
        weekOfLabel.setText("Month of");
        fillLabel.setText(TimeUtil.thisMonth(LocalDate.now()));
        weekRB.setSelected(false);
        appointmentTableView.setItems(AccessDB.monthAppointments(ld));

    }

    @FXML
    private void weekHandler(ActionEvent event) {
        backCounter = 1;
        forwardCounter = 1;
        isWeek = true;
        weekOfLabel.setText("Week of");
        fillLabel.setText(TimeUtil.thisWeek(LocalDate.now()));
        monthRB.setSelected(false);
        LocalDate ld = LocalDate.parse(TimeUtil.getNowDate());//.minusDays(7);
        LocalDate ldd = ld.with(DayOfWeek.SUNDAY);
        appointmentTableView.setItems(AccessDB.weekAppointments(ldd));
    }

    @FXML
    private void deleteHandler(ActionEvent event) {
        if(appointmentTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("SELECTION ERROR");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select an appointment to delete");
            alert.showAndWait();
            return;
        }
        selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText("DELETE APPOINTMENT");
            alert.setContentText("Are you sure that you want to delete this appointment? ");
            Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
              AccessDB.deleteAppointment(selectedAppointment);
              appointmentTableView.setItems(AccessDB.allAppointments());
        }
    }

    @FXML
    private void detailsHandler(ActionEvent event) throws IOException {
        if(appointmentTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("SELECTION ERROR");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select an appointment to delete");
            alert.showAndWait();
            return;
        }
       selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
       Stage stage; 
       Parent root;
       stage=(Stage) editButton.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("detailsAppointment.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();      
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
       versionAdd.equals("");
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
        if(appointmentTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("SELECTION ERROR");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select an appointment to edit");            
            alert.showAndWait();
            return;
        }
       selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
       versionAdd = "edit";
       isEdit = true;
       Stage stage; 
       Parent root;
       stage=(Stage) editButton.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();             
    }
    
//End Class
}
