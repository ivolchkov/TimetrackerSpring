package ua.com.timetracker.controller;


import org.springframework.web.servlet.ModelAndView;
import ua.com.timetracker.exception.InvalidPaginatingException;

import java.util.Objects;

/**
 * Interface which is needed for pagination.
 * Provides two default methods for pagination and validation of parameters
 *
 * @author Ihor Volchkov
 **/
public interface PaginationUtility {
    /**
     * Returns String which represents what url will be shown.
     *
     * @param currentPage    element which set current page for pagination.
     * @param recordsPerPage element which set records per page for pagination.
     * @param modelAndView   element who is needed to set appropriate attributes.
     * @param rows           element which set number of rows for pagination.
     */
    default void paginating(Integer currentPage, Integer recordsPerPage, ModelAndView modelAndView,
                            String page, long rows) {
        long numberOfPages = rows / recordsPerPage;

        if (numberOfPages % recordsPerPage > 0 && rows % recordsPerPage != 0) {
            numberOfPages += 1;
        }

        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("recordsPerPage", recordsPerPage);
        modelAndView.addObject("numberOfPages", numberOfPages);

        modelAndView.setViewName(page);
    }

    /**
     * Validate parameters for pagination. They shouldn`t have null value.
     *
     * @param currentPage    element which is validating.
     * @param recordsPerPage element which is validating.
     * @throws InvalidPaginatingException if the current page or records per page is null.
     */
    default void paginatingValidation(Integer currentPage, Integer recordsPerPage) {
        if (Objects.isNull(currentPage) || Objects.isNull(recordsPerPage)) {
            throw new InvalidPaginatingException("Current page or records per page are invalid!");
        }
    }
}
