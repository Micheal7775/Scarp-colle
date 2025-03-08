package com.example.scrap.collection.service;

import com.example.scrap.collection.model.ScrapRequest;
import com.example.scrap.collection.model.User;
import com.example.scrap.collection.repo.ScrapRequestRepository;
import com.example.scrap.collection.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapRequestService {
    @Autowired
    private ScrapRequestRepository scrapRequestRepository;

    @Autowired
    private UserRepository userRepository;

    // Create scrap request
    public ScrapRequest createScrapRequest(ScrapRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        request.setUser(user);
        request.setStatus("PENDING");
        request.setEstimatedPrice(request.getWeight() * 10); // Example price calculation
        return scrapRequestRepository.save(request);
    }

    // Assign a collector to the request
    public ScrapRequest assignCollector(Long requestId, Long collectorId) {
        ScrapRequest request = scrapRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        User collector = userRepository.findById(collectorId)
                .orElseThrow(() -> new RuntimeException("Collector not found"));

        if (!"COLLECTOR".equals(collector.getRole())) {
            throw new RuntimeException("User is not a collector");
        }
        request.setAssignedCollector(collector);
        request.setStatus("ACCEPTED");
        return scrapRequestRepository.save(request);
    }

    // Collector marks request as completed
    public ScrapRequest completeRequest(Long requestId) {
        ScrapRequest request = scrapRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("COMPLETED");
        return scrapRequestRepository.save(request);
    }

    // Collector rejects request
    public ScrapRequest rejectRequest(Long requestId) {
        ScrapRequest request = scrapRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus("REJECTED");
        return scrapRequestRepository.save(request);
    }

    // Get all pending requests
    public List<ScrapRequest> getPendingRequests() {
        return scrapRequestRepository.findByStatus("PENDING");
    }

    // Get all requests assigned to a collector
    public List<ScrapRequest> getCollectorRequests(Long collectorId) {
        User collector = userRepository.findById(collectorId)
                .orElseThrow(() -> new RuntimeException("Collector not found"));
        return scrapRequestRepository.findByAssignedCollector(collector);
    }
}
