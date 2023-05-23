package com.ssafy.trip.services;

import com.ssafy.trip.models.Gugun;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface GugunService {
    Optional<Gugun> findByCode(String code) throws Exception;
    Optional<Gugun> findByName(String name) throws Exception;
    List<Gugun> findAll() throws Exception;
}
