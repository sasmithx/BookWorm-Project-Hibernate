package lk.ijse.BookWorm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.ijse.BookWorm.util.Navigation;

public class UserSignUpFormController {

    @FXML
    private JFXButton btnLeftBack;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private TextField txtPwd;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnBackOnAction(ActionEvent event) {
        btnLeftBack.getScene().getWindow().hide();
        Navigation.changeStage("/view/userLoginForm.fxml","LOGIN FORM");
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {

    }

}
