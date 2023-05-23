package com.ssafy.trip.services;

import com.ssafy.trip.models.Plan;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface PlanService {
    List<Plan> findAll() throws SQLException;
    Optional<Plan> findById(long id) throws SQLException;
    String insert(Plan plan) throws SQLException;
    String delete(long id) throws SQLException;
    String update(Plan plan) throws SQLException;
}
