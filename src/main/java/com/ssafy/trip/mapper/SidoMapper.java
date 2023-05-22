package com.ssafy.trip.mapper;

import com.ssafy.trip.models.Sido;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;

@Mapper
public interface SidoMapper {
    Optional<Sido> findByCode(String code) throws SQLException;
    Optional<Sido> findByName(String name) throws SQLException;
    List<Sido> findAll() throws SQLException;
}
