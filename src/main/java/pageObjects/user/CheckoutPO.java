package pageObjects.user;

import commons.BaseElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.CheckoutUIs;

public class CheckoutPO extends BaseElement {
    WebDriver driver;

    public CheckoutPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("enter into Bill Information with value {0}, {1}, {2}, {3}")
    public void enterIntoBillInformation(String address, String state, String zip, String telephone) {
    waitForElementVisible(driver, CheckoutUIs.CHECKOUT_PAGE_TITLE);
    sendkeyToElement(driver,CheckoutUIs.DYNAMIC_TEXTBOX_BY_LABEL_NAME,address,"Address");
    sendkeyToElement(driver,CheckoutUIs.DYNAMIC_TEXTBOX_BY_LABEL_NAME,state,"City");

    selectItemInDefaultDropdown(driver,CheckoutUIs.STATE_DROPDOWN,state,"State");
    sendkeyToElement(driver,CheckoutUIs.DYNAMIC_TEXTBOX_BY_LABEL_NAME,zip,"Zip");
    sendkeyToElement(driver,CheckoutUIs.DYNAMIC_TEXTBOX_BY_LABEL_NAME,telephone,"Telephone");
    }
    @Step("Click to Continue button")
    public void clickToContinueButton() {
        waitForElementVisible(driver,CheckoutUIs.COUNTINUE_BUTTON);
        clickToElement(driver,CheckoutUIs.COUNTINUE_BUTTON);
    }
    @Step("is Flat Rate Ship Display at Ship Method")
    public boolean isFlatRateShipDisplayAtShipMethod() {
        waitForElementVisible(driver,CheckoutUIs.FLAT_RATE_SHIP_AT_SHIP_METHOD);
        return isElementDisplayed(driver,CheckoutUIs.FLAT_RATE_SHIP_AT_SHIP_METHOD);
    }
    @Step("Select Payment Infor with value {0}")
    public void selectPaymentInformation(String paymentCheckbox) {
        waitForElementVisible(driver,CheckoutUIs.DYNAMIC_PAYMENT_CHECKBOX_BY_NAME,paymentCheckbox);
        clickToElement(driver,CheckoutUIs.DYNAMIC_PAYMENT_CHECKBOX_BY_NAME,paymentCheckbox);
    }
    @Step("Click to Place Order button")
    public void clickToPlaceOrderButton() {
        waitForElementVisible(driver,CheckoutUIs.PLACE_ORDER_BUTTON);
        clickToElement(driver,CheckoutUIs.PLACE_ORDER_BUTTON);
    }
    @Step("is The Order Number Generated")
    public boolean isTheOrderNumberGenerated() {
        waitForElementVisible(driver,CheckoutUIs.ORDER_NUMBER);
        int orderNumber = Integer.parseInt(getElementText(driver,CheckoutUIs.ORDER_NUMBER));
        return (isElementDisplayed(driver,CheckoutUIs.ORDER_NUMBER) && orderNumber >0 );
    }
}
