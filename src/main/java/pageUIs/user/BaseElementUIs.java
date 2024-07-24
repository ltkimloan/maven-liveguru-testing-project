package pageUIs.user;

public class BaseElementUIs {
    public static final String UPLOAD_FILE_TYPE = "css=input[name='files[]']";
    public static final String LIVE_GURU_LOGO = "xpath=//div[@class='page-title']//img";
    public static final String DYNAMIC_SUCCESS_MSG = "xpath=//li[@class='success-msg']//span";
    public static final String DYNAMIC_ERROR_MSG = "xpath=//li[@class='error-msg']//span";
    public static final String PAGE_TITLE_MSG = "xpath=//div[@class='page-title']/h1";
    public static final String DYNAMIC_PRICE_OF_PRODUCT_BY_NAME = "xpath=//a[text()='%s']/parent::h2[@class='product-name']/following-sibling::div[@class='price-box']";
    public static final String DYNAMIC_PRODUCT_BY_NAME = "xpath=//a[text()='%s']/parent::h2[@class='product-name']";
    public static final String POPUP_MSG = "id=message-popup-window";
    public static final String CLOSE_POPUP_BUTTON = "xpath=//a[@title='close']";
    public static final String COLUMN_NUMBER_BY_COLUMN_NAME = "xpath=//span[text()='%s']/parent::a/ancestor::th/preceding-sibling::th";
    public static final String RECORD_VALUE_COLUMN_BY_COLUMN_NUMBER = "xpath=//tr[contains(@class,'pointer')]//td[%s]";
    public static final String RECORD_VALUE_COLUMN_BY_COLUMN_NUMBER_AND_EMAIL_ADDRESS = "xpath=//td[contains(.,'%s')]/parent::tr/td[%s]";
    public static final String FIRST_RECORD_VALUE_COLUMN_BY_COLUMN_NUMBER = "xpath=//tr[contains(@class,'even')][1]//td[%s]";
    public static final String REVIEW_HEADING = "css=div.review-heading";
    public static final String RESET_FILTER_BUTTON = "xpath=//button[@title='Reset Filter']";

}
