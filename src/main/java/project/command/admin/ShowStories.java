package project.command.admin;

import project.command.Command;
import project.domain.Story;
import project.service.StoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowStories implements Command {
    private final StoryService storyService;

    public ShowStories(StoryService storyService) {
        this.storyService = storyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = parseParameter(request, "currentPage");
        int recordsPerPage = parseParameter(request, "recordsPerPage");

        List<Story> stories = storyService.showAllStories(currentPage, recordsPerPage);

        request.setAttribute("stories", stories);

        int rows = storyService.showNumberOfRows();

        paginating(request, "showStories", rows, currentPage, recordsPerPage);

        return "showStories.jsp";
    }
}
