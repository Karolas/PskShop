package psk.database.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import psk.InterceptorLog;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
@NamedQueries({
        @NamedQuery(name = "Account.findAllCount", query = "SELECT count(a.id) FROM Account a"),
        @NamedQuery(name = "Account.selectAll", query = "Select a from Account a")
})
@Getter
@Setter
@EqualsAndHashCode(of = "email")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_nr")
    private String postalNr;

    @Column(name = "telephone_nr")
    private String telephoneNr;

    @Column(name = "role")
    private String role;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;
}
