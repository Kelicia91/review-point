package assignment.dto;

import java.util.List;
import java.util.UUID;

public class AddReviewDTO {

    private UUID userId;

    private UUID placeId;

    private String content;

    private List<UUID> attachedPhotoIds;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPlaceId() {
        return placeId;
    }

    public void setPlaceId(UUID placeId) {
        this.placeId = placeId;
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
