package pages;

import objects.Project;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Second page of creating project for From a repository URL option
 */
public class CreateProjectFromUrlPage extends AbstractPage {
    private final String TITLE = "Create Project From URL";
    private Project project;
    @FindBy(id = "projectName")
    private WebElement projectName;
    @FindBy(id = "buildTypeName")
    private WebElement buildTypeName;
    @FindBy(id = "branch")
    private WebElement defaultBranch;
    @FindBy(id = "teamcity:branchSpec")
    private WebElement branchSpecification;
    @FindBy(name = "createProject")
    private WebElement createProjectButton;
    @FindBy(xpath = "//*[@class='saveButtonsBlock']/a")
    private WebElement cancelButton;
    @FindBy(xpath = "//*[@id='restPageTitle']//a")
    private WebElement title;

    public CreateProjectFromUrlPage(WebDriver driver, Project project) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(title, TITLE));

        this.project = project;
        project.setName(projectName.getAttribute("value"));
        project.setBuildConfigurationName(buildTypeName.getAttribute("value"));
        project.setDefaultBranch(defaultBranch.getAttribute("value"));
        project.setBranchSpecification(branchSpecification.getText());
    }

    public BuildPage clickCreateProjectButton() {
        createProjectButton.click();
        return new BuildPage(webDriver);
    }

    public CreateProjectPage clickCancelCreating() {
        cancelButton.click();
        return new CreateProjectPage(webDriver);
    }

}
