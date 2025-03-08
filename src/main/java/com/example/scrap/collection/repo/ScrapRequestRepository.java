package com.example.scrap.collection.repo;

import com.example.scrap.collection.model.ScrapRequest;
import com.example.scrap.collection.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScrapRequestRepository extends JpaRepository<ScrapRequest, Long> {
    List<ScrapRequest> findByAssignedCollector(User collector);
    List<ScrapRequest> findByStatus(String status);
}
