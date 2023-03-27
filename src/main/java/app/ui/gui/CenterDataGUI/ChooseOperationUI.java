package app.ui.gui.CenterDataGUI;

import app.controller.CenterDataController;
import app.ui.gui.AlertUI;
import app.ui.gui.App;
import app.ui.gui.RolesGUI.CoordinatorWindowUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class ChooseOperationUI implements Initializable {



    @FXML
    public Button returnButton;

    @FXML
    private Label chooseOption;

    private App mainApp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setMainApp(App mainApp){
        this.mainApp = mainApp;
    }

    public void loadCenterData(ActionEvent actionEvent) {
        try {
            LoadDataUI loadUI = (LoadDataUI) this.mainApp.replaceSceneContent("/fxml/CenterDataFXML/loadDataWindow.fxml");
            loadUI.setMainApp(mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }

    }

    public void viewCenterData(ActionEvent actionEvent) {
        try {
            ViewDataUI  viewUI = (ViewDataUI) this.mainApp.replaceSceneContent("/fxml/CenterDataFXML/viewDataWindow.fxml");
            viewUI.setMainApp(mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    public void returnCord(ActionEvent actionEvent) {
        try {
            CoordinatorWindowUI cordUI = (CoordinatorWindowUI) this.mainApp.replaceSceneContent("/fxml/RolesFXML/cordWindow.fxml");
            cordUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }
}