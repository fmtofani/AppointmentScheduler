package View;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Util.DatabaseConnect;

/**
 * FXML Controller class
 *
 * @author micha
 */
public class LoginController implements Initializable {

    @FXML
    private Label appTitle;
    @FXML
    private Label headerLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Label passLabel;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passTextField;
    @FXML
    private Button loginButton;
    
    // for use with alert in proper language
    private String alertTitle;
    private String alertHeader;
    private String alertMessage;

       @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("test)");
        DatabaseConnect.dbConnect();
        setLang();
    }
    
  
    //Reference current user throughout program
    public static String currentUser;
    public static String getCurrentUser() {
        return currentUser;
    }


    private static Boolean attemptLogin(String user, String pass) {
            try {
                Statement statement = DatabaseConnect.getDbConnection().createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM user WHERE userName='" + user + "' AND password='" + pass + "'");
                if(results.next()) {
//set new user
//Log success
                return true;
                } else
//Log failure
                    return false;
            } catch (SQLException ex) {
                System.out.println("There has been an SQL error \n Error: " + ex.getMessage());
                return false;
            }
        

        }
    
    
    @FXML
    public void loginHandler(ActionEvent event) throws IOException {
        System.out.println("TTT");
        String user = userTextField.getText();
        String pass = passTextField.getText();
        
        if(attemptLogin(user, pass) == false) {
            //Show Error in Locale Language
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertTitle);
            alert.setHeaderText(alertHeader);
            alert.setContentText(alertMessage); 
        } else {
            currentUser = user;
//  Alert to show and wait for 15min apptments
//  Close login window

//Go to main home screen
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("View/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
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

        
   

}
