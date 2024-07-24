package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.admin.*;
import pageObjects.user.*;

public class PageGeneratorManager {
    /*  USER */
    public static HomePageObject getHomePage(WebDriver driver){
        return new HomePageObject(driver);
    }
    public static RegisterPageObject getRegisterPage(WebDriver driver){
        return new RegisterPageObject(driver);
    }
    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }
    public static MyAccountPageObject getMyAccountPage(WebDriver driver){
        return new MyAccountPageObject(driver);
    }
    public static MyAccountDashboardPO getMyAccountDashboardPage(WebDriver driver){
        return new MyAccountDashboardPO(driver);
    }
    public static MobilePageObject getMobilePage(WebDriver driver){
        return new MobilePageObject(driver);
    }
    public static ProductDetailPO getProductDetailPage(WebDriver driver){
        return new ProductDetailPO(driver);
    }
    public static ShoppingCartPO getShopingCartPage(WebDriver driver){
        return new ShoppingCartPO(driver);
    }
    public static CompareProductPO getCompareProductPage(WebDriver driver){
        return new CompareProductPO(driver);
    }
    public static MyWishlistPO getMyWishlistPage(WebDriver driver){
        return new MyWishlistPO(driver);
    }
    public static ShareYourWishlistPO getShareYourWishlistPage(WebDriver driver){
        return new ShareYourWishlistPO(driver);
    }
    public static TVPO getTVPage(WebDriver driver){
        return new TVPO(driver);
    }
    public static AdvancedSearchPO getAdvancedSearchPage(WebDriver driver){
        return new AdvancedSearchPO(driver);
    }
    public static CheckoutPO getCheckoutPage(WebDriver driver){
        return new CheckoutPO(driver);
    }

    /*  ADMIN */
    public static AdminLoginPO getAdminLoginPage(WebDriver driver){
        return new AdminLoginPO(driver);
    }
    public static AdminCustomersPO getAdminCustomerPage(WebDriver driver){
        return new AdminCustomersPO(driver);
    }
    public static ProductReviewPO getProductReviewPage(WebDriver driver){
        return new ProductReviewPO(driver);
    }
    public static AdminPendingReviewPO getAdminPendingReviewPage(WebDriver driver){
        return new AdminPendingReviewPO(driver);
    }
    public static AdminEditReviewPO getAdminEditReviewPage(WebDriver driver){
        return new AdminEditReviewPO(driver);
    }
    public static AdminInvoicesPO getAdminInvoicesPage(WebDriver driver){
        return new AdminInvoicesPO(driver);
    }
    public static AdminOrdersPO getAdminOrdersPage(WebDriver driver){
        return new AdminOrdersPO(driver);
    }

}
