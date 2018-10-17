package assignment.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class User extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Point point;

    public Point getPoint() {
        return point;
    }
}
