package pageObjects.admin;

import commons.BaseElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminInvoicesUIs;

public class AdminInvoicesPO extends BaseElement {
    WebDriver driver;

    public AdminInvoicesPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("is Invoice Display By {0} Sort")
    public boolean isInvoiceDisplayBySort(String sortByName) {
        return isElementUnDisplayed(driver, AdminInvoicesUIs.INVOICE_DISPLAYED_BY_SORT,sortByName);
    }
    @Step("Click Invoices To Sort")
    public void clickInvoicesToSort() {
        waitForElementClickable(driver,AdminInvoicesUIs.INVOICE_SORT);
        clickToElement(driver,AdminInvoicesUIs.INVOICE_SORT);
    }
    @Step("Get Invoice ID By Row Number {0} ")
    public int getInvoiceIDByRowNumber(String number) {
        return Integer.parseInt(getElementText(driver,AdminInvoicesUIs.INVOICE_VALUE_BY_RECORD_NUMBER,number));
    }
}
