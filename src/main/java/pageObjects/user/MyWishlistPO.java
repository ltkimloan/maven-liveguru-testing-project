package pageObjects.user;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.MyWishlistUIs;

public class MyWishlistPO extends BaseElement {
    WebDriver driver;

    public MyWishlistPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Click to Share Wishlist button")
    public ShareYourWishlistPO clickToShareWishlistButton() {
        waitForElementClickable(driver, MyWishlistUIs.SHARE_WISHLIST_BUTTON);
        clickToElement(driver, MyWishlistUIs.SHARE_WISHLIST_BUTTON);
        return PageGeneratorManager.getShareYourWishlistPage(driver);
    }
    @Step("Get Size of Product at My Wishlist Page ")
    public int getSizeOfProductAtMyWishlistPage() {
        waitForElementVisible(driver,MyWishlistUIs.PRODUCTS_AT_WISHLIST);
        return getListElementSize(driver,MyWishlistUIs.PRODUCTS_AT_WISHLIST);
    }
    @Step("Click Add To Cart link by Product Name with value {0}")
    public ShoppingCartPO clickAddToCartLinkByProductName(String productName) {
        waitForElementClickable(driver, MyWishlistUIs.ADD_TO_CART_LINK_BY_PRODUCT_NAME,productName);
        clickToElement(driver,MyWishlistUIs.ADD_TO_CART_LINK_BY_PRODUCT_NAME,productName);
        return PageGeneratorManager.getShopingCartPage(driver);
    }
    @Step("Click Add All To Cart link")
    public ShoppingCartPO clickAddAllToCartLink() {
        waitForElementClickable(driver, MyWishlistUIs.ADD_TO_CART_LINK_BY_PRODUCT_NAME);
        clickToElement(driver,MyWishlistUIs.ADD_TO_CART_LINK_BY_PRODUCT_NAME);
        return PageGeneratorManager.getShopingCartPage(driver);
    }
}
