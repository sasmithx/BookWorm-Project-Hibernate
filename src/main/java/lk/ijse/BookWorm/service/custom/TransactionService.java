package lk.ijse.BookWorm.service.custom;

import lk.ijse.BookWorm.dto.TransactionDTO;
import lk.ijse.BookWorm.dto.UserDTO;
import lk.ijse.BookWorm.service.SuperBO;

import java.sql.SQLException;

public interface TransactionService extends SuperBO {
    boolean saveTransaction(TransactionDTO transactionDTO) throws SQLException, ClassNotFoundException;
}
