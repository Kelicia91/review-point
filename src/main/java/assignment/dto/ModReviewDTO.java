package assignment.dto;

import java.util.List;
import java.util.UUID;

public class ModReviewDTO {

    private UUID reviewId;

    private String content;

    private List<UUID> attachedPhotoIds;

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<UUID> getAttachedPhotoIds() {
        return attachedPhotoIds;
    }

    public void setAttachedPhotoIds(List<UUID> attachedPhotoIds) {
        this.attachedPhotoIds = attachedPhotoIds;
    }
}
