package Base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utilities.AppConstants;
import Utilities.CommonUtilities;
import Utilities.DataUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static String  sReportTime;
	public static WebDriver driver=null;
	public static ExtentReports extent=null; 
	public static ExtentHtmlReporter htmlReporter=null;
	public static ExtentTest test=null;
	private String sBrowserName;
	public static Properties sProperties;
	public CommonUtilities oCommonUtilities=new CommonUtilities();

	public DataUtilities oDataUtils = new DataUtilities();
	@BeforeTest
	public void setup() {
		Initializereports();
	}
	
	@AfterTest
	public void tearDown() {
		extent.flush();
	}	
	
	
public static void Initializereports() {
	sReportTime=new SimpleDateFormat("yyyymmddhhmm").format(new Date());
	extent=new ExtentReports();
	htmlReporter=new ExtentHtmlReporter(AppConstants.EXTENT_HTML_REPORT_PATH+"//"+sReportTime+".html");
	extent.attachReporter(htmlReporter);
}
public WebDriver getDriver(String BrowserName) {

	sBrowserName = BrowserName.toLowerCase();
	switch (sBrowserName) {

	case "chrome":
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		break;

	case "firefox":
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		break;

	case "ie":
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		break;

	default:
		driver = null;

	}
	return driver;

}


}
