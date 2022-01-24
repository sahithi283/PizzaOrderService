package com.sample.test.demo.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class WebDriverUtilClass {

    private static WebDriverWait wait;
    private static Actions actions;
    private static JavascriptExecutor javaScriptExecutor;
    private WebDriver webDriver;

    public WebDriverUtilClass(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
        actions = new Actions(webDriver);
        javaScriptExecutor = (JavascriptExecutor) webDriver;
    }

    public static void selectAValueFromDropDown(WebElement dropDownElement, String text) {
        Select select = new Select(dropDownElement);
        select.selectByValue(text);
    }

    public static void selectAValueFromDropDownByText(WebElement dropDownElement, String text) {
        Select select = new Select(dropDownElement);
        select.selectByVisibleText(text);
    }

    public static boolean verifySelectedOption(WebElement dropDownElement, String displayName) {
        Select select = new Select(dropDownElement);
        return select.getFirstSelectedOption().getText().contains(displayName);
    }

    public static void waitTillElementIsVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitElementToBeSelected(By by) {
        wait.until(ExpectedConditions.elementToBeSelected(by));
    }

    public static void waitTillPresenceOfElementLocated(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static boolean isElementEnabled(WebElement webElement) {
        return webElement.isEnabled();
    }

    public static boolean isElementDisplayed(WebElement webElement) {
        return webElement.isDisplayed();
    }

    public static void enterText(WebElement webElement, String textToBeEntered) {
        webElement.clear();
        webElement.sendKeys(textToBeEntered);
    }

    public static boolean verifyTextEntered(WebElement webElement, String textEntered) {
        return getElementValue(webElement).equals(textEntered);
    }

    public static String getElementValue(WebElement webElement) {
        return webElement.getAttribute("value");
    }

    public static String getElementText(WebElement webElement) {
        return webElement.getText();
    }

    public static void clickOnButtonUsingActions(WebElement webElement) {
        actions.click().build().perform();
    }

    public static void clickOnButtonUsingJSExecutor(WebElement webElement) {
        javaScriptExecutor.executeScript("arguments[0].click()", webElement);
    }

    public static boolean isWebElementSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    public static void scrollDownTheWebPage() {
        javaScriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void scrollToTheWebElement(WebElement webElement) {
        javaScriptExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public static void captureScreenShot(WebDriver driver, String screenShotName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destinationPath = "test-output/" + screenShotName + ".png";
        File destination = new File(destinationPath);
        FileUtils.copyFile(source, destination);
    }
}
