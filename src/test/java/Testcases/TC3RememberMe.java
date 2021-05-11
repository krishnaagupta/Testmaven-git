package Testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseTest;

public class TC3RememberMe extends BaseTest {

	SoftAssert sa = new SoftAssert();

	@BeforeMethod
	public void CreateReport(Method sTestMethod) {
		test = extent.createTest(sTestMethod.getName());
	}

	@AfterMethod
	public void CloseReport() throws InterruptedException {
		Thread.sleep(4000);
		driver.close();
	}

@Test
public void rememberme() throws IOException {
	driver = getDriver("Chrome");
	driver.get(oDataUtils.ReadWebElementProperties("App.URL"));

	sa.assertEquals(driver.getTitle(), "Login | Salesforce");

	test.info("Application is launched");

	WebElement sUserName = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.username.xpath")));

	if (oCommonUtilities.waitForElementVisible(sUserName)) {
		oCommonUtilities.enterText(sUserName, oDataUtils.ReadAccountProperties("prodaccount.name"), "USERNAME");

	}
	sa.assertNotNull(sUserName.getText(), "krishnaa.mar21@xyz.com");
	test.info("Username is Entered");

	WebElement sPassword = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.password.xpath")));

	if (oCommonUtilities.waitForElementVisible(sPassword)) {
		sPassword.clear();
		sPassword.sendKeys(oDataUtils.ReadAccountProperties("prodaccount.password"));
		System.out.println("check pwd:"+ sPassword.getText());
		test.info("Password is entered");
	}

	
	//rememberme checkbox field
	WebElement rememberMe = driver.findElement(By.name(oDataUtils.ReadWebElementProperties("we.rememberme")));
	if (oCommonUtilities.waitForElementVisible(rememberMe)) {
		rememberMe.click();
	}
	test.info("RememberMe Checked");
	test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
	sa.assertEquals(oDataUtils.ReadAccountProperties("prodaccount.password"), "one1two2");
	WebElement sLoginButton = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.login.xpath")));
	
	if (oCommonUtilities.waitForElementVisible(sLoginButton))
		sLoginButton.click();

	oCommonUtilities.logOut(driver);
	// now login and check if user name is available
	//driver.close();
	//driver = getDriver("Chrome");
	//driver.get(oDataUtils.ReadWebElementProperties("App.URL"));

	//sa.assertEquals(driver.getTitle(), "Login | Salesforce");

	//test.info("Application is launched");
// get the username and check with the one u have and click login.
	WebElement sUserName1 = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.username.xpath")));
	sa.assertEquals(sUserName1.getText(), "krishnaa.mar21@xyz.com");
	test.info("Username displayed is correct");
	
	WebElement sLoginButton1 = driver.findElement(By.xpath(oDataUtils.ReadWebElementProperties("we.login.xpath")));
	if (oCommonUtilities.waitForElementVisible(sLoginButton))
		sLoginButton1.click();
	test.addScreenCaptureFromPath(oCommonUtilities.takeScreenshot());
	test.info("loginclicked");
	sa.assertTrue(driver.getCurrentUrl().contains(oDataUtils.ReadPageURLproperties("Salesforce.HomePage")));
	test.info("Login successful");

	sa.assertAll();
}
	
	
}
