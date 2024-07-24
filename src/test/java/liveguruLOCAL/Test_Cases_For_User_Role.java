package liveguruLOCAL;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.*;
import testdata.jSonData.liveGuru.BillInformation;
import testdata.jSonData.liveGuru.UserInfor;

import java.util.List;

@Epic("Regression Tests")
@Feature("User Role")
public class Test_Cases_For_User_Role extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePageObject = PageGeneratorManager.getHomePage(driver);

        userInfor   = UserInfor.getUserInformation(0);
        firstName   = userInfor.getFirstName();
        lastName    = userInfor.getLastName();
        password    = userInfor.getPassword();
        emailAddress = userInfor.getEmailAddress();

        billInformation = BillInformation.getBillInformation(0);
        country     = billInformation.getCountry();
        state       = billInformation.getState();
        zipCode     = billInformation.getZipCode();
        address     = billInformation.getAddress();
        telephone   = billInformation.getTelephone();
    }
    @Description("Register Success")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 1)
    public void TC_01_Register_Success_To_System() {
        log.info("Register - Step 01: click to Account Menu");
        homePageObject.clickOnAccountMenu();

        log.info("Register - Step 02: click to Register Link");
        registerPO = homePageObject.clickToRegisterLink();

        log.info("Register - Step 03: enter info");
        registerPO.enterToRegisterForm(firstName,lastName,emailAddress,password,password);

        log.info("Register - Step 04: click to Register Button");
        registerPO.clickToRegisterButton();

        log.info("Register - Step 05: verify successful register message");
        Assert.assertEquals(registerPO.getRegisterTextMessage(),"Thank you for registering with Main Website Store.");

        log.info("Register - Step 06: verify correct information");
        Assert.assertTrue(registerPO.getContactInformation().contains(firstName + " " + lastName));
        Assert.assertTrue(registerPO.getContactInformation().contains(emailAddress));

        log.info("Register - Step 07: logout account");
        homePageObject = PageGeneratorManager.getHomePage(driver);
        homePageObject.clickOnAccountMenu();
        homePageObject.clickToLogOutLink();
    }
    @Description("Login Success To System")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,dependsOnMethods = "TC_01_Register_Success_To_System",priority = 2)
    public void TC_02_Login_Success_To_System() {
        log.info("Login - Step 01: open live guru home page");
        homePageObject = registerPO.openLiveGuruSite();

        log.info("Login - Step 02: click to Account Menu");
        homePageObject.clickOnAccountMenu();

        log.info("Login - Step 03: click to log in link");
        loginPO = homePageObject.clickToLoginLink();

        log.info("Login - Step 04: enter email address");
        loginPO.enterToEmailAddressField(emailAddress);

        log.info("Login - Step 05: enter password");
        loginPO.enterToPasswordField(password);

        log.info("Login - Step 06: click log in button");
        myAccountDashboardPO = loginPO.clickToLoginButton();

        log.info("Login - Step 07: verify information");
        Assert.assertTrue(myAccountDashboardPO.getDashboardText().contains("MY DASHBOARD"));
        Assert.assertTrue(myAccountDashboardPO.getHelloMsgText().contains("Hello,"+ " " + firstName + " " + lastName));
    }
    @Description("Verify That Cost Of Product In List Page And Details Page Are Equal")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 3)
    public void TC_03_Verify_That_Cost_Of_Product_In_List_Page_And_Details_Page_Are_Equal() {
        log.info("Verify Cost of Product - Step 01: open live guru home page");
        homePageObject = registerPO.openLiveGuruSite();

        log.info("Verify Cost of Product - Step 02: click to Mobile Menu");
        mobilePO = homePageObject.clickToMobileMenu();

        log.info("Verify Cost of Product - Step 03: get price of product in Mobile List");
        String productName= "Sony Xperia";
        String costOfProductAtList = mobilePO.getPriceOfProductByName(productName);

        log.info("Verify Cost of Product - Step 04: click to product, go to product detail page");
        mobilePO.clickToProductByName(productName);
        productDetailPO = PageGeneratorManager.getProductDetailPage(driver);

        log.info("Verify Cost of Product - Step 05: get price in product detail page");
        String costOfProductAtDetailPage = productDetailPO.getCostOfProduct(productName);

        log.info("Verify Cost of Product - Step 06: Compare between price of product in List and detail page");
        Assert.assertEquals(costOfProductAtList, costOfProductAtDetailPage);
    }
    @Description("Verify Discount Coupon Works Correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 4)
    public void TC_04_Verify_Discount_Coupon_Works_Correctly() {
        log.info("Verify Discount Coupon - Step 01: open live guru home page");
        homePageObject = registerPO.openLiveGuruSite();

        log.info("Verify Discount Coupon - Step 02: click to Mobile Menu");
        mobilePO = homePageObject.clickToMobileMenu();

        log.info("Verify Discount Coupon - Step 03: get price of product in Mobile List");
        String productName= "Sony Xperia";
        shoppingCartPO = mobilePO.clickAddProductToCartByName(productName);

        log.info("Verify Discount Coupon - Step 04: verify the product add to cart successful");
        Assert.assertEquals(shoppingCartPO.getSuccessMessageText(),productName + " was added to your shopping cart.");
        Assert.assertTrue(shoppingCartPO.isProductDisplayAtShoppingCart(productName));

        log.info("Verify Discount Coupon - Step 05: get grand total price before applying coupon codes");
        Float grandTotalPriceBefore = shoppingCartPO.getGrandTotalPrice();

        log.info("Verify Discount Coupon - Step 06: enter coupon code into the field");
        String couponCode = "GURU50";
        shoppingCartPO.enterCouponCodeIntoTheField(couponCode);

        log.info("Verify Discount Coupon - Step 07: click to apply coupon code");
        shoppingCartPO.clickToApplyCouponCodes();
        shoppingCartPO.sleepInSecond(3);
        Assert.assertEquals(shoppingCartPO.getSuccessMessageText(),"Coupon code \"" + couponCode + "\" was applied.");

        log.info("Verify Discount Coupon - Step 08: verify discount coupon");
        Assert.assertTrue(shoppingCartPO.isDiscountCouponCodeDisplayAtTotal(couponCode));
        Assert.assertTrue(shoppingCartPO.getDiscountCouponValuePriceDisplay(couponCode).contains("$5.00"));
        Assert.assertEquals(shoppingCartPO.getPriceValueByName("Grand Total"),grandTotalPriceBefore);
    }
    @Description("Verify That You Can Not Add More 500 Items Of Product")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 5)
    public void TC_05_Verify_That_You_Can_Not_Add_More_500_Items_Of_Product() {
        log.info("Verify Add More 500 Item - Step 01: open live guru home page");
        homePageObject = registerPO.openLiveGuruSite();

        log.info("Verify Add More 500 Item  - Step 02: click to Mobile Menu");
        mobilePO = homePageObject.clickToMobileMenu();

        log.info("Verify Add More 500 Item  - Step 03: get price of product in Mobile List");
        String productName= "Sony Xperia";
        shoppingCartPO = mobilePO.clickAddProductToCartByName(productName);

        log.info("Verify Add More 500 Item  - Step 04: verify the product add to cart successful");
        Assert.assertEquals(shoppingCartPO.getSuccessMessageText(),productName + " was added to your shopping cart.");
        Assert.assertTrue(shoppingCartPO.isProductDisplayAtShoppingCart(productName));

        log.info("Verify Add More 500 Item  - Step 05: change QTY value to 501");
        shoppingCartPO.enterQTYValueByProductName(productName,"501");

        log.info("Verify Add More 500 Item  - Step 06: click to update button after changing QTY");
        shoppingCartPO.clickToUpdateButton();

        log.info("Verify Add More 500 Item  - Step 07: verify error message to announce that you can not add more 500");
        Assert.assertEquals(shoppingCartPO.getErrorMessageText(),"Some of the products cannot be ordered in requested quantity.");

        log.info("Verify Add More 500 Item  - Step 08: click to empty cart button");
        shoppingCartPO.clickToEmptyCartButton();

        log.info("Verify Add More 500 Item  - Step 09: verify cart empty ");
        Assert.assertEquals(shoppingCartPO.getPageTitleMsg(),"SHOPPING CART IS EMPTY");
    }
    @Description("Verify That You Are Able To Compare Two Products")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 6)
    public void TC_06_Verify_That_You_Are_Able_To_Compare_Two_Products() {
        log.info("Verify Compare Two Product - Step 01: open live guru home page");
        homePageObject = registerPO.openLiveGuruSite();

        log.info("Verify Compare Two Product - Step 02: click to Mobile Menu");
        mobilePO = homePageObject.clickToMobileMenu();

        String productName1= "Sony Xperia";
        String productName2= "Samsung Galaxy";

        log.info("Verify Compare Two Product - Step 03: click product 1 to comparison list" + productName1 );
        mobilePO.clickCompareProductByName(productName1);
        log.info("Verify Compare Two Product - Step 04: Verify message add " + productName1 + " to comparison list successfully");
        Assert.assertEquals(mobilePO.getSuccessMessageText(),"The product " + productName1 + " has been added to comparison list.");

        log.info("Verify Compare Two Product - Step 05: click product 2 to comparison list" + productName2 );
        mobilePO.clickCompareProductByName(productName2);
        log.info("Verify Compare Two Product - Step 06: Verify message add " + productName2 + " to comparison list successfully");
        Assert.assertEquals(mobilePO.getSuccessMessageText(),"The product " + productName2 + " has been added to comparison list.");

        log.info("Verify Compare Two Product - Step 07: Click to Compare 2 product " + productName1 + " & " + productName2);
        String parentID = mobilePO.clickToCompareButton();

        log.info("Verify Compare Two Product - Step 08: Switch driver to Product Compare Window");
        mobilePO.switchToWindowByID(driver,parentID);
        compareProductPO = PageGeneratorManager.getCompareProductPage(driver);

        log.info("Verify Compare Two Product - Step 09: Verify Compare 2 product "+ productName1 + " & " + productName2);
        compareProductPO.isCompareProductTitleDisplay();
        compareProductPO.isProductByNameDisplay(productName1);
        compareProductPO.isProductByNameDisplay(productName2);

        log.info("Verify Compare Two Product - Step 10: Close Compare Product Window");
        mobilePO = compareProductPO.closeCompareProductWindows(parentID);
    }
    @Description("Verify That You Can Share Wishlist To Other People Using Email")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,dependsOnMethods = {"TC_02_Login_Success_To_System"},priority = 7)
    public void TC_07_Verify_That_You_Can_Share_Wishlist_To_Other_People_Using_Email() {
        log.info("Share Wishlist - Step 01: open live guru home page");
        homePageObject = registerPO.openLiveGuruSite();

        log.info("Share Wishlist - Step 02: Click to TV Menu");
        tvPO = homePageObject.clickToTVMenu();

        log.info("Share Wishlist - Step 03: Click to Add Product to Wishlist");
        myWishlistPO = tvPO.clickToAddProductToWishlistByName("LG LCD");

        log.info("Share Wishlist - Step 04: Verify success message");
        Assert.assertEquals(myWishlistPO.getSuccessMessageText(),"LG LCD has been added to your wishlist. Click here to continue shopping.");

        log.info("Share Wishlist - Step 05: Click to Wishlist button to share");
        shareYourWishlistPO = myWishlistPO.clickToShareWishlistButton();

        log.info("Share Wishlist - Step 06: Enter value into Sharing Information");
        shareYourWishlistPO.enterEmailIntoSharingInformation(getEmailAddress());
        shareYourWishlistPO.enterMessageIntoSharingInformation("Share my wishlist to " + getEmailAddress());

        log.info("Share Wishlist - Step 07: Click to Share Wishlist button");
        myWishlistPO = shareYourWishlistPO.clickToShareWishlistButton();

        log.info("Share Wishlist - Step 08: Verify Success Message");
        Assert.assertEquals(myWishlistPO.getSuccessMessageText(),"Your Wishlist has been shared.");

        log.info("Share Wishlist - Step 09: Verify quantity of product at My Wishlist Page is + 1");
        Assert.assertEquals(myWishlistPO.getSizeOfProductAtMyWishlistPage(),1);
    }
    @Description("Verify That You Can Add Your Review")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,dependsOnMethods = {"TC_02_Login_Success_To_System"},priority = 8)
    public void TC_08_Verify_That_You_Can_Add_Your_Review() {
        log.info("Add Your Review - Step 01: open live guru home page");
        homePageObject = registerPO.openLiveGuruSite();

        log.info("Add Your Review - Step 02: Click To TV Menu");
        tvPO = homePageObject.clickToTVMenu();

        log.info("Add Your Review - Step 03: Click Product By Name");
        productDetailPO = tvPO.clickToProductByName("Samsung LCD");

        log.info("Add Your Review - Step 04: Click Add Your Review");
        productReviewPO = productDetailPO.clickToAddYourReview();

        log.info("Add Your Review - Step 05: Enter empty value into Review Form");
        productReviewPO.enterIntoThoughtsField("");
        productReviewPO.enterIntoYourReviewsField("");
        productReviewPO.enterIntoNicknameField("");

        log.info("Add Your Review - Step 06: Click Submit Review Button");
        productReviewPO.clickToSubmitReviewButton();

        log.info("Add Your Review - Step 07: Verify Error message");
        Assert.assertEquals(productReviewPO.getErrorMessageByQualityCheckbox(),"Please select one of each of the ratings above");
        Assert.assertEquals(productReviewPO.getErrorMessageByNameField("thoughts"),"THIS IS A REQUIRED FIELD.");
        Assert.assertEquals(productReviewPO.getErrorMessageByNameField("Review"),"THIS IS A REQUIRED FIELD.");
        Assert.assertEquals(productReviewPO.getErrorMessageByNameField("nickname"),"THIS IS A REQUIRED FIELD.");

        log.info("Add Your Review - Step 08: Enter valid value into Review Form");
        productReviewPO.selectQualityRateOfProduct("3");
        productReviewPO.enterIntoThoughtsField("Nice");
        productReviewPO.enterIntoYourReviewsField("This product is very good");
        productReviewPO.enterIntoNicknameField("auto" +getRandomNumber());

        log.info("Add Your Review - Step 09: Click Submit Review Button");
        productReviewPO.clickToSubmitReviewButton();

        log.info("Add Your Review - Step 10: Verify Success message");
        Assert.assertEquals(productReviewPO.getSuccessMessageText(),"Your review has been accepted for moderation.");
    }
    @Description("Verify That You Are Able To Purchase Product")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TC_07_Verify_That_You_Can_Share_Wishlist_To_Other_People_Using_Email"},priority = 9)
    public void TC_09_Verify_That_You_Are_Able_To_Purchase_Product() {
        log.info("Purchase Product - Step 01: open live guru home page");
        homePageObject = registerPO.openLiveGuruSite();

        log.info("Purchase Product - Step 02: Click on Account Menu");
        homePageObject.clickOnAccountMenu();

        log.info("Purchase Product - Step 02: Click on My Wish list link");
        myWishlistPO = homePageObject.clickToMyWishlistLink();

        log.info("Purchase Product - Step 03: Click on Add All To Cart link");
        shoppingCartPO = myWishlistPO.clickAddAllToCartLink();

        log.info("Purchase Product - Step 04: Verify Success Message");
        verifyTrue(shoppingCartPO.getSuccessMessageText().contains(" was added to your shopping cart."));

        log.info("Purchase Product - Step 05: Fill information " + country + " & " + state + " & " + zipCode);
        shoppingCartPO.selectCountryItem(country);
        shoppingCartPO.selectStateItem(state);
        shoppingCartPO.enterZipField(zipCode);

        log.info("Purchase Product - Step 06: Click on Estimate Button");
        shoppingCartPO.clickToEstimateButton();

        log.info("Purchase Product - Step 07: Verify Flat Rate Ship Generate");
        Assert.assertTrue(shoppingCartPO.isFlatRateShipGenerate());

        log.info("Purchase Product - Step 08: Select Shop Cost");
        shoppingCartPO.selectShipCost();

        log.info("Purchase Product - Step 09: Click on Update Total");
        shoppingCartPO.clickToUpdateTotal();

        log.info("Purchase Product - Step 10: Verify Flat Rate Ship Generate At Total");
        Assert.assertTrue(shoppingCartPO.isFlatRateShipDisplayAtTotal());

        float subtotalPrice = shoppingCartPO.getPriceValueByName("Subtotal");
        float flatRatePrice =shoppingCartPO.getPriceValueByName("Flat Rate");

        log.info("Purchase Product - Step 11: Verify Grand Total");
        Assert.assertEquals(shoppingCartPO.getPriceValueByName("Grand Total"),subtotalPrice + flatRatePrice);

        log.info("Purchase Product - Step 12: Click To Proceed Checkout Button");
        checkoutPO = shoppingCartPO.clickToProceedCheckoutButton();

        log.info("Purchase Product - Step 13: enter into Bill Infor");
        checkoutPO.enterIntoBillInformation(address,state,zipCode,telephone);

        log.info("Purchase Product - Step 14: Click To Continue Button");
        checkoutPO.clickToContinueButton();

        log.info("Purchase Product - Step 15: Verify Flat Rate Ship Display At Ship Method");
        Assert.assertTrue(checkoutPO.isFlatRateShipDisplayAtShipMethod());

        log.info("Purchase Product - Step 16: Click To Continue Button");
        checkoutPO.clickToContinueButton();

        log.info("Purchase Product - Step 17: Select Money Order");
        checkoutPO.selectPaymentInformation("Money order");

        log.info("Purchase Product - Step 18: Click To Continue Button");
        checkoutPO.clickToContinueButton();

        log.info("Purchase Product - Step 19: Click To Place Order Button");
        checkoutPO.clickToPlaceOrderButton();

        log.info("Purchase Product - Step 20: Verify The number Order Generated");
        Assert.assertTrue(checkoutPO.isTheOrderNumberGenerated());
    }
    @Description("Verify Search Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 10)
    public void TC_10_Verify_Search_Functionality() {
        log.info("Verify Advanced Search - Step 01: open live guru home page");
        homePageObject = checkoutPO.openLiveGuruSite();

        log.info("Verify Advanced Search - Step 02: Click to Advanced Search");
        advancedSearchPO = homePageObject.clickToAdvancedSearch();

        log.info("Verify Advanced Search - Step 03: Enter Price From & To");
        advancedSearchPO.enterToPriceFrom("0");
        advancedSearchPO.enterToPriceTo("150");

        log.info("Verify Advanced Search - Step 04: Click to Search Button");
        advancedSearchPO.clickToSearchButton();

        List<WebElement> listPriceProduct1 = advancedSearchPO.getPriceOfListProductsInResult();
        for (WebElement product : listPriceProduct1){
            String price = product.getText().replace("$","");
            System.out.println(price);
            float priceValue = Float.parseFloat(price);
            log.info("Verify Advanced Search - Step 05: Verify Result");
            Assert.assertTrue((priceValue > 0 && priceValue < 151 ));
        }

        log.info("Verify Advanced Search - Step 06: back to page");
        advancedSearchPO.backToPage(driver);

        log.info("Verify Advanced Search - Step 07: Enter Price From & To");
        advancedSearchPO.enterToPriceFrom("151");
        advancedSearchPO.enterToPriceTo("1000");

        log.info("Verify Advanced Search - Step 08: Click to Search Button");
        advancedSearchPO.clickToSearchButton();

        List<WebElement> listPriceProduct2 = advancedSearchPO.getPriceOfListProductsInResult();
        for (WebElement product : listPriceProduct2){
            String price = product.getText().replace("$","");
            System.out.println(price);
            float priceValue = Float.parseFloat(price);
            log.info("Verify Advanced Search - Step 09: Verify Result");
            Assert.assertTrue((priceValue > 151 && priceValue < 1000 ));
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private HomePageObject homePageObject;
    private LoginPageObject loginPO;
    private RegisterPageObject registerPO;
    private MyAccountPageObject myAccountPO;
    private MyAccountDashboardPO myAccountDashboardPO;
    private MobilePageObject mobilePO;
    private ProductDetailPO productDetailPO;
    private ShoppingCartPO shoppingCartPO;
    private CompareProductPO compareProductPO;
    private MyWishlistPO myWishlistPO;
    private ShareYourWishlistPO shareYourWishlistPO;
    private AdvancedSearchPO advancedSearchPO;
    private CheckoutPO checkoutPO;
    private TVPO tvPO;
    private ProductReviewPO productReviewPO;
    private BillInformation billInformation;
    private UserInfor userInfor;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String country ;
    private String state;
    private String zipCode;
    private String address;
    private String telephone;

}
