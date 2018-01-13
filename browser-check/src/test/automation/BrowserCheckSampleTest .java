/**
 * 
 */
package test.automation;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * @author jovzhou
 *
 */
class BrowserCheckTest {
	
	public static String URL="https://www.google.co.jp";
	public static String IEDriverPath = "D:\\Selenium\\Driver\\IEDriverServer.exe";
	public static String ChromeDriverPath = "D:\\Selenium\\Driver\\chromedriver.exe";
	public static String KinzaPath = "C:\\Users\\XXXX\\AppData\\Local\\Kinza\\Application\\kinza.exe";
	public static String DoCanPath = "C:\\Program Files (x86)\\connectone\\ConnectONE.exe";
	public static String INFORSTATION_URL = "https://XXXXXXXXXX/main1.html";
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Disabled("Not Run IE")
	void testIE() throws InterruptedException {
		System.setProperty("webdriver.ie.driver", IEDriverPath);
		
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		WebDriver driver = new InternetExplorerDriver(options);

		driver.get(URL);

		driver.close();
	}
	
	@Test
	@Disabled("Not Run Chrome")
	void testChrome() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
		WebDriver driver = new ChromeDriver();
		driver.get(URL);
	
		driver.close();	
	}
	
	@Test
	@Disabled("Not Run Kinza")
	void testKinza() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\Users\\jovzhou\\AppData\\Local\\Kinza\\Application\\kinza.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.get(URL);
		driver.close();			
	}
	
	@Test
	@Disabled("Not Run DoCan")
	void testDoCan() throws InterruptedException, IOException {
		
		String[] Command = {"cmd","/c","\"C:\\Program Files (x86)\\ConnectONE.exe\" chrome:version"};
		Runtime runtime = Runtime.getRuntime();
		runtime.exec(Command);
		
		System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "localhost:8088");	
		WebDriver driver = new ChromeDriver(options);
		
		driver.get(URL);

		driver.close();			
	}	
	
	
	@Test
	//@Disabled("Not Run testInfoStation")
	void testInfoStation() throws InterruptedException, IOException {
		
		String[] Command = {"cmd","/c","\"C:\\Program Files (x86)\\ConnectONE.exe\" \"https://www.google.co.jp"};
		Runtime runtime = Runtime.getRuntime();
		runtime.exec(Command);
		
		System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "localhost:8088");	
		WebDriver driver = new ChromeDriver(options);

		driver.close();				
	}		
	
	@Test
	@Disabled("Not Run DoCan Via IE")
	void testDoCanViaIE() throws InterruptedException {
		System.setProperty("webdriver.ie.driver", IEDriverPath);
		
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		WebDriver driver = new InternetExplorerDriver(options);
		
		driver.get(URL);
		
		
		
		WebDriver DocanDriver;
        driver.findElement(By.xpath("//input[@value='open docan']")).click();
		driver.switchTo().alert().accept();


		driver.close();
		
        System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
		ChromeOptions options1 = new ChromeOptions();
		options1.setExperimentalOption("debuggerAddress", "localhost:8088");	
		DocanDriver = new ChromeDriver(options1);    
		Thread.sleep(12000);		
		
		JavascriptExecutor jse = (JavascriptExecutor) DocanDriver;
		jse.executeScript("arguments[0].scrollIntoView()", DocanDriver.findElement(By.linkText("XXXXX")));
		DocanDriver.findElement(By.linkText("XXXXX")).click();
		
		DocanDriver.switchTo().frame("Body");
		DocanDriver.switchTo().frame("Menu");
		DocanDriver.switchTo().frame("MainMenu");
		String currentwindow  = DocanDriver.getWindowHandle();
		System.out.println("1: "+currentwindow);
		DocanDriver.findElement(By.partialLinkText("XXXXXX")).click(); 

		String handlewindow = (String) DocanDriver.getWindowHandles().toArray()[1];
		System.out.println("2: "+(String) DocanDriver.getWindowHandles().toArray()[0]);
		System.out.println("3: "+(String) DocanDriver.getWindowHandles().toArray()[1]);
		WebDriver GSSPDriver = driver.switchTo().window(handlewindow);
		GSSPDriver.manage().window().fullscreen();
		
		
		
	}	


}
