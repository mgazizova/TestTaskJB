package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Project page
 */
public class ProjectPage extends AbstractPage {
    @FindBy(id = "message_projectCreated")
    private WebElement successMessage;

    public ProjectPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.urlContains("/editProject.html"));
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText();
    }
}
