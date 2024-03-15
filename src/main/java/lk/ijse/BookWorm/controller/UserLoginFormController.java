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

        /*id = (String) cmbUserId.getValue();

        UserDTO userDTO = userBO.searchUsers((String) cmbUserId.getValue());
        String userName = userDTO.getName();
        String password = userDTO.getPassword();

        if (userName.equals(txtUserName.getText()) && password.equals(txtPassword.getText())) {
//            new ChangePwdController(userDTO.getUserId());
            Stage stage = new Stage();
            stage.resizableProperty().setValue(false);
            try {
                URL resource = UserGlobalFormController.class.getResource("/view/userGlobalForm.fxml");
                Parent load = FXMLLoader.load(resource);
                stage.setScene(new Scene(load));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("BookWorm");

            stage.centerOnScreen();


            stage.show();
            paneLogin.getScene().getWindow().hide();
            UserGlobalFormController changePwdController = new UserGlobalFormController();
            changePwdController.setUserId((String) cmbUserId.getValue());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Login Incomplete!");
            alert.show();
        }
*/

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
