package lk.ijse.BookWorm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.BookWorm.dto.UserDTO;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.UserBO;
import lk.ijse.BookWorm.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserSignUpFormController implements Initializable {

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
    private AnchorPane SignUpPane;


    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UserBO);

    @FXML
    void btnBackOnAction(ActionEvent event) {
        btnLeftBack.getScene().getWindow().hide();
        Navigation.changeStage("/view/userLoginForm.fxml","LOGIN FORM");
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        String userId = txtUserId.getText();
        String userName = txtUserName.getText();
        String password = txtPwd.getText();

        UserDTO userDTO = new UserDTO(userId, userName, password);

        boolean saved = userBO.saveUsers(userDTO);

        if (saved) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "SIGN UP COMPLETED");
            alert.show();
            Stage stage = new Stage();
            stage.resizableProperty().setValue(true);
            try {
                URL resource = UserLoginFormController.class.getResource("/view/userLoginForm.fxml");
                Parent load = FXMLLoader.load(resource);
                stage.setScene(new Scene(load));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("BookWorm");

            stage.centerOnScreen();


            stage.show();
            SignUpPane.getScene().getWindow().hide();
        }
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {

    }
}
