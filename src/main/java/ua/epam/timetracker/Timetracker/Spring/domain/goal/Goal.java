package ua.epam.timetracker.Timetracker.Spring.domain.goal;


import lombok.Builder;
import lombok.Data;
import ua.epam.timetracker.Timetracker.Spring.domain.backlog.Backlog;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@Builder
public class Goal {
    private final Integer id;

    @NotEmpty(message = "Please provide goal name")
    private final String name;

    @NotNull(message = "Please provide goal backlog")
    private final Backlog backlog;
}
