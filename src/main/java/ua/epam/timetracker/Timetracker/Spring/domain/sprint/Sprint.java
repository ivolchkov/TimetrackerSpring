package ua.epam.timetracker.Timetracker.Spring.domain.sprint;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
public class Sprint {
    private final Integer id;

    @NotEmpty(message = "Please provide goal name")
    private final String name;

    @NotEmpty(message = "Please provide start of your sprint")
    private final LocalDate start;

    @NotEmpty(message = "Please provide end of your sprint")
    private final LocalDate end;

    private final String description;
}
