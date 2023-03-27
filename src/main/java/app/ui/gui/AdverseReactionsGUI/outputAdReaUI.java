/**
 * imports necessary for the construction and execution of the program
 */

package app.ui.gui.AdverseReactionsGUI;
import app.ui.gui.App;
import app.ui.gui.RolesGUI.NurseWindowUI;
import java.net.URL;
import java.util.ResourceBundle;
import app.ui.gui.AdverseReactionsGUI.AdverseReactionsUI;
import app.ui.gui.AdverseReactionsGUI.inputAdReaUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import app.domain.model.AdverseReac;
import java.text.ParseException;
import java.util.Arrays;
import java.util.regex.Pattern;

import app.ui.console.AdverseReacUI;
import app.controller.RegisterAdReaController;
import app.domain.stores.AdverseReacStore;
import app.domain.shared.Constants;
import app.ui.gui.AdverseReactionsGUI.inputAdReaUI;

/**
 * Class responsible for the output
 */

public class outputAdReaUI implements Initializable{

    private inputAdReaUI inputUI;
   private AdverseReac inputanalise = new AdverseReac();
    private AdverseReactionsUI inputdata;

    private App mainApp;

    /**
     * buttons and text fields/areas, present in the JAVAFX (jxml), that execute operations
     */

    @FXML
    private Button Exitbutton;
    @FXML
    private Label analysis ;
    @FXML
    private Label registinfo;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * sets the main class, making a connection between the output and main class
     * @param inputdata is a attribute of the AdverseReactionsUI classn
     */
    public void setAdverseReactionsUI(AdverseReactionsUI inputdata) {
        this.inputdata = inputdata;
    }

    /**
     * sets the input class in the output class, for data importation
     * @param inputUI attribute of the input class, for data importation
     */
    public void setInputUI(inputAdReaUI inputUI) {
        this.inputUI = inputUI;
    }

    /**
     *analysis the input given by the user and returns a response to the JAVAFX screen
     */
    public void OutputResults() throws Exception {

        String snsnumber = inputUI.getSnsnumber().getText();
        String adversereactions = inputUI.getAdversereactions().getText();


        if (snsnumber.length()==9) {
            this.analysis.setText("ANALYSIS : Your input was validated");
            this.registinfo.setText("REGISTERED INFORMATION | SNS NUMBER: " + snsnumber + ", ADVERSE REACTIONS: " + adversereactions + ".");
        } else {
            this.analysis.setText("ANALYSIS: Your input was wrongly inserted");
            this.registinfo.setText(".");
        }
    }

    /**
     * functions that exits the User from the page, returning in into the Main page
     * @param event javafx attribute
     */
    @FXML
    private void exitbut(ActionEvent event) {
        this.inputdata.getMainApp().toMainScene();
    }




}
