package pageObjects.user;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.ShoppingCartUIs;

public class ShoppingCartPO extends BaseElement {
    WebDriver driver;

    public ShoppingCartPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Get Discount {0} Coupon Value Price Display")
    public String getDiscountCouponValuePriceDisplay(String couponCode) {
        waitForElementVisible(driver, ShoppingCartUIs.DISCOUNT_COUPON_VALUE_PRICE_AT_TOTAL, couponCode);
        return getElementText(driver, ShoppingCartUIs.DISCOUNT_COUPON_VALUE_PRICE_AT_TOTAL, couponCode);
    }
    @Step("Is Product Display At Shopping Cart")
    public boolean isProductDisplayAtShoppingCart(String nameOfProduct) {
        nameOfProduct = upperCaseAllFirstCharacter(nameOfProduct);
        waitForElementVisible(driver, ShoppingCartUIs.DYNAMIC_PRODUCT_BY_NAME, nameOfProduct);
        return isElementDisplayed(driver, ShoppingCartUIs.DYNAMIC_PRODUCT_BY_NAME, nameOfProduct);
    }
    @Step("Enter {0} Coupon Code Into The Field")
    public void enterCouponCodeIntoTheField(String couponCode) {
        waitForElementVisible(driver, ShoppingCartUIs.COUPON_CODE_FIELD);
        sendkeyToElement(driver, ShoppingCartUIs.COUPON_CODE_FIELD, couponCode);
    }
    @Step("Click to Apply Coupon Codes")
    public void clickToApplyCouponCodes() {
        waitForElementClickable(driver, ShoppingCartUIs.APPLY_COUPON_CODE_BUTTON);
        clickToElement(driver, ShoppingCartUIs.APPLY_COUPON_CODE_BUTTON);
    }
    @Step("is Discount {0} Coupon Code Display At Total")
    public boolean isDiscountCouponCodeDisplayAtTotal(String couponCode) {
        waitForElementVisible(driver, ShoppingCartUIs.DYNAMIC_DISCOUNT_COUPON_CODE_AT_TOTAL, couponCode);
        return isElementDisplayed(driver, ShoppingCartUIs.DYNAMIC_DISCOUNT_COUPON_CODE_AT_TOTAL, couponCode);
    }
    @Step("Enter {1} QTY Value By Product Name with value {0}")
    public void enterQTYValueByProductName(String productName, String number) {
        waitForElementVisible(driver, ShoppingCartUIs.DYNAMIC_QTY_BY_PRODUCT_NAME, productName);
        sendkeyToElement(driver, ShoppingCartUIs.DYNAMIC_QTY_BY_PRODUCT_NAME, number, productName);
    }
    @Step("Click To Update button")
    public void clickToUpdateButton() {
        waitForElementVisible(driver, ShoppingCartUIs.UPDATE_QTY_BUTTON);
        clickToElement(driver, ShoppingCartUIs.UPDATE_QTY_BUTTON);
    }
    @Step("Click To Empty Cart button")
    public void clickToEmptyCartButton() {
        waitForElementClickable(driver, ShoppingCartUIs.EMPTY_CART_BUTTON);
        clickToElement(driver, ShoppingCartUIs.EMPTY_CART_BUTTON);
    }
    @Step("Get Price Value By Name Title with value {0}")
    public float getPriceValueByName(String priceName) {
        waitForElementVisible(driver, ShoppingCartUIs.DYNAMIC_PRICE_VALUE_BY_NAME, priceName);
        String priceText = getElementText(driver, ShoppingCartUIs.DYNAMIC_PRICE_VALUE_BY_NAME, priceName).replace("$", "");
        return Float.parseFloat(priceText);
    }
    @Step("Click to Proceed Checkout button")
    public CheckoutPO clickToProceedCheckoutButton() {
        waitForElementClickable(driver,ShoppingCartUIs.PROCEED_CHECKOUT_BUTTON);
        clickToElement(driver,ShoppingCartUIs.PROCEED_CHECKOUT_BUTTON);
        return PageGeneratorManager.getCheckoutPage(driver);
    }
    @Step("Select Country item with value {0}")
    public void selectCountryItem(String country) {
        waitForElementClickable(driver,ShoppingCartUIs.COUNTRY_DROPDOWN);
        selectItemInDefaultDropdown(driver,ShoppingCartUIs.COUNTRY_DROPDOWN,country);
    }
    @Step("Select State item with value {0}")
    public void selectStateItem(String state) {
        waitForElementClickable(driver,ShoppingCartUIs.STATE_DROPDOWN);
        selectItemInDefaultDropdown(driver,ShoppingCartUIs.STATE_DROPDOWN,state);
    }
    @Step("enter into Zip field with value {0}")
    public void enterZipField(String zipCode) {
        waitForElementClickable(driver,ShoppingCartUIs.ZIP_FIELD);
        sendkeyToElement(driver,ShoppingCartUIs.ZIP_FIELD,zipCode);
    }
    @Step("Click to Estimate button")
    public void clickToEstimateButton() {
        waitForElementClickable(driver,ShoppingCartUIs.ESTIMATE_BUTTON);
        clickToElement(driver,ShoppingCartUIs.ESTIMATE_BUTTON);
    }
    @Step("is FlatRate Ship Generate")
    public boolean isFlatRateShipGenerate() {
        waitForElementVisible(driver,ShoppingCartUIs.FLAT_RATE_SHIP);
        return isElementDisplayed(driver,ShoppingCartUIs.FLAT_RATE_SHIP);
    }
    @Step("Select Ship Cost")
    public void selectShipCost() {
        waitForElementClickable(driver,ShoppingCartUIs.SHIP_COST_METHOD);
        clickToElement(driver,ShoppingCartUIs.SHIP_COST_METHOD);
    }
    @Step("Click to Update Total")
    public void clickToUpdateTotal() {
        waitForElementClickable(driver,ShoppingCartUIs.UPDATE_TOTAL_BUTTON);
        clickToElement(driver,ShoppingCartUIs.UPDATE_TOTAL_BUTTON);
    }
    @Step("is Flat Rate Ship Display At Total")
    public boolean isFlatRateShipDisplayAtTotal() {
        waitForElementVisible(driver,ShoppingCartUIs.FLAT_RATE_SHIP_AT_TOTAL);
        return isElementDisplayed(driver,ShoppingCartUIs.FLAT_RATE_SHIP_AT_TOTAL);
    }
    @Step("Get Grand Total Price")
    public float getGrandTotalPrice() {
        waitForElementVisible(driver, ShoppingCartUIs.GRAND_TOTAL_PRICE);
        String priceText = getElementText(driver, ShoppingCartUIs.GRAND_TOTAL_PRICE).replace("$", "");
        return Float.parseFloat(priceText);
    }
}