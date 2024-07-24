package pageObjects.user;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.CompareProductUIs;

public class CompareProductPO extends BaseElement {
    WebDriver driver;

    public CompareProductPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("is Compare Product Title display")
    public boolean isCompareProductTitleDisplay() {
        waitForElementVisible(driver, CompareProductUIs.COMPARE_PRODUCT_TITLE_PAGE);
        return isElementDisplayed(driver, CompareProductUIs.COMPARE_PRODUCT_TITLE_PAGE);
    }
    @Step("Is Product By {0} Name display")
    public boolean isProductByNameDisplay(String productName) {
        waitForElementVisible(driver, CompareProductUIs.DYNAMIC_PRODUCT_BY_NAME,productName);
        return isElementDisplayed(driver, CompareProductUIs.DYNAMIC_PRODUCT_BY_NAME,productName);
    }
    @Step("Close Compare Product window")
    public MobilePageObject closeCompareProductWindows(String parentID) {
        closeAllWindowsWithoutParent(driver,parentID);
        return PageGeneratorManager.getMobilePage(driver);
    }
}
