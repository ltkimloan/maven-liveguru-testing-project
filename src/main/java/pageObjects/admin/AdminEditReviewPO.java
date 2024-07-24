package pageObjects.admin;

import commons.BaseElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminEditReviewUIs;

public class AdminEditReviewPO extends BaseElement {
    WebDriver driver;

    public AdminEditReviewPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Select {0} Items Status Dropdrop By itemName ")
    public void selectItemsStatusDropdownByItemName(String itemValue) {
        waitForElementClickable(driver, AdminEditReviewUIs.STATUS_DROPDOWN);
        selectItemInDefaultDropdown(driver,AdminEditReviewUIs.STATUS_DROPDOWN,itemValue);
    }
    @Step("Click To Save Review button")
    public void clickToSaveReviewButton() {
        waitForElementClickable(driver, AdminEditReviewUIs.SAVE_REVIEW_BUTTON);
        clickToElement(driver, AdminEditReviewUIs.SAVE_REVIEW_BUTTON);
    }
}
