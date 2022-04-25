import config.ConfigFileReader;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Basic class for tests
 */
public abstract class AbstractTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfigFileReader.getInstance().getChromeDriverPath());

        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
