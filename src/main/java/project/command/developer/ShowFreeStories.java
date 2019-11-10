package project.command.developer;

import project.command.Command;
import project.domain.story.Story;
import project.service.story.StoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowFreeStories implements Command {
    private final StoryService storyService;

    public ShowFreeStories(StoryService storyService) {
        this.storyService = storyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

        List<Story> stories = storyService.showStoriesWithoutUser(currentPage, recordsPerPage);

        request.setAttribute("stories", stories);

        int rows = storyService.showNumberOfRowsWithoutUser();

        paginating(request, "showFreeStories", rows, currentPage, recordsPerPage);

        return "freeStories.jsp";
    }
}
