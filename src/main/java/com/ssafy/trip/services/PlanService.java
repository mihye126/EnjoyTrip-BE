package com.ssafy.trip.services;

import com.ssafy.trip.models.Plan;
import java.util.List;
import java.util.Optional;

public interface PlanService {
    List<Plan> findAll();
    Optional<Plan> findById(long id);
    void insert(Plan plan);
    void delete(long id);
}
