package lk.ijse.BookWorm.embedded;

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
    @Column(name = "book_id")
    private String bookId;
    @Column(name = "transaction_id")
    private int transactionID;
}
