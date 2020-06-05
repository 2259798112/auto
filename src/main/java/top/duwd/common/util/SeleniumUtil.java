package top.duwd.common.util;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Set;

@Component
public class SeleniumUtil {
    public static final String TYPE_MOBILE = "Baidu-Mobile";
    public static final String TYPE_PC = "Baidu-PC";
    public static final String BAIDU_URL_PC = "https://www.baidu.com";
    public static final String BAIDU_URL_MOBILE = "https://m.baidu.com";
    @Value("${webdriver.name}")
    public static String DRIVE_NAME;// = "webdriver.chrome.driver";
    @Value("${webdriver.value}")
    public static String DRIVE_VALUE;// = "C:\\dev\\webdriver\\chromedriver.exe";

    public static WebDriver pcDrive = null;
    public static WebDriver mobileDrive = null;
    static {
        pcDrive = getHeadlessWebDrive();
        mobileDrive = getHeadlessWebDrive();
    }

    public static WebDriver getHeadlessWebDrive(){
        System.setProperty(DRIVE_NAME, DRIVE_VALUE);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080","--ignore-certificate-errors", "--silent");
        return new ChromeDriver(options);
    }

    public static WebDriver getWebDriverMobile(String driveName, String driveValue, String ip, Integer port, String windowHandle) {

        if (StringUtils.isEmpty(windowHandle)){
            return mobileDrive;
        }else {
            mobileDrive.switchTo().window(windowHandle);
            return mobileDrive;
        }

    }



    public static WebDriver getCookieWebDriver(String driveName, String driveValue) {
        System.setProperty(driveName, driveValue);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080","--ignore-certificate-errors", "--silent");
        return new ChromeDriver(options);

    }

    public static WebDriver getWebDriver(String driveName, String driveValue, String ip, Integer port, String windowHandle) {

            if (StringUtils.isEmpty(windowHandle)){
                return pcDrive;
            }else {

                pcDrive.switchTo().window(windowHandle);
                return pcDrive;
            }

    }

    public static String getBaiduCookie(WebDriver driver, String url, String keyword, String type) {
        String inputId = "kw";

        if (TYPE_MOBILE.equalsIgnoreCase(type)) {
            inputId = "index-kw";
        }

        driver.get(url);
        WebElement searchInput = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(ExpectedConditions.elementToBeClickable(By.id(inputId)));
        searchInput.sendKeys(keyword);
        searchInput.sendKeys(Keys.ENTER);

        Set<Cookie> cookies = driver.manage().getCookies();
        String Cookie = "";
        int count = 0;
        if (cookies != null && cookies.size() > 0) {
            for (Cookie cookie : cookies) {
                count++;
                if (count == cookies.size()) {
                    Cookie = Cookie + cookie.getName() + "=" + cookie.getValue();
                } else {
                    Cookie = Cookie + cookie.getName() + "=" + cookie.getValue() + ";";
                }
            }
        }

        return Cookie;
    }

    public static void getBaiduSearch(WebDriver driver, String url, String keyword, String type) {
        String inputId = "kw";

        if (TYPE_MOBILE.equalsIgnoreCase(type)) {
            inputId = "index-kw";
        }

        driver.get(url);
        WebElement searchInput = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds())
                .until(ExpectedConditions.elementToBeClickable(By.id(inputId)));
        searchInput.sendKeys(keyword);
        searchInput.sendKeys(Keys.ENTER);
    }
}
