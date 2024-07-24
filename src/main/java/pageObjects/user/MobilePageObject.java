package pageObjects.user;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.MobileUIs;

public class MobilePageObject extends BaseElement {
    WebDriver driver;
    public MobilePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Click to Add Product To Cart By productName with value {0}")
    public ShoppingCartPO clickAddProductToCartByName(String nameOfProduct ) {
        nameOfProduct = upperCaseAllFirstCharacter(nameOfProduct);
        waitForElementClickable(driver, MobileUIs.DYNAMIC_ADD_PRODUCT_TO_CART_BY_NAME,nameOfProduct);
        clickToElement(driver, MobileUIs.DYNAMIC_ADD_PRODUCT_TO_CART_BY_NAME,nameOfProduct);
        return PageGeneratorManager.getShopingCartPage(driver);
    }
    @Step("Click to Compare Product By productName with value {0}")
    public void clickCompareProductByName(String productName) {
        waitForElementClickable(driver,MobileUIs.DYNAMIC_COMPARE_PRODUCT_BY_NAME,productName);
        clickToElement(driver,MobileUIs.DYNAMIC_COMPARE_PRODUCT_BY_NAME,productName);
    }
    @Step("Click to Compare button")
    public String clickToCompareButton() {
        String currentWindowID = driver.getWindowHandle();
        waitForElementClickable(driver,MobileUIs.COMPARE_PRODUCT_BUTTON);
        clickToElement(driver,MobileUIs.COMPARE_PRODUCT_BUTTON);
        return currentWindowID;
    }
}
