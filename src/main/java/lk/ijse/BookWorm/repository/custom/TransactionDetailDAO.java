package lk.ijse.BookWorm.repository.custom;

import lk.ijse.BookWorm.repository.SuperDAO;
import lk.ijse.BookWorm.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDetailDAO extends SuperDAO {
  boolean  saveOrderDetail(String transactionId, List<CartTm> tmList) throws SQLException, ClassNotFoundException;

  boolean   saveOrderDetails(String transactionId, CartTm cartTm) throws SQLException, ClassNotFoundException;
}
