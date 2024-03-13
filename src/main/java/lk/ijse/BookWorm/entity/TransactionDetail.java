package lk.ijse.BookWorm.entity;

import lk.ijse.BookWorm.embedded.TransactionDetailPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transaction_detail")
public class TransactionDetail {

    @EmbeddedId
    private TransactionDetailPK transactionDetailPK;

    @ManyToOne
    @JoinColumn(
            name = "transaction_id",
            insertable = false,
            updatable = false
    )
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(
            name = "book_id",
            insertable = false,
            updatable = false
    )
    private Book book;
}
