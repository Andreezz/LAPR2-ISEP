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
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadDataUI implements Initializable {

    public Button returnButton;
    private App mainApp;

    private CenterDataController ctrl;

    public Button loadButton;
    public Label loadMessage;
    public Label loadDataLabel;
    public TextField pathText;

    public Button viewData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMessage.setVisible(false);
        viewData.setDisable(true);
        viewData.setVisible(false);
        ctrl = new CenterDataController();
    }

    public void setMainApp(App mainApp){
        this.mainApp = mainApp;
    }

    public void confirmLoadData(ActionEvent actionEvent) {
        try {
            ctrl.createCenterDataFileLoader(pathText.getText());
        }catch (Exception e){
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
        if(ctrl.loadCenterDataFile())
            loadMessage.setText("The file was loaded successfully!");
        else
            loadMessage.setText("The file was not loaded due to an error!");
        loadDataLabel.setVisible(false);
        loadButton.setDisable(true);
        loadButton.setVisible(false);
        pathText.setDisable(true);
        pathText.setVisible(false);
        loadMessage.setVisible(true);
        viewData.setDisable(false);
        viewData.setVisible(true);
    }

    public void viewData(ActionEvent actionEvent) {
        try {
            ViewDataUI viewUI = (ViewDataUI) this.mainApp.replaceSceneContent("/fxml/CenterDataFXML/viewDataWindow.fxml");
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
