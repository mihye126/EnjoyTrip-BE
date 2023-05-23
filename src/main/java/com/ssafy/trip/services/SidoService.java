package com.ssafy.trip.services;

import com.ssafy.trip.models.Sido;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface SidoService {
    Optional<Sido> findByCode(String code) throws Exception;
    Optional<Sido> findByName(String name) throws Exception;
    List<Sido> findAll() throws Exception;
}
