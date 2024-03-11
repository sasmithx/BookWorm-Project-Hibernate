package lk.ijse.BookWorm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.dto.BookDTO;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.BookBO;
import lk.ijse.BookWorm.tm.BookTM;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminBookFormController implements Initializable {

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<BookTM> tblBook;

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

    @FXML
    private TableColumn<?, ?> colAuthorName;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colQty;

    BookBO bookBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BookBO);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        new Alert(Alert.AlertType.CONFIRMATION,"Clear Successfully").show();
    }

    public void clearFields(){
        txtID.clear();
        txtBookName.clear();
        txtAuthorName.clear();
        txtGenre.clear();
        txtQty.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(id);
        boolean deleted = bookBO.deleteBooks(bookDTO);
        if(deleted){
            System.out.println("Delete Successfully");
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Successfully").show();
            loadAllBooks();
        }

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
            new Alert(Alert.AlertType.CONFIRMATION,"Saved Successfully").show();
            loadAllBooks();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtID.getText();
        String bookName = txtBookName.getText();
        String authorName = txtAuthorName.getText();
        String genre = txtGenre.getText();
        int qty = Integer.parseInt(txtQty.getText());

        BookDTO bookDTO = new BookDTO(id,bookName,authorName,genre,qty);
        boolean updated = bookBO.updateBooks(bookDTO);
        if(updated){
            System.out.println("Update Successfully");
            new Alert(Alert.AlertType.CONFIRMATION,"Update Successfully").show();
            loadAllBooks();
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValuFactory();
        loadAllBooks();
    }

    private void setCellValuFactory(){
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colAuthorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    /*private void loadAllBooks() throws SQLException {

        tblBook.getItems().clear();
        ArrayList<BookDTO> allBooks = bookBO.getAllBooks();
        for (BookDTO allBook : allBooks) {
            tblBook.getItems().addAll(new BookTM(
                    allBook.getId(),
                    allBook.getBookName(),
                    allBook.getAuthorName(),
                    allBook.getGenre(),
                    allBook.getQty()
                    ));
        }
    }*/

    private void loadAllBooks(){
        ObservableList<BookTM> obList = FXCollections.observableArrayList();
        tblBook.getItems().clear();

        try{
            ArrayList<BookDTO> list = bookBO.getAllBooks();
            for (BookDTO dto : list) {
                BookTM bookTM = new BookTM(
                        dto.getId(),
                        dto.getBookName(),
                        dto.getAuthorName(),
                        dto.getGenre(),
                        dto.getQty()
                );
                obList.add(bookTM);
            }
            tblBook.setItems(obList);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
