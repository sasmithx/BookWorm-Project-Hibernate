package lk.ijse.BookWorm.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.BookWorm.dto.TransactionDTO;
import lk.ijse.BookWorm.service.SuperBO;

import java.sql.SQLException;

public interface TransactionBO extends SuperBO {
    ObservableList<String> loadUserId() throws SQLException, ClassNotFoundException;
    ObservableList<String> loadBookId() throws SQLException, ClassNotFoundException;
    String generateNextOrderId() throws SQLException, ClassNotFoundException;
    boolean placeOrder(TransactionDTO transactionDTO) throws SQLException, ClassNotFoundException;
}
