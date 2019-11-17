package ua.epam.timetracker.Timetracker.Spring.service.mapper;

import org.springframework.stereotype.Component;
import ua.epam.timetracker.Timetracker.Spring.domain.Goal;
import ua.epam.timetracker.Timetracker.Spring.domain.Sprint;
import ua.epam.timetracker.Timetracker.Spring.domain.Story;
import ua.epam.timetracker.Timetracker.Spring.domain.User;
import ua.epam.timetracker.Timetracker.Spring.entity.GoalEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.SprintEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.StoryEntity;
import ua.epam.timetracker.Timetracker.Spring.entity.UserEntity;

@Component
public class StoryMapper {
    public StoryEntity mapStoryToStoryEntity(Story domain) {
//        return StoryEntity.builder()
//                .name(domain.getName())
//                .spentTime(domain.getSpentTime())
//                .description(domain.getDescription())
//                .status(domain.getStatus())
//                .goal(GoalEntity.builder()
//                        .id(domain.getGoal().getId())
//                        .build())
//                .build();
        return new StoryEntity(domain.getName(),domain.getSpentTime(),domain.getStatus(), new GoalEntity(domain.getGoal().getId()));
    }

    public StoryEntity mapStoryToStoryEntity(Story domain, User user) {
//        return StoryEntity.builder()
//                .id(domain.getId())
//                .user(UserEntity.builder()
//                        .id(user.getId())
//                        .build())
//                .build();
        return new StoryEntity(domain.getId(),new UserEntity(user.getId()));
    }

    public StoryEntity mapStoryToStoryEntity(Story domain, Sprint sprint) {
//        return StoryEntity.builder()
//                .id(domain.getId())
//                .sprint(SprintEntity.builder()
//                        .id(sprint.getId())
//                        .build())
//                .build();
//        return StoryEntity.builder()
//                .id(domain.getId())
//                .sprint(new SprintEntity(1))
//                .build();
        return new StoryEntity(domain.getId(),new SprintEntity(sprint.getId()));
    }

    public Story mapStoryEntityToStory(StoryEntity entity) {
        return Story.builder()
                .id(entity.getId())
                .name(entity.getName())
                .spentTime(entity.getSpentTime())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .goal(Goal.builder()
                        .id(entity.getGoal().getId())
                        .build())
                .build();
    }
}
