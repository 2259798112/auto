package top.duwd.auto.controller;

import org.openqa.selenium.WebDriver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.duwd.common.util.SeleniumUtil;
@Component
public class SeleniumSession {
    public static String PC = "";
    public static String Mobile = "";

    @Scheduled(cron = "50 */5 * * * ?")
    public void closePC(){
        WebDriver pcDrive = SeleniumUtil.pcDrive;
        pcDrive.quit();

        SeleniumUtil.pcDrive = SeleniumUtil.getHeadlessWebDrive();
        PC = SeleniumUtil.pcDrive.getWindowHandle();
    }


    @Scheduled(cron = "50 */5 * * * ?")
    public void closeMobile(){
        WebDriver pcDrive = SeleniumUtil.mobileDrive;
        pcDrive.quit();


        SeleniumUtil.mobileDrive = SeleniumUtil.getHeadlessWebDrive();
        Mobile = SeleniumUtil.mobileDrive.getWindowHandle();
    }
}
