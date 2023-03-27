package app.ui.gui.performanceGUI;

import app.controller.PerformanceAnalysisController;
import app.ui.gui.AlertUI;
import app.ui.gui.App;
import app.ui.gui.LoginWindowUI;

import app.ui.gui.RolesGUI.CoordinatorWindowUI;

public class PerformanceAnalysisUI {

    private App mainApp;
    private PerformanceAnalysisController controller;
    private PerformanceUI performanceUI;
    private LoginWindowUI loginUI;

    public PerformanceAnalysisUI(App mainApp) {
        this.mainApp = mainApp;
        this.controller = new PerformanceAnalysisController();
    }

    public App getMainApp() {
        return this.mainApp;
    }

    public PerformanceAnalysisController getController() {
        return this.controller;
    }

    public void setPerformanceUI(PerformanceUI performanceUI) {
        this.performanceUI = performanceUI;
    }

    public void setLoginUI(LoginWindowUI loginUI) {
        this.loginUI = loginUI;
    }

    public void toAnalyzePerformance() {
        try{
            PerformanceUI performanceUI = (PerformanceUI) this.mainApp.replaceSceneContent("/fxml/performanceFXML/performance.fxml");
            performanceUI.setPerformanceAnalysisUI(this);
            performanceUI.setLoginUI(this.loginUI);
        }catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

   public void toShowResults() {
        try{
            PerformanceResultUI perfResultUI = (PerformanceResultUI) this.mainApp.replaceSceneContentSplitPane("/fxml/performanceFXML/perfResult.fxml");
            perfResultUI.setPerformanceAnalysisUI(this);
            perfResultUI.setPerformanceUI(this.performanceUI);
            perfResultUI.showPerformanceResults();
        }catch(Exception e){
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }


}
