/*
I am well aware that having all users access the user table and allowing them to see passwords is not good practice. 
I would edit the database and add a management table and have it implement the user table to stay in 4th normal form to make it more secure.  
I would also supply a random default password to a new user, email it to them, and have them change it upon their next login. 
I would also not allow the password field to be viewed by anyone and the password value would contain a hash. 
All of this work is outside of the scope of this  but I am going to figure all that out after finishing school. 
I added the User Access module as an easy way to learn the basic mechanics of this project.
I know its not a requirement and I can easily remove it if need be for revision 2. 
*/

package View;

//import static Model.AccessDB.getAllUsers;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author micha
 */
public class UserController implements Initializable {

    @FXML
    private AnchorPane userTable;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private Button exitUserButton;
    @FXML
    private Button addUserButton;
    @FXML
    private Button modifyUserButton;
    @FXML
    private Button deleteUserButton;

    
    private static User selectedUser;
    public static User getSelectedUser() {
        return selectedUser;
    }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userNameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        userTableView.setItems(AccessDB.allUsers());
    }    


    @FXML
    private void addUserHandler(ActionEvent event) throws IOException {
        Stage stage; 
        Parent root;
        stage=(Stage) addUserButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddUser.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
    }
 
    @FXML
    private void exitUserHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("CANCEL");
        alert.setContentText("Are you sure that you want to exit this module?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {      
            Stage stage; 
            Parent root;
            stage=(Stage) deleteUserButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();        
        }
    }
    
    
    
    @FXML
    private void modifyUserHandler(ActionEvent event) throws IOException {
        if(userTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("SELECTION ERROR");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select a User to modify");
            alert.showAndWait();
            return;
        }        
        selectedUser = userTableView.getSelectionModel().getSelectedItem();
        
        Stage stage; 
        Parent root;
        stage=(Stage) modifyUserButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("EditUser.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        AccessDB.modifyUser(selectedUser);
    }

    @FXML
    private void deleteUserHandler(ActionEvent event) {
        if(userTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("SELECTION ERROR");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select a User to delete");
            alert.showAndWait();
            return;
        }        
        selectedUser = userTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("DELETE USER");
        alert.setContentText("Are you sure that you want to delete user " + selectedUser.getUserName());
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
              AccessDB.deleteUser(selectedUser);
              userTableView.setItems(AccessDB.allUsers());
        }        
    }

//End Class
}
