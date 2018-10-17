package assignment.repository;

import assignment.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    // 특정 장소에 리뷰가 존재하는가
    boolean existsByPlaceId(UUID placeId);

    // 특정 장소에 생성일이 가장 빠른 리뷰 가져오기
    Review findFirstByPlaceIdOrderByCreatedAtAsc(UUID placeId);
}
