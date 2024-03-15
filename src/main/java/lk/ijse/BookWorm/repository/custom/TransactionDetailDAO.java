package lk.ijse.BookWorm.repository.custom;

import lk.ijse.BookWorm.entity.Transaction;
import lk.ijse.BookWorm.entity.TransactionDetail;
import lk.ijse.BookWorm.repository.SuperDAO;
import lk.ijse.BookWorm.tm.CartTm;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDetailDAO extends SuperDAO {
  boolean  saveOrderDetail(Transaction transactionId, List<TransactionDetail> tmList) throws SQLException, ClassNotFoundException;

  boolean   saveOrderDetails(Transaction transactionId, TransactionDetail cartTm) throws SQLException, ClassNotFoundException;

    void setSession(Session session);
}
