package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.user.BaseElementUIs;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BasePage {
    public static BasePage getBasePage(){
        return new BasePage();
    }
    public void openPageUrl(WebDriver driver, String urlPage){
        driver.get(urlPage);
    }
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }
    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }
    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }
    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }
    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }
    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
    }
    public void acceptToAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }
    public void cancelToAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }
    public String getTextInAlert(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String keyToSend) {
        waitForAlertPresence(driver).sendKeys(keyToSend);
    }

    public void switchToWindowByID(WebDriver driver, String currentID) {
           Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if(!id.equals(currentID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }
    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            driver.switchTo().window(id);
            sleepInSecond(2);
            String actualTitle = driver.getTitle();
            System.out.println(actualTitle);
            if( actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }
    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String windowID : allIDs) {
            if ( !windowID.equals(parentID)) {
                driver.switchTo().window(windowID);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }
    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Set<Cookie> getBrowserCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }
    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }

    public void deleteAllCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    public By getByLocator(String locatorValue) {
        By by = null;
        if (locatorValue.startsWith("xpath=") || locatorValue.startsWith("Xpath=") || locatorValue.startsWith("XPATH=")) {
            by = By.xpath(locatorValue.substring(6));
        } else if (locatorValue.startsWith("css=") || locatorValue.startsWith("Css=") || locatorValue.startsWith("CSS=")) {
            by = By.cssSelector(locatorValue.substring(4));
        } else if (locatorValue.startsWith("id=") || locatorValue.startsWith("Id=") || locatorValue.startsWith("ID=")) {
            by = By.id(locatorValue.substring(3));
        } else if (locatorValue.startsWith("name=") || locatorValue.startsWith("Name =") || locatorValue.startsWith("NAME=")) {
            by = By.name(locatorValue.substring(5));
        } else if (locatorValue.startsWith("class=") || locatorValue.startsWith("Class=") || locatorValue.startsWith("CLASS=")) {
            by = By.className(locatorValue.substring(6));
        } else if (locatorValue.startsWith("tagname=") || locatorValue.startsWith("Tagname=") || locatorValue.startsWith("TAGNAME=")) {
            by = By.tagName(locatorValue.substring(8));
        } else {
            throw new RuntimeException("Locator is invalid");
        }
        return by;
    }
    public By getByXpath(String locator){
        return By.xpath(locator);
    }

    public String getDynamicLocator(String locatorValue, String... restParams) {
        return String.format(locatorValue,(Object[]) restParams );
    }

    public WebElement getWebElement(WebDriver driver, String locator){
       return driver.findElement(getByLocator(locator));
    }
    public List<WebElement> getListWebElement(WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }
    public List<WebElement> getListWebElement(WebDriver driver, String locator, String... restParams){
        return driver.findElements(getByLocator(getDynamicLocator(locator,restParams)));
    }
    public void clickToElement(WebDriver driver, String locator) {
       getWebElement(driver,locator).click();
    }
    public void clickToElement(WebDriver driver, WebElement element) {
      element.click();
    }
    public void clickToElement(WebDriver driver, String locator, String... restParams) {
       getWebElement(driver,getDynamicLocator(locator,restParams)).click();
    }
    public void sendkeyToElement(WebDriver driver, String locator, String valueToSend){
        getWebElement(driver,locator).clear();
        sleepInSecond(1);
        getWebElement(driver, locator).sendKeys(valueToSend);
    }
    public void sendkeyToElement_Delete_By_Keys(WebDriver driver, String locator, String valueToSend){
        getWebElement(driver, locator).sendKeys(Keys.chord(Keys.CONTROL),"a");
        getWebElement(driver, locator).sendKeys(Keys.DELETE);
        sleepInSecond(1);
        getWebElement(driver, locator).sendKeys(valueToSend);
    }
    public void sendkeyToElement(WebDriver driver, String locator, String valueToSend, String... restParams){
        getWebElement(driver,getDynamicLocator(locator,restParams)).clear();
        getWebElement(driver, getDynamicLocator(locator,restParams)).sendKeys(valueToSend);
    }

    public void sendkeyToElement_Delete_By_Keys(WebDriver driver, String locator, String valueToSend, String... restParams){
        getWebElement(driver, getDynamicLocator(locator,restParams)).sendKeys(Keys.chord(Keys.CONTROL),"a");
        getWebElement(driver, getDynamicLocator(locator,restParams)).sendKeys(Keys.DELETE);
        sleepInSecond(1);
        getWebElement(driver, getDynamicLocator(locator,restParams)).sendKeys(valueToSend);
    }

    public String getElementText(WebDriver driver, String locator){
       return getWebElement(driver,locator).getText();
    }
    public String getElementText(WebDriver driver, String locator, String... restParams){
       return getWebElement(driver,getDynamicLocator(locator,restParams)).getText();
    }
    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue){
        new Select(getWebElement(driver,locator)).selectByVisibleText(itemValue);
    }
    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue, String... restParams){
        new Select(getWebElement(driver,getDynamicLocator(locator,restParams))).selectByVisibleText(itemValue);
    }
    public String getFirstSelectedTextInDefaultDropdown(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }
    public boolean isDefaultDropdownMultiple(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).isMultiple();
    }
    public void selectItemInDropdown(WebDriver driver, String parentLocator, String childItemsLocator, String itemExpected) {
        clickToElement(driver, parentLocator);
        sleepInSecond(2);
        List<WebElement> allItems = new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemsLocator)));

        for (WebElement item : allItems) {
            String textItem = item.getText();
            if (textItem.equals(itemExpected)) {
                item.click();
                break;
            }
        }
    }
    public void selectItemInDropdownByActionsAndClickJS(WebDriver driver, String parentLocator, String childItemsLocator, String itemExpected) {
        WebElement parentDropdown = getWebElement(driver,parentLocator);
        Actions actions = new Actions(driver);
        actions.moveToElement(parentDropdown).click(parentDropdown).perform();
        sleepInSecond(1);

        waitForElementVisible(driver,childItemsLocator);
        List<WebElement> allItems = getListWebElement(driver,childItemsLocator);

        for (WebElement item : allItems) {
            String textItem = item.getText();
            if (textItem.equals(itemExpected)) {
                if(item.isDisplayed()){
                    item.click();
                } else {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
                    sleepInSecond(1);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", item);
                }
                sleepInSecond(1);
                break;
            }
        }
    }


    public String getElementAttribute(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... restParams){
        return getWebElement(driver, getDynamicLocator(locator,restParams)).getAttribute(attributeName);
    }
    public String getElementCssValue(WebDriver driver, String locator, String cssPropertyName){
        return getWebElement(driver, locator).getCssValue(cssPropertyName);
    }
    public String convertRGBAToHexaColor(WebDriver driver, String locator){
        String backgroundColorRGBA = getElementCssValue(driver,locator,"background-color");
        return Color.fromString(backgroundColorRGBA).asHex();
    }
    public int getListElementSize(WebDriver driver, String locator){
        return getListWebElement(driver, locator).size();
    }
    /**
     * Apply for checkbox and radio Button
    * */
    public void checkToElement(WebDriver driver, String locator){
        if(!getWebElement(driver, locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }

    /**
     * Only apply for checkbox
     * */
    public void uncheckToElement(WebDriver driver, String locator){
        if(getWebElement(driver, locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }
    public boolean isElementDisplayed(WebDriver driver, String locator){
      return  getWebElement(driver, locator).isDisplayed();
    }
    public boolean isElementDisplayed(WebDriver driver, String locator, String... restParams){
      return  getWebElement(driver, getDynamicLocator(locator,restParams)).isDisplayed();
    }
    public void setTimeOutImplicitwait(WebDriver driver, long timeout){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }
    public boolean isElementUnDisplayed(WebDriver driver, String locator){
        setTimeOutImplicitwait(driver, longTimeout);
        List<WebElement> elements = getListWebElement(driver,locator);
        setTimeOutImplicitwait(driver, longTimeout);
        if (elements.size() == 0 ){
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isElementUnDisplayed(WebDriver driver, String locator, long shortTimeout){
        setTimeOutImplicitwait(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver,locator);
        setTimeOutImplicitwait(driver, longTimeout);
        if (elements.size() == 0 ){
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isElementUnDisplayed(WebDriver driver, String locator, String... restParams){
        setTimeOutImplicitwait(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, getDynamicLocator(locator,restParams));
        setTimeOutImplicitwait(driver, longTimeout);
        if (elements.size() == 0 ){
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return  getWebElement(driver, locator).isSelected();
    }
    public boolean isElementEnabled(WebDriver driver, String locator){
        return  getWebElement(driver, locator).isEnabled();
    }
    public void switchToIframe(WebDriver driver, String locator){
        new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(locator)));
    }
    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }
    public void hoverToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver,locator)).perform();
    }
    public void doubleClickToElement(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }
    public void rightClickToElement(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver,targetLocator)).perform();
    }

    public void sendkeyBoardToElement(WebDriver driver, String locator, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver,locator),key).perform();
    }

    public Object executeForBrowser(WebDriver driver,String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(WebDriver driver,String textExpected) {
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver,String url) {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver,locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver,locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver,locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(driver,locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver,locator));
    }

    public void sendkeyToElementByJS(WebDriver driver,String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver,locator));
    }

    public String getAttributeInDOM(WebDriver driver,String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver,locator));
    }

    public String getElementValidationMessage(WebDriver driver,String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver,locator));
    }

    public boolean isImageLoaded(WebDriver driver,String locator) {
        boolean status = (boolean) ((JavascriptExecutor) driver) .executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver,locator));
        return status;
    }

    public void waitForElementVisible(WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }
    public void waitForElementVisible(WebDriver driver,String locator, String... restParams){
        new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locator,restParams))));
    }
    public void waitForListElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver,locator)));
    }
    public void waitForListElementVisible(WebDriver driver, String locator, String... restParams) {
        new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver,locator,restParams)));
    }

    public void waitForListElementInVisible(WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,locator)));
    }

    public void waitForElementInVisible(WebDriver driver,String locator, String... restParams){
        new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locator,restParams))));
    }
    public void waitForElementClickable(WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getWebElement(driver,locator)));
    }
    public void waitForElementClickable(WebDriver driver,WebElement element){
        new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitForElementClickable(WebDriver driver,String locator, String... restParams){
        new WebDriverWait(driver,Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getWebElement(driver,getDynamicLocator(locator,restParams))));
    }
    public boolean isPageLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }
    public void upLoadMultipleFiles(WebDriver driver, String...fileNames){
        String filePath = GlobalConstants.UPLOAD_PATH ;
        String fullFileName ="";
        for(String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }

        fullFileName = fullFileName.trim();
        getWebElement(driver, BaseElementUIs.UPLOAD_FILE_TYPE).sendKeys(fullFileName);

    }
    public String upperCaseAllFirstCharacter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return Arrays.stream(text.split("\\s+"))
                .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
    public void deleteAllFileInFolder() {
        try {
            File file = new File(GlobalConstants.DOWLOAD_PATH);
            File[] listOfFiles = file.listFiles();
            System.out.println("File = " + listOfFiles.length);
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;



}
