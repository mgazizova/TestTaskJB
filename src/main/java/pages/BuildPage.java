package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Page of build
 */
public class BuildPage extends AbstractPage {
    @FindBy(id = "unprocessed_objectsCreated")
    private WebElement successMessage;
    @FindBy(xpath = "//*[@id='buildTypeConfSteps']//a[text()='Build Steps']")
    private WebElement buildStepsCategory;
    @FindBy(xpath = "//*[@id='discoveredRunners']//td[2]")
    private List<WebElement> webElements;

    public BuildPage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText();
    }

    public List<String> getAutoDetectedSteps() {
        List<String> steps = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
        for (WebElement element : webElements) {
            steps.add(element.getText());
        }
        return steps;
    }
}
