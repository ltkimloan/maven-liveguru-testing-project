package pageUIs.user;

public class ShoppingCartUIs {
    public static final String DISCOUNT_COUPON_VALUE_PRICE_AT_TOTAL = "xpath=//div[@class='cart-totals']//tr[2]/td[contains(.,'%s')]/following-sibling::td//span";
    public static final String DYNAMIC_PRODUCT_BY_NAME = "xpath=//h2[@class='product-name']/a[text()='%s']";
    public static final String DYNAMIC_PRICE_VALUE_BY_NAME = "xpath=//td[contains(.,'%s')]/following-sibling::td";
    public static final String COUPON_CODE_FIELD = "id=coupon_code";
    public static final String APPLY_COUPON_CODE_BUTTON ="xpath=//button[@title='Apply']" ;
    public static final String DYNAMIC_DISCOUNT_COUPON_CODE_AT_TOTAL = "xpath=//div[@class='cart-totals']//tr[2]/td[contains(.,'%s')]";
    public static final String DYNAMIC_QTY_BY_PRODUCT_NAME = "xpath=//h2[@class='product-name']/a[text()='Sony Xperia']/ancestor::td[@class='product-cart-info']/following-sibling::td[@class='product-cart-actions']/input";
    public static final String UPDATE_QTY_BUTTON = "xpath=//button[@class='button btn-update']";
    public static final String EMPTY_CART_BUTTON = "id=empty_cart_button";
    public static final String COUNTRY_DROPDOWN = "name=country_id";
    public static final String PROCEED_CHECKOUT_BUTTON = "css=button.btn-proceed-checkout";
    public static final String STATE_DROPDOWN = "id=region_id";
    public static final String ZIP_FIELD = "xpath=//li[@class='shipping-postcode']//input";
    public static final String ESTIMATE_BUTTON = "xpath=//button[@title='Estimate']";
    public static final String FLAT_RATE_SHIP = "xpath=//dt[text()='Flat Rate']";
    public static final String SHIP_COST_METHOD = "id=s_method_flatrate_flatrate";
    public static final String UPDATE_TOTAL_BUTTON = "xpath=//button[@title='Update Total']";
    public static final String FLAT_RATE_SHIP_AT_TOTAL = "xpath=//td[contains(.,'Flat Rate')]";
    public static final String GRAND_TOTAL_PRICE = "xpath=//strong[text()='Grand Total']/parent::td/following-sibling::td//span[@class='price']";
}
