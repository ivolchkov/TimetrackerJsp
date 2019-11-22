package project.command.developer;

import project.command.Command;
import project.domain.Story;
import project.domain.User;
import project.service.StoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowDeveloperStories implements Command {
    private final StoryService storyService;

    public ShowDeveloperStories(StoryService storyService) {
        this.storyService = storyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = parseParameter(request, "currentPage");
        int recordsPerPage = parseParameter(request, "recordsPerPage");
        User user = (User) request.getSession().getAttribute("user");
        Integer id = user.getId();

        List<Story> stories = storyService.showStoryByUser(id, currentPage, recordsPerPage);

        request.setAttribute("stories", stories);

        int rows = storyService.showNumberOfRowsByUserId(id);

        paginating(request, "showDeveloperStories", rows, currentPage, recordsPerPage);

        return "showDeveloperStories.jsp";
    }
}
