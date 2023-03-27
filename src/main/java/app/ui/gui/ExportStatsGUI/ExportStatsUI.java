package app.ui.gui.ExportStatsGUI;

import app.controller.ExportCtrl;
import app.domain.model.CheckDate;
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

public class ExportStatsUI implements Initializable {

    @FXML
    public TextField date1Text;
    @FXML
    public TextField date2Text;
    @FXML
    public Button returnButton;
    @FXML
    public Button exportButton;
    @FXML
    public TextField nameText;
    @FXML
    public Label finalLabel;

    private App mainApp;

    private ExportCtrl ctrl;

    public void setMainApp(App mainApp){
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ctrl = new ExportCtrl();
    }

    public void returnCord(ActionEvent actionEvent) {
        try {
            CoordinatorWindowUI cordUI = (CoordinatorWindowUI) this.mainApp.replaceSceneContent("/fxml/RolesFXML/cordWindow.fxml");
            cordUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    public void export(ActionEvent actionEvent) {
        String fileName = nameText.getText();
        String date1 = date1Text.getText();
        String date2 = date2Text.getText();
        if(CheckDate.checkDate(date1) && CheckDate.checkDate(date2)) {
            ctrl.createExport(fileName);
            if (ctrl.checkFileName(fileName)) {
                try {
                    if (ctrl.export(date1, date2))
                        finalLabel.setText("The file was loaded successfully!");
                } catch (Exception e) {
                    AlertUI.infoAlert(e.getMessage(), "File was not loaded!");
                }
            } else {
                AlertUI.infoAlert("File's name is invalid!", "Error");
            }
        }else  AlertUI.infoAlert("Date format is invalid!", "Error");
    }
}
