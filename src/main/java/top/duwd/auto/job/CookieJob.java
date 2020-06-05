package top.duwd.auto.job;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.duwd.auto.service.BaiduCookieService;
import top.duwd.auto.service.KeywordService;
import top.duwd.common.domain.sub.entity.Keyword;
import top.duwd.common.util.SeleniumUtil;

import javax.annotation.Resource;
import java.time.Duration;

@Component
public class CookieJob {
    @Resource
    private KeywordService keywordService;
    @Resource
    private BaiduCookieService baiduCookieService;

    @Value("${webdriver.name}")
    private String webDriveName;
    @Value("${webdrive.value}")
    private String webDriveValue;

    @Scheduled(cron = "0 */30 * * * ?")
    public void runPC(){
        Keyword lastOne = keywordService.findLastOne();

        if (lastOne == null || lastOne.getKeywordMain() == null){
            return;
        }
        WebDriver webDriver = SeleniumUtil.getCookieWebDriver(webDriveName, webDriveValue);
        String pcCookie;
        try {
            pcCookie = SeleniumUtil.getBaiduCookie(webDriver, SeleniumUtil.BAIDU_URL_PC, lastOne.getKeywordMain(), SeleniumUtil.TYPE_PC);

            //加载到 出现下一页
            WebElement page = new WebDriverWait(webDriver, Duration.ofSeconds(10).getSeconds())
                    .until(ExpectedConditions.elementToBeClickable(By.id("page")));
        } finally {
            webDriver.quit();
        }
        if (pcCookie !=null && pcCookie.trim().length() >0){
            baiduCookieService.save(SeleniumUtil.TYPE_PC,pcCookie);
        }


    }


    @Scheduled(cron = "30 */10 * * * ?")
    public void runMobile(){
        Keyword lastOne = keywordService.findLastOne();

        if (lastOne == null || lastOne.getKeywordMain() == null){
            return;
        }
        WebDriver webDriver = SeleniumUtil.getCookieWebDriver(webDriveName, webDriveValue);
        String moCookie;
        try {
            moCookie = SeleniumUtil.getBaiduCookie(webDriver, SeleniumUtil.BAIDU_URL_MOBILE, lastOne.getKeywordMain(), SeleniumUtil.TYPE_MOBILE);
            //page-controller

            //加载到 出现下一页
            WebElement page = new WebDriverWait(webDriver, Duration.ofSeconds(10).getSeconds())
                    .until(ExpectedConditions.elementToBeClickable(By.id("page-controller")));
        } finally {
            webDriver.quit();
        }
        if (moCookie !=null && moCookie.trim().length() >0){
            baiduCookieService.save(SeleniumUtil.TYPE_MOBILE,moCookie);
        }

    }
}
