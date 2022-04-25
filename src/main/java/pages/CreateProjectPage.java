package pages;

import config.ConfigFileReader;
import objects.Project;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page of creating project
 */
public class CreateProjectPage extends AbstractPage {
    private final String CURRENT_URL;
    private final String TITLE = "Create Project";
    @FindBy(xpath = "//*[@id='restPageTitle']//a")
    private WebElement title;
    @FindBy(xpath = "//*[@id='main-content-tag']//h3[text()='From a repository URL']/ancestor::a")
    private WebElement createFromRepositoryUrlSection;
    @FindBy(id = "url")
    private WebElement url;
    @FindBy(id = "username")
    private WebElement username;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(name = "createProjectFromUrl")
    private WebElement createProjectFromUrlButton;
    @FindBy(id = "createProject")
    private WebElement createManuallyButton;
    @FindBy(xpath = "//*[@id='main-content-tag']//h3[text()=' Manually']/ancestor::a")
    private WebElement createManuallySection;
    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "externalId")
    private WebElement externalId;
    @FindBy(id = "description")
    private WebElement description;
    @FindBy(name = "submitCreateProject")
    private WebElement submitCreateProjectButton;
    public CreateProjectPage(WebDriver driver) {
        super(driver);
        CURRENT_URL = ConfigFileReader.getInstance().getWebAddress() + "/admin/createObjectMenu.html?projectId=_Root&showMode=createProjectMenu&#createFromUrl";
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public CreateProjectPage clickCreateFromRepoUrlSection() {
        createFromRepositoryUrlSection.click();
        return this;
    }

    public CreateProjectPage clickCreateManuallySection() {
        createManuallySection.click();
        return this;
    }

    public ProjectPage createManually(Project project) {
        getCreateProjectPage()
                .clickCreateManuallySection()
                .fillManuallyFields(project)
                .clickCreateManuallyButton();

        return new ProjectPage(webDriver);
    }

    public CreateProjectFromUrlPage createFromRepositoryUrl(Project project) {
        getCreateProjectPage()
                .clickCreateFromRepoUrlSection()
                .fillRepoUrlFields(project)
                .clickCreateFromUrlButton();

        return new CreateProjectFromUrlPage(webDriver, project);
    }

    public void clickCreateManuallyButton() {
        createManuallyButton.click();
    }

    public CreateProjectPage clickCreateFromUrlButton() {
        createProjectFromUrlButton.click();
        return this;
    }

    public CreateProjectPage fillManuallyFields(Project project) {
        if (project.getName() != null)
            name.sendKeys(project.getName());
        if (project.getProjectId() != null) {
            externalId.clear();
            externalId.sendKeys(project.getProjectId());
        }
        if (project.getDescription() != null)
            description.sendKeys(project.getDescription());
        return this;
    }

    public String getUrl() {
        return url.getText();
    }

    public CreateProjectPage fillRepoUrlFields(Project project) {
        if (project.getRepositoryUrl() != null)
            url.sendKeys(project.getRepositoryUrl());
        if (project.getUserNameAccessRepo() != null)
            username.sendKeys(project.getUserNameAccessRepo());
        if (project.getPasswordAccessRepo() != null)
            password.sendKeys(project.getPasswordAccessRepo());
        return this;
    }

    public CreateProjectPage getCreateProjectPage() {
        webDriver.get(CURRENT_URL);
        wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(title), ExpectedConditions.titleContains(LoginPage.HEADER)));
        if (webDriver.getTitle().contains(LoginPage.HEADER)) {
            LoginPage loginPage = new LoginPage(webDriver);
            loginPage.loginAsDefault();
        }
        return this;
    }
}
