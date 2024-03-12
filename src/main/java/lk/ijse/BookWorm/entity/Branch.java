package lk.ijse.BookWorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Branch")
public class Branch {
    @Id
    @Column(name = "branch_id")
    private String id;

    @Column(name = "branch_name")
    private String name;

    @Column(name = "branch_location")
    private String location;

    @Column(name = "branch_mobile")
    private String mobile;

    @Column(name = "branch_email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

}
