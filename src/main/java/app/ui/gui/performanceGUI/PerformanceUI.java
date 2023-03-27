package app.ui.gui.performanceGUI;

import app.controller.PerformanceAnalysisController;
import app.domain.model.CheckDate;
import app.ui.gui.AlertUI;
import app.ui.gui.App;
import app.ui.gui.LoginWindowUI;
import app.ui.gui.RolesGUI.CoordinatorWindowUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import static app.domain.model.CheckDate.checkDate;

import java.net.URL;
import java.util.ResourceBundle;

public class PerformanceUI implements Initializable {

    private App mainApp;
    private PerformanceAnalysisUI performanceAnalysis;
    private LoginWindowUI loginUI;
    private app.ui.console.PerformanceAnalysisUI perf = new app.ui.console.PerformanceAnalysisUI();

    @FXML
    private Button startAnalysis;
    @FXML
    private Button btnReturn;
    @FXML
    private TextField date;
    @FXML
    private TextField time;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public TextField getDate() {
        return this.date;
    }

    public TextField getTime() {
        return this.time;
    }

    public void setPerformanceAnalysisUI(PerformanceAnalysisUI performanceAnalysis) {
        this.performanceAnalysis = performanceAnalysis;
    }

    public void setLoginUI(LoginWindowUI loginUI) {
        this.loginUI = loginUI;
    }

    @FXML
    public void startAnalysisButton(ActionEvent actionEvent) {
        try{
            performanceAnalysis.setPerformanceUI(this);
            if( checkDate(date.getText()) && perf.checkTime(Integer.parseInt(time.getText()))) {
                this.performanceAnalysis.toShowResults();
            }else{
                throw new Exception("Error : Invalid Date or Time! Please try again");
            }
        }catch(Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error 404 :/");
        }
    }

    @FXML
    public void returnButton(ActionEvent actionEvent) {
        this.loginUI.getUiForRole();
    }


}
