package com.chronocraft.chronocraft.controller.customer;

import com.chronocraft.chronocraft.dto.WatchDTO;
import com.chronocraft.chronocraft.service.customer.CustomerWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")

public class CustomerWatchController {
    private final CustomerWatchService customerWatchService;

    @Autowired
    public CustomerWatchController(CustomerWatchService customerWatchService) {
        this.customerWatchService = customerWatchService;
    }

    @GetMapping("/watches")
    public ResponseEntity<List<WatchDTO>> getAllWatches() {
        List<WatchDTO> watches = customerWatchService.getAllWatches();
        return ResponseEntity.ok(watches);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<WatchDTO>> getWatchesByName(@PathVariable String name) {
        List<WatchDTO> watches = customerWatchService.getWatchesByName(name);
        return ResponseEntity.ok(watches);
    }
}
