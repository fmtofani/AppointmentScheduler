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
public class EditUserController implements Initializable {

    @FXML
    private Button modifyUserButton;
    @FXML
    private Button cancelModifyUserButton;
    @FXML
    private Label addUserPassword;
    @FXML
    private TextField editPasswordTextField;
    @FXML
    private TextField editUserNameTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        editUserNameTextField.setText(UserController.selectedUser.getUserName());
        editPasswordTextField.setText(UserController.selectedUser.getPassword());
    }    

    @FXML
    private void modifyUserHandler(ActionEvent event) throws IOException {
        String name = editUserNameTextField.getText();
        String password = editPasswordTextField.getText();
        User u = UserController.selectedUser;
       
        if (name.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Add User");
            alert.setContentText("Please enter a Username and Password");
            alert.showAndWait();    
            return;
        }
                     
        u.setUserName(name);
        u.setPassword(password);
        AccessDB.modifyUser(u);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Modify User");
        alert.setContentText("User Has Been Modified");
        alert.showAndWait();
        
        Stage stage; 
        Parent root;
        stage=(Stage) modifyUserButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("User.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
        
    }

    @FXML
    private void cancelModifyUserHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("CANCEL");
        alert.setContentText("Are you sure that you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {      
            Stage stage; 
            Parent root;
            stage=(Stage) modifyUserButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("User.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();    
        }
    }
    
}
