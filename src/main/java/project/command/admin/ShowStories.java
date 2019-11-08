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

        int numberOfPages = rows / recordsPerPage;

        if (numberOfPages % recordsPerPage > 0) {
            numberOfPages += 1;
        }

        request.setAttribute("command", "showStories");
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return "showStories.jsp";
    }
}
