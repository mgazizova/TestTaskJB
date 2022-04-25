package pages;

/**
 * Templates of records for Audit log on Administration page
 */
public enum RecordTemplate {
    CREATE_PROJECT("Project %s was created"),
    REMOVE_PROJECT("Project \"%s\" was removed");
    private String template;

    RecordTemplate(String template) {
        this.template = template;
    }

    public String getTemplate(String... params) {
        return String.format(template, params);
    }
}
