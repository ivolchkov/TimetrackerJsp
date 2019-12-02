package com.ua.timetracking.command.developer;

import com.ua.timetracking.command.Command;
import com.ua.timetracking.domain.Story;
import com.ua.timetracking.service.StoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowFreeStories implements Command {
    private final StoryService storyService;

    public ShowFreeStories(StoryService storyService) {
        this.storyService = storyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = parseParameter(request, "currentPage");
        int recordsPerPage = parseParameter(request, "recordsPerPage");

        List<Story> stories = storyService.showStoriesWithoutUser(currentPage, recordsPerPage);

        request.setAttribute("stories", stories);

        int rows = storyService.showNumberOfRowsWithoutUser();

        paginating(request, "showFreeStories", rows, currentPage, recordsPerPage);

        return "freeStories.jsp";
    }
}
