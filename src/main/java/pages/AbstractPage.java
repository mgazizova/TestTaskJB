package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Basic class for Pages
 */
public abstract class AbstractPage {
    public WebDriver webDriver;
    public WebDriverWait wait;

    public AbstractPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.webDriver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(7000));
    }
}
