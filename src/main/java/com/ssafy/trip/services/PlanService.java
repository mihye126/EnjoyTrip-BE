package com.ssafy.trip.services;

import com.ssafy.trip.models.Plan;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface PlanService {
    List<Plan> findAll() throws Exception;
    Optional<Plan> findById(long id) throws Exception;
    String insert(Plan plan) throws Exception;
    String delete(long id) throws Exception;
    String update(Plan plan) throws Exception;
}
