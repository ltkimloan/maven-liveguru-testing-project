package pageObjects.admin;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminCustomersUIs;
import pageUIs.admin.AdminOrdersUIs;
import pageUIs.user.BaseElementUIs;

public class AdminCustomersPO extends BaseElement {
    WebDriver driver;

    public AdminCustomersPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Click to Orders Menu of Sales")
    public AdminOrdersPO clickToOrdersMenuOfSales() {
        waitForElementVisible(driver,AdminCustomersUIs.SALE_MENU);
        clickToElement(driver,AdminCustomersUIs.SALE_MENU);

        waitForElementVisible(driver,AdminCustomersUIs.ORDERS_OF_SALE_MENU);
        clickToElement(driver,AdminCustomersUIs.ORDERS_OF_SALE_MENU);
        return PageGeneratorManager.getAdminOrdersPage(driver);
    }
    @Step("Click to Search button")
    public void clickToSearchButton() {
        waitForElementClickable(driver, AdminOrdersUIs.SEARCH_BUTTON);
        clickToElement(driver,AdminOrdersUIs.SEARCH_BUTTON);
    }
    @Step("Click To Pending Reviews Menu Of Catalog Menu")
    public AdminPendingReviewPO clickToPendingReviewsMenuOfCatalog() {
        waitForElementVisible(driver,AdminCustomersUIs.CATALOG_MENU);
        clickToElement(driver,AdminCustomersUIs.CATALOG_MENU);
        clickToElement(driver,AdminCustomersUIs.REVIEWS_AND_RATINGS_MENU);
        clickToElement(driver,AdminCustomersUIs.CUSTOMER_REVIEWS_MENU);

        waitForElementVisible(driver,AdminCustomersUIs.PENDING_REVIEWS_MENU);
        clickToElement(driver,AdminCustomersUIs.PENDING_REVIEWS_MENU);
        return PageGeneratorManager.getAdminPendingReviewPage(driver);
    }
    @Step("Click To Invoices Menu Of Sales")
    public AdminInvoicesPO clickToInvoicesMenuOfSales() {
        waitForElementVisible(driver,AdminCustomersUIs.SALE_MENU);
        clickToElement(driver,AdminCustomersUIs.SALE_MENU);

        waitForElementVisible(driver,AdminCustomersUIs.INVOICES_OF_SALE_MENU);
        clickToElement(driver,AdminCustomersUIs.INVOICES_OF_SALE_MENU);
        return PageGeneratorManager.getAdminInvoicesPage(driver);
    }
    @Step("Enter Value Into Search Textbox by Email Address {0}")
    public void enterValueIntoSearchByEmail(String emailAddress) {
        waitForElementVisible(driver,AdminCustomersUIs.SEARCH_TEXTBOX_BY_EMAIL);
        sendkeyToElement(driver,AdminCustomersUIs.SEARCH_TEXTBOX_BY_EMAIL,emailAddress);
    }
    @Step("Get Record Value By Column Name {0} according to Row Value {1}  ")
    public String getRecordValueByColumnNameAccordingToRowValue(String columnName, String emailAddress) {
        int columnNumber = getListWebElement(driver, BaseElementUIs.COLUMN_NUMBER_BY_COLUMN_NAME,columnName).size() +1;
        waitForElementVisible(driver,BaseElementUIs.RECORD_VALUE_COLUMN_BY_COLUMN_NUMBER_AND_EMAIL_ADDRESS,emailAddress,String.valueOf(columnNumber));
        return getElementText(driver,BaseElementUIs.RECORD_VALUE_COLUMN_BY_COLUMN_NUMBER_AND_EMAIL_ADDRESS,emailAddress,String.valueOf(columnNumber));
    }

    @Step("Enter ID {0} into Search texbox")
    public void enterValueIntoSearchByID(String idNumber) {
        waitForElementVisible(driver,AdminCustomersUIs.SEARCH_TEXTBOX_BY_FROM_ID_NUMBER);
        sendkeyToElement(driver,AdminCustomersUIs.SEARCH_TEXTBOX_BY_FROM_ID_NUMBER,idNumber);
    }
}
