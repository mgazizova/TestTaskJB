package pages;

import config.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Page of administration
 */
public class AdministrationPage extends AbstractPage {
    private final String CURRENT_URL;
    private final String ADMINISTRATION_TITLE = "Administration";
    @FindBy(xpath = "//h1")
    private WebElement title;
    @FindBy(xpath = "//*[@id='admin-container']//a[text()='Projects']")
    private WebElement projectSection;
    @FindBy(xpath = "//*[@id='admin-container']//a[text()='Audit']")
    private WebElement auditSection;

    /**
     * Audit log container
     */

    // Records
    @FindBy(xpath = "//*[@id='auditLogContainerInner']//td[3]/div")
    private List<WebElement> records;

    public AdministrationPage(WebDriver driver) {
        super(driver);
        CURRENT_URL = ConfigFileReader.getInstance().getWebAddress() + "/admin/admin.html";
    }

    public AdministrationPage getAuditSection() {
        auditSection.click();
        wait.until(ExpectedConditions.titleContains("Audit"));
        return this;
    }

    public AdministrationPage getPage() {
        webDriver.get(CURRENT_URL);
        wait.until(ExpectedConditions.textToBePresentInElement(title, ADMINISTRATION_TITLE));
        return this;
    }

    public void clickProjectCategory() {
        projectSection.click();
    }

    public void clickAuditCategory() {
        auditSection.click();
    }

    public boolean findRecordInAudit(String recordDescription) {
        for (WebElement record : records) {
            if (record.getText().equals(recordDescription))
                return true;
        }
        return false;
    }
}