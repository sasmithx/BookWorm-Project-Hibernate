package lk.ijse.BookWorm.repository.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.BookWorm.entity.Book;
import lk.ijse.BookWorm.repository.custom.BookDAO;
import lk.ijse.BookWorm.tm.CartTm;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private Session session;

    @Override
    public boolean save(Book entity) throws SQLException, ClassNotFoundException {
        session.save(entity);
        return false;
    }

    @Override
    public void delete(Book entity) throws SQLException, ClassNotFoundException {
        session.delete(entity);
    }

    @Override
    public void update(Book entity) throws SQLException, ClassNotFoundException {
        session.update(entity);
    }

    @Override
    public ArrayList<Book> getAll() throws SQLException, ClassNotFoundException {

        try{
            List<Book> books = session.createNativeQuery("SELECT * FROM Book", Book.class).getResultList();
            return (ArrayList<Book>) books;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException {
        String sql = "SELECT BK.id FROM Book AS BK";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return (ArrayList<String>) list;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }


    @Override
    public boolean updateQty(List<CartTm> tmList) throws SQLException, ClassNotFoundException {
        for (CartTm cartTm : tmList) {
            if(!updateBook(cartTm)) {
                return false;
            }       }
        return true;
    }

    @Override
    public boolean updateBook(CartTm cartTm) throws SQLException, ClassNotFoundException {
        try {
//            session.beginTransaction();

            // Use HQL to update book quantity
            String hql = "UPDATE Book b SET b.qty = b.qty - :qty WHERE b.id = :book_id";

            Query query = session.createQuery(hql);
            query.setParameter("qty", cartTm.getQty());
            query.setParameter("book_id", cartTm.getBookID());

            // Execute the update
            int updatedRows = query.executeUpdate();

            // Commit the transaction if the update is successful
            if (updatedRows > 0) {
                return true;
            } else {
                // Rollback if no rows were updated

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ObservableList<String> loadBookId() throws SQLException, ClassNotFoundException {
        ObservableList<String> bookData = FXCollections.observableArrayList();

        String hql = "SELECT b.id FROM Book b";
        Query<String> query = session.createQuery(hql, String.class);

        List<String> result = query.list();

        bookData.addAll(result);

        return bookData;
    }

    @Override
    public Book getBookyId(String id) {
        String sql = "SELECT BK FROM Book AS BK WHERE BK.id = :book_id";
        Query namedquery = session.createQuery(sql);
        namedquery.setParameter("book_id",id);
        Book book = (Book) namedquery.getSingleResult();
        session.close();
        return book;
    }

    @Override
    public String generateNextId() {
        String sql = "SELECT BK.id FROM Book AS BK ORDER BY BK.id desc";
        Query namedquery = session.createQuery(sql);
        String bookId = (String) namedquery.setMaxResults(1).uniqueResult();
        return bookId;
    }

    @Override
    public int bookCount() {
        String sql = "SELECT COUNT(B.id) FROM Book AS B";
        Query query = session.createQuery(sql);
        Long count = (Long) query.getSingleResult();
        return Math.toIntExact(count);
    }

    @Override
    public Book get(String bookID) {
        return session.get(Book.class,bookID);
    }
}
