package psk.database.entities.accountGroup;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class AccountGroupsPK implements Serializable {
    @Column(name = "account_id")
    private Integer accoundId;

    @Column(name = "group_name")
    private String groupName;
}

