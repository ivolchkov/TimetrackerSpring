package ua.epam.timetracker.Timetracker.Spring.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class Backlog {
    private final Integer id;

    @NotEmpty(message = "Please provide project name")
    private final String projectName;

    private final String description;
}
