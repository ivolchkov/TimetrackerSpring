package project.domain;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goal {
    private Integer id;

    @NotEmpty(message = "Please provide goal name")
    private String name;

    @Setter(AccessLevel.PUBLIC) private Backlog backlog;
}
