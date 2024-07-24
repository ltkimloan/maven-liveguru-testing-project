package pageObjects.user;

import commons.BaseElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.ProductReviewUIs;
import pageUIs.user.TVUIs;

public class ProductReviewPO extends BaseElement {
    WebDriver driver;

    public ProductReviewPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Select Quality Rate of Product with value is {0}")
    public void selectQualityRateOfProduct(String number) {
        waitForElementVisible(driver, ProductReviewUIs.DYNAMIC_QUALITY_RATE_CHECKBOX,number);
        clickToElement(driver,ProductReviewUIs.DYNAMIC_QUALITY_RATE_CHECKBOX,number);
    }
    @Step("Enter into Thoughts field with value {0}")
    public void enterIntoThoughtsField(String text) {
        waitForElementVisible(driver, ProductReviewUIs.THOUGHTS_FIELD);
        sendkeyToElement(driver,ProductReviewUIs.THOUGHTS_FIELD,text);
    }
    @Step("Enter into Your Review field with value {0}")
    public void enterIntoYourReviewsField(String text) {
        waitForElementVisible(driver,ProductReviewUIs.REVIEWS_FIELD);
        sendkeyToElement(driver,ProductReviewUIs.REVIEWS_FIELD,text);
    }
    @Step("Enter into Nickname field with value {0}")
    public void enterIntoNicknameField(String text) {
        waitForElementVisible(driver,ProductReviewUIs.NICKNAME_FIELD);
        sendkeyToElement(driver,ProductReviewUIs.NICKNAME_FIELD,text);
    }
    @Step("Click to Submit Review button")
    public void clickToSubmitReviewButton() {
        waitForElementClickable(driver,ProductReviewUIs.SUBMIT_REVIEW_BUTTON);
        clickToElement(driver,ProductReviewUIs.SUBMIT_REVIEW_BUTTON);
    }
    @Step("Get Error Message By Quality Checkbox")
    public String getErrorMessageByQualityCheckbox() {
        waitForElementVisible(driver,ProductReviewUIs.ERROR_MSG_QUALITY);
        return getElementText(driver,ProductReviewUIs.ERROR_MSG_QUALITY);
    }
    @Step("Get Error Message By Name Field with value {0}")
    public String getErrorMessageByNameField(String nameField) {
        waitForElementVisible(driver,ProductReviewUIs.DYNAMIC_ERROR_MSG_BY_NAME_FIELD,nameField);
        return getElementText(driver,ProductReviewUIs.DYNAMIC_ERROR_MSG_BY_NAME_FIELD,nameField);
    }
}
