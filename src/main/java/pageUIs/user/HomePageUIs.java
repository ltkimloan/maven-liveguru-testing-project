package pageUIs.user;

public class HomePageUIs {
    public static final String ACCOUNT_MENU = "xpath=//div[@class='page-header-container']//span[text()='Account']/parent::a";
    public static final String REGISTER_LINK  = "xpath=//div[@id='header-account']//a[text()='Register']/parent::li";

    public static final String LOGIN_LINK = "xpath=//div[@id='header-account']//a[text()='Log In']/parent::li";
    public static final String LOGOUT_LINK = "xpath=//div[@id='header-account']//a[text()='Log Out']/parent::li";
    public static final String MOBLE_MENU = "xpath=//a[text()='Mobile']";
    public static final String TV_MENU = "xpath=//a[text()='TV']";
    public static final String ADVANCED_SEARCH_BUTTON = "xpath=//a[@title='Advanced Search']";
    public static final String MY_WISHLIST_LINK = "xpath=//div[@id='header-account']//a[contains(.,'My Wishlist')]/parent::li";
}
