package lk.ijse.BookWorm.service.custom.impl;

import lk.ijse.BookWorm.config.SessionFactoryConfig;
import lk.ijse.BookWorm.dto.BookDTO;
import lk.ijse.BookWorm.entity.Book;
import lk.ijse.BookWorm.repository.DAOFactory;
import lk.ijse.BookWorm.repository.custom.BookDAO;
import lk.ijse.BookWorm.service.custom.BookBO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookBOImpl implements BookBO {
    BookDAO bookDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BookDAO);

    @Override
    public boolean saveBooks(BookDTO bookDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();


        try{
            bookDAO.setSession(session);
            bookDAO.save(bookDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBooks(BookDTO bookDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            bookDAO.setSession(session);
            bookDAO.update(bookDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBooks(BookDTO bookDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            bookDAO.setSession(session);
            bookDAO.delete(bookDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
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
