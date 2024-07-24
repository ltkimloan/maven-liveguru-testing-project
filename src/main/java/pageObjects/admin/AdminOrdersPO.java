package pageObjects.admin;

import commons.BaseElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminCustomersUIs;
import pageUIs.admin.AdminOrdersUIs;

public class AdminOrdersPO extends BaseElement {
    WebDriver driver;

    public AdminOrdersPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Click to Search button")
    public void clickToSearchButton() {
        waitForElementClickable(driver,AdminOrdersUIs.SEARCH_BUTTON);
        clickToElement(driver,AdminOrdersUIs.SEARCH_BUTTON);
    }
    @Step("Click to the First Order Checkbox")
    public void clickToCheckboxNextToTheFirstOrder() {
        waitForElementClickable(driver,AdminOrdersUIs.FIRST_ORDER_CHECKBOX);
        clickToElementByJS(driver,AdminOrdersUIs.FIRST_ORDER_CHECKBOX);
    }
    @Step("Click Submit button")
    public void clickSubmitButton() {
        waitForElementClickable(driver,AdminOrdersUIs.SUBMIT_BUTTON);
        clickToElement(driver,AdminOrdersUIs.SUBMIT_BUTTON);
    }
    @Step("Select {0} Item of Actions Dropdown")
    public void selectItemOfActionsDropdownByItemName(String itemValue) {
        waitForElementVisible(driver, AdminOrdersUIs.ACTIONS_DROPDOWN);
        clickToElement(driver,AdminOrdersUIs.ACTIONS_DROPDOWN);
        selectItemInDefaultDropdown(driver,AdminOrdersUIs.ACTIONS_DROPDOWN,itemValue);
    }
    @Step("Select {0} Item of Status Dropdown")
    public void selectItemOfStatusDropdownByItemName(String itemValue) {
        waitForElementVisible(driver, AdminOrdersUIs.STATUS_DROPDOWN);
        clickToElement(driver,AdminOrdersUIs.STATUS_DROPDOWN);
        selectItemInDefaultDropdown(driver,AdminOrdersUIs.STATUS_DROPDOWN,itemValue);
    }
    @Step("CLick To Select Visible link")
    public void clickSelectVisibleLink() {
        waitForElementClickable(driver,AdminOrdersUIs.SELECT_VISIBLE_BUTTON);
        clickToElement(driver,AdminOrdersUIs.SELECT_VISIBLE_BUTTON);
    }
    @Step("Get Number Item Selected")
    public int getNumberItemSelected() {
        waitForElementClickable(driver,AdminOrdersUIs.SELECT_ITEM_NUMBER);
        String numberText = getElementText(driver,AdminOrdersUIs.SELECT_ITEM_NUMBER);
        return Integer.parseInt(numberText);
    }
    @Step("Get Number Total Record Found")
    public int getNumberTotalRecordFound() {
        waitForElementClickable(driver,AdminOrdersUIs.TOTAL_RECORD_FOUND);
        String totalRecordText = getElementText(driver,AdminOrdersUIs.TOTAL_RECORD_FOUND);
        String[] totalrecord = totalRecordText.split("\\|");
        String countTotal = totalrecord[2].trim();

        int totalRecordNumber = Integer.parseInt(countTotal.replaceAll("[^\\d\\,\\.]",""));
        return totalRecordNumber;
    }
    @Step("Click to Unselect Visible link")
    public void clickUnSelectVisibleLink() {
        waitForElementClickable(driver,AdminOrdersUIs.UNSELECT_VISIBLE_BUTTON);
        clickToElement(driver,AdminOrdersUIs.UNSELECT_VISIBLE_BUTTON);
    }
    @Step("select {0} of View Per Page ")
    public void selectViewPerPageByNumber(String number) {
        waitForElementVisible(driver, AdminOrdersUIs.VIEW_PAGE_DROPDOWN);
        selectItemInDefaultDropdown(driver,AdminOrdersUIs.VIEW_PAGE_DROPDOWN,number);
    }
    @Step("Get Number Record Displayed Per Page")
    public int getNumberRecordDisplayedPerPage() {
        waitForElementVisible(driver,AdminOrdersUIs.LIST_RECORD_ORDER_ID);
        return getListWebElement(driver,AdminOrdersUIs.LIST_RECORD_ORDER_ID).size();
    }

}
