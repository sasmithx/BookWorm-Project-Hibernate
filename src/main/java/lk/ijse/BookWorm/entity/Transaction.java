package lk.ijse.BookWorm.entity;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private int id;
    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "qty")
    private int qty;
    @Column(name = "total")
    private int total;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "trasaction_type")
    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "transaction"
    )
    private List<TransactionDetail> transactionDetails = new ArrayList<>();

}
