package com.sample.test.demo.tests;

import static org.testng.Assert.fail;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sample.test.demo.Configuration;
import com.sample.test.demo.utilities.PizzaOrderDataClass;
import com.sample.test.demo.utilities.WebDriverUtilClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class TestBase {

    protected Configuration config;
    protected PizzaOrderDataClass pizzaOrderData;
    protected WebDriver driver;
    protected String url;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod(alwaysRun = true)
    public void initializeDriver() throws Throwable {
        config = new Configuration();
        pizzaOrderData = new PizzaOrderDataClass();
        url = config.getUrl();
        initializedDriver();
        navigateToSite();
        WebDriverUtilClass util = new WebDriverUtilClass(driver);
    }

    @BeforeTest(alwaysRun = true)
    public void initialiseExtentReport() {
        extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/report.html", true);
    }

    private void navigateToSite() {
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                String screenShotName = result.getName();
                WebDriverUtilClass.captureScreenShot(driver, screenShotName);
                test.log(LogStatus.FAIL, result.getThrowable().toString());
                test.log(LogStatus.FAIL, "Please find the snapshot of the error below: " + test.addScreenCapture(screenShotName + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        extent.endTest(test);
    }

    @AfterTest
    public void endReport() {
        extent.flush();
        extent.close();
    }

    @AfterMethod(dependsOnMethods = "getResult", alwaysRun = true)
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void initializedDriver() {
        if (config.getBrowser().equalsIgnoreCase("chrome")) {
            if (config.getPlatform().equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", config.getMacOSChromeDriverPath());
            } else {
                System.setProperty("webdriver.chrome.driver", config.getWindowsOSChromeDriverPath());
            }
            driver = new ChromeDriver();
        } else {
            fail("Unsupported browser " + config.getBrowser());
        }
    }
}
