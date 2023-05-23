package com.ssafy.trip.services;

import com.ssafy.trip.models.Gugun;
import com.ssafy.trip.models.Sido;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface GugunService {
    Optional<Gugun> findByCode(String code) throws SQLException;
    Optional<Gugun> findByName(String name) throws SQLException;
    List<Gugun> findAll() throws SQLException;
}
