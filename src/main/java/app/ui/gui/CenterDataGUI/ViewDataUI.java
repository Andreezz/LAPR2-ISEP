package app.ui.gui.CenterDataGUI;

import app.controller.CenterDataController;
import app.ui.gui.AlertUI;
import app.ui.gui.App;
import app.ui.gui.RolesGUI.CoordinatorWindowUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewDataUI implements Initializable {


    @FXML
    public TextArea list;
    private App mainApp;

    private CenterDataController ctrl;

    @FXML
    public Button listButton;

    @FXML
    public Label selectionLabel;
    @FXML
    public ComboBox orderBox;
    @FXML
    public ComboBox typeBox;

    @FXML
    public Button returnButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.setVisible(false);
        ctrl = new CenterDataController();
        String[] orderOptions = {"Ascending", "Descending"};
        String[] typeOptions = {"ArrivalDateTime", "LeavingDateTime"};
        orderBox.setItems(FXCollections.observableArrayList(orderOptions));
        typeBox.setItems(FXCollections.observableArrayList(typeOptions));
    }

    public void setMainApp(App mainApp){
        this.mainApp = mainApp;
    }

    public void viewList(ActionEvent actionEvent) {
        if(!(typeBox.getSelectionModel().isEmpty()) && !(orderBox.getSelectionModel().isEmpty())) {
            int order = getOrderInt(String.valueOf(orderBox.getValue()));
            int type = getTypeInt(String.valueOf(typeBox.getValue()));
            orderBox.setVisible(false);
            orderBox.setDisable(true);
            typeBox.setVisible(false);
            typeBox.setDisable(true);
            listButton.setDisable(true);
            listButton.setVisible(false);
            selectionLabel.setVisible(false);
            if (!ctrl.isListEmpty()) {
                list.setVisible(true);
                list.setText(ctrl.getSortedList(order,type));
            } else {
                AlertUI.infoAlert("list is empty!", "");
            }
        }
        else{
            AlertUI.infoAlert("You need to choose the order and type of sorting to view the list!", "Error");
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

    public int getOrderInt(String option){
        if(option.equals("Ascending"))
            return 1;
        else
            return 2;
    }

    public int getTypeInt(String option){
        if(option.equals("ArrivalDateTime"))
            return 1;
        else
            return 2;
    }
}
