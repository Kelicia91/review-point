package assignment.service;

import assignment.domain.Place;
import assignment.domain.PointHistory;
import assignment.domain.Review;
import assignment.domain.User;
import assignment.dto.AddReviewDTO;
import assignment.dto.DelReviewDTO;
import assignment.dto.ModReviewDTO;
import assignment.enumeration.ContentType;
import assignment.enumeration.PointAction;
import assignment.enumeration.PointType;
import assignment.repository.PlaceRepository;
import assignment.repository.PointHistoryRepository;
import assignment.repository.ReviewRepository;
import assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    // temp
    public static final int POINT_FIRST = 1;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PointHistoryRepository pointHistoryRepository;

    @Autowired
    private PointService pointService;


    public Review add(AddReviewDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Place place = placeRepository.findById(dto.getPlaceId()).orElseThrow(EntityNotFoundException::new);
        // user가 place에 작성한 리뷰가 없다고 가정 (1개만 작성가능)
        Review reqReview = new Review(user, place, dto.getContent(), dto.getAttachedPhotoIds());
        Review savedReview = reviewRepository.save(reqReview);
        addPoint(savedReview);
        return savedReview;
    }

    private void addPoint(Review review) {
        User writer = review.getWriter();
        int point = review.getPoint();
        UUID reviewId = review.getId();
        pointService.increment(writer, point, ContentType.REVIEW, reviewId);
        addBonus(review);
    }

    private void addBonus(Review review) {
        UUID placeId = review.getPlace().getId();
        if (reviewRepository.existsByPlaceId(placeId)) {
            return;
        } // 첫 리뷰 작성 보너스
        User writer = review.getWriter();
        UUID reviewId = review.getId();
        pointService.incrementBonus(writer, POINT_FIRST, ContentType.REVIEW, reviewId);
    }

    public Review modify(ModReviewDTO dto) {
        Review originReview = reviewRepository.findById(dto.getReviewId()).orElseThrow(EntityNotFoundException::new);
        int originPoint = originReview.getPoint();
        Review savedReview = reviewRepository.save(originReview.modify(dto.getContent(), dto.getAttachedPhotoIds()));
        modifyPoint(originPoint, savedReview);
        return savedReview;
    }

    private void modifyPoint(int originPoint, Review modReview) {
        int modPoint = modReview.getPoint();
        int amount = Math.abs(modPoint - originPoint);
        if (PointService.POINT_ZERO == amount) {
            return;
        }
        User writer = modReview.getWriter();
        UUID reviewId = modReview.getId();
        if (modPoint > originPoint) {
            pointService.increment(writer, amount, ContentType.REVIEW, reviewId);
        } else {
            pointService.decrement(writer, amount, ContentType.REVIEW, reviewId);
        }
    }

    public void delete(DelReviewDTO dto) {
        Review review = reviewRepository.findById(dto.getReviewId()).orElseThrow(EntityNotFoundException::new);
        reviewRepository.delete(review);
        deletePoint(review);
    }

    private void deletePoint(Review review) {
        User writer = review.getWriter();
        int point = review.getPoint();
        UUID reviewId = review.getId();
        pointService.decrement(writer, point, ContentType.REVIEW, reviewId);
        deleteBonus(review);
    }

    private void deleteBonus(Review review) {
        User writer = review.getWriter();
        UUID reviewId = review.getId();

        List<PointHistory> bonusHistory = pointHistoryRepository.findAllByUserAndTypeAndActionAndContentTypeAndContentId(writer, PointType.BONUS, PointAction.INCREMENT, ContentType.REVIEW, reviewId);
        if (bonusHistory.size() <= 0) {
            return;
        }

        bonusHistory.stream()
                .forEach(history -> pointService.decrementBonus(writer, history.getAmount(), ContentType.REVIEW, reviewId));

        // 첫 리뷰가 삭제되면 생성일이 가장 빠른 리뷰를 첫 리뷰로 보고 보너스 점수 부여
        UUID placeId = review.getPlace().getId();
        Review firstReview = reviewRepository.findFirstByPlaceIdOrderByCreatedAtAsc(placeId);
        if (null == firstReview) {
            return;
        }
        User firstReviewWriter = firstReview.getWriter();
        UUID firstReviewId = firstReview.getId();
        pointService.incrementBonus(firstReviewWriter, POINT_FIRST, ContentType.REVIEW, firstReviewId);
    }
}
