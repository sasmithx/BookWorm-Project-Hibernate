package lk.ijse.BookWorm.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTM {
    private String id;
    private String bookName;
    private String authorName;
    private String genre;
    private int qty;
}
