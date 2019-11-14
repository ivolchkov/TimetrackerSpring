package ua.epam.timetracker.Timetracker.Spring.domain.user;

public enum Role {
    ADMIN("Admin"), DEVELOPER("Developer"), SCRUM_MASTER("Scrum master");

    String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
