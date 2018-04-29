package psk.database.entities.accountGroup;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="account_groups")
@Getter
@Setter
public class AccountGroups {
    @EmbeddedId
    private AccountGroupsPK id;
}
