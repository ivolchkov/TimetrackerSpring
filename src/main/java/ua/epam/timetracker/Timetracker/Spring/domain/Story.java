package ua.epam.timetracker.Timetracker.Spring.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
@Builder
public class Story {
    private final Integer id;

    @NotEmpty(message = "Please provide story name")
    private final String name;

    @NotEmpty(message = "Please provide time for story executing")
    private final LocalTime spentTime;

    private final String description;

    @NotEmpty(message = "Please provide story status")
    private final Status status;

    @NotNull(message = "Please provide story goal")
    private final Goal goal;
}
