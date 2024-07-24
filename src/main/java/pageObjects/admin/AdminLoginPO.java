package pageObjects.admin;

import commons.BaseElement;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminLoginUIs;

public class AdminLoginPO extends BaseElement {
    WebDriver driver;

    public AdminLoginPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Enter {0} into Username Textbox")
    public void enterIntoUsernameTextbox(String username) {
        waitForElementVisible(driver, AdminLoginUIs.USER_NAME_TBX);
        sendkeyToElement(driver, AdminLoginUIs.USER_NAME_TBX,username);
    }
    @Step("Enter {0} into Password Textbox")
    public void enterIntoPasswordTextbox(String password) {
        waitForElementVisible(driver, AdminLoginUIs.PASSWORD_TBX);
        sendkeyToElement(driver, AdminLoginUIs.PASSWORD_TBX,password);
    }
    @Step("Click to Login button")
    public AdminCustomersPO clickToLoginButton() {
        waitForElementVisible(driver, AdminLoginUIs.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginUIs.LOGIN_BUTTON);
        return PageGeneratorManager.getAdminCustomerPage(driver);
    }
    @Step("Is Login Page Display")
    public boolean isLoginPageDisplay() {
        return !isElementUnDisplayed(driver, AdminLoginUIs.LOGIN_FORM,10);
    }
}
