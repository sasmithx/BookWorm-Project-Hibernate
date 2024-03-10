package lk.ijse.BookWorm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.BookWorm.util.Navigation;

public class MainFormController {

    @FXML
    private JFXButton btnAdmin;

    @FXML
    private JFXButton btnUser;

    @FXML
    private JFXButton btnPower;

    @FXML
    void btnAdminOnAction(ActionEvent event) {
        btnAdmin.getScene().getWindow().hide();
        Navigation.changeStage("/view/adminLoginForm.fxml","ADMIN LOGIN");
    }

    @FXML
    void btnUserOnAction(ActionEvent event) {
        btnUser.getScene().getWindow().hide();
        Navigation.changeStage("/view/userLoginForm.fxml","USER LOGIN");
    }

    @FXML
    void btnPowerOnAction(ActionEvent event) {
        System.exit(0);
    }

}
