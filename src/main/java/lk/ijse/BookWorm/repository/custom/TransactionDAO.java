package lk.ijse.BookWorm.repository.custom;

import javafx.collections.ObservableList;
import lk.ijse.BookWorm.entity.Transaction;
import lk.ijse.BookWorm.repository.CrudDAO;

import java.sql.SQLException;

public interface TransactionDAO extends CrudDAO<Transaction,String> {
    String generateNextOrderId() throws SQLException, ClassNotFoundException;
    ObservableList<String> loadOrderID() throws SQLException, ClassNotFoundException;

}
