package com.ua.timetracking.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The root interface in the command hierarchy.
 * Provides also two default methods for pagination and parsing parameter from {@link HttpServletRequest}
 *
 * @author Ihor Volchkov
 **/
public interface Command {
    /**
     * Returns String which represents what url will be shown.
     *
     * @param request element who has all needed information for processing command.
     * @return String which represents what url will be shown
     */
    String execute(HttpServletRequest request);

    /**
     * Returns String which represents what url will be shown.
     *
     * @param request        element who is needed to set appropriate attributes.
     * @param command        element which has information for links with pagination.
     * @param rows           element which set number of rows for pagination.
     * @param currentPage    element which set current page for pagination.
     * @param recordsPerPage element which set records per page for pagination.
     */
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

    /**
     * Returns number after parsing from String parameter.
     *
     * @param request element who is needed to get appropriate attributes.
     * @param param   element which will be parsed.
     * @return the number which has already parsed.
     */
    default int parseParameter(HttpServletRequest request, String param) {
        try {
            return Integer.parseInt(request.getParameter(param));
        } catch (NumberFormatException | NullPointerException e) {
            return 1;
        }
    }
}
