package pageObjects.user;

import commons.BaseElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.AdvancedSearchUIs;
import pageUIs.user.BaseElementUIs;

import java.util.List;

public class AdvancedSearchPO extends BaseElement {
    WebDriver driver;

    public AdvancedSearchPO(WebDriver driver) {
        super(driver);
        this.driver =driver;
    }
    @Step("Enter {0} into Price From field")
    public void enterToPriceFrom(String priceValue) {
        waitForElementVisible(driver, AdvancedSearchUIs.PRICE_FROM_FIELD);
        sendkeyToElement(driver, AdvancedSearchUIs.PRICE_FROM_FIELD,priceValue);
    }
    @Step("Click to Search button")
    public void clickToSearchButton() {
        waitForElementClickable(driver,AdvancedSearchUIs.SEARCH_BUTTON);
        clickToElement(driver,AdvancedSearchUIs.SEARCH_BUTTON);
    }
    @Step("Enter {0} into Price To field")
    public void enterToPriceTo(String priceValue) {
        waitForElementVisible(driver, AdvancedSearchUIs.PRICE_TO_FIELD);
        sendkeyToElement(driver, AdvancedSearchUIs.PRICE_TO_FIELD,priceValue);
    }
    @Step("Get Price of List Products in Result")
    public List<WebElement> getPriceOfListProductsInResult() {
        waitForElementVisible(driver, AdvancedSearchUIs.PRICE_OF_LIST_PRODUCTS_BY_NAME);
        return getListWebElement(driver, AdvancedSearchUIs.PRICE_OF_LIST_PRODUCTS_BY_NAME);
    }
}
