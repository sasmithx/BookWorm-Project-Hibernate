package lk.ijse.BookWorm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserGlobalFormController implements Initializable {

    @FXML
    private JFXButton btnLogout;

    @FXML
    private Pane pagingPane;

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"userBookForm.fxml");
    }

    @FXML
    void btnBranchOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"userBranchForm.fxml");
    }

    @FXML
    void btnCatalogOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"userCatalogForm.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"userDashboardForm.fxml");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("mainForm.fxml",event);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"userUserForm.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pagingPane.setVisible(true);
        try {
            Navigation.switchPaging(pagingPane,"userDashboardForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
