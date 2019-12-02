package ua.com.timetracker.domain;


import lombok.*;

import javax.validation.constraints.NotEmpty;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goal {
    private Integer id;

    @NotEmpty(message = "Please provide goal name")
    private String name;

    private Backlog backlog;
}
