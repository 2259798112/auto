package top.duwd.auto.job;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.duwd.auto.service.BaiduCookieService;
import top.duwd.auto.service.KeywordService;
import top.duwd.common.domain.sub.entity.BaiduCookie;
import top.duwd.common.domain.sub.entity.Keyword;
import top.duwd.common.mapper.sub.BaiduCookieMapper;
import top.duwd.common.util.SeleniumUtil;

import javax.annotation.Resource;

@Component
@Slf4j
public class HeartJob {

    @Resource
    private BaiduCookieMapper mapper;

    @Scheduled(cron = "*/1 * * * * ?")
    public void run(){

        int heart = mapper.heart();
        log.debug("heart [{}]",heart);
    }
}
