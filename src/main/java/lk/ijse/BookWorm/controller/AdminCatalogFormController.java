package lk.ijse.BookWorm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.util.Navigation;

import java.io.IOException;

public class AdminCatalogFormController {

    @FXML
    private JFXButton btnBorrowed;

    @FXML
    private JFXButton btnReturned;

    @FXML
    private Pane pagingPane;

    @FXML
    void btnLateReturnOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"adminReturnBook.fxml");
    }

}
