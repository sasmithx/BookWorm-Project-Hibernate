package lk.ijse.BookWorm.entity;

import com.sun.javafx.geom.transform.Identity;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.BookWorm.tm.CartTm;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transaction")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;
    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "qty")
    private int qty;


    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "trasaction_type")
    private String transactionType;
    
   /* @Column(name = "tm_list")
    private List<CartTm> tmList = new ArrayList<>();*/


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "transaction"
    )
    private List<TransactionDetail> transactionDetails = new ArrayList<>();

    public Transaction(int id, LocalDate orderDate, String userName, int qty, String dueDate, String transactionType,User user) {
        this.id=id;
        this.orderDate=orderDate;
        this.userName=userName;
        this.qty=qty;

        this.dueDate=dueDate;
        this.transactionType=transactionType;
        this.user = user;

    }

    public Transaction(String bookID, String title, int qty) {

    }
}
