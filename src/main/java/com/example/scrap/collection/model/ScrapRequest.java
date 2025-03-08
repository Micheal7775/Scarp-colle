package com.example.scrap.collection.model;

import com.example.scrap.collection.model.User;
import jakarta.persistence.*;

@Entity
public class ScrapRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scrapType;
    private Double weight;
    private Double estimatedPrice;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "assigned_collector_id")
    private User assignedCollector;
    // Collector assigned to request

    public ScrapRequest(){

    }
public ScrapRequest(Long id, String scrapType, Double weight, Double estimatedPrice, String status, User user, User assignedCollector) {
    this.id = id;
    this.scrapType = scrapType;
    this.weight = weight;
    this.estimatedPrice = estimatedPrice;
    this.status = status;
    this.user = user;
    this.assignedCollector = assignedCollector;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getScrapType() {
    return scrapType;
}

public void setScrapType(String scrapType) {
    this.scrapType = scrapType;
}

public Double getWeight() {
    return weight;
}

public void setWeight(Double weight) {
    this.weight = weight;
}

public Double getEstimatedPrice() {
    return estimatedPrice;
}

public void setEstimatedPrice(Double estimatedPrice) {
    this.estimatedPrice = estimatedPrice;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}

public User getAssignedCollector() {
    return assignedCollector;
}

public void setAssignedCollector(User assignedCollector) {
    this.assignedCollector = assignedCollector;
}
}