package assignment.service;

import assignment.domain.Point;
import assignment.domain.PointHistory;
import assignment.domain.User;
import assignment.dto.PointDTO;
import assignment.enumeration.ContentType;
import assignment.enumeration.PointAction;
import assignment.enumeration.PointType;
import assignment.repository.PointHistoryRepository;
import assignment.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class PointService {

    // temp
    public static final int POINT_ZERO = 0;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private PointHistoryRepository historyRepository;


    public Point get(UUID userId) {
        return pointRepository.findByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Point increment(UUID userId, PointDTO dto) {
        Point point = get(userId);
        return increment(point.getUser(), dto.getAmount(), dto.getContentType(), dto.getContentId());
    }

    public Point decrement(UUID userId, PointDTO dto) {
        Point point = get(userId);
        return decrement(point.getUser(), dto.getAmount(), dto.getContentType(), dto.getContentId());
    }

    public Point incrementBonus(UUID userId, PointDTO dto) {
        Point point = get(userId);
        return incrementBonus(point.getUser(), dto.getAmount(), dto.getContentType(), dto.getContentId());
    }

    public Point decrementBonus(UUID userId, PointDTO dto) {
        Point point = get(userId);
        return decrementBonus(point.getUser(), dto.getAmount(), dto.getContentType(), dto.getContentId());
    }

    public Point increment(User user, int amount, ContentType contentType, UUID contentId) {
        Point point = user.getPoint();
        Point savedPoint = savePoint(point.increment(amount));
        saveHistory(PointType.DEFAULT, PointAction.INCREMENT, amount, user, contentType, contentId);
        return savedPoint;
    }

    public Point decrement(User user, int amount, ContentType contentType, UUID contentId) {
        Point point = user.getPoint();
        Point savedPoint = savePoint(point.decrement(amount));
        saveHistory(PointType.DEFAULT, PointAction.DECREMENT, amount, user, contentType, contentId);
        return savedPoint;
    }

    public Point incrementBonus(User user, int amount, ContentType contentType, UUID contentId) {
        Point point = user.getPoint();
        Point savedPoint = savePoint(point.incrementBonus(amount));
        saveHistory(PointType.BONUS, PointAction.INCREMENT, amount, user, contentType, contentId);
        return savedPoint;
    }

    public Point decrementBonus(User user, int amount, ContentType contentType, UUID contentId) {
        Point point = user.getPoint();
        Point savedPoint = savePoint(point.decrementBonus(amount));
        saveHistory(PointType.BONUS, PointAction.DECREMENT, amount, user, contentType, contentId);
        return savedPoint;
    }

    private Point savePoint(Point point) {
        return pointRepository.save(point);
    }

    private PointHistory saveHistory(PointType type, PointAction action, int amount, User user, ContentType contentType, UUID contentId) {
        PointHistory history = new PointHistory(type, action, amount, user, contentType, contentId);
        return historyRepository.save(history);
    }
}
