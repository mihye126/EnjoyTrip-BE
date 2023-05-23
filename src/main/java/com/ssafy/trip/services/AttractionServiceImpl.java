package com.ssafy.trip.services;


import com.ssafy.trip.mapper.AttractionMapper;
import com.ssafy.trip.models.Attraction;
import com.ssafy.trip.models.MyBatisResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttractionServiceImpl  implements AttractionService{ //service에서 exception 처리한다.

    @Autowired
    private AttractionMapper attractionMapper;

    @Override
    public Optional<Attraction> findByContentID(String contentID) throws Exception {
        return attractionMapper.findByContentID(contentID);
    }
    @Override
    public List<Attraction> findAll() throws Exception {
        return attractionMapper.findAll();
    }

    @Override
    public String insert(Attraction attraction) throws Exception {
        // 삽입하기 -> 유저가 추가한 관광지
        int result=attractionMapper.insert(attraction);
        if(result== MyBatisResult.FAIL.getResult())
            throw new IllegalArgumentException( "Attraction 삽입에 실패하였습니다.");
        return "Attraction 삽입에 성공하였습니다.";
    }

    public String delete(String contentID) throws Exception {
        // 삭제하기 -> 유저가 추가한 관광지만 삭제 가능하도록
        int result=attractionMapper.delete(contentID);
        if(result== MyBatisResult.FAIL.getResult())
            throw new IllegalArgumentException( "id에 해당하는 Attraction이 존재하지 않습니다.");
        return "Attraction 삭제에 성공하였습니다.";
    }

    @Override
    public String update(Attraction attraction) throws Exception {
        int result= attractionMapper.update(attraction);
        if(result== MyBatisResult.FAIL.getResult())
            throw new IllegalArgumentException("Attraction 업데이트에 실패하였습니다.");
        return "Attraction 업데이트에  성공하였습니다.";
    }

    @Override
    public List<Attraction> search(String sidoCode, String contentTypeId, String keyword) throws Exception {
        Map<String, String> map=new HashMap<>();
        map.put("sidoCode",sidoCode);
        map.put("contentTypeId",contentTypeId);
        if(keyword==null)
            return  attractionMapper.findBySidoCodeAndContentTypeId(map);
        map.put("keyword",keyword);
        return attractionMapper.findByOverviewContainingOrTitleContainingAndSidoCodeAndContentTypeId(map);

    }
}
