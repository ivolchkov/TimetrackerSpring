package ua.epam.timetracker.Timetracker.Spring.domain;

public enum Status {
    TO_DO("To do"), IN_PROCESS("In Process"), DONE("Done");

    String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
