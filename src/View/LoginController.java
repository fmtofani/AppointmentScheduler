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
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    private String alertTitle;
    private String alertHeader;
    private String alertMessage;
    @FXML
    private Button loginExitButton;


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
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseConnect.dbConnect();
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

//  Alert to show and wait for 15min appointments
        

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
