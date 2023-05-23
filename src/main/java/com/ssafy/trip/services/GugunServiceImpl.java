package com.ssafy.trip.services;

import com.ssafy.trip.mapper.GugunMapper;
import com.ssafy.trip.models.Gugun;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GugunServiceImpl implements GugunService{
    @Autowired
    GugunMapper gugunMapper;

    @Override
    public Optional<Gugun> findByCode(String code) throws Exception {
        return gugunMapper.findByCode(code);
    }

    @Override
    public Optional<Gugun> findByName(String name) throws Exception {
        return gugunMapper.findByName(name);
    }

    @Override
    public List<Gugun> findAll() throws Exception {
        return gugunMapper.findAll();
    }
}
