 package app.ui.gui.performanceGUI;

import app.algorithms.Benchmark;
import app.algorithms.BruteForce;
import app.domain.model.PerformanceAnalysis;
import app.domain.model.PerformanceEvaluator;
import app.properties.PropertiesCache;
import app.ui.gui.App;
import app.ui.gui.RolesGUI.CoordinatorWindowUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PerformanceResultUI implements Initializable {

    private PerformanceUI performanceUI;
    private PerformanceAnalysis perfAnalysis = new PerformanceAnalysis();
    private PerformanceAnalysisUI performanceAnalysis;

    @FXML
    private Button btnExit;
    @FXML
    private Label lblInputList;
    @FXML
    private Label lblMaxSubList;
    @FXML
    private Label lblSum;
    @FXML
    private Label lblTimeInterval;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setPerformanceAnalysisUI(PerformanceAnalysisUI performanceAnalysis) {
        this.performanceAnalysis = performanceAnalysis;
    }

    public void setPerformanceUI(PerformanceUI performanceUI) {
        this.performanceUI = performanceUI;
    }

    public void showPerformanceResults() throws Exception {
        int time = Integer.parseInt(performanceUI.getTime().getText());
        int[] inputList = perfAnalysis.difArrivalLeaving( performanceUI.getDate().getText() , time , performanceAnalysis.getController().getCenterDataDTO() , performanceAnalysis.getController().getArrivalsDTO());

        int[] maxSublist;
        int sum;

        BruteForce bf = new BruteForce();
        maxSublist = perfAnalysis.computeMaxSublist(inputList,bf);
        sum = perfAnalysis.getSublistSum(maxSublist);
        String time_interval = perfAnalysis.getSublistTimeInterval(bf);

        this.lblInputList.setText("Input List : " + Arrays.toString(inputList));
        this.lblMaxSubList.setText("Max Sublist: " + Arrays.toString(maxSublist));
        this.lblSum.setText("Sum : " + String.valueOf(sum));
        this.lblTimeInterval.setText("Time Interval : " + time_interval);
    }

    @FXML
    private void btnExitAction(ActionEvent event) {
        this.performanceAnalysis.getMainApp().toMainScene();
    }
}
