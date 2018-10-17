package assignment.dto;

import java.util.UUID;

public class DelReviewDTO {

    private UUID reviewId;

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }
}
