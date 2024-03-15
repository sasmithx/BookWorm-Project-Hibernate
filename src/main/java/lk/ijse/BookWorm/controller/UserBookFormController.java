package lk.ijse.BookWorm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.dto.BookDTO;
import lk.ijse.BookWorm.dto.TransactionDTO;
import lk.ijse.BookWorm.dto.UserDTO;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.TransactionBO;
import lk.ijse.BookWorm.tm.CartTm;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class UserBookFormController implements Initializable {

   private final ObservableList<CartTm> obList = FXCollections.observableArrayList();

   @FXML
   private JFXButton btnAddToCart;

   @FXML
   private JFXButton btnBorrow;

   @FXML
   private JFXComboBox<String> cmbBookId;

   @FXML
   private JFXComboBox<String> cmbUserId;

   @FXML
   private TableColumn<?, ?> colAction;

   @FXML
   private TableColumn<?, ?> colBookId;

   @FXML
   private TableColumn<?, ?> colQty;

   @FXML
   private TableColumn<?, ?> colTitle;

   @FXML
   private Pane pagingPane;

   @FXML
   private TableView<CartTm> tblOrderCart;

   @FXML
   private TextField txtOrderDate;

   @FXML
   private TextField txtQty;

   @FXML
   private TextField txtTitle;

   @FXML
   private TextField txtTotal;

   @FXML
   private TextField txtTransactionId;

   @FXML
   private TextField txtUserName;


   TransactionBO transactionBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TransactionBO);

   BookDTO bookDTO;

   public void userIdClick(){
      try {
         User user = transactionBO.searchUser((String) cmbUserId.getValue());
         txtUserName.setText(user.getName());
      } catch (Exception throwables) {

      }
   }

   public void loadUserId() throws SQLException, ClassNotFoundException {

      ArrayList<UserDTO> allUsers = transactionBO.getAllUsers();
      for(UserDTO userDTO : allUsers){
         cmbUserId.getItems().add(userDTO.getId());
      }
   }

   public void loadBookId() throws SQLException, ClassNotFoundException {

    /*  ArrayList<BookDTO> allUsers = transactionBO.getAllBooks();
      for(BookDTO bookDTO1 : allBooks){
         cmbBookId.getItems().add(.getId());
      }*/
      ObservableList<String> bookData = transactionBO.loadBookId();
      cmbBookId.setItems(bookData);
   }

   public boolean isContain(String bookId,int qty){
      for(CartTm cartTm : obList){
        if(cartTm.getBookID().equals(bookId) && (cartTm.getQty()>=qty)) return true;
      }
      return false;
   }

   @FXML
   void btnAddToCartOnAction(ActionEvent event) {
      String code = cmbBookId.getValue();
      String description = txtTitle.getText();
      int qty = Integer.parseInt(txtQty.getText());

      JFXButton btnremove = new JFXButton("Remove");
      btnremove.setCursor(Cursor.HAND);
      // Setting style using CSS
      btnremove.setStyle("-fx-background-color: #f68fad; -fx-text-fill: white;");

      if(isContain(code,qty)){
         new Alert(Alert.AlertType.CONFIRMATION,"out of stock or not enough stock").show();

      }else {
         setRemoveBtnOnAction(btnremove);
         for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if (code.equals(colBookId.getCellData(i))) {


               int qty1 = obList.get(i).getQty();
               obList.get(i).setQty(qty1+1);


               tblOrderCart.refresh();

               return;
            }
         }
         System.out.println(code);

         obList.add(new CartTm(
                 code,
                 description,
                 1,
                 btnremove
         ));
         tblOrderCart.setItems(obList);

         txtQty.clear();
      }

   }

   private void setRemoveBtnOnAction(Button btn) {
      btn.setOnAction((e) -> {
         ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
         ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

         Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

         if (result.orElse(no) == yes) {
            int index = tblOrderCart.getSelectionModel().getSelectedIndex();

            TablePosition pos = tblOrderCart.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            ObservableList<TableColumn<CartTm, ?>> columns = tblOrderCart.getColumns();


            obList.remove(index);
            tblOrderCart.refresh();
         }

      });
   }

   @FXML
   void btnBorrowOnAction(ActionEvent event) {
//      String transactionId = txtTransactionId.getText();
//      String bookId = cmbBookId.getValue();
      LocalDate orderDate = LocalDate.parse(txtOrderDate.getText());
      String userName = txtUserName.getText();
      int qty = tblOrderCart.getItems().size();





      List<CartTm> tmList = new ArrayList<>();

      for (CartTm cartTm : obList) {
         tmList.add(cartTm);
      }

      var transactionDTO = new TransactionDTO(
              -1,
              orderDate,
              userName,
              qty,
              tmList
      );

      boolean isSuccess = false;
      try {
         isSuccess = transactionBO.placeOrder(transactionDTO);
         System.out.println(isSuccess+"success");
//         txtTransactionId.setText(transactionBO.generateNextOrderId());
      } catch (SQLException | ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
      if(isSuccess) {
         ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
         ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

         Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Borrow completed!...", yes, no).showAndWait();

      }
   }

   @FXML
   void cmbBookOnAcion(ActionEvent event) {
      try {
         bookDTO = transactionBO.searchBook(cmbBookId.getValue());
      } catch (SQLException | ClassNotFoundException e) {
         new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
      }
      txtTitle.setText(bookDTO.getBookName());
      txtQty.setText(String.valueOf(bookDTO.getQty()));

   }

   @FXML
   void cmbUserOnAction(ActionEvent event) {
      userIdClick();
   }

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
      /* try{
          loadUserId();
          loadBookId();
          setCellValueFactory();
       }catch (Exception e){
          e.printStackTrace();
       }*/

      try {
         loadUserId();
         loadBookId();
         setCellValueFactory();
         /*txtTransactionId.setText(transactionBO.generateNextOrderId());*/

         txtOrderDate.setText(String.valueOf(LocalDate.now()));
      } catch (SQLException | ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   private void setCellValueFactory(){
      /*colBookId.setCellValueFactory(new PropertyValueFactory<>("code"));
      colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
      colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
      colAction.setCellValueFactory(new PropertyValueFactory<>("removebtn"));*/

      tblOrderCart.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookID"));
      tblOrderCart.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
      tblOrderCart.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
      tblOrderCart.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("removebtn"));

   }
}
