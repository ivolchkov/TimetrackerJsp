package project.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request);

    default void paginating(HttpServletRequest request, String command, int rows, int currentPage, int recordsPerPage) {
        int numberOfPages = rows / recordsPerPage;

        if (numberOfPages % recordsPerPage > 0 && rows % recordsPerPage != 0) {
            numberOfPages += 1;
        }

        request.setAttribute("command", command);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }
}
