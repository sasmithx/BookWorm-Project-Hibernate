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
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.dto.BookDTO;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.TransactionBO;
import lk.ijse.BookWorm.tm.CartTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

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

   public void loadCustomerId() throws SQLException, ClassNotFoundException {

      ObservableList<String> userData = transactionBO.loadUserId();
      cmbUserId.setItems(userData);
   }

   public void loadBookId() throws SQLException, ClassNotFoundException {

      ObservableList<String> bookData = transactionBO.loadBookId();
      cmbBookId.setItems(bookData);
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

      if(qty>Integer.parseInt(txtQty.getText())){
         new Alert(Alert.AlertType.CONFIRMATION,"out of stock or not enough stock").show();

      }else {
         setRemoveBtnOnAction(btnremove);
         for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if (code.equals(colBookId.getCellData(i))) {


               obList.get(i).setQty(qty);


               tblOrderCart.refresh();

               return;
            }
         }
         System.out.println(code);

         obList.add(new CartTm(
                 code,
                 description,
                 qty,
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

   }

   @FXML
   void cmbBookOnAcion(ActionEvent event) {

   }

   @FXML
   void cmbUserOnAction(ActionEvent event) {

   }

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {

   }
}
