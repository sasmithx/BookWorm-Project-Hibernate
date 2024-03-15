package lk.ijse.BookWorm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.BookWorm.dto.UserDTO;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.UserBO;
import lk.ijse.BookWorm.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UserLoginFormController implements Initializable {

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
    private AnchorPane paneLogin;
    
    static String id;

    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UserBO);
    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("userGlobalForm.fxml",event);

       /* if (userBO.verifyCredentials(txtUserName.getText(),txtPassword.getText())){
            try {
                Navigation.switchNavigation("dashboardForm.fxml",event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/

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


    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {

    }
}
