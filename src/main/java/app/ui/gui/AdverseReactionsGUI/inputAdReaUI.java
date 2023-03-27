package app.ui.gui.AdverseReactionsGUI;


/**
 * imports necessary for the construction and execution of the program
 */

import app.ui.gui.AlertUI;
import app.ui.gui.App;
import app.ui.gui.RolesGUI.NurseWindowUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import app.ui.gui.LoginWindowUI;



import java.net.URL;
import java.util.ResourceBundle;

import static app.domain.model.CheckDate.checkDate;


/**
 * Class responsible for the input
 */

public class inputAdReaUI implements Initializable {


    private App mainApp;
    private AdverseReactionsUI adversereactionsUI;
    private LoginWindowUI loginUI;
    private app.ui.console.AdverseReacUI adreaui = new app.ui.console.AdverseReacUI();

    /**
     * buttons and text fields/areas, present in the JAVAFX (jxml), that execute operations
     */
    @FXML
    private Button okbutton;

    @FXML
    private Button EXIT;

    @FXML
    private TextField snsnumber;

    @FXML
    private TextArea adversereactions;


    public void setMainApp(App mainApp) {this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * gets the SNS User's SNS Number
     * @return the SNS User's SNS Number
     */
    public TextField getSnsnumber() {
        return this.snsnumber;
    }

    /**
     * gets the SNS User's adverse reactions
     * @return the SNS User's adverse reactions
     */
    public TextArea getAdversereactions() {
        return this.adversereactions;
    }

    /**
     * sets the main class in the input class, making a connection
     * @param adversereactionsUI attribute of the main class
     */
    public void setAdversereactionsUI(AdverseReactionsUI adversereactionsUI) {
        this.adversereactionsUI = adversereactionsUI;
    }

    /**
     * sets the login in the main class, this means, it defines the class LoginWindow of the fxml in the main class
     * @param loginUI
     */

    public void setLoginUI(LoginWindowUI loginUI) {
        this.loginUI = loginUI;
    }

    /**
     * function related to the button "okbutton", that validates the information given
     * @param actionEvent javafx attribute
     */
    @FXML
    public void startValidation(ActionEvent actionEvent) {
        try {
            adversereactionsUI.setinputAdReaUI(this);
            if( snsnumber.getLength()>0 ) {
                this.adversereactionsUI.toShowResults();
            }else{
                throw new Exception("Error : Invalid SNS Number! Please try again");
            }
        }catch(Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error 404 :/");
        }
    }

    /**
     * method associated with "EXIT" button, that takes the User out of the page and puts him in the nurse menu
     * @param actionEvent javafx attribute
     */
   @FXML
    public void exitButton(ActionEvent actionEvent) {
        try {
            NurseWindowUI NurseUI = (NurseWindowUI) this.mainApp.replaceSceneContent("/fxml/RolesFXML/nurseWindow.fxml");
            NurseUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }


}
