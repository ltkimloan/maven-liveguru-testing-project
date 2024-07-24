package pageObjects.user;

import commons.BaseElement;
import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.LoginUIs;

public class LoginPageObject extends BaseElement {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Click To Login button")
    public MyAccountDashboardPO clickToLoginButton() {
        waitForElementClickable(driver, LoginUIs.LOGIN_BUTTON);
        clickToElement(driver, LoginUIs.LOGIN_BUTTON);
        return PageGeneratorManager.getMyAccountDashboardPage(driver);
    }
    @Step("Enter To Email Address textbox with value {0}")
    public void enterToEmailAddressField(String emailAddress) {
        waitForElementVisible(driver,LoginUIs.EMAIL_ADDRESS_TEXTBOX);
        sendkeyToElement(driver,LoginUIs.EMAIL_ADDRESS_TEXTBOX,emailAddress);
    }
    @Step("Enter To Password textbox with value {0}")
    public void enterToPasswordField(String password) {
        waitForElementVisible(driver,LoginUIs.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,LoginUIs.PASSWORD_TEXTBOX,password);
    }
}
