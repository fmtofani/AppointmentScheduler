/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    private Label calenderView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backHandler(ActionEvent event) {
    }

    @FXML
    private void forwardHandler(ActionEvent event) {
    }
    
}
