package HelperFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
    By usernameLocator = By.name("username");
    By passwordLocator = By.name("password");
    By submitButtonLocator = By.name("submit");
    By searchTextBodyLocator = By.tagName("body");

    private final WebDriver driver;
    
    public LoginPage(WebDriver driver) 
    {
        this.driver = driver;   
    }
    
    public void open(String url)
    {
    	driver.get(url);
    }
    
    public void setupWindow()
    {
    	driver.manage().window().maximize();
    }
    
    public LoginPage typeUsername(String username) 
    {
        driver.findElement(usernameLocator).sendKeys(username);
        return this;    
    }
    
    public LoginPage typePassword(String password) 
    {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;    
    }
    
    public LoginPage submitLogin() 
    {
        driver.findElement(submitButtonLocator).click();  
        return this;
    }
    
    public String pageText()
    {
    	return driver.findElement(searchTextBodyLocator).getText();
    }
    
    public void close()
    {
    	driver.quit();
    }
    
}
