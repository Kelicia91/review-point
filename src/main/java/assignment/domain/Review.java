package assignment.domain;

import assignment.service.PointService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(indexes = {
        @Index(name = "IDX_REVIEW_WRITER", columnList = "writer_id"),
        @Index(name = "IDX_REVIEW_PLACE", columnList = "place_id")
})
public class Review extends AbstractEntity {

    // temp
    public static final int POINT_TEXT = 1;
    public static final int POINT_PHOTO = 1;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_review_writer"))
    private User writer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_review_place"))
    private Place place;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Photo> attachedPhotos;

    public Review(User writer, Place place, String content, List<UUID> attachedPhotos) {
        this.writer = writer;
        this.place = place;
        this.content = content;
        this.attachedPhotos = createPhoto(attachedPhotos);
    }

    public int getPoint() {
        int point = PointService.POINT_ZERO;
        if (content.length() > 0) {
            point += POINT_TEXT;
        }
        if (attachedPhotos.size() > 0) {
            point += POINT_PHOTO;
        }
        return point;
    }

    public Review modify(String content, List<UUID> attachedPhotoIds) {
        this.content = content;
        this.attachedPhotos = createPhoto(attachedPhotoIds); // @temp
        return this;
    }

    private List<Photo> createPhoto(List<UUID> attachedPhotoIds) {
        return new ArrayList<Photo>(); // @temp
    }

    public User getWriter() {
        return writer;
    }

    public Place getPlace() {
        return place;
    }

    public String getContent() {
        return content;
    }

    public List<Photo> getAttachedPhotos() {
        return attachedPhotos;
    }
}
