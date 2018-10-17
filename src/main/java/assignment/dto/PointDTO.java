package assignment.dto;

import assignment.enumeration.ContentType;

import javax.validation.constraints.Size;
import java.util.UUID;

public class PointDTO {

    @Size(min = 0)
    private int amount;

    private ContentType contentType;

    private UUID contentId;

    public int getAmount() {
        return amount;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public UUID getContentId() {
        return contentId;
    }
}
