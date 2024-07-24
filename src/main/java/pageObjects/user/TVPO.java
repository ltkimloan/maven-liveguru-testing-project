package pageObjects.user;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.TVUIs;

public class TVPO extends BaseElement {
    WebDriver driver;

    public TVPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Click To Add Product To Wishlist By Product Name {0}")
    public MyWishlistPO clickToAddProductToWishlistByName(String productName) {
        waitForElementClickable(driver, TVUIs.DYNAMIC_ADD_TO_WISHLIST_BY_NAME,productName);
        clickToElement(driver, TVUIs.DYNAMIC_ADD_TO_WISHLIST_BY_NAME,productName);
        return PageGeneratorManager.getMyWishlistPage(driver);
    }
}
