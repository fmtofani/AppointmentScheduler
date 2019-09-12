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
import Util.InvalidTimeException;
import Util.OverlappingAppointmentException;
import Util.TimeUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @FXML
    private Button clearButton;

    //Variables I need to pass to other class methods
    private static Customer selectedClient;
    public static Customer getSelectedClient() {
            return selectedClient;
    }
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
        amRadioButton.setSelected(true);
        isPM = false;
        if(AppointmentController.getVersionAdd().equals("edit")) {      
            addAppointmentButton.setText("Modify");
            //change this after mod
            Appointment sel = AppointmentController.getSelectedAppointment();
            //break down date to parse to datepicker
            String str = sel.getDate();
            int year = Integer.parseInt(str.substring(0,4));
            int month = Integer.parseInt(str.substring(5,7));
            int day = Integer.parseInt(str.substring(8,10));
            datePicker.setValue(LocalDate.of(year, month, day));
            clientTF.setText(sel.getCustomerName());
            clientId = sel.getCustomerId();
            titleTF.setText(sel.getType());
            descriptionTF.setText(sel.getDescription());
            locationComboBox.getSelectionModel().select(sel.getLocation());
            typeComboBox.getSelectionModel().select(sel.getType());
            durationComboBox.getSelectionModel().select("30 minutes");
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
    
    @FXML
    private void clearHandler(ActionEvent event) {
        //Clears the data entry objects
 
        datePicker.setValue(null);
        startTF.setText("");
        amRadioButton.setSelected(true);
        pmRadioButton.setSelected(false);
        durationComboBox.getSelectionModel().select(0);
        clientTF.setText("");
        locationComboBox.getSelectionModel().select(0);
        typeComboBox.getSelectionModel().select(0);
        titleTF.setText("");
        descriptionTF.setText("");
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
    boolean badTime = false;
    
    private void alertTime(String s) {
            badTime = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error Adding Appointment");
            alert.setContentText("Please verify that time is entered in HH:MM format");
            alert.showAndWait();
    }
        private void verifyStart(String vt) {
            if(vt.length() != 5) {
                alertTime(vt);
                return;
            }
            if(!vt.substring(2,3).equals(":")) {
                alertTime(vt);
                return;
            }
            int hour = Integer.parseInt(vt.substring(0,2));
            int min = Integer.parseInt(vt.substring(3,5));
            if(hour < 1 || hour > 12 || min < 0 || min >59) {
                alertTime(vt);
                return;
            }
        }
        
/*
    *
    *
    *  Custom Exception Control to verify business hours
    *
    *
*/        
        
    private boolean businessHours(int num) throws InvalidTimeException {
        try {
            if (isPM) {
                num += 12;
            }
            if(num > 8 || num < 18) {
                return true;
            } else {
                throw new InvalidTimeException("The selected hours are not during business hours");
            }
        } catch(InvalidTimeException ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }        
    
    private boolean overlappingAppointment(String start, String end) throws OverlappingAppointmentException, SQLException {
        try {
            if (!AccessDB.overlap(start, end)) {
                return false;
            } else {
                throw new OverlappingAppointmentException("This appointment conflicts with another appointment");
            }
        } catch(OverlappingAppointmentException ex) {
            System.out.println("\"Error: " + ex.getMessage());
            return true;
        }
    }
    
    @FXML
    private void addAppointmentHandler(ActionEvent event) throws SQLException, IOException, InvalidTimeException, OverlappingAppointmentException {
        //Verify fields have been filled out
        if(clientTableView.getSelectionModel().isEmpty() && !AppointmentController.getVersionAdd().equals("edit")) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("SELECTION ERROR");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select a Client to Add to Appointment");
            alert.showAndWait();
            return;
        }                
        if(clientTF.getText().equals("") || startTF.getText().equals("") || descriptionTF.getText().equals("") || titleTF.getText().equals("") || datePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error Adding Appointment");
            alert.setContentText("Please verify that all fields are filled out");
            alert.showAndWait();
            return;               
        }
        badTime = false;
        //verify proper time formaat
        verifyStart(startTF.getText());
        if(badTime) return;
        //Add military time
        int add12 = 0;
        if(pmRadioButton.isSelected()) add12 = 12;
        
/*
 *
 *      Satisfies Rubric F -> Verify within business hours
 *
 *
*/

        if (!businessHours(Integer.parseInt(startTF.getText().substring(0,2)))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error Adding Appointment");
            alert.setContentText("Please enter a time between 9am and 5pm");
            alert.showAndWait();   
            return;
        }

/*
 *
 *      Satisfies Rubric E -> Adjust for timezone
 *
 *
*/
        
        //Construct Start time
        LocalTime lt = LocalTime.of(Integer.parseInt(startTF.getText().substring(0,2)), Integer.parseInt(startTF.getText().substring(3,5)));
        lt = lt.plusHours(add12);
        LocalDate ld = LocalDate.parse(datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        ldt = ldt.plusHours(add12);
        ldt = ldt.plusMinutes(TimeUtil.getOffset());
        String start = ldt.toString();
        //Construct End Time
        ldt = ldt.plusMinutes(parseDuration(durationComboBox.getSelectionModel().getSelectedIndex()));
        String end = ldt.toString();
        

/*
 *
 *      Satisfies Rubric F -> Verify Overlapping Appointment
 *
 *
*/

        if(overlappingAppointment(start, end)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error Adding Appointment");
            alert.setContentText("This appointment conflicts with an already scheduled appointment");
            alert.showAndWait();   
            return;
        }
        
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
        //Delete the old appointment if being edited
        //Had I implemented a management user I would edit the LastUpdatedBy field in the Database
        //Since only the user can edit their own appointments, I didn't deem it necessary to update the LastUpdatedBy field
        if(AppointmentController.getIsEdit()) {
            AccessDB.deleteAppointment(AppointmentController.getSelectedAppointment());
            addAppointmentButton.setText("Add");
        }
        AppointmentController.setIsEdit(false);        
        AppointmentController.setVersionAdd("");
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

    boolean isPM = false;
    @FXML
    private void amHandler(ActionEvent event) {
        pmRadioButton.setSelected(false);
        isPM = false;
    }

    @FXML
    private void pmHandler(ActionEvent event) {
        amRadioButton.setSelected(false);
        isPM = true;
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
            AppointmentController.setIsEdit(false);
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
