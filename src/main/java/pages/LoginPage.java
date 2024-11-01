package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
     WebDriver driver;
    // Locators
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.className("error-message-container error");
    private final By swagLabsHeader =By.xpath("//div[@class='app_logo']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isErrorMessageDisplayed() {
        return !driver.findElements ( errorMessage ).isEmpty ( );
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isSwagLabsHeaderVisible() {
        return driver.findElement(swagLabsHeader).isDisplayed();
    }

    public boolean areElementsPresent() {
        return driver.findElement(usernameInput).isDisplayed() &&
                driver.findElement(passwordInput).isDisplayed() &&
                driver.findElement(loginButton).isDisplayed();
    }
}
