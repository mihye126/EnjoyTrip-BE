package com.ssafy.trip.mapper;

import com.ssafy.trip.models.Gugun;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;

@Mapper
public interface GugunMapper {
    Optional<Gugun> findByCode(String code) throws SQLException;
    Optional<Gugun> findByName(String name) throws SQLException;
    List<Gugun> findAll()throws SQLException;
}
