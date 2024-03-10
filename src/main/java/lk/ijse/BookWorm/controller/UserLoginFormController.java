package lk.ijse.BookWorm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.BookWorm.util.Navigation;

import java.io.IOException;


public class UserLoginFormController {

    @FXML
    private Hyperlink hyperfp;

    @FXML
    private Hyperlink hyperfp2;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private JFXButton btnLeftBack;

    @FXML
    private ComboBox<?> cmbUserId;


    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("userGlobalForm.fxml",event);
    }

    @FXML
    void hlinkForgotPassword(ActionEvent event) {

    }

    @FXML
    void hlinkSignUp(ActionEvent event) {
        hyperfp2.getScene().getWindow().hide();
        Navigation.changeStage("/view/userSignUpForm.fxml","SIGN UP FORM");
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        btnLeftBack.getScene().getWindow().hide();
        Navigation.changeStage("/view/mainForm.fxml","MAIN FORM");
    }


}
