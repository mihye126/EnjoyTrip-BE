package com.ssafy.trip.services;

import com.ssafy.trip.mapper.PlanMapper;
import com.ssafy.trip.models.Plan;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class PlanServiceImpl implements PlanService{

    @Autowired
    PlanMapper planMapper;

    @Override
    public List<Plan> findAll() throws SQLException {
        return planMapper.findByAll();
    }

    @Override
    public Optional<Plan> findById(long id) throws SQLException{
        return planMapper.findById(id);
    }

    @Override
    public int insert(Plan plan) throws SQLException{
        return planMapper.insert(plan);
    }

    @Override
    public int delete(long id) throws SQLException{
        return planMapper.delete(id);
    }
}
