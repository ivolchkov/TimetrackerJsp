package project.command.admin;

import project.command.Command;
import project.domain.story.Story;
import project.service.story.StoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowStories implements Command {
    private final StoryService storyService;

    public ShowStories(StoryService storyService) {
        this.storyService = storyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

        List<Story> stories = storyService.showAllStories(currentPage, recordsPerPage);

        request.setAttribute("stories", stories);

        int rows = storyService.showNumberOfRows();

        paginating(request, "showStories", rows, currentPage, recordsPerPage);

        return "showStories.jsp";
    }
}
