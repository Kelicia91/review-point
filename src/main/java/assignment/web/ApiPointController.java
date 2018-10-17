package assignment.web;

import assignment.domain.Point;
import assignment.dto.PointDTO;
import assignment.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/points/{userId}")
public class ApiPointController {

    @Autowired
    private PointService pointService;

    @GetMapping
    public ResponseEntity<Point> get(@PathVariable UUID userId) {
        return ResponseEntity.ok(pointService.get(userId));
    }

    @PutMapping("/increment")
    public ResponseEntity<Point> increment(@PathVariable UUID userId, @Valid @RequestBody PointDTO pointDTO) {
        return ResponseEntity.ok(pointService.increment(userId, pointDTO));
    }

    @PutMapping("/decrement")
    public ResponseEntity<Point> decrement(@PathVariable UUID userId, @Valid @RequestBody PointDTO pointDTO) {
        return ResponseEntity.ok(pointService.decrement(userId, pointDTO));
    }

    @PutMapping("/increment/bonus")
    public ResponseEntity<Point> incrementBonus(@PathVariable UUID userId, @Valid @RequestBody PointDTO pointDTO) {
        return ResponseEntity.ok(pointService.incrementBonus(userId, pointDTO));
    }

    @PutMapping("/decrement/bonus")
    public ResponseEntity<Point> decrementBonus(@PathVariable UUID userId, @Valid @RequestBody PointDTO pointDTO) {
        return ResponseEntity.ok(pointService.decrementBonus(userId, pointDTO));
    }
}
