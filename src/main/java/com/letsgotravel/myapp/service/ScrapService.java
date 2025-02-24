package com.letsgotravel.myapp.service;

import java.util.List;
import com.letsgotravel.myapp.domain.Criteria;
import com.letsgotravel.myapp.domain.ScrapVo;

public interface ScrapService {
    
    List<ScrapVo> getScrapList(int midx, Criteria cri);
    int scrapTotalCount(int midx);
    ScrapVo scrapSelectOne(int sidx);
}