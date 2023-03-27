package app.ui.gui;

import app.controller.AuthController;
import app.domain.shared.Constants;
import app.ui.Main;
import app.ui.gui.RolesGUI.CoordinatorWindowUI;
import app.ui.gui.RolesGUI.NurseWindowUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginWindowUI implements Initializable {

    private App mainApp;
    private AuthController authController;
    private Stage stage;
    private UserRoleDTO role;
    @FXML
    private Button btnDoLogin;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authController = new AuthController();
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public App getMainApp() {
        return this.mainApp;
    }

    private void doLogin(ActionEvent actionEvent) {
        String email = emailField.getText();
        String password = passwordField.getText();
        boolean success;
        if (!email.isBlank() && !password.isBlank()) {
            success = authController.doLogin(email, password);

            if (!success) {
                AlertUI.infoAlert("Invalid UserId and/or Password.", "Login Error");
            } else {
                List<UserRoleDTO> roles = this.authController.getUserRoles();
                if ((roles == null) || (roles.isEmpty())) {
                    AlertUI.infoAlert("Login Error", "User has no valid roles.");
                } else {
                    this.role = roles.get(0);
                }
            }
        } else {
            AlertUI.infoAlert("Email/Password fields can't be empty", "Login Error");
        }
    }

    public void getUiForRole() {
        switch (this.role.getDescription()) {
            case Constants.ROLE_NURSE:

                try {
                    NurseWindowUI nurseUI = (NurseWindowUI) this.mainApp.replaceSceneContent("/fxml/RolesFXML/nurseWindow.fxml");
                    nurseUI.setLoginUI(this);
                    nurseUI.setMainApp(this.mainApp);
                } catch (Exception e) {
                    AlertUI.infoAlert(e.getMessage(), "Error");
                }

                break;
           case Constants.ROLE_COORDINATOR:
                try {
                    CoordinatorWindowUI cordUI = (CoordinatorWindowUI) this.mainApp.replaceSceneContent("/fxml/RolesFXML/cordWindow.fxml");
                    cordUI.setLoginUI(this);
                    cordUI.setMainApp(this.mainApp);
                } catch (Exception e) {
                    AlertUI.infoAlert(e.getMessage(), "Error");
                }
                break;
            default:
                AlertUI.infoAlert("Only the NurseUI and CoordinatorUI are available for GUI", "Login Error");
        }
    }

    @FXML
    private void doLoginButton(ActionEvent actionEvent) {
        doLogin(actionEvent);
        getUiForRole();
    }
}
