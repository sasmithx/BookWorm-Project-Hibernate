package lk.ijse.BookWorm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.UserBO;
import lk.ijse.BookWorm.util.Navigation;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UserLoginFormController implements Initializable{

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
    private AnchorPane paneLogin;

    @FXML
    private ComboBox<String> cmbUserID;


    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UserBO);
    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
//        Navigation.switchNavigation("userGlobalForm.fxml",event);

        String password = userBO.verifyCredentials(cmbUserID.getValue());
        System.out.println(password);




        if(txtPassword.getText().equals(password) ){
            try {
                Navigation.switchNavigation("userGlobalForm.fxml",event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void loadIds() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        ArrayList<String> userIds = userBO.loadUserIds();
        for (String studentId : userIds) {
            obList.add(studentId);
        }
        cmbUserID.setItems(obList);
    }
    @FXML
    void cmbUserIdAction(ActionEvent event) {

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
        try {
            loadIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
