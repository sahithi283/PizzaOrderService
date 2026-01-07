package com.sample.test.demo.tests;

import static org.testng.Assert.fail;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sample.test.demo.Configuration;
import com.sample.test.demo.utilities.WebDriverUtilClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;

public class TestBase {

    protected Configuration config;
    protected WebDriver driver;
    protected String url;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod(alwaysRun = true)
    public void initializeDriver() throws Throwable {
        config = new Configuration();
        url = config.getUrl();
        initializingDriver();
        navigateToSite();
        WebDriverUtilClass util = new WebDriverUtilClass(driver);
    }

    @BeforeTest(alwaysRun = true)
    public void initialiseExtentReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Selenium Test Results");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        test = extent.createTest("Pizza Service Test")
                .assignAuthor("QA Engineer")
                .assignCategory("Functional Test")
                .assignDevice("Chrome");
    }

    private void navigateToSite() {
        URL fileUrl = PizzaOrderPageTests.class
                .getClassLoader()
                .getResource("files/index.html");
        driver.get(fileUrl.toString());
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                String screenShotName = result.getName();
                WebDriverUtilClass.captureScreenShot(driver, screenShotName);
                test.fail(result.getThrowable().toString());
                test.fail("Please find the snapshot of the error below: " + test.addScreenCaptureFromPath(screenShotName + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterTest
    public void endReport() {
        extent.flush();
    }

    @AfterMethod(dependsOnMethods = "getResult", alwaysRun = true)
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void initializingDriver() {
        if (config.getBrowser().equalsIgnoreCase("chrome")) {
            driver =  new ChromeDriver();
        } else {
            fail("Unsupported browser " + config.getBrowser());
        }
    }
}
