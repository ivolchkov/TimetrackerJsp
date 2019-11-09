package project.command.developer;

import project.command.Command;
import project.service.story.StoryService;

import javax.servlet.http.HttpServletRequest;

public class ShowDeveloperStories implements Command {
    private final StoryService storyService;

    public ShowDeveloperStories(StoryService storyService) {
        this.storyService = storyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
