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

public class AdminGlobalFormController implements Initializable {

    @FXML
    private JFXButton btnLogout;

    @FXML
    public Pane pagingPane;

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"adminBookForm.fxml");
    }

    @FXML
    void btnBranchOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"adminBranchForm.fxml");
    }

    @FXML
    void btnCatalogOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"adminCatalogForm.fxml");
    }

    @FXML
    void btnDashbordOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"adminDashboardForm.fxml");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("mainForm.fxml",event);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"adminUserForm.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pagingPane.setVisible(true);
        try {
            Navigation.switchPaging(pagingPane,"adminDashboardForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
