package lk.ijse.BookWorm.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.BookWorm.dto.TransactionDTO;
import lk.ijse.BookWorm.entity.Book;
import lk.ijse.BookWorm.entity.Transaction;
import lk.ijse.BookWorm.repository.DAOFactory;
import lk.ijse.BookWorm.repository.custom.BookDAO;
import lk.ijse.BookWorm.repository.custom.TransactionDAO;
import lk.ijse.BookWorm.repository.custom.UserDAO;
import lk.ijse.BookWorm.service.custom.TransactionBO;

import java.sql.SQLException;

public class TransactionBOImpl implements TransactionBO {

    UserDAO userDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.UserDAO);
    BookDAO bookDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BookDAO);
    TransactionDAO transactionDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TransactionDAO);


    @Override
    public ObservableList<String> loadUserId() throws SQLException, ClassNotFoundException {
        return userDAO.loadUserId();
    }

    @Override
    public ObservableList<String> loadBookId() throws SQLException, ClassNotFoundException {
        return bookDAO.loadBookId();
    }

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return transactionDAO.generateNextOrderId();
    }

    @Override
    public boolean placeOrder(TransactionDTO transactionDTO) throws SQLException, ClassNotFoundException {
        boolean result = false;

        Transaction transaction = new Transaction(
                transactionDTO.getId(),
                transactionDTO.getOrderDate(),
                transactionDTO.getUserName(),
                transactionDTO.getQty(),
                transactionDTO.getTotal(),
                transactionDTO.getDueDate(),
                transactionDTO.getTransactionType()
        );

       transactionDAO.save(transaction);

       bookDAO.update((Book) transactionDTO.getTmList());

//       tra
        return true;
    }
}
