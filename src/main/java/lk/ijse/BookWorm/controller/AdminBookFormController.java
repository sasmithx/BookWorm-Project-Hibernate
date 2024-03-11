package lk.ijse.BookWorm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.dto.BookDTO;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.BookBO;

public class AdminBookFormController {

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<?> tblBook;

    @FXML
    private TextField txtAuthorName;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtQty;

    BookBO bookBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BookBO);

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtID.getText();
        String bookName = txtBookName.getText();
        String authorName = txtAuthorName.getText();
        String genre = txtGenre.getText();
        int qty = Integer.parseInt(txtQty.getText());

        BookDTO bookDTO = new BookDTO(id,bookName,authorName,genre,qty);
        boolean saved = bookBO.saveBooks(bookDTO);
        if(saved){
            System.out.println("Saved Successfully");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
