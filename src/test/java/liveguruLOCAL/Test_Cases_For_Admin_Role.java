package liveguruLOCAL;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.*;
import pageObjects.user.*;
import testdata.jSonData.liveGuru.UserInfor;

@Epic("Regression Tests")
@Feature("Admin Role")
public class Test_Cases_For_Admin_Role extends BaseTest {
    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePageObject = PageGeneratorManager.getHomePage(driver);

        userInfor = UserInfor.getUserInformation(0);
        firstName = userInfor.getFirstName();
        lastName = userInfor.getLastName();
        userPassword = userInfor.getPassword();
        emailAddress = userInfor.getEmailAddress();
        adminUsername = GlobalConstants.DEV_ADMIN_USERNAME;
        adminPassword = GlobalConstants.DEV_ADMIN_PASSWORD;
        deleteFileInDowloadFilesFolder();
    }

    @Description("Verify Account Created Success")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 1)
    public void TC_01_Verify_Account_Created_Success() {
        log.info("Verify Account Created Success - Step 01: click to Account Menu");
        homePageObject.clickOnAccountMenu();

        log.info("Verify Account Created Success - Step 02: click to Register Link");
        registerPO = homePageObject.clickToRegisterLink();

        log.info("Verify Account Created Success - Step 03: enter info");
        registerPO.enterToRegisterForm(firstName, lastName, emailAddress, userPassword, userPassword);

        log.info("Verify Account Created Success - Step 04: click to Register Button");
        registerPO.clickToRegisterButton();

        log.info("Verify Account Created Success - Step 05: verify successful register message");
        Assert.assertEquals(registerPO.getRegisterTextMessage(), "Thank you for registering with Main Website Store");

        log.info("Verify Account Created Success - Step 06: verify correct information");
        Assert.assertTrue(registerPO.getContactInformation().contains(firstName + " " + lastName));
        Assert.assertTrue(registerPO.getContactInformation().contains(emailAddress));

        log.info("Verify Account Created Success - Step 07: logout account");
        homePageObject = PageGeneratorManager.getHomePage(driver);
        homePageObject.clickOnAccountMenu();
        homePageObject.clickToLogOutLink();

        log.info("Verify Account Created Success - Step 08: navigate to Admin URL");
        adminLoginPO = homePageObject.navigationAdminURL();

        log.info("Verify Account Created Success - Step 09: enter to username text box");
        adminLoginPO.enterIntoUsernameTextbox("user01");

        log.info("Verify Account Created Success - Step 10: enter to password text box");
        adminLoginPO.enterIntoPasswordTextbox("guru99com");

        log.info("Verify Account Created Success - Step 11: click to log in button");
        adminCustomersPO = adminLoginPO.clickToLoginButton();

        if(adminCustomersPO.isPopupMessageShow()){
            log.info("Verify Account Created Success : click to close popup");
            adminCustomersPO.clickToClosePopup();
        }
        log.info("Verify Account Created Success - Step 12: verify the user created an new account successfully and recorded at admin data");
        Assert.assertTrue(adminCustomersPO.getFirstRecordValueByColumnName("Email").contains(emailAddress));
    }
    @Description("Verify Invoice Can Be Printed")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 2)
    public void TC_02_Verify_Invoice_Can_Be_Printed() {
        log.info("Verify Invoice Can Be Print - Step 01: Navigation to Admin Url");
        adminCustomersPO = PageGeneratorManager.getAdminCustomerPage(driver);
        adminLoginPO=adminCustomersPO.navigationAdminURL();

        if(adminLoginPO.isLoginPageDisplay()){
            log.info("Verify Invoice Can Be Print: login with admin account");
            adminLoginPO.enterIntoUsernameTextbox(adminUsername);
            adminLoginPO.enterIntoPasswordTextbox(adminPassword);
            adminCustomersPO = adminLoginPO.clickToLoginButton();
        } else {
            adminCustomersPO = PageGeneratorManager.getAdminCustomerPage(driver);
        }

        if(adminCustomersPO.isPopupMessageShow()){
            log.info("Verify Invoice Can Be Print: click to close popup");
            adminCustomersPO.clickToClosePopup();
        }
        log.info("Verify Invoice Can Be Print - Step 02: Click to Orders Menu Of Sales");
        adminOrdersPO = adminCustomersPO.clickToOrdersMenuOfSales();

        log.info("Verify Invoice Can Be Print - Step 03: Select item of Status Drop Down");
        adminOrdersPO.selectItemOfStatusDropdownByItemName("Canceled");

        log.info("Verify Invoice Can Be Print - Step 04: Click to Search button");
        adminOrdersPO.clickToSearchButton();

        log.info("Verify Invoice Can Be Print - Step 05: Click to Checkbox of The First Order");
        adminOrdersPO.clickToCheckboxNextToTheFirstOrder();

        log.info("Verify Invoice Can Be Print - Step 06: Select item of Status Drop Down");
        adminOrdersPO.selectItemOfActionsDropdownByItemName("Print Invoices");

        log.info("Verify Invoice Can Be Print - Step 07: Click to Search button");
        adminOrdersPO.clickSubmitButton();

        log.info("Verify Invoice Can Be Print - Step 08: Verify error message");
        verifyEquals(adminOrdersPO.getErrorMessageText(),"There are no printable documents related to selected orders.");

        log.info("Verify Invoice Can Be Print - Step 09: Select item of Status Drop Down");
        adminOrdersPO.selectItemOfStatusDropdownByItemName("Complete");

        log.info("Verify Invoice Can Be Print - Step 10: Click to Search button");
        adminOrdersPO.clickToSearchButton();
        adminOrdersPO.sleepInSecond(3);

        log.info("Verify Invoice Can Be Print - Step 11: Click to Checkbox of The First Order");
        adminOrdersPO.clickToCheckboxNextToTheFirstOrder();

        log.info("Verify Invoice Can Be Print - Step 12: Select item of Status Drop Down");
        adminOrdersPO.selectItemOfActionsDropdownByItemName("Print Invoices");

        log.info("Verify Invoice Can Be Print - Step 13: Click to Search button");
        adminOrdersPO.clickSubmitButton();

        log.info("Verify Invoice Can Be Print - Step 14: Wait for download file");
        waitForDownloadFileContainsNameCompleted("invoice");
        int countFileBeforeDelete = countFilesInDirectory();

        log.info("Verify Invoice Can Be Print - Step 15: Verify file download success");
        Assert.assertEquals(countFileBeforeDelete, 1);

        log.info("Verify Invoice Can Be Print - Step 16: Click to Reset filter");
        adminOrdersPO.clickToResetFilter();
    }

    @Description("Verify The Product Review Mechanism")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 3)
    public void TC_03_Verify_The_Product_Review_Mechanism() {
        log.info("Verify The Product Review - Step 01: Navigation to User URL");
        adminOrdersPO = PageGeneratorManager.getAdminOrdersPage(driver);
        loginPO = adminOrdersPO.navigationUserURL();

        log.info("Verify The Product Review - Step 02: click to Account Menu");
        homePageObject.clickOnAccountMenu();

        log.info("Verify The Product Review - Step 03: click to log in link");
        loginPO = homePageObject.clickToLoginLink();

        log.info("Verify The Product Review - Step 04: enter email address");
        loginPO.enterToEmailAddressField(emailAddress);

        log.info("Verify The Product Review - Step 05: enter password");
        loginPO.enterToPasswordField(userPassword);

        log.info("Verify The Product Review - Step 06: click log in button");
        myAccountDashboardPO = loginPO.clickToLoginButton();

        log.info("Verify The Product Review - Step 07: navigate to sony xperia detail page");
        loginPO.navigateToUrlByJS(driver,"http://live.techpanda.org/index.php/sony-xperia.html");
        productDetailPO = PageGeneratorManager.getProductDetailPage(driver);

        log.info("Verify The Product Review - Step 08: Count amount reviews");
        int countReviewBefore = productDetailPO.getCountItemsAtRating();

        log.info("Verify The Product Review - Step 09: Click to Add Your Review");
        productReviewPO = productDetailPO.clickToAddYourReview();

        log.info("Verify The Product Review - Step 10: Enter your reviews");
        productReviewPO.selectQualityRateOfProduct("3");
        productReviewPO.enterIntoThoughtsField("Nice");
        productReviewPO.enterIntoYourReviewsField("This product is very good");

        String nickName= "auto" +getRandomNumber();
        productReviewPO.enterIntoNicknameField(nickName);

        log.info("Verify The Product Review - Step 11: Click to Submit Review button");
        productReviewPO.clickToSubmitReviewButton();

        log.info("Verify The Product Review - Step 12: navigate to Admin Url");
        productReviewPO.navigationAdminURL();
        adminLoginPO = PageGeneratorManager.getAdminLoginPage(driver);

        if(adminLoginPO.isLoginPageDisplay()){
            adminLoginPO.enterIntoUsernameTextbox(adminUsername);
            adminLoginPO.enterIntoPasswordTextbox(adminPassword);
            adminCustomersPO = adminLoginPO.clickToLoginButton();
        } else {
            adminCustomersPO = PageGeneratorManager.getAdminCustomerPage(driver);
        }
        if(adminCustomersPO.isPopupMessageShow()){
            log.info("Verify Account Created Success : click to close popup");
            adminCustomersPO.clickToClosePopup();
        }
        adminPendingReviewPO = adminCustomersPO.clickToPendingReviewsMenuOfCatalog();

        verifyTrue(adminPendingReviewPO.getFirstRecordValueByColumnName("Nickname").contains(nickName));

        adminEditReviewPO = adminPendingReviewPO.clickReviewRecordByNickname(nickName);
        adminEditReviewPO.selectItemsStatusDropdownByItemName("Approved");
        adminEditReviewPO.clickToSaveReviewButton();
        Assert.assertEquals(adminEditReviewPO.getSuccessMessageText(),"The review has been saved.");

        adminEditReviewPO.navigateToUrlByJS(driver,"http://live.techpanda.org/index.php/review/product/list/id/1/");
        productDetailPO = PageGeneratorManager.getProductDetailPage(driver);

        productDetailPO.scrollToBottomPage(driver);

        String revielatest = productDetailPO.GetLatestReviewTextAtReviewTab().toLowerCase();
        Assert.assertTrue(revielatest.contains(nickName));
        Assert.assertEquals(productDetailPO.getCountItemsAtRating(),countReviewBefore+1);
    }
    @Description("Verify Sort Invoice Is Working Correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 4)
    public void TC_04_Verify_Sort_Invoice_Is_Working_Correctly() {
        log.info("Sort Invoice - Step 01: click to Account Menu");
        productDetailPO = PageGeneratorManager.getProductDetailPage(driver);
        adminLoginPO = productDetailPO.navigationAdminURL();

        if(adminLoginPO.isLoginPageDisplay()){
            log.info("Sort Invoice: login with admin account");
            adminLoginPO.enterIntoUsernameTextbox(adminUsername);
            adminLoginPO.enterIntoPasswordTextbox(adminPassword);
            adminCustomersPO = adminLoginPO.clickToLoginButton();
        } else {
            adminCustomersPO = PageGeneratorManager.getAdminCustomerPage(driver);
        }
        if(adminCustomersPO.isPopupMessageShow()){
            log.info("Sort Invoice : click to close popup");
            adminCustomersPO.clickToClosePopup();
        }

        log.info("Sort Invoice - Step 02 : click to Invoices Menu of Sales Menu");
        adminInvoicesPO = adminCustomersPO.clickToInvoicesMenuOfSales();

        log.info("Sort Invoice - Step 03 : click to Invoices Column Name to Sort");
        adminInvoicesPO.clickInvoicesToSort();

        log.info("Sort Invoice - Step 04 : Verify Des Sort");
        if(adminInvoicesPO.isInvoiceDisplayBySort("asc")) {
            adminInvoicesPO.clickInvoicesToSort();
            int invoiceNumber1 = adminInvoicesPO.getInvoiceIDByRowNumber("1");
            int invoiceNumber2 = adminInvoicesPO.getInvoiceIDByRowNumber("2");
            int invoiceNumber3 = adminInvoicesPO.getInvoiceIDByRowNumber("3");
            verifyTrue(invoiceNumber1 > invoiceNumber2 && invoiceNumber2 > invoiceNumber3);
        }
        log.info("Sort Invoice - Step 04 : Verify Asc Sort");
        if(adminInvoicesPO.isInvoiceDisplayBySort("desc")) {
            adminInvoicesPO.clickInvoicesToSort();
            int invoiceNumber1 = adminInvoicesPO.getInvoiceIDByRowNumber("1");
            int invoiceNumber2 = adminInvoicesPO.getInvoiceIDByRowNumber("2");
            int invoiceNumber3 = adminInvoicesPO.getInvoiceIDByRowNumber("3");
            verifyTrue(invoiceNumber1 < invoiceNumber2 && invoiceNumber2 < invoiceNumber3);
        }
    }
    @Description("Verify Pagingation Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 5)
    public void TC_05_Verify_Pagination_Functionality() {
        log.info("Pagination Functionality - Step 01: navigate to Admin Url");
        adminInvoicesPO = PageGeneratorManager.getAdminInvoicesPage(driver);
        adminLoginPO=adminInvoicesPO.navigationAdminURL();

        if(adminLoginPO.isLoginPageDisplay()){
            log.info("Pagination Functionality: login with admin account");
            adminLoginPO.enterIntoUsernameTextbox(adminUsername);
            adminLoginPO.enterIntoPasswordTextbox(adminPassword);
            adminCustomersPO = adminLoginPO.clickToLoginButton();
        } else {
            adminCustomersPO = PageGeneratorManager.getAdminCustomerPage(driver);
        }
        if(adminCustomersPO.isPopupMessageShow()){
            log.info("Pagination Functionality: click to close popup");
            adminCustomersPO.clickToClosePopup();
        }
        log.info("Pagination Functionality - Step 02: Click to Orders Menu of Sales");
        adminOrdersPO = adminCustomersPO.clickToOrdersMenuOfSales();

        log.info("Pagination Functionality - Step 03: Select number View per Page");
        adminOrdersPO.selectViewPerPageByNumber("20");
        sleepInSecond(3);
        log.info("Pagination Functionality - Step 04: Verify Records Display correct");
        verifyEquals(adminOrdersPO.getNumberRecordDisplayedPerPage(),20);

        log.info("Pagination Functionality - Step 05: Select number View per Page");
        adminOrdersPO.selectViewPerPageByNumber("30");
        sleepInSecond(3);
        log.info("Pagination Functionality - Step 06: Verify Records Display correct");
        verifyEquals(adminOrdersPO.getNumberRecordDisplayedPerPage(),30);

        log.info("Pagination Functionality - Step 07: Select number View per Page");
        adminOrdersPO.selectViewPerPageByNumber("50");
        sleepInSecond(3);
        log.info("Pagination Functionality - Step 08: Verify Records Display correct");
        verifyEquals(adminOrdersPO.getNumberRecordDisplayedPerPage(),50);

        log.info("Pagination Functionality - Step 09: Select number View per Page");
        adminOrdersPO.selectViewPerPageByNumber("100");
        sleepInSecond(3);
        log.info("Pagination Functionality - Step 10: Verify Records Display correct");
        verifyEquals(adminOrdersPO.getNumberRecordDisplayedPerPage(),100);
    }
    @Description("Verify Search Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TC_01_Verify_Account_Created_Success"}, priority = 6)
    public void TC_06_Verify_Search_Functionality() {
        log.info("Search Functionality - Step 01: navigate to Admin url");
        adminOrdersPO = PageGeneratorManager.getAdminOrdersPage(driver);
        adminLoginPO=adminOrdersPO.navigationAdminURL();

        if(adminLoginPO.isLoginPageDisplay()){
            log.info("Search Functionality: login with admin account");
            adminLoginPO.enterIntoUsernameTextbox(adminUsername);
            adminLoginPO.enterIntoPasswordTextbox(adminPassword);
            adminCustomersPO = adminLoginPO.clickToLoginButton();
        } else {
            adminCustomersPO = PageGeneratorManager.getAdminCustomerPage(driver);
        }
        if(adminCustomersPO.isPopupMessageShow()){
            log.info("Search Functionality: click to close popup");
            adminCustomersPO.clickToClosePopup();
        }
        log.info("Search Functionality - Step 02: enter email address into Search textbox");
        adminCustomersPO.enterValueIntoSearchByEmail(emailAddress);

        log.info("Search Functionality - Step 03: Click to Search button");
        adminCustomersPO.clickToSearchButton();

        log.info("Search Functionality - Step 04: Get ID number");
        String IDNumber = adminCustomersPO.getRecordValueByColumnNameAccordingToRowValue("ID",emailAddress);

        log.info("Search Functionality - Step 05: Click to Reset Filter");
        adminCustomersPO.clickToResetFilter();

        log.info("Search Functionality - Step 06: enter ID number into Search textbox");
        adminCustomersPO.enterValueIntoSearchByID(IDNumber);

        log.info("Search Functionality - Step 07: Click to Search Button");
        adminCustomersPO.clickToSearchButton();

        log.info("Search Functionality - Step 08: Verify record value is correctly");
        verifyEquals(adminCustomersPO.getRecordValueByColumnNameAccordingToRowValue("Email",IDNumber),emailAddress);
        verifyEquals(adminCustomersPO.getRecordValueByColumnNameAccordingToRowValue("Name",IDNumber),firstName + " " + lastName);
    }
    @Description("Verify Checkbox Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 7)
    public void TC_07_Verify_Checkbox_Functionality() {
        log.info("Checkbox Functionality - Step 01: navigate to Admin Url");
        adminCustomersPO = PageGeneratorManager.getAdminCustomerPage(driver);
        adminLoginPO=adminCustomersPO.navigationAdminURL();

        if(adminLoginPO.isLoginPageDisplay()){
            log.info("Checkbox Functionality: login with admin account");
            adminLoginPO.enterIntoUsernameTextbox(adminUsername);
            adminLoginPO.enterIntoPasswordTextbox(adminPassword);
            adminCustomersPO = adminLoginPO.clickToLoginButton();
        } else {
            adminCustomersPO = PageGeneratorManager.getAdminCustomerPage(driver);
        }
        if(adminCustomersPO.isPopupMessageShow()){
            log.info("Verify Account Created Success : click to close popup");
            adminCustomersPO.clickToClosePopup();
        }
        log.info("Checkbox Functionality - Step 02: click to Orders Menu Of Sales");
        adminOrdersPO = adminCustomersPO.clickToOrdersMenuOfSales();

        log.info("Checkbox Functionality - Step 03: Select 20 Record Per Page");
        adminOrdersPO.selectViewPerPageByNumber("20");
        sleepInSecond(3);

        log.info("Checkbox Functionality - Step 04: click to Select Visible Link");
        adminOrdersPO.clickSelectVisibleLink();

        log.info("Checkbox Functionality - Step 05: Verify Select Checkbox");
        if(adminOrdersPO.getNumberTotalRecordFound() >= 20) {
            Assert.assertEquals(adminOrdersPO.getNumberItemSelected(), 20 );
        } else {
            Assert.assertEquals(adminOrdersPO.getNumberItemSelected(), adminOrdersPO.getNumberRecordDisplayedPerPage());
        }

        log.info("Checkbox Functionality - Step 06: click to Unselect Visible Link");
        adminOrdersPO.clickUnSelectVisibleLink();
        sleepInSecond(3);

        log.info("Checkbox Functionality - Step 07: Verify Select Checkbox");
        Assert.assertEquals(adminOrdersPO.getNumberItemSelected(),0);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
//        closeBrowser();
    }
    private WebDriver driver;
    private HomePageObject homePageObject;
    private RegisterPageObject registerPO;
    private UserInfor userInfor;
    private AdminLoginPO adminLoginPO;
    private AdminCustomersPO adminCustomersPO;
    private LoginPageObject loginPO;
    private MyAccountDashboardPO myAccountDashboardPO;
    private ProductReviewPO productReviewPO;
    private AdminPendingReviewPO adminPendingReviewPO;
    private AdminEditReviewPO adminEditReviewPO;
    private ProductDetailPO productDetailPO;
    private AdminInvoicesPO adminInvoicesPO;
    private AdminOrdersPO adminOrdersPO;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userPassword;
    private String adminUsername;
    private String adminPassword;
}
