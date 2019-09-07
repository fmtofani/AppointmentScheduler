/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package View;

import Model.AccessDB;
import Model.User;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author micha
 */
public class AddUserController implements Initializable {

    @FXML
    private Button addUserButton;
    @FXML
    private Button cancelAddUserButton;
    @FXML
    private Label addUserPassword;
    @FXML
    private TextField addUserPasswordTextField;
    @FXML
    private TextField addUserNameTextField;
    @FXML
    private Button exitAddUserButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addUserHandler(ActionEvent event) {
        String name = addUserNameTextField.getText();
        String password = addUserPasswordTextField.getText();
        
        if (name.equals("") || password.equals("")) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Add User");
        alert.setContentText("Please enter a Username and Password");
        alert.showAndWait();     
        return;
        }
        
        User u = new User();
        u.setUserName(name);
        u.setPassword(password);
        AccessDB.addUser(u);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Add User");
        alert.setContentText("User Has Been Added");
        alert.showAndWait();
        
        addUserNameTextField.setText("");
        addUserPasswordTextField.setText("");
     
        
    }

    @FXML
    private void cancelAddUserHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("CANCEL");
        alert.setContentText("Are you sure that you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {      
            Stage stage; 
            Parent root;
            stage=(Stage) addUserButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("User.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();        
        }
        
    }

    @FXML
    private void exitAddUserHandler(ActionEvent event) throws IOException {
       Stage stage; 
       Parent root;
       stage=(Stage) addUserButton.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("User.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();             
    }

    
}
