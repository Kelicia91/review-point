package assignment.domain;

import assignment.enumeration.ContentType;
import assignment.enumeration.PointAction;
import assignment.enumeration.PointType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(indexes = { @Index(name = "IDX_POINT_HISTORY_USER", columnList = "user_id") })
public class PointHistory extends AbstractEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private PointType type;

    @Column
    private PointAction action;

    @Column
    @Size(min = 0)
    private int amount;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_point_history_to_user"))
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column
    private UUID contentId;

    public PointHistory(PointType type, PointAction action, @Size(min = 0) int amount, User user, ContentType contentType, UUID contentId) {
        this.type = type;
        this.action = action;
        this.amount = amount;
        this.user = user;
        this.contentType = contentType;
        this.contentId = contentId;
    }

    public int getAmount() {
        return amount;
    }
}
