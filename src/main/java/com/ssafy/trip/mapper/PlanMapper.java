package com.ssafy.trip.mapper;

import com.ssafy.trip.models.Plan;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanMapper {
    Optional<Plan> findById(long id) throws SQLException;
    List<Plan> findByAll() throws SQLException;
    int insert(Plan plan) throws  SQLException;
    int delete(long id) throws  SQLException;
    int update(Plan plan) throws  SQLException;
}
