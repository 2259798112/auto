package top.duwd.auto.service;


import org.springframework.stereotype.Service;
import top.duwd.common.domain.sub.entity.BaiduCookie;
import top.duwd.common.mapper.sub.BaiduCookieMapper;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class BaiduCookieService {

    @Resource
    private BaiduCookieMapper baiduCookieMapper;

    public int save(String plat, String cookie) {
        BaiduCookie baiduCookie = new BaiduCookie(null, new Date(), plat, cookie);
        int insert = baiduCookieMapper.insert(baiduCookie);
        return insert;
    }




}
