package com.ssafy.trip.services;

import com.ssafy.trip.mapper.SidoMapper;
import com.ssafy.trip.models.Sido;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SidoServiceImpl implements SidoService{

    @Autowired
    SidoMapper sidoMapper;

    @Override
    public Optional<Sido> findByCode(String code) throws SQLException {
        return sidoMapper.findByCode(code);
    }

    @Override
    public Optional<Sido> findByName(String name) throws SQLException {
        return sidoMapper.findByName(name);
    }

    @Override
    public List<Sido> findAll() throws SQLException {
        return sidoMapper.findAll();
    }
}
