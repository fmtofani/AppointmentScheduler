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
import Model.Customer;
import Util.TimeUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private TextField titleTF;
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
    private TextField clientIdTF;

    //Variables I need to pass to other class methods
    public static Customer selectedClient;    
    private int clientId;
    
    //Values for comboboxes
    private final ObservableList<String>  locationList = FXCollections.observableArrayList("Phoenix", "New York", "London");
    private final ObservableList<String> typeList = FXCollections.observableArrayList("Status", "Information", "Decision", "Problem", "Innovation", "Team");
    private final ObservableList<String> durationList = FXCollections.observableArrayList("30 minutes", "60 minutes", "90 minutes", "120 minutes");

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Fill Table
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().customerPhoneProperty());
        customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        clientTableView.setItems(AccessDB.addClientAppointment());
        clientIdTF.setVisible(false);
        clientTF.setDisable(true);
        //Fill Combo Boxes
        locationComboBox.setItems(locationList);
        typeComboBox.setItems(typeList);
        durationComboBox.setItems(durationList); 
        if(AppointmentController.versionAdd.equals("edit")) {      
            Appointment sel = AppointmentController.selectedAppointment;
            //break down date to parse to datepicker
            String str = sel.getDate();
            int year = Integer.parseInt(str.substring(0,4));
            int month = Integer.parseInt(str.substring(5,7));
            int day = Integer.parseInt(str.substring(8,10));
            datePicker.setValue(LocalDate.of(year, month, day));
            clientTF.setText(sel.getCustomerName());
            descriptionTF.setText(sel.getDescription());
            locationComboBox.getSelectionModel().select(sel.getLocation());
            typeComboBox.getSelectionModel().select(sel.getType());
            durationComboBox.getSelectionModel().select("2");
            //change military time to am/pm
            String hour = String.format("%02d", Integer.parseInt(sel.getStart().substring(0,2)));
            String min = String.format("%02d", Integer.parseInt(sel.getStart().substring(3,5)));
            System.out.println(hour + ", " + min);
            int h = Integer.parseInt(hour); 
            if(h <= 12) {
                amRadioButton.setSelected(true);
                pmRadioButton.setSelected(false);
            } else {
                amRadioButton.setSelected(false);
                pmRadioButton.setSelected(true);
            }
            startTF.setText(hour + ":" + min);
        } else {
            locationComboBox.getSelectionModel().select(0);
            typeComboBox.getSelectionModel().select(0);
            durationComboBox.getSelectionModel().select(0);
        }    
    }    
    //needed for addAppointmentHandler
    private int parseDuration(int num) {
        switch (num) {
            case 0:
                return 30;
            case 1:
                return 60;
            case 2:
                return 90;
            case 3:
                return 120;
        }
            return 0;
    }

    @FXML
    private void addAppointmentHandler(ActionEvent event) throws SQLException, IOException {
        //Add military time
        int add12 = 0;
        if(pmRadioButton.isSelected()) add12 = 12;
        //Construct Start time
        LocalTime lt = LocalTime.of(Integer.parseInt(startTF.getText().substring(0,2)), Integer.parseInt(startTF.getText().substring(3,5)));
        //Construct End Time
        lt.plusMinutes(parseDuration(durationComboBox.getSelectionModel().getSelectedIndex()));
        //Parse LocalTime into String
        String hour = String.format(Integer.toString(lt.getHour()));
        String min = Integer.toString(lt.getMinute());
        //Finally the finished product
        //System.out.println(TimeUtil.dateToString(datePicker.getValue()));
        String start = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).concat(" ").concat(Integer.toString(Integer.parseInt(startTF.getText().substring(0,2)) + add12).concat(":").concat(min));
        String end = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).concat(" ").concat(hour).concat(":").concat(min);
        
        
        //Construct an appointment and add it to DB
        Appointment a = new Appointment();      
        a.setCustomerId(clientId);
        a.setUserId(LoginController.getcurrentUserId());
        a.setTitle(titleTF.getText());
        a.setDescription(descriptionTF.getText());
        a.setLocation(String.valueOf(locationComboBox.getSelectionModel().getSelectedItem()));
        a.setType(String.valueOf(typeComboBox.getSelectionModel().getSelectedItem()));
        a.setStart(start);
        a.setEnd(end);
        AccessDB.addAppointment(a);

        //Let User know the appointment has been added
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Add Appointment");
        alert.setContentText("Appointment Has Been Added");
        alert.showAndWait();
        //Go back to Appointment Screen
        Stage stage; 
        Parent root;
        stage=(Stage) addAppointmentButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Appointment.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();        

                 
        
    }

    @FXML
    private void amHandler(ActionEvent event) {
        pmRadioButton.setSelected(false);
    }

    @FXML
    private void pmHandler(ActionEvent event) {
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
            clientId = addClientToAppt.getCustomerId();
            clientIdTF.setText(Integer.toString(addClientToAppt.getCustomerId()));
        }
            
    }

    
//End Class
}
