package commons;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.admin.AdminLoginPO;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.ProductDetailPO;
import pageUIs.admin.AdminOrdersUIs;
import pageUIs.user.BaseElementUIs;

public class BaseElement extends BasePage {
    public WebDriver driver;

    public BaseElement(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get Success Messgage Text")
    public String getSuccessMessageText() {
        waitForElementVisible(driver, BaseElementUIs.DYNAMIC_SUCCESS_MSG);
        return getElementText(driver,BaseElementUIs.DYNAMIC_SUCCESS_MSG);
    }
    @Step("Get Error Message Text")
    public String getErrorMessageText() {
        waitForElementVisible(driver, BaseElementUIs.DYNAMIC_ERROR_MSG);
        return getElementText(driver,BaseElementUIs.DYNAMIC_ERROR_MSG);
    }
    @Step("Get Page Title Msg")
    public String getPageTitleMsg() {
        waitForElementVisible(driver, BaseElementUIs.PAGE_TITLE_MSG);
        return getElementText(driver,BaseElementUIs.PAGE_TITLE_MSG);
    }
    @Step("Open Live Guru Site")
    public HomePageObject openLiveGuruSite() {
        navigateToUrlByJS(driver,"http://live.techpanda.org/");
        waitForElementVisible(driver, BaseElementUIs.LIVE_GURU_LOGO);
        return PageGeneratorManager.getHomePage(driver);
    }
    @Step("Get Price of Product by Product Name with value {0}")
    public String getPriceOfProductByName(String nameOfProduct) {
        System.out.println(nameOfProduct);
        nameOfProduct = upperCaseAllFirstCharacter(nameOfProduct);
        System.out.println(nameOfProduct);
        waitForElementVisible(driver, BaseElementUIs.DYNAMIC_PRICE_OF_PRODUCT_BY_NAME,nameOfProduct);
        return getElementText(driver, BaseElementUIs.DYNAMIC_PRICE_OF_PRODUCT_BY_NAME,nameOfProduct);
    }
    @Step("Click to Product By Product Name {0}")
    public ProductDetailPO clickToProductByName(String nameOfProduct) {
        waitForElementClickable(driver, BaseElementUIs.DYNAMIC_PRODUCT_BY_NAME,nameOfProduct);
        clickToElement(driver, BaseElementUIs.DYNAMIC_PRODUCT_BY_NAME,nameOfProduct);
        return PageGeneratorManager.getProductDetailPage(driver);
    }
    @Step("Is Popup Message Show")
    public boolean isPopupMessageShow() {
        return !isElementUnDisplayed(driver,BaseElementUIs.POPUP_MSG);
    }
    @Step("Navigation to Admin URL")
    public AdminLoginPO navigationAdminURL() {
        openPageUrl(driver, GlobalConstants.ADMIN_URL);
        return PageGeneratorManager.getAdminLoginPage(driver);
    }
    @Step("Navigation to User URL")
    public LoginPageObject navigationUserURL() {
        openPageUrl(driver, GlobalConstants.USER_URL);
        isPageLoadedSuccess(driver);
        return PageGeneratorManager.getLoginPage(driver);
    }
    @Step("Click to Close Popup ")
    public void clickToClosePopup() {
        waitForElementClickable(driver, BaseElementUIs.CLOSE_POPUP_BUTTON);
        clickToElement(driver,BaseElementUIs.CLOSE_POPUP_BUTTON);
    }
    @Step("Get First Record Value By Column Name with value {0}")
    public String getFirstRecordValueByColumnName(String columnName) {
        int columnNumber = getListWebElement(driver, BaseElementUIs.COLUMN_NUMBER_BY_COLUMN_NAME,columnName).size() +1;
        return getElementText(driver,BaseElementUIs.FIRST_RECORD_VALUE_COLUMN_BY_COLUMN_NUMBER,String.valueOf(columnNumber));
    }
    @Step("Click To Reset Filter")
    public void clickToResetFilter() {
        waitForElementClickable(driver, BaseElementUIs.RESET_FILTER_BUTTON);
        clickToElementByJS(driver,BaseElementUIs.RESET_FILTER_BUTTON);
    }

}
