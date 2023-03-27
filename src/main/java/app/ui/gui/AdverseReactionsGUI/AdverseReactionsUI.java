package app.ui.gui.AdverseReactionsGUI;

/**
 * imports that are necessary for the program execution and runability
 */

import app.controller.RegisterAdReaController;
import app.ui.gui.App;
import app.ui.gui.AlertUI;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import app.ui.gui.LoginWindowUI;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main class. Responsible for the management and construction of the functional JAVA FX and GUI
 */

public class AdverseReactionsUI {

    private App mainApp;
    private RegisterAdReaController controller;
    private inputAdReaUI inputUI;
    private LoginWindowUI loginUI;

    /**
     * constructor of the AdverseReactionsUI class
     * @param mainApp attribute of the App
     */
    public AdverseReactionsUI(App mainApp) {
        this.mainApp = mainApp;
        this.controller = new RegisterAdReaController();
    }

    public App getMainApp() {
        return this.mainApp;
    }

    /**
     * gets the functionalities and validations from the Controller method
     * @return functionalities and validations from the Controller method
     */

    public RegisterAdReaController getController(){return this.controller;}

    /**
     * sets the input class in the main class
     * @param inputUI attribute of the class inputAdReaUI
     */

    public void setinputAdReaUI(inputAdReaUI inputUI) {
        this.inputUI = inputUI;
    }

    /**
     * sets the login in the main class, this means, it defines the class LoginWindow of the fxml in the main class
     * @param loginUI
     */

    public void setLoginUI(LoginWindowUI loginUI) {
        this.loginUI = loginUI;
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }


    /**
     * Method related to the inputAdReaUI. This method is responsible for collecting, organizing and output all information that is related to the input class
     */
    public void inputclass() {
        try{
            inputAdReaUI inputUI = (inputAdReaUI) this.mainApp.replaceSceneContent("/fxml/AdverseReactionFXML/InputAdRea.fxml");
            inputUI.setAdversereactionsUI(this);
            inputUI.setLoginUI(this.loginUI);
        }catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    /**
     * Method related to the outputAdReaUI. This method is responsable for collecting, organizing and output all information that is related to the output class
     */
    public void toShowResults() {
        try{
            outputAdReaUI outputUI = (outputAdReaUI) this.mainApp.replaceSceneContent("/fxml/AdverseReactionFXML/Output.fxml");
            outputUI.setAdverseReactionsUI(this);
            outputUI.setInputUI(this.inputUI);
            outputUI.OutputResults();
        }catch(Exception e){
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }


}