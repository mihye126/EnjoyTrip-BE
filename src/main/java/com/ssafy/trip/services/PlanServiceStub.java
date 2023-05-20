package com.ssafy.trip.services;

import com.ssafy.trip.models.Plan;
import java.util.List;
import java.util.Optional;

public class PlanServiceStub implements PlanService{


    // 가데이터 생성하기


    @Override
    public List<Plan> findAll() {
        return null;
    }

    @Override
    public Optional<Plan> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void insert(Plan plan) {

    }

    @Override
    public void delete(long id) {

    }
}
