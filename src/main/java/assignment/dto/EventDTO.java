package assignment.dto;

import assignment.enumeration.EventAction;
import assignment.enumeration.EventType;

import java.util.List;
import java.util.UUID;

public class EventDTO {

    private EventType type;

    private EventAction action;

    private UUID reviewId;

    private String content;

    private List<UUID> attachedPhotoIds;

    private UUID userId;

    private UUID placeId;

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventAction getAction() {
        return action;
    }

    public void setAction(EventAction action) {
        this.action = action;
    }

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
}
