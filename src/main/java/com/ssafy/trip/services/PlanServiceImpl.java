package com.ssafy.trip.services;

import com.ssafy.trip.mapper.PlanMapper;
import com.ssafy.trip.models.MyBatisResult;
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
    private PlanMapper planMapper;

    @Override
    public List<Plan> findAll() throws SQLException {
        return planMapper.findByAll();
    }

    @Override
    public Optional<Plan> findById(long id) throws SQLException{
        return planMapper.findById(id);
    }

    @Override
    public String insert(Plan plan) throws SQLException{
        int result=planMapper.insert(plan);
        if(result== MyBatisResult.FAIL.getResult())
            return "Plan 삽입에 실패하였습니다.";
        return "Plan 삽입에 성공하였습니다.";
    }

    @Override
    public String delete(long id) throws SQLException{
        int result= planMapper.delete(id);
        if(result== MyBatisResult.FAIL.getResult())
            return "Plan 삭제에 실패하였습니다.";
        return "Plan 삭제에 성공하였습니다.";
    }

    @Override
    public String update(Plan plan) throws SQLException{
        int result= planMapper.update(plan);
        if(result== MyBatisResult.FAIL.getResult())
            return "Plan 수정에 실패하였습니다.";
        return "Plan 수정에 성공하였습니다.";    }
}
