package objects;

import javax.annotation.Nullable;

/**
 * Class describes basics of projects in TeamCity
 */
public class Project {
    private Project parentProject;
    private String name;
    private String projectId;
    private String description;
    private String repositoryUrl;
    private String userNameAccessRepo;
    private String passwordAccessRepo;
    private String defaultBranch;
    private String branchSpecification;
    private String buildConfigurationName;

    public Project(Project parentProject) {
        this.parentProject = parentProject;
    }

    public void setRepoUserName(String userNameAccessRepo) {
        this.userNameAccessRepo = userNameAccessRepo;
    }

    public void setRepoPassword(String passwordAccessRepo) {
        this.passwordAccessRepo = passwordAccessRepo;
    }

    public String getBranchSpecification() {
        return branchSpecification;
    }

    public void setBranchSpecification(String branchSpecification) {
        this.branchSpecification = branchSpecification;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public String getBuildConfigurationName() {
        return buildConfigurationName;
    }

    public void setBuildConfigurationName(String buildConfigurationName) {
        this.buildConfigurationName = buildConfigurationName;
    }

    public Project getParentProject() {
        return parentProject;
    }

    public Project setParentProject(Project parentProject) {
        this.parentProject = parentProject;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public Project setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Project setDescription(String description) {
        this.description = description;
        return this;
    }

    public Project setRepositoryParams(String url, @Nullable String username, @Nullable String password) {
        setRepositoryUrl(url);
        setRepoUserName(username);
        setRepoPassword(password);
        return this;
    }

    public Project setManuallyParams(String name, String projectId, @Nullable String description) {
        setName(name);
        setProjectId(projectId);
        setDescription(description);
        return this;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getUserNameAccessRepo() {
        return userNameAccessRepo;
    }

    public String getPasswordAccessRepo() {
        return passwordAccessRepo;
    }
}
