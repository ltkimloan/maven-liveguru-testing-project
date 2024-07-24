package pageUIs.user;

public class ProductReviewUIs {
    public static final String THOUGHTS_FIELD = "xpath=//label[contains(.,'thoughts')]/following-sibling::div[@class='input-box']/textarea";
    public static final String REVIEWS_FIELD = "xpath=//label[contains(.,'Review')]/following-sibling::div[@class='input-box']/input";
    public static final String NICKNAME_FIELD = "xpath=//label[contains(.,'nickname')]/following-sibling::div[@class='input-box']/input";
    public static final String SUBMIT_REVIEW_BUTTON = "xpath=//button[@title='Submit Review']";
    public static final String DYNAMIC_QUALITY_RATE_CHECKBOX = "xpath=//input[@type='radio' and @value='%s']";
    public static final String ERROR_MSG_QUALITY = "xpath=//div[@id='advice-validate-rating-validate_rating']";
    public static final String DYNAMIC_ERROR_MSG_BY_NAME_FIELD = "xpath=//label[contains(.,'%s')]/following-sibling::div[@class='input-box']/div[@class='validation-advice']";
}
