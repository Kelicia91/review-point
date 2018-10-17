package assignment.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.List;

@Entity
public class Place extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place")
    @OrderBy("created_at DESC")
    private List<Review> reviews;
}
