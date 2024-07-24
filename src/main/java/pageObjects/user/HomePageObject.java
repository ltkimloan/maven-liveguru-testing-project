package pageObjects.user;

import commons.BaseElement;
import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.HomePageUIs;

public class HomePageObject extends BaseElement {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Click on Account Menu")
    public void clickOnAccountMenu() {
        waitForElementClickable(driver, HomePageUIs.ACCOUNT_MENU);
        clickToElement(driver, HomePageUIs.ACCOUNT_MENU);
    }
    @Step("Click To Register link")
    public RegisterPageObject clickToRegisterLink() {
        waitForElementClickable(driver, HomePageUIs.REGISTER_LINK);
        clickToElement(driver, HomePageUIs.REGISTER_LINK);
        return PageGeneratorManager.getRegisterPage(driver);
    }
    @Step("Click to Login link")
    public LoginPageObject clickToLoginLink() {
        waitForElementClickable(driver,HomePageUIs.LOGIN_LINK);
        clickToElement(driver,HomePageUIs.LOGIN_LINK);
        return PageGeneratorManager.getLoginPage(driver);
    }
    @Step("Click to Log out link")
    public void clickToLogOutLink() {
        waitForElementClickable(driver,HomePageUIs.LOGOUT_LINK);
        clickToElement(driver,HomePageUIs.LOGOUT_LINK);
    }
    @Step("Click to Mobile Menu")
    public MobilePageObject clickToMobileMenu() {
        waitForElementClickable(driver,HomePageUIs.MOBLE_MENU);
        clickToElement(driver,HomePageUIs.MOBLE_MENU);
        return PageGeneratorManager.getMobilePage(driver);
    }
    @Step("Click To TV Menu")
    public TVPO clickToTVMenu() {
        waitForElementClickable(driver,HomePageUIs.TV_MENU);
        clickToElement(driver,HomePageUIs.TV_MENU);
        return PageGeneratorManager.getTVPage(driver);
    }
    @Step("Click to Advanced Search")
    public AdvancedSearchPO clickToAdvancedSearch() {
        waitForElementClickable(driver,HomePageUIs.ADVANCED_SEARCH_BUTTON);
        clickToElement(driver,HomePageUIs.ADVANCED_SEARCH_BUTTON);
        return  PageGeneratorManager.getAdvancedSearchPage(driver);
    }
    @Step("Click to My Wishlist link")
    public MyWishlistPO clickToMyWishlistLink() {
        waitForElementClickable(driver,HomePageUIs.MY_WISHLIST_LINK);
        clickToElement(driver,HomePageUIs.MY_WISHLIST_LINK);
        return PageGeneratorManager.getMyWishlistPage(driver);
    }
}
