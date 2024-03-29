package lk.ijse.BookWorm.embedded;

import lk.ijse.BookWorm.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TransactionDetailPK implements Serializable {
    @Column(name = "transaction_id")
    private int transactionID;
    @Column(name = "book_id")
    private String bookId;

}
