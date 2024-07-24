package commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private String projectPath = System.getProperty("user.dir");
    protected final Logger log;
    private Platform platform;
    private WebDriver driver;
    public BaseTest() {
        log = LogManager.getLogger(getClass());
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        switch (browser) {
            case FIREFOX:
                FirefoxOptions optionsFF = new FirefoxOptions();
                optionsFF.addPreference("browser.download.folderList", 2);
                optionsFF.addPreference("browser.download.dir", GlobalConstants.DOWLOAD_PATH);
                optionsFF.addPreference("browser.helperApps.neverAsk.saveToDisk","image/jpeg, application/pdf, application/octet-stream");
                optionsFF.addPreference("pdfjs.disabled", true);

                FirefoxDriverService serviceFF = new GeckoDriverService.Builder().withLogFile(new File(GlobalConstants.BROWSER_LOG_PATH + "FirefoxLog.log")).build();
                driver = new FirefoxDriver(serviceFF,optionsFF);
                break;

            case CHROME:
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", GlobalConstants.DOWLOAD_PATH);
                chromePrefs.put("safebrowsing.enabled", "false");
                chromePrefs.put("credentials_enable_service", false);
                chromePrefs.put("profile.password_manager_enabled", false);
                chromePrefs.put("profile.password_manager_leak_detection", false);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-web-security");
                options.setExperimentalOption("prefs", chromePrefs);

                ChromeDriverService serviceChrome = new ChromeDriverService.Builder().withLogFile(new File(GlobalConstants.BROWSER_LOG_PATH + "ChromeLog.log")).build();
                driver = new ChromeDriver(serviceChrome,options);
                break;

            case EDGE:
                EdgeDriverService serviceEdge = new EdgeDriverService.Builder().withLogFile(new File(GlobalConstants.BROWSER_LOG_PATH + "EdgeLog.log")).build();
                driver = new EdgeDriver(serviceEdge);
                break;

            case SAFARI:
                SafariDriverService serviceSafari = new SafariDriverService.Builder()
                        .withLogging(true)
                        .build();
                driver = new SafariDriver(serviceSafari);
                break;

            default:
                throw new RuntimeException("Browser name is not valid.");

        }
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://live.techpanda.org/");
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser) {
            case FIREFOX:
                FirefoxOptions options = new FirefoxOptions();
                options.addPreference("browser.download.folderList", 2);
                options.addPreference("browser.download.dir", GlobalConstants.DOWLOAD_PATH);
                options.addPreference("browser.helperApps.neverAsk.saveToDisk","image/jpeg, application/pdf, application/octet-stream");
                options.addPreference("pdfjs.disabled", true);
                driver = new FirefoxDriver(options);
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case CHROMEPROFILE:
                ChromeDriverService sevice1 = ChromeDriverService.createDefaultService();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--user-data-dir=C:/Users/USER/AppData/Local/Google/Chrome/User Data");
                chromeOptions.addArguments("--profile-directory=Default");
                driver = new ChromeDriver(sevice1,chromeOptions);
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid.");

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();

        driver.get(url);
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url, String osName, String ipAddress, String portNumber) {
        Capabilities capability = null;

        if (osName.toLowerCase().contains("windows")) {
            platform = Platform.WINDOWS;
        } else {
            platform = Platform.MAC;
        }

        switch (browserName) {
            case "firefox":
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
                capability = fOptions;
                break;
            case "chrome":
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
                capability = cOptions;
                break;
            case "edge":
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
                capability = eOptions;
                break;
            case "safari":
                SafariOptions sOptions = new SafariOptions();
                sOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
                capability = sOptions;
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }

        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/", ipAddress, portNumber)), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    protected String getEmailAddress() {
        Random rand = new Random();
        return "tonydang" + rand.nextInt(9999) + "@gmail.net";
    }
    protected String getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9999) + "";
    }

    protected void closeBrowser() {
        String cmd = null;
        try {
            String osName = GlobalConstants.OS_NAME.toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI 'IMAGENAME eq " + browserDriverName + "*'";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info("-------Passed-------");
        } catch (Throwable e) {
            log.info("-------Failed-------");
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info("-------Passed-------");
        } catch (Throwable e) {
            log.info("-------Failed-------");
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("-------Passed-------");
        } catch (Throwable e) {
            log.info("-------Failed-------");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }
    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        deleteAllFileInFolder("reportNGImage");

        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-json");
    }
    public void deleteFileInDowloadFilesFolder() {
        deleteAllFileInFolder("downloadFiles");
    }
    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.RELATIVE_PROJECT_PATH + File.separator+ folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void waitForDownloadFileFullnameCompleted(String fileName) {
        int i = 0;
        while (i < 60) {
            boolean exist = isFileExists(fileName);
            if (exist == true) {
                i = 60;
                break;
            }
            sleepInSecond(1);
            i = i + 1;
        }
    }

    public void waitForDownloadFileContainsNameCompleted(String fileName) {
        int i = 0;
        while (i < 60) {
            boolean exist = isFileContain(fileName);
            if (exist == true) {
                i = 60;
                break;
            }
            sleepInSecond(1);
            i = i + 1;
        }
    }

    public boolean isFileContain(String fileName) {
        try {
            boolean flag = false;
            File dir = new File(GlobalConstants.DOWLOAD_PATH);
            File[] files = dir.listFiles();
            if (files == null || files.length == 0) {
                flag = false;
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().endsWith(fileName)) {
                    flag = true;
                }
            }
            return flag;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }

    public boolean isFileExists(String file) {
        try {
            File files = new File(GlobalConstants.DOWLOAD_PATH + file);
            boolean exists = files.exists();
            return exists;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }

    public int countFilesInDirectory() {
        File file = new File(GlobalConstants.DOWLOAD_PATH);
        int i = 0;
        for (File listOfFiles : file.listFiles()) {
            if (listOfFiles.isFile()) {
                i++;
            }
        }
        return i;
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
