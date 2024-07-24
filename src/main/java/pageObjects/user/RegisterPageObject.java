package pageObjects.user;

import commons.BaseElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.RegisterUIs;

public class RegisterPageObject extends BaseElement {
    WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Enter into Register Form")
    public void enterToRegisterForm(String firstName, String lastName, String emailAdress, String password,String confirmPassword) {
        enterToFirstNameField(firstName);
        enterToLastNameField(lastName);
        enterToEmailAddressField(emailAdress);
        enterToPasswordField(password);
        enterToConfirmPasswordField(confirmPassword);
    }
    @Step("Enter into Firstname textbox with value {0}")
    public void enterToFirstNameField(String firstName) {
        waitForElementVisible(driver, RegisterUIs.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, RegisterUIs.FIRST_NAME_TEXTBOX,firstName);
    }
    @Step("Enter into Lastname textbox with value {0}")
    public void enterToLastNameField(String lastName) {
        waitForElementVisible(driver, RegisterUIs.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, RegisterUIs.LAST_NAME_TEXTBOX,lastName);
    }
    @Step("Enter into Confirm Email textbox with value {0}")
    public void enterToEmailAddressField(String emailAdress) {
        waitForElementVisible(driver, RegisterUIs.EMAIL_ADDRESS_TEXTBOX);
        sendkeyToElement(driver, RegisterUIs.EMAIL_ADDRESS_TEXTBOX,emailAdress);
    }
    @Step("Enter into Password textbox with value {0}")
    public void enterToPasswordField(String password) {
        waitForElementVisible(driver, RegisterUIs.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, RegisterUIs.PASSWORD_TEXTBOX,password);
    }
    @Step("Enter into Confirm Password textbox with value {0}")
    public void enterToConfirmPasswordField(String password) {
        waitForElementVisible(driver, RegisterUIs.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, RegisterUIs.CONFIRM_PASSWORD_TEXTBOX,password);
    }
    @Step("Click to Register button")
    public void clickToRegisterButton() {
        waitForElementClickable(driver,RegisterUIs.REGISTER_BUTTON);
        clickToElement(driver,RegisterUIs.REGISTER_BUTTON);
    }
    @Step("Get Register text message")
    public String getRegisterTextMessage() {
        waitForElementVisible(driver,RegisterUIs.SUCCESS_MESSAGE);
        return getElementText(driver,RegisterUIs.SUCCESS_MESSAGE);
    }
    @Step("Get Contact Information")
    public String getContactInformation() {
        waitForElementVisible(driver,RegisterUIs.CONTACT_INFORMATION);
        return getElementText(driver,RegisterUIs.CONTACT_INFORMATION);
    }

}
