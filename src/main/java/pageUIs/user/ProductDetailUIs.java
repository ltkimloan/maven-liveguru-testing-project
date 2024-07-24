package pageUIs.user;

public class ProductDetailUIs {
    public static final String ADD_YOUR_REVIEW_BUTTON = "xpath=//a[text()='Add Your Review']";
    public static final String DYNAMIC_COST_OF_PRODUCT = "xpath=//span[text()='%s']/parent::div/following-sibling::div[@class='price-info']//span[@class='price']";
    public static final String RATING_REVIEW = "xpath=//a[contains(.,'Review(s)')]";
    public static final String RATING_REVIEW_AT_REVIEW_TAB = "xpath=//div[@id='customer-reviews']//div[@class='review-heading']//span";
    public static final String REVIEW_LATEST = "xpath=//div[@id='customer-reviews']//dd[1]//span[@class='review-meta'][1]";
}
