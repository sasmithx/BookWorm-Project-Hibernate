package lk.ijse.BookWorm.repository.custom;

import javafx.collections.ObservableList;
import lk.ijse.BookWorm.entity.Book;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.repository.CrudDAO;
import lk.ijse.BookWorm.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO extends CrudDAO<Book,String> {

    boolean updateQty(List<CartTm> tmList) throws SQLException, ClassNotFoundException;
    boolean updateBook(CartTm cartTm) throws SQLException, ClassNotFoundException;
    ObservableList<String> loadBookId() throws SQLException, ClassNotFoundException;
    Book getBookyId(String id);
    String generateNextId();

    int bookCount();
}
