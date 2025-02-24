package com.letsgotravel.myapp.service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.letsgotravel.myapp.domain.Criteria;
import com.letsgotravel.myapp.domain.ScrapVo;
import com.letsgotravel.myapp.persistance.ScrapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ScrapServiceImpl implements ScrapService {

    private ScrapMapper sm;

    @Autowired
    public ScrapServiceImpl(SqlSession sqlSession) {
        this.sm = sqlSession.getMapper(ScrapMapper.class);
    }

    private static final Logger logger = LoggerFactory.getLogger(ScrapServiceImpl.class);

    @Override
    public List<ScrapVo> getScrapList(int midx, Criteria cri) {
        logger.info("getScrapList 실행됨! midx: " + midx);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("midx", midx);
        paramMap.put("cri", cri);

        return sm.getScrapList(paramMap); //MyBatis에서 Map을 받을 수 있도록 변경
    }

    @Override
    public int scrapTotalCount(int midx) {
        logger.info("scrapTotalCount 실행됨! midx: " + midx);
        return sm.scrapTotalCount(midx);
    }

    @Override
    public ScrapVo scrapSelectOne(int sidx) {
        logger.info("scrapSelectOne 실행됨! sidx: " + sidx);
        return sm.scrapSelectOne(sidx);
    }
}
