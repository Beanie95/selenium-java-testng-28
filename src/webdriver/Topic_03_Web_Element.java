package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	//khai bien global toan cuc
//	By emailTextbox = By.id("mail");
//	By ageRadio = By.id("under_18");
//	By eduTextbox = By.id("edu");
//	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
//	By languageJava = By.id("java");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_ElementDisplay() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("phamhien@gmail.com");
			System.out.println("Email is displayed");
			
		} else {
			System.out.println("Email is not displayed");

		}
		
		if (driver.findElement(ageRadio).isDisplayed()) {
			driver.findElement(ageRadio).click();
			System.out.println("Age is displayed");
			
		} else {
			System.out.println("Age is not displayed");

		}
		
		if (driver.findElement(eduTextbox).isDisplayed()) {
			driver.findElement(eduTextbox).sendKeys("National University");
			System.out.println("Education is displayed");
			
		} else {
			System.out.println("Education is not displayed");

		}
		
		if (driver.findElement(nameUser5Text).isDisplayed()) {
		
			System.out.println("Name user is displayed");
			
		} else {
			System.out.println("Name user is not displayed");

		}
	}
	
	//@Test
	public void TC_02_ElementEnable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("emailtextbox is enabled");
		} else {
			System.out.println("emailtextbox is disabled");
		}
		
		if (driver.findElement(By.id("disable_password")).isEnabled()) {
			System.out.println("password is enabled");
		} else {
			System.out.println("password is disabled");
		}
	}

	//@Test
	public void TC_03_ElementSelect() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertFalse(driver.findElement(ageRadio).isSelected());
		Assert.assertFalse(driver.findElement(languageJava).isSelected());
		
		driver.findElement(ageRadio).click();
		driver.findElement(languageJava).click();
		sleepTime(3);
		
		Assert.assertTrue(driver.findElement(ageRadio).isSelected());
		Assert.assertTrue(driver.findElement(languageJava).isSelected());
		
	}
	
	//@Test
	public void TC_04_Combine() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("phamhien@gmail.com");
		By passwordText = By.id("new_password");
		By signupButton = By.id("create-account-enabled");
		By showPassword = By.xpath("//label[@title='Show Password']");
		sleepTime(2);
		
		driver.findElement(showPassword).click();
		sleepTime(1);
		
		driver.findElement(passwordText).sendKeys("abc");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordText).clear();
		sleepTime(1);
		driver.findElement(passwordText).sendKeys("aA1@hien");
		
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		
	}
	
	//ExerciseLogin
	
	@Test
	public void emptyEmailPassword() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepTime(2);
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}
	
	@Test
	public void invalidEmail() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepTime(2);
		driver.findElement(By.id("email")).sendKeys("12324@132234");
		sleepTime(1);
		driver.findElement(By.id("pass")).sendKeys("123456");
		sleepTime(2);
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	@Test
	public void passwordLessthan6char() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepTime(2);
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		sleepTime(1);
		driver.findElement(By.id("pass")).sendKeys("123");
		sleepTime(2);
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test
	public void incorrectEmailorPassword() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepTime(2);
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		sleepTime(1);
		driver.findElement(By.id("pass")).sendKeys("123123123");
		sleepTime(2);
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
		
	}
	
	public void sleepTime(long timeSleep) {
		try {
			Thread.sleep(timeSleep * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
