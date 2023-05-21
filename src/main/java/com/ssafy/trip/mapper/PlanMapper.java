package com.ssafy.trip.mapper;

import com.ssafy.trip.models.Plan;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PlanMapper {
    Optional<Plan> findById(long id) throws SQLException;
    List<Plan> findByAll() throws SQLException;
    int insert(Plan plan) throws  SQLException;
    int delete(long id) throws  SQLException;

}
