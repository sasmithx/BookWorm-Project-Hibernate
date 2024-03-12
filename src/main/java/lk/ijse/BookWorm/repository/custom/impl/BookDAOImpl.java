package lk.ijse.BookWorm.repository.custom.impl;

import lk.ijse.BookWorm.entity.Book;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.repository.custom.BookDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private Session session;

    @Override
    public void save(Book entity) throws SQLException, ClassNotFoundException {
        session.save(entity);
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
}
