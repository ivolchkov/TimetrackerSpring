package ua.com.timetracker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Story {
    private Integer id;

    @NotEmpty(message = "Please provide story name")
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime spentTime;

    private String description;

    private Status status;

    private Goal goal;
}
