package top.duwd.auto.service;

import org.springframework.stereotype.Service;
import top.duwd.common.domain.sub.entity.Keyword;
import top.duwd.common.mapper.sub.KeywordBaiduSearchResultMapper;
import top.duwd.common.mapper.sub.KeywordMapper;

import javax.annotation.Resource;

@Service
public class KeywordBaiduSearchResultService {

    @Resource
    private KeywordBaiduSearchResultMapper mapper;

}
