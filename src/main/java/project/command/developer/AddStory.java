package project.command.developer;

import project.command.Command;
import project.domain.Story;
import project.domain.User;
import project.service.StoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddStory implements Command {
    private final StoryService storyService;

    public AddStory(StoryService storyService) {
        this.storyService = storyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = parseParameter(request, "currentPage");
        int recordsPerPage = parseParameter(request, "recordsPerPage");
        User user = (User) request.getSession().getAttribute("user");
        Story story = Story.builder().withId(parseParameter(request, "storyId")).build();

        storyService.addStoryToUser(story, user);

        List<Story> stories = storyService.showStoriesWithoutUser(currentPage, recordsPerPage);

        request.setAttribute("stories", stories);

        int rows = storyService.showNumberOfRowsWithoutUser();

        paginating(request, "showFreeStories", rows, currentPage, recordsPerPage);

        return "developer?command=showFreeStories&currentPage=1&recordsPerPage=10";
    }
}
