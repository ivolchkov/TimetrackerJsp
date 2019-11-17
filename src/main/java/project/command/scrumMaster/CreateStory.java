package project.command.scrumMaster;

import project.command.Command;
import project.domain.Goal;
import project.domain.Story;
import project.entity.Status;
import project.service.GoalService;
import project.service.StoryService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

public class CreateStory implements Command {
    private final StoryService storyService;
    private final GoalService goalService;

    public CreateStory(StoryService storyService, GoalService goalService) {
        this.storyService = storyService;
        this.goalService = goalService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        LocalTime spentTime = LocalTime.parse(request.getParameter("spentTime"));
        Status status = Status.TO_DO;
        Integer id = Integer.parseInt(request.getParameter("goalId"));

        Goal goal = goalService.showGoalById(id);
        Story story = Story.builder()
                .withName(name)
                .withSpentTime(spentTime)
                .withDescription(description)
                .withStatus(status)
                .withGoal(goal)
                .build();

        storyService.createStory(story);

        return "scrum-master-service.jsp";
    }
}
