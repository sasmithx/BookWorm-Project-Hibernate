package lk.ijse.BookWorm.service.custom;

import lk.ijse.BookWorm.dto.BookDTO;
import lk.ijse.BookWorm.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookBO extends SuperBO {
    boolean saveBooks(BookDTO bookDTO);
    boolean updateBooks(BookDTO bookDTO);
    boolean deleteBooks(BookDTO bookDTO);
    ArrayList<BookDTO> getAllBooks() throws SQLException;
}
