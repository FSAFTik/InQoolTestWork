package com.example.TennisReservation.Controllers;
import com.example.TennisReservation.Entities.Surface;
import com.example.TennisReservation.Services.Surfaces.SurfaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/surfaces")
public class SurfaceController {
    private final SurfaceService surfaceService;

    @PostMapping
    public ResponseEntity<Surface> addSurface(@RequestBody Surface surface) {
        return ResponseEntity.ok(surfaceService.createSurface(surface));
    }

    @GetMapping
    public ResponseEntity<List<Surface>> getAllSurfaces() {
        return ResponseEntity.ok(surfaceService.getAllSurfaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Surface> getSurface(@PathVariable long id) {
        return surfaceService.getSurface(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Surface> updateSurface(@PathVariable long id, @RequestBody Surface surface) {
        return ResponseEntity.ok(surfaceService.updateSurface(id, surface));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurface(@PathVariable long id) {
        if (surfaceService.canDeleteSurface(id)) {
            surfaceService.deleteSurface(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}
