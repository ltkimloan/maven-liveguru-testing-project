package pageUIs.user;

public class MyWishlistUIs {
    public static final String SHARE_WISHLIST_BUTTON = "xpath=//button[@title='Share Wishlist']";
    public static final String PRODUCTS_AT_WISHLIST = "xpath=//h3[@class='product-name']";
    public static final String ADD_ALL_TO_CART_LINK = "xpath=//button[@title='Add All to Cart']";
    public static final String ADD_TO_CART_LINK_BY_PRODUCT_NAME = "xpath=//div[@class='cart-cell']/button[@class='button btn-cart']";
    public static final String DYNAMIC_ADD_TO_CART_LINK_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/ancestor::td/following-sibling::td//div[@class='cart-cell']/button[@class='button btn-cart']";

}
