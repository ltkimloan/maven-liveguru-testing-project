package pageUIs.user;

public class CheckoutUIs {
    public static final String CHECKOUT_PAGE_TITLE = "xpath=//div[@class='page-title']/h1[text()='Checkout']";
    public static final String DYNAMIC_TEXTBOX_BY_LABEL_NAME = "xpath=//label[@class='required' and contains(.,'%s')]/following-sibling::div[@class='input-box']/input";
    public static final String COUNTINUE_BUTTON = "xpath=//div[@style='']//span[text()='Continue']/ancestor::button[@class='button']";
    public static final String FLAT_RATE_SHIP_AT_SHIP_METHOD = "xpath=//dt[text()='Flat Rate']";
    public static final String DYNAMIC_PAYMENT_CHECKBOX_BY_NAME = "xpath=//label[contains(.,'%s')]/preceding-sibling::input";
    public static final String PLACE_ORDER_BUTTON = "xpath=//button[@title='Place Order']";
    public static final String ORDER_NUMBER = "xpath=//p[contains(.,'Your order')]/a";
    public static final String STATE_DROPDOWN = "xpath=//select[@id='billing:region_id']";
}
