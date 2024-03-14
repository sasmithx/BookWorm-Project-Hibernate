package lk.ijse.BookWorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    private String id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "book_genre")
    private String genre;

    @Column(name = "book_quantity")
    private int qty;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "book"
    )
    private List<TransactionDetail> transactionDetails = new ArrayList<>();

    public Book(String bookID, String title, int qty) {
        this.id=bookID;
        this.bookName=title;
        this.qty=qty;
    }


    /*@OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "book"
    )
    private List<TransactionDetail> transactionDetails = new ArrayList<>();*/
}
