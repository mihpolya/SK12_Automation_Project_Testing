package iSkilloTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TestObject {
    public static final String TEST_RESOURCES_DIR = "src\\test\\resources\\";
    public static final String DOWNLOAD_DIR = TEST_RESOURCES_DIR.concat("download\\");
    public static final String SCREENSHOTS_DIR = TEST_RESOURCES_DIR.concat("screenshots\\");
    private WebDriver webDriver;
    @BeforeSuite
    protected final void setupTestSuite() throws IOException{
        cleanDirectory(SCREENSHOTS_DIR);
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest(){
        this.webDriver = new ChromeDriver(configChromeOptions());
        this.webDriver.manage().window().maximize();
        this.webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @AfterMethod
    protected final void tearDownTest(ITestResult testResult){
        takeScreenshot(testResult);
        quitDriver();
    }

    @AfterSuite
    public void deleteDownloadFiles() throws IOException{
     //   cleanDirectory(DOWNLOAD_DIR);
    }

    private void quitDriver() {
        if(this.webDriver != null){
            this.webDriver.quit();
        }
    }

    protected WebDriver getWebDriver(){
        return webDriver;
    }

    //make the chrome browser download automaticaly

    private ChromeOptions configChromeOptions(){
        //definition to change the default download directory - replaces the default download directory
        // of the browser with the current folder that the user is in + the folder where we want the files
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", System.getProperty("user.dir").concat("\\").concat(DOWNLOAD_DIR));
       //create the chrome options that are going to be given to the browser
        ChromeOptions chromeOptions = new ChromeOptions();
        //send the defined options to change the download directory to the chrome options -
        // it changes and replaces with the new download path
        chromeOptions.setExperimentalOption("prefs", prefs);
        //if for some reason the browser doesn't request the manual click on the Save button,
        // the browser will make it automatically
        chromeOptions.addArguments("disable-popup-blocking");
        return chromeOptions;
    }
    private void cleanDirectory(String directoryPath) throws IOException{
        File directory = new File(directoryPath);
        Assert.assertTrue(directory.isDirectory(), "Invalid directory");
        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if(fileList != null && fileList.length == 0){
            System.out.printf("All files are deleted in Directory %s%n", directoryPath);
        }else{
            System.out.printf("Unable to delete the files in Directory:%s%n", directoryPath);


        }
    }
    private void takeScreenshot(ITestResult testResult){
        if(ITestResult.FAILURE == testResult.getStatus()){
            try{
                TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                //name the screenshot with the name of the test
                String testName = testResult.getName();
                //save the screenshot in a specific folder
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
            }catch (IOException error){
                System.out.println("Unable to create a screenshot file: " + error.getMessage());
            }
        }
    }


}
