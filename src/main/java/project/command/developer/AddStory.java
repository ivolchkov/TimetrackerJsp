package project.command.developer;

import project.command.Command;
import project.domain.story.Story;
import project.domain.user.User;
import project.service.story.StoryService;

import javax.servlet.http.HttpServletRequest;

public class AddStory implements Command {
    private final StoryService storyService;

    public AddStory(StoryService storyService) {
        this.storyService = storyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Story story = Story.builder().withId(Integer.parseInt(request.getParameter("storyId"))).build();

        storyService.addStoryToUser(story, user);

        return "developer?command=showFreeStories&currentPage=1&recordsPerPage=10";
    }
}
