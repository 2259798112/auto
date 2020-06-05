package top.duwd.auto.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.duwd.common.util.SeleniumUtil;

import java.time.Duration;

@RestController
@RequestMapping("/chrome")
public class ChromeController {


    @Value("${webdriver.name}")
    private String webDriveName;
    @Value("${webdriver.value}")
    private String webDriveValue;


    @GetMapping("/pc")
    public String pc(@RequestParam String keyword) {
        WebDriver webDriver = SeleniumUtil.getWebDriver(webDriveName, webDriveValue, null, null,SeleniumSession.PC);

        if (StringUtils.isEmpty(SeleniumSession.PC)) {
            SeleniumSession.PC = webDriver.getWindowHandle();
        }

        int size = webDriver.getWindowHandles().size();

        SeleniumUtil.getBaiduSearch(webDriver, SeleniumUtil.BAIDU_URL_PC, keyword, SeleniumUtil.TYPE_PC);
        //加载到 出现下一页
        WebElement page = new WebDriverWait(webDriver, Duration.ofSeconds(10).getSeconds())
                .until(ExpectedConditions.elementToBeClickable(By.id("page")));
        String pageSource = webDriver.getPageSource();

//        webDriver.close();
        return pageSource;
    }



    @GetMapping("/mobile")
    public String mobile(@RequestParam String keyword) {
        WebDriver webDriver = SeleniumUtil.getWebDriverMobile(webDriveName, webDriveValue, null, null,SeleniumSession.Mobile);

        if (StringUtils.isEmpty(SeleniumSession.Mobile)) {
            SeleniumSession.Mobile = webDriver.getWindowHandle();
        }

        int size = webDriver.getWindowHandles().size();

        SeleniumUtil.getBaiduSearch(webDriver, SeleniumUtil.BAIDU_URL_MOBILE, keyword, SeleniumUtil.TYPE_MOBILE);
        //加载到 出现下一页
        WebElement page = new WebDriverWait(webDriver, Duration.ofSeconds(10).getSeconds())
                .until(ExpectedConditions.elementToBeClickable(By.id("page-controller")));
        String pageSource = webDriver.getPageSource();

//        webDriver.close();
        return pageSource;
    }
}
