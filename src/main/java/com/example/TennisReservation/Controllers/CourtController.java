package com.example.TennisReservation.Controllers;

import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Services.Courts.CourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courts")
public class CourtController {
    private final CourtService courtService;

    @PostMapping
    public ResponseEntity<Court> addCourt(@RequestBody Court court) {
        return ResponseEntity.ok(courtService.CreateCourt(court));
    }

    @GetMapping
    public ResponseEntity<List<Court>> getAllCourts() {
        return ResponseEntity.ok(courtService.getAllCourts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Court> getCourt(@PathVariable long id) {
        return courtService.getCourt(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Court> updateCourt(@PathVariable long id, @RequestBody Court court) {
        return ResponseEntity.ok(courtService.UpdateCourt(id, court));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourt(@PathVariable long id) {
        if (courtService.canDeleteCourt(id)) {
            courtService.deleteCourt(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}
