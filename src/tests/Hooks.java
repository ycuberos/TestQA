package tests;

import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.ScreenCapture;
import org.openqa.selenium.WebDriver;
import core.utils.GetProperty;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import core.utils.TakeScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.concurrent.TimeUnit;

public class Hooks {

    public WebDriver driver = null;
    GetProperty property = new GetProperty();
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;
    public TakeScreenshot takeScreenshot = new TakeScreenshot();
    
    @BeforeTest
    public void setup(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setDocumentTitle("Simple Automation Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    @BeforeClass
    public void setUp() throws Exception {
          try {

          String browser = property.getConfProperties("BROWSER");
            String OS = property.getConfProperties("OS");
            ChromeOptions options = new ChromeOptions();

            if (browser.equals("CHROME")) {
                //MAC CONFIG
                if (OS.equals("MAC")) {

                    options.addArguments("--remote-allow-origins=*");
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
                }else if (OS.equals("WINDOWS"))
                    //WINDOWS CONFIG
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
            }

            driver = new ChromeDriver(options);
            //SAVE DRIVER AND WAIT FOR PAGE LOAD
            //driver.manage().timeouts().implicitlyWait(Integer.parseInt(property.getConfProperties("IMPLICIT_WAIT")), TimeUnit.SECONDS);
            driver.get(property.getConfProperties("URL"));
            //driver.manage().window().maximize();

        }catch(Exception e){
            System.out.println("HOOKS - General Exception " + e);
        }
    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if(result.getStatus() == ITestResult.FAILURE) {
            
            test.log(Status.FAIL, "Test Case Failed is "+result.getName());
            String screenshotPath = takeScreenshot.getScreenshot(driver, result.getName());
            //To add it in the extent report 
            test.log(Status.FAIL, String.valueOf(test.addScreenCaptureFromPath(screenshotPath)));

        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getTestName());
        }
        else {
            test.log(Status.SKIP, result.getTestName());
        }
    }

    @AfterClass
    public void after() {
        try{
           driver.quit();

        }catch(Exception e){
            System.out.println("HOOKS - General Exception " + e);
        }
    }

    @AfterTest
    public void tearDown(){
        extent.flush();
    }
}
