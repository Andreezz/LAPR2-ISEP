package app.ui.gui.RolesGUI;

import app.ui.gui.AdverseReactionsGUI.AdverseReactionsUI;
import app.ui.gui.AlertUI;
import app.ui.gui.App;
import app.ui.gui.LoginWindowUI;
import app.ui.gui.VaccineAdminGUI.VaccineAdminUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class NurseWindowUI implements Initializable {

    @FXML
    public Button reactionsButton;

    @FXML
    public Button vaccineAdminButton;

    @FXML
    public Button returnButton;

    private App mainApp;
    private LoginWindowUI loginUI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }
    public void setLoginUI(LoginWindowUI loginUI) {
        this.loginUI = loginUI;
    }
    public App getMainApp() {
        return this.mainApp;
    }


    public void vaccineAdminUI(ActionEvent actionEvent) {
        VaccineAdminUI vaccineAdminUI = null;
        try {
            vaccineAdminUI = (VaccineAdminUI) this.mainApp.replaceSceneContent("/fxml/vaccineAdminFXML/vaccineAdmin.fxml");
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
        vaccineAdminUI.setMainApp(mainApp);
    }

    public void reactionsUI(ActionEvent actionEvent) {
        try {
            AdverseReactionsUI reactionsUI = new AdverseReactionsUI(this.mainApp);
            reactionsUI.setLoginUI(this.loginUI);
            reactionsUI.inputclass();
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    public void returnMain(ActionEvent actionEvent) {
        try{
            this.mainApp.toMainScene();
        }catch (Exception e){
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }
}
