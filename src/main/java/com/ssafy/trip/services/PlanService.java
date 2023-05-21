package com.ssafy.trip.services;

import com.ssafy.trip.models.Plan;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PlanService {
    List<Plan> findAll() throws SQLException;
    Optional<Plan> findById(long id) throws SQLException;
    int insert(Plan plan) throws SQLException;
    int delete(long id) throws SQLException;
}
