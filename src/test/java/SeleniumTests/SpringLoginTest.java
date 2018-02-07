package SeleniumTests;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import HelperFiles.LoginPage;



public class SpringLoginTest {
	
	static final String BROWSER_DRIVER = "webdriver.gecko.driver";
	static final String DRIVER_PATH = "C:\\Users\\Modest Evil\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe";
	
	static final String WORKING_URL = "http://localhost:8080/spring-security-mvc-login/login.html";
	
	static final String TEXTBOX_FOR_USENAME = "username";
	static final String TEXTBOX_FOR_PASSWORD = "password";
	static final String SUBMIT_BUTTON = "submit";
	static final String PAGE_BODY = "body";
	
	@BeforeClass
	public static void beforeClass()
	{
	/*
     * Comment this line if geckodriver path is set up 
	 * in system variables (doesn't work on my PC somewhy)
	 */
		System.setProperty(BROWSER_DRIVER, DRIVER_PATH);
	}
	
	@Test
	/* Tests successful logging in with user credentials
	 * Passes when text on page is "This text is only visible to a user"
	 * what means that user is logged in as trivial user 
	 */
	public void successfulUserLoginTest()
	{	
		String userToLogIn = "user1";
		String userPasswordToLogIn = "user1Pass";
		String textForUser = "This text is only visible to a user";
		
        WebDriver driver = new FirefoxDriver();
		
		LoginPage SpringLogin = new LoginPage(driver);
		
		SpringLogin.open(WORKING_URL);
		SpringLogin.setupWindow();
		SpringLogin.typeUsername(userToLogIn);
		SpringLogin.typePassword(userPasswordToLogIn);
		SpringLogin.submitLogin();
		
		assertTrue(SpringLogin.pageText().contains(textForUser));

        SpringLogin.close();

	}
	
	/* Tests successful logging in as administrator
	 * Passes when text on page is "This text is only visible to an admin"
	 * what means that user is logged in as administrator
	 */
	@Test
	public void successfullAdminLoginTest()
	{
		String userToLogIn = "admin";
		String userPasswordToLogIn = "adminPass";
		String textForAdmin = "This text is only visible to an admin";
			
		WebDriver driver = new FirefoxDriver();
		
		LoginPage SpringLogin = new LoginPage(driver);
		
		SpringLogin.open(WORKING_URL);
		SpringLogin.setupWindow();
		SpringLogin.typeUsername(userToLogIn);
		SpringLogin.typePassword(userPasswordToLogIn);
		SpringLogin.submitLogin();
		
		assertTrue(SpringLogin.pageText().contains(textForAdmin));

        SpringLogin.close();
	
	}
	
	@Test
	/* Tests "failed to log in" case.
	 * There could be more options to test failed to log in behaviour 
	 * - wrong password for existing user
	 * - user does not exist (wrong login)
	 * - empty password field
	 * - empty user field
	 * - both fields empty
	 * As the app demonstrates same behaviour for all mentioned cases, 
	 * all tests for "bad credentials" will belong to one equivalence
	 * class
	 * 
	 * Test input is bad login and wrong password
	 * Passes when after submitting   app routes to start page.
	 */
	public void wrongCredentialsTest()
	{
		String userToLogIn = "superuser";
		String userPasswordToLogIn = "12345";	
		String startPageText = "Login";
		
		WebDriver driver = new FirefoxDriver();
		
		LoginPage SpringLogin = new LoginPage(driver);
		
		SpringLogin.open(WORKING_URL);
		SpringLogin.setupWindow();
		SpringLogin.typeUsername(userToLogIn);
		SpringLogin.typePassword(userPasswordToLogIn);
		SpringLogin.submitLogin();
		
		assertTrue(SpringLogin.pageText().contains(startPageText));

        SpringLogin.close();
		
	}

}
