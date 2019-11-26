package project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Backlog {
    private Integer id;

    @NotEmpty(message = "Please provide project name")
    private String projectName;

    private String description;
}
