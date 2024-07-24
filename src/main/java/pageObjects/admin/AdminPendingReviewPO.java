package pageObjects.admin;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.admin.AdminPedingReviewUIs;
import pageUIs.user.BaseElementUIs;

import java.util.List;

public class AdminPendingReviewPO extends BaseElement {
    WebDriver driver;

    public AdminPendingReviewPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Click to Review Record By Nickname {0}")
    public AdminEditReviewPO clickReviewRecordByNickname(String nickName) {
        waitForElementClickable(driver, AdminPedingReviewUIs.RECORD_BY_VALUE_NICKNAME,nickName);
        clickToElement(driver, AdminPedingReviewUIs.RECORD_BY_VALUE_NICKNAME,nickName);
        return PageGeneratorManager.getAdminEditReviewPage(driver);
    }
    @Step("Get List Record By Colum Name {0}")
    public List<WebElement> getListRecordByColumnName(String columnName) {
        int columnNumber = getListWebElement(driver, BaseElementUIs.COLUMN_NUMBER_BY_COLUMN_NAME,columnName).size() +1;
        waitForElementVisible(driver,BaseElementUIs.RECORD_VALUE_COLUMN_BY_COLUMN_NUMBER,String.valueOf(columnNumber));
        return getListWebElement(driver,BaseElementUIs.RECORD_VALUE_COLUMN_BY_COLUMN_NUMBER,String.valueOf(columnNumber));
    }
}
