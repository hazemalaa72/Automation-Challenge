package tests;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.JsonUtils;
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private JsonUtils jsonUtils;

    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        jsonUtils = new JsonUtils("test-data/loginData.json");
    }

    @Test(priority = 1)
    public void testElementPresence() {
        Assert.assertTrue(loginPage.areElementsPresent(), "Login elements are not present");
    }

    @Test(priority = 2)
    public void testValidLogin() {
        loginPage.enterUsername(jsonUtils.getValidUsername());
        loginPage.enterPassword(jsonUtils.getValidPassword());
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isSwagLabsHeaderVisible(), "Swag Labs header is not after login");
    }

    @Test(priority = 3)
    public void testInvalidLogin() {
        loginPage.enterUsername(jsonUtils.getInvalidUsername());
        loginPage.enterPassword(jsonUtils.getInvalidPassword());
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message displayed for invalid login");
        Assert.assertEquals(loginPage.getErrorMessageText(), jsonUtils.getLoginFailedMessage(), "Error message text is incorrect");
    }

    @Test(priority = 4)
    public void testEmptyUsername() {
        loginPage.enterPassword(jsonUtils.getValidPassword());
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for empty username");
        Assert.assertEquals(loginPage.getErrorMessageText(), jsonUtils.getUsernameRequiredMessage(), "Error message text is incorrect for empty username");
    }

    @Test(priority = 5)
    public void testEmptyPassword() {
        loginPage.enterUsername(jsonUtils.getValidUsername());
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for empty password");
        Assert.assertEquals(loginPage.getErrorMessageText(), jsonUtils.getPasswordRequiredMessage(), "Error message text is incorrect for empty password");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
