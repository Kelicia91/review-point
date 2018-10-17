package assignment.repository;

import assignment.domain.PointHistory;
import assignment.domain.User;
import assignment.enumeration.ContentType;
import assignment.enumeration.PointAction;
import assignment.enumeration.PointType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
    List<PointHistory> findAllByUserAndTypeAndActionAndContentTypeAndContentId(User user, PointType type, PointAction action, ContentType contentType, UUID contentId);
}
