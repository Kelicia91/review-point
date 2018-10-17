package assignment.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Point extends AbstractEntity {

    @MapsId
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_point_user"))
    private User user;

    @Column
    @Size(min = 0)
    private int point;

    @Column
    @Size(min = 0)
    private int bonus;

    public Point increment(int point) {
        this.point += point;
        return this;
    }

    public Point decrement(int point) {
        this.point -= point;
        return this;
    }

    public Point incrementBonus(int bonus) {
        this.bonus += bonus;
        return this;
    }

    public Point decrementBonus(int bonus) {
        this.bonus -= bonus;
        return this;
    }

    public User getUser() {
        return user;
    }

    public int getPoint() {
        return point;
    }

    public int getBonus() {
        return bonus;
    }
}
