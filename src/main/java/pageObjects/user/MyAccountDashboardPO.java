package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.MyAccountDashboardUIs;

public class MyAccountDashboardPO extends BasePage {
    WebDriver driver;
    public MyAccountDashboardPO(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Get Hello Msg")
    public String getHelloMsgText() {
        waitForElementVisible(driver, MyAccountDashboardUIs.HELLO_MSG_TEXT);
        return getElementText(driver, MyAccountDashboardUIs.HELLO_MSG_TEXT);
    }
    @Step("Get Dashboard text")
    public String getDashboardText() {
        waitForElementVisible(driver, MyAccountDashboardUIs.MY_DASHBOARD_TEXT);
        return getElementText(driver, MyAccountDashboardUIs.MY_DASHBOARD_TEXT);
    }
}
