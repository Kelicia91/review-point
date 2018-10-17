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
    public ResponseEntity<Point> increment(@Valid @RequestBody PointDTO pointDTO) {
        return ResponseEntity.ok(pointService.increment(pointDTO));
    }

    @PutMapping("/decrement")
    public ResponseEntity<Point> decrement(@Valid @RequestBody PointDTO pointDTO) {
        return ResponseEntity.ok(pointService.decrement(pointDTO));
    }

    @PutMapping("/increment/bonus")
    public ResponseEntity<Point> incrementBonus(@Valid @RequestBody PointDTO pointDTO) {
        return ResponseEntity.ok(pointService.incrementBonus(pointDTO));
    }

    @PutMapping("/decrement/bonus")
    public ResponseEntity<Point> decrementBonus(@Valid @RequestBody PointDTO pointDTO) {
        return ResponseEntity.ok(pointService.decrementBonus(pointDTO));
    }
}
