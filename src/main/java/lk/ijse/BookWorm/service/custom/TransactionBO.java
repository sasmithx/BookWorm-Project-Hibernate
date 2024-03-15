package lk.ijse.BookWorm.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.BookWorm.dto.BookDTO;
import lk.ijse.BookWorm.dto.TransactionDTO;
import lk.ijse.BookWorm.dto.UserDTO;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TransactionBO extends SuperBO {
    User searchUser(String newValue) throws SQLException, ClassNotFoundException;
    List<String> loadUserId() throws SQLException, ClassNotFoundException;
    BookDTO searchBook(String value) throws SQLException, ClassNotFoundException;
    ObservableList<String> loadBookId() throws SQLException, ClassNotFoundException;
//    String generateNextOrderId() throws SQLException, ClassNotFoundException;
    boolean placeOrder(TransactionDTO transactionDTO) throws SQLException, ClassNotFoundException;
    ArrayList<UserDTO> getAllUsers() throws SQLException;

    ArrayList<BookDTO> getAllBooks() throws SQLException;

}
