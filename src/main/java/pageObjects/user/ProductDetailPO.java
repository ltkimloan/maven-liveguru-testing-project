package pageObjects.user;

import commons.BaseElement;
import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.BaseElementUIs;
import pageUIs.user.ProductDetailUIs;

public class ProductDetailPO extends BaseElement {
    WebDriver driver;

    public ProductDetailPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Get Cost of Product by Product Name with value {0}")
    public String getCostOfProduct(String nameOfProduct) {
        nameOfProduct = upperCaseAllFirstCharacter(nameOfProduct);
        waitForElementVisible(driver, ProductDetailUIs.DYNAMIC_COST_OF_PRODUCT,nameOfProduct);
        return getElementText(driver, ProductDetailUIs.DYNAMIC_COST_OF_PRODUCT,nameOfProduct);
    }
    @Step("Click to Add Your Review")
    public ProductReviewPO clickToAddYourReview() {
        waitForElementClickable(driver,ProductDetailUIs.ADD_YOUR_REVIEW_BUTTON);
        clickToElement(driver,ProductDetailUIs.ADD_YOUR_REVIEW_BUTTON);
        return PageGeneratorManager.getProductReviewPage(driver);
    }
    @Step("Get count Items at Rating")
    public int getCountItemsAtRating() {
        waitForElementVisible(driver,ProductDetailUIs.RATING_REVIEW);
        String itemsReview = getElementText(driver,ProductDetailUIs.RATING_REVIEW);
        itemsReview = itemsReview.replaceAll("[^\\d.]", "");
        return Integer.valueOf(itemsReview);
    }
    @Step("Get count items at Review tab")
    public int getCountItemsAtReviewTab() {
        waitForElementVisible(driver,ProductDetailUIs.RATING_REVIEW_AT_REVIEW_TAB);
        String itemsReview = getElementText(driver,ProductDetailUIs.RATING_REVIEW_AT_REVIEW_TAB);
        itemsReview = itemsReview.replaceAll("[^\\d.]", "");
        return Integer.parseInt(itemsReview);
    }
    @Step("Get Latest Review Text At Review Tab")
    public String GetLatestReviewTextAtReviewTab() {
        waitForElementVisible(driver,ProductDetailUIs.REVIEW_LATEST);
        scrollToElementOnTop(driver, BaseElementUIs.REVIEW_HEADING);
        return getElementText(driver,ProductDetailUIs.REVIEW_LATEST);
    }
}
