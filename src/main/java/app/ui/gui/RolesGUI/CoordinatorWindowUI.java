 package app.ui.gui.RolesGUI;

import app.ui.gui.AlertUI;
import app.ui.gui.App;
import app.ui.gui.CenterDataGUI.ChooseOperationUI;
import app.ui.gui.ExportStatsGUI.ExportStatsUI;
import app.ui.gui.LoginWindowUI;
import app.ui.gui.performanceGUI.PerformanceAnalysisUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class CoordinatorWindowUI implements Initializable {

    @FXML
    public Button centerData;
    @FXML
    public Button exportStats;
    @FXML
    public Button centerPerformance;

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


    public void centerData(ActionEvent actionEvent) {
        try {
            ChooseOperationUI centerDataUI = (ChooseOperationUI) this.mainApp.replaceSceneContent("/fxml/CenterDataFXML/ChooseOperationWindow.fxml");
            centerDataUI.setMainApp(mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    public void exportStats(ActionEvent actionEvent) {
        try {
            ExportStatsUI exportUI = (ExportStatsUI) this.mainApp.replaceSceneContent("/fxml/exportFXML/export.fxml");
            exportUI.setMainApp(mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }


    public void centerPerformance(ActionEvent actionEvent) {
        try {
            PerformanceAnalysisUI perfAnalysis = new PerformanceAnalysisUI(this.mainApp);
            perfAnalysis.setLoginUI(this.loginUI);
            perfAnalysis.toAnalyzePerformance();
        }catch(Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error 404 : Date or Time invalid!!! Try again :/");
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


