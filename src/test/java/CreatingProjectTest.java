import jdk.jfr.Description;
import objects.Project;
import org.junit.Assert;
import org.junit.Test;
import pages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Test creating project in root
 */
public class CreatingProjectTest extends AbstractTest {
    @Description("Creating a project from a repository URL without authentication in root project")
    @Test
    public void testCreatingProjectFromUrlRepoWithoutAuthInRoot() {
        CreateProjectPage createProjectPage = new CreateProjectPage(driver);

        Project project = new Project(null);
        project.setRepositoryParams("https://github.com/marcobehlerjetbrains/maven", null, null);

        CreateProjectFromUrlPage createProjectFromUrlPage = createProjectPage.createFromRepositoryUrl(project);
        BuildPage buildPage = createProjectFromUrlPage.clickCreateProjectButton();

        String expectedMessage = String.format("New project \"%s\", build configuration \"%s\" and VCS root \"%s#%s\" have been successfully created.",
                project.getName(),
                project.getBuildConfigurationName(),
                project.getRepositoryUrl(),
                project.getDefaultBranch());

        Assert.assertEquals(expectedMessage, buildPage.getSuccessMessage());

        List<String> expectedAutoDetectedSteps = new ArrayList<>();
        expectedAutoDetectedSteps.add("Maven");
        expectedAutoDetectedSteps.add("Command Line");

        Assert.assertEquals(expectedAutoDetectedSteps, buildPage.getAutoDetectedSteps());

        AdministrationPage administrationPage = new AdministrationPage(driver);
        administrationPage.getPage().getAuditSection();
        Assert.assertTrue(administrationPage.findRecordInAudit(RecordTemplate.CREATE_PROJECT.getTemplate(project.getName())));
    }

    @Description("Creating project manually in root project")
    @Test
    public void testCreatingProjectManuallyInRoot() {
        CreateProjectPage createProjectPage = new CreateProjectPage(driver);

        Project project = new Project(null);
        String testName = "Test" + ThreadLocalRandom.current().nextInt(1, 101);
        project.setManuallyParams(testName, testName, "description");

        ProjectPage projectPage = createProjectPage.createManually(project);

        String expectedMessage = String.format("Project \"%s\" has been successfully created. You can now create a build configuration.",
                project.getName());
        Assert.assertEquals(expectedMessage, projectPage.getSuccessMessage());

        String expectedUrlContains = String.format("projectId=%s", project.getProjectId());
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlContains));

        AdministrationPage administrationPage = new AdministrationPage(driver);
        administrationPage.getPage().getAuditSection();
        Assert.assertTrue(administrationPage.findRecordInAudit(RecordTemplate.CREATE_PROJECT.getTemplate(project.getName())));
    }

    @Description("Canceling creating a project from a repository URL")
    @Test
    public void testCancelingCreatingProjectFromRepo() {
        CreateProjectPage createProjectPage = new CreateProjectPage(driver);

        Project project = new Project(null);
        project.setRepositoryParams("https://github.com/marcobehlerjetbrains/maven", "testuser", "testpass");

        CreateProjectFromUrlPage createProjectFromUrlPage = createProjectPage.createFromRepositoryUrl(project);
        CreateProjectPage createProjectPageAfterCanceling = createProjectFromUrlPage.clickCancelCreating();
        Assert.assertEquals("", createProjectPageAfterCanceling.getUrl());
        Assert.assertEquals("", createProjectPageAfterCanceling.getUsername());
        Assert.assertEquals("", createProjectPageAfterCanceling.getPassword());
    }
}