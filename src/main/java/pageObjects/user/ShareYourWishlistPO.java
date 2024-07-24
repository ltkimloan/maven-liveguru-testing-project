package pageObjects.user;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.ShareYourWishlistUIs;

public class ShareYourWishlistPO extends BaseElement {
    WebDriver driver;

    public ShareYourWishlistPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Enter email address into Sharing information with value {0}")
    public void enterEmailIntoSharingInformation(String emailAddress) {
        waitForElementVisible(driver,ShareYourWishlistUIs.EMAIL_TEXTAREA);
        sendkeyToElement(driver,ShareYourWishlistUIs.EMAIL_TEXTAREA,emailAddress);
    }
    @Step("Enter Message into Sharing information with value {0}")
    public void enterMessageIntoSharingInformation(String message) {
        waitForElementVisible(driver,ShareYourWishlistUIs.MESSAGE_TEXTAREA);
        sendkeyToElement(driver,ShareYourWishlistUIs.MESSAGE_TEXTAREA,message);
    }
    @Step("Click To Share Wishlist button")
    public MyWishlistPO clickToShareWishlistButton() {
        waitForElementClickable(driver, ShareYourWishlistUIs.SHARE_WISHLIST_BUTTON);
        clickToElement(driver, ShareYourWishlistUIs.SHARE_WISHLIST_BUTTON);
        return PageGeneratorManager.getMyWishlistPage(driver);
    }
}
