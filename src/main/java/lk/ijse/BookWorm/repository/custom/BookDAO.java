package lk.ijse.BookWorm.repository.custom;

import lk.ijse.BookWorm.entity.Book;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.repository.CrudDAO;

public interface BookDAO extends CrudDAO<Book,String> {
    Book getBookyId(String id);
    String generateNextId();
}
