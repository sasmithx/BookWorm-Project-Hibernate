package lk.ijse.BookWorm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.BookWorm.util.Navigation;

public class AdminChangePasswordFormController {

    @FXML
    private Label lblUsrId;

    @FXML
    private TextField txtConfirmPwd;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private JFXButton btnLeftBack;

    @FXML
    void ChangeOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        btnLeftBack.getScene().getWindow().hide();
        Navigation.changeStage("/view/adminGlobalForm.fxml","DASHBOARD FORM");
    }

}
