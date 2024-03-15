package lk.ijse.BookWorm.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.BookWorm.config.SessionFactoryConfig;
import lk.ijse.BookWorm.dto.BookDTO;
import lk.ijse.BookWorm.dto.TransactionDTO;
import lk.ijse.BookWorm.entity.Book;
import lk.ijse.BookWorm.entity.Transaction;
import lk.ijse.BookWorm.entity.TransactionDetail;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.repository.DAOFactory;
import lk.ijse.BookWorm.repository.custom.BookDAO;
import lk.ijse.BookWorm.repository.custom.TransactionDAO;
import lk.ijse.BookWorm.repository.custom.TransactionDetailDAO;
import lk.ijse.BookWorm.repository.custom.UserDAO;
import lk.ijse.BookWorm.service.custom.TransactionBO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class TransactionBOImpl implements TransactionBO {

    UserDAO userDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.UserDAO);
    BookDAO bookDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BookDAO);
    TransactionDAO transactionDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TransactionDAO);

    TransactionDetailDAO transactionDetailDAO =DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TransactionDetailDAO);


    @Override
    public User searchUser(String newValue) throws SQLException, ClassNotFoundException {
        return userDAO.search(newValue);
    }

    @Override
    public ObservableList<String> loadUserId() throws SQLException, ClassNotFoundException {
        return userDAO.loadUserId();
    }

    @Override
    public BookDTO searchBook(String value) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
//        org.hibernate.Transaction transaction = session.beginTransaction();
        try {
            Query<Book> query = session.createQuery("FROM Book WHERE id = :id", Book.class);
            query.setParameter("id", value);
            List<Book> results = query.list();

            if (!results.isEmpty()) {
                Book book = results.get(0);
                return new BookDTO(
                        book.getId(),
                        book.getBookName(),
                        book.getAuthorName(),
                        book.getGenre(),
                        book.getQty()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

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

        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        org.hibernate.Transaction transaction = session.beginTransaction();

        Transaction transaction1 = new Transaction(
                transactionDTO.getId(),
                transactionDTO.getOrderDate(),
                transactionDTO.getUserName(),
                transactionDTO.getQty(),
                transactionDTO.getTotal(),
                transactionDTO.getDueDate(),
                transactionDTO.getTransactionType()
        );

        boolean isOrderSaved = transactionDAO.save(transaction1);
        if (isOrderSaved) {
            boolean isUpdated = bookDAO.updateQty(transactionDTO.getTmList());
            if (isUpdated) {
                boolean isOrderDetailSaved = transactionDetailDAO.saveOrderDetail(transactionDTO.getId(), transactionDTO.getTmList());
                System.out.println(isOrderDetailSaved);
                if (isOrderDetailSaved) {
                    transaction.commit();
                    session.close();

                    result = true;
                } else {
                    transaction.rollback();
                    session.close();
                }
            } else {
                transaction.rollback();
               session.close();
            }
        }
        else {

            transaction.rollback();
            session.close();
        }

        return result;

    }
}
