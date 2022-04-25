package pages;

import config.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Login page
 */
public class LoginPage extends AbstractPage {
    public static final String HEADER = "Log in to TeamCity";
    private final String CURRENT_URL;
    private final String XPATH_HEADER = "//*[@id='header' and text()='" + HEADER + "']";
    @FindBy(xpath = XPATH_HEADER)
    private WebElement header;
    @FindBy(id = "username")
    private WebElement username;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(name = "submitLogin")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        CURRENT_URL = ConfigFileReader.getInstance().getWebAddress() + "/login.html";
    }

    public LoginPage getLoginPage() {
        webDriver.get(CURRENT_URL);
        wait.until(ExpectedConditions.visibilityOf(header));
        return this;
    }

    public LoginPage setUsername(String username) {
        this.username.sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    public void loginAsDefault() {
        String username = ConfigFileReader.getInstance().getUserName();
        String password = ConfigFileReader.getInstance().getPassword();
        login(username, password);
    }

    public void login(String username, String password) {
        getLoginPage().setUsername(username)
                .setPassword(password)
                .clickLoginButton();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(CURRENT_URL)));
    }
}
