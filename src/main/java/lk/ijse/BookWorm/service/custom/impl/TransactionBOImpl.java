package lk.ijse.BookWorm.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.BookWorm.dto.TransactionDTO;
import lk.ijse.BookWorm.repository.DAOFactory;
import lk.ijse.BookWorm.repository.custom.BookDAO;
import lk.ijse.BookWorm.repository.custom.UserDAO;
import lk.ijse.BookWorm.service.custom.TransactionBO;

import java.sql.SQLException;

public class TransactionBOImpl implements TransactionBO {

    UserDAO userDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.UserDAO);
    BookDAO bookDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BookDAO);



    @Override
    public ObservableList<String> loadUserId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<String> loadBookId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean placeOrder(TransactionDTO transactionDTO) throws SQLException, ClassNotFoundException {
        return false;
    }
}
