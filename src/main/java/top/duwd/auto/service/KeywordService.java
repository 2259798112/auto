package top.duwd.auto.service;

import org.springframework.stereotype.Service;
import top.duwd.common.domain.sub.entity.BaiduCookie;
import top.duwd.common.domain.sub.entity.Keyword;
import top.duwd.common.mapper.sub.BaiduCookieMapper;
import top.duwd.common.mapper.sub.KeywordMapper;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class KeywordService {

    @Resource
    private KeywordMapper keywordMapper;

    public Keyword findLastOne(){
        return keywordMapper.findLastOne();
    }




}
