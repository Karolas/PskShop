package psk.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "status")
    private String status;

    @Column(name= "order_created")
    private Timestamp orderCreated;

    @JoinColumn(name = "account_id", referencedColumnName = "ID")
    @ManyToOne
    private Account account;

    @OneToMany(
            mappedBy = "order",
            cascade = {CascadeType.MERGE,
                       CascadeType.REMOVE},
            fetch = FetchType.EAGER
    )
    private List<OrderProduct> products = new ArrayList<>();
}
