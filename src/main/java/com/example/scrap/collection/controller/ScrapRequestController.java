package com.example.scrap.collection.controller;

import com.example.scrap.collection.model.ScrapRequest;
import com.example.scrap.collection.service.ScrapRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scrap")
public class ScrapRequestController {
    @Autowired
    private ScrapRequestService scrapRequestService;

    // Create new scrap collection request
    @PostMapping("/request/{userId}")
    public ResponseEntity<ScrapRequest> createScrapRequest(
            @RequestBody ScrapRequest request, @PathVariable Long userId) {
        return ResponseEntity.ok(scrapRequestService.createScrapRequest(request, userId));
    }

    // Assign a collector to the request
    @PutMapping("/assign/{requestId}/collector/{collectorId}")
    public ResponseEntity<ScrapRequest> assignCollector(
            @PathVariable Long requestId, @PathVariable Long collectorId) {
        return ResponseEntity.ok(scrapRequestService.assignCollector(requestId, collectorId));
    }

    // Collector marks request as completed
    @PutMapping("/complete/{requestId}")
    public ResponseEntity<ScrapRequest> completeRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(scrapRequestService.completeRequest(requestId));
    }

    // Collector rejects request
    @PutMapping("/reject/{requestId}")
    public ResponseEntity<ScrapRequest> rejectRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(scrapRequestService.rejectRequest(requestId));
    }

    // Get all pending requests
    @GetMapping("/pending")
    public ResponseEntity<List<ScrapRequest>> getPendingRequests() {
        return ResponseEntity.ok(scrapRequestService.getPendingRequests());
    }

    // Get all requests assigned to a collector
    @GetMapping("/collector/{collectorId}")
    public ResponseEntity<List<ScrapRequest>> getCollectorRequests(@PathVariable Long collectorId) {
        return ResponseEntity.ok(scrapRequestService.getCollectorRequests(collectorId));
    }
}
