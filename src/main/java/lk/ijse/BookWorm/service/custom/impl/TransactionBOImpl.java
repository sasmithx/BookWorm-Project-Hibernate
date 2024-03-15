package lk.ijse.BookWorm.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.BookWorm.config.SessionFactoryConfig;
import lk.ijse.BookWorm.dto.BookDTO;
import lk.ijse.BookWorm.dto.TransactionDTO;
import lk.ijse.BookWorm.dto.UserDTO;
import lk.ijse.BookWorm.embedded.TransactionDetailPK;
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
import lk.ijse.BookWorm.tm.CartTm;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<String> loadUserId() throws SQLException, ClassNotFoundException {
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
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        try{
            bookDAO.setSession(session);
            return bookDAO.loadBookId();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }



    /*@Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return transactionDAO.generateNextOrderId();
    }*/

    @Override
    public boolean placeOrder (TransactionDTO transactionDTO) throws SQLException, ClassNotFoundException {
        boolean result = false;

        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        LocalDate date = LocalDate.now().plusDays(14);

        userDAO.setSession(session);
       User user = userDAO.getUserByUserName(transactionDTO.getUserName());

        Transaction transaction1 = new Transaction(
                transactionDTO.getId(),
                transactionDTO.getOrderDate(),
                transactionDTO.getUserName(),
                transactionDTO.getQty(),
                String.valueOf(date),
                "borrowed",
                user
        );

        try {
            transactionDAO.setSession(session);
            boolean isOrderSaved = transactionDAO.save(transaction1);
            bookDAO.setSession(session);
            boolean isUpdated = bookDAO.updateQty(transactionDTO.getTmList());

            List<TransactionDetail> transactionDetails = new ArrayList<>();
            for (CartTm cartTm : transactionDTO.getTmList()) {
                bookDAO.setSession(session);
                Book book = bookDAO.get(cartTm.getBookID());
                transactionDetails.add(new TransactionDetail(
                        new TransactionDetailPK(transaction1.getId(),
                                book.getId()),
                        book,
                        transaction1

                ));
            }
            transactionDetailDAO.setSession(session);
            boolean isOrderDetailSaved = transactionDetailDAO.saveOrderDetail(transaction1, transactionDetails);


            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws SQLException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        userDAO.setSession(session);
        ArrayList<User> users = null;
        try {
            users = userDAO.getAll();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for(User user : users){
            userDTOS.add(new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.getPassword(),
                    user.getMobile(),
                    user.getEmail(),
                    user.getAddress()
                    /*user.getDob()*/
            ));
        }
        session.close();
        return userDTOS;
    }
    @Override
    public ArrayList<BookDTO> getAllBooks() throws SQLException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        bookDAO.setSession(session);
        ArrayList<Book> books = null;

        try {
            books = bookDAO.getAll();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<BookDTO> bookDTOS = new ArrayList<>();
        for(Book book : books){
            bookDTOS.add(new BookDTO(
                    book.getId(),
                    book.getBookName(),
                    book.getAuthorName(),
                    book.getGenre(),
                    book.getQty()
            ));
        }
        session.close();
        return bookDTOS;
    }
}
