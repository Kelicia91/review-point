package assignment.web;

import assignment.dto.EventDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class ApiEventController {

    @PostMapping
    public ResponseEntity<Void> addReview(@RequestBody EventDTO eventDTO) {
        // @todo: call a service with args by event.type, event.action
        return ResponseEntity.ok().build(); // @temp
    }
}
