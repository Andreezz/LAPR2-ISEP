package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUI implements Initializable {

    @FXML
    public Button loginButton;
    @FXML
    public Button devButton;

    private App mainApp;

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public App getMainApp() {
        return this.mainApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void dev(ActionEvent actionEvent) {
        try {
            DevUI developersUI = (DevUI) this.mainApp.replaceSceneContent("/fxml/Dev.fxml");
            developersUI.setMainApp(this.mainApp);
            developersUI.setMainUI(this);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }

    }

    @FXML
    public void login(ActionEvent actionEvent) {
        try {
            LoginWindowUI loginUI = (LoginWindowUI) this.mainApp.replaceSceneContent("/fxml/loginWindow.fxml");
            loginUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }
}
