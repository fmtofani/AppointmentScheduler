/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package View;

import Model.Report;
import Model.AccessDB;
import Util.TimeUtil;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author micha
 */
public class ReportsController implements Initializable {

    @FXML
    private Button exitReportsButton;
    @FXML
    private Button viewReport_3Button;
    @FXML
    private Button viewReport_2Button;
    @FXML
    private Button viewReport_1Button;
    @FXML
    private Button forwardButton;
    @FXML
    private Button backButton;
    @FXML
    private TableView<Report> reportTableView;
    @FXML
    private TableColumn<Report, String> usernameColumn;
    @FXML
    private TableColumn<Report, String> dateColumn;
    @FXML
    private TableColumn<Report, String> timeColumn;
    @FXML
    private TableColumn<Report, String> clientColumn;
    @FXML
    private TableColumn<Report, String> typeColumn;
    @FXML
    private Label monthLabel;
    @FXML
    private Label fillLabel;
    @FXML
    private Label monthOfLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        monthLabel.setVisible(false);
        backButton.setVisible(false);
        monthOfLabel.setVisible(false);
        fillLabel.setVisible(false);
        forwardButton.setVisible(false);
        //Fill the table
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
        clientColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
       
    }    

    @FXML
    private void viewReport_1Handler(ActionEvent event) {
        monthLabel.setVisible(true);
        backButton.setVisible(true);
        forwardButton.setVisible(true);
        monthOfLabel.setVisible(true);
        fillLabel.setVisible(true);
        fillLabel.setText(TimeUtil.thisMonth(LocalDate.now()));
        reportTableView.setItems(AccessDB.report1(LocalDate.now()));
       
    }

    @FXML
    private void viewReport_2Handler(ActionEvent event) {
        monthLabel.setVisible(false);
        backButton.setVisible(false);
        forwardButton.setVisible(false);
        reportTableView.setItems(AccessDB.report2());
    }

    @FXML
    private void viewReport_3Handler(ActionEvent event) {
        monthLabel.setVisible(false);
        backButton.setVisible(false);
        forwardButton.setVisible(false);
        reportTableView.setItems(AccessDB.report3());
    }

    int forwardCounter = 1;
    @FXML
    private void forwardHandler(ActionEvent event) {
        LocalDate ld = LocalDate.parse(TimeUtil.getNowDate());
        reportTableView.setItems(AccessDB.report1(ld.plusMonths(forwardCounter)));
        fillLabel.setText(TimeUtil.thisMonth(ld.plusMonths(forwardCounter)));
        forwardCounter++;
        backCounter--;
    } 
    
    int backCounter = 1;
    @FXML
    private void backHandler(ActionEvent event) {
        LocalDate ld = LocalDate.parse(TimeUtil.getNowDate());
        reportTableView.setItems(AccessDB.report1(ld.minusMonths(backCounter)));            
        fillLabel.setText(TimeUtil.thisMonth(ld.minusMonths(backCounter)));
        backCounter++;
        forwardCounter--;
    }

    @FXML
    private void exitReportsHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("CANCEL");
        alert.setContentText("Are you sure that you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {      
            Stage stage; 
            Parent root;
            stage=(Stage) exitReportsButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();        
        }
   }

    
    //End Class
}
