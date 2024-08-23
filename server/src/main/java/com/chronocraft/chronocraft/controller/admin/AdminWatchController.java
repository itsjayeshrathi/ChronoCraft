package com.chronocraft.chronocraft.controller.admin;

import com.chronocraft.chronocraft.dto.WatchDTO;
import com.chronocraft.chronocraft.service.admin.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminWatchController {
    private final WatchService watchService;

    @Autowired
    public AdminWatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    @PostMapping("/watch")
    public ResponseEntity<WatchDTO> addWatch(@ModelAttribute WatchDTO watchDTO) {
        WatchDTO savedWatch = watchService.addWatch(watchDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWatch);
    }


    @GetMapping("/watches")
    public ResponseEntity<List<WatchDTO>> getAllWatches() {
        List<WatchDTO> watches = watchService.getAllWatches();
        return ResponseEntity.ok(watches);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<WatchDTO>> getWatchesByName(@PathVariable String name) {
        List<WatchDTO> watches = watchService.getWatchesByName(name);
        return ResponseEntity.ok(watches);
    }

    @DeleteMapping("/watch/{id}")
    public ResponseEntity<Void> deleteWatch(@PathVariable Long id) {
        boolean isDeleted = watchService.deleteWatch(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/watch/{id}")
    public ResponseEntity<WatchDTO> getWatchById(@PathVariable Long id) {
        WatchDTO watch = watchService.getWatchById(id);
        if(watch != null) {
            return ResponseEntity.ok(watch);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/watch/{id}")
    public ResponseEntity<WatchDTO> updateWatch(@PathVariable Long id, @ModelAttribute WatchDTO watchDTO) {
        WatchDTO watch = watchService.updateWatch(id, watchDTO);
        if(watch != null) {
            return ResponseEntity.ok(watch);
        }
        return ResponseEntity.notFound().build();
    }
}
