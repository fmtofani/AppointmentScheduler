/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package View;

import Model.User;
import Util.DatabaseConnect;
import Util.LoggerUtil;
import Util.TimeUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author micha
 */
public class LoginController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passTextField;
    @FXML
    private TextField userTextField;
    @FXML
    private Label passLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Label headerLabel;
    @FXML
    private Button loginExitButton;

    //Variables to set locale language
    private String alertTitle;
    private String alertHeader;
    private String alertMessage;

   //Reference current user throughout program
    private static String currentUser;
    public static String getCurrentUser() {
        return currentUser;
    }
    //References current userId
    private static int currentUserId;
    public static int getcurrentUserId() {
        return currentUserId;
    }
    //References current localdatetime and time zone offset
    
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set local time
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        TimeUtil.setNow(ldt.format(formatter));
        TimeUtil.setNowDate(ldt.format(formatter).substring(0, 10));
        TimeUtil.setNowMonth(ldt.format(formatter).substring(5,7));
        TimeUtil.setNowYear(ldt.format(formatter).substring(0,4));
        TimeUtil.setNowTime(ldt.format(formatter).substring(11,16));
        //get local timezone offset by breaking down the string and parsing it into a Double. 
        //There is probably an easy way to do this, however this is the only iteration I have used in the program which is odd.
        String stringOffset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now()).toString();
        String str1="";
        String str2="";
        String str3;
        Double num;
        int length = stringOffset.length();
        for(int i=0; i < length + 1; i++) {
            if(stringOffset.substring(i, i+1).equals(":")) {
                str1 = stringOffset.substring(0, i);
                str2 = stringOffset.substring(i+1, stringOffset.length());
                break;
            }
        }
            if(str2.equals(30)) {
                str2 = "5";
            }
            str3 = (str1 + "." + str2);
            num = Double.valueOf(str3);
            TimeUtil.setOffset(num);
        
        //Fire up the database
        DatabaseConnect.dbConnect();
        //Set the local language
        setLang();
    }    
    
    //Set the language according to locale
    private void setLang() {
        ResourceBundle rb = ResourceBundle.getBundle("Lang/login", Locale.getDefault());
        headerLabel.setText(rb.getString("header"));
        userLabel.setText(rb.getString("user"));
        passLabel.setText(rb.getString("pass"));
        loginButton.setText(rb.getString("loginButton"));
        alertTitle = rb.getString("errTitle");
        alertHeader = rb.getString("errHeader");
        alertMessage = rb.getString("errMessage");
    }            

    private static Boolean attemptLogin(String user, String pass) throws IOException {
            try {
                Statement statement = DatabaseConnect.getDbConnection().createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM user WHERE userName='" + user + "' AND password='" + pass + "'");
                if(results.next()) {
                User u = new User();
                u.setUserId(results.getInt("user.userId"));
                currentUserId = u.getUserId();
                //Log success   
                LoggerUtil.addEntry(user, true);
                return true; 
                } else {
                    //Log failure    
                    LoggerUtil.addEntry(user, false);
                    return false;
                }
            } catch (SQLException ex) {
                System.out.println("There has been an SQL error \n Error: " + ex.getMessage());
                return false;
            }
    }
    
    @FXML
    public void loginHandler(ActionEvent event) throws IOException {
        String user = userTextField.getText();
        String pass = passTextField.getText();

        if(attemptLogin(user, pass) == false) {
            //Show Error in Locale Language
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertTitle);
            alert.setHeaderText(alertHeader);
            alert.setContentText(alertMessage); 
            alert.showAndWait();
            //Clear password and keep username for another attempt
            passTextField.setText("");
        } else {
            currentUser = user;

/*  Alert to show and wait for 15min appointments
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Alert");
            alert.setHeaderText("Upcoming Appointment");
            alert.setContentText("You have an appointment within 15 minutes"); 
            alert.showAndWait();
*/
        //Go to main home screen
                Stage stage; 
                Parent root;
                stage=(Stage) loginButton.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

        }
    }     

    @FXML
    private void loginExitHandler(ActionEvent event) {
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
    
//End Class    
}
