package ua.com.timetracker.controller;


import org.springframework.web.servlet.ModelAndView;
import ua.com.timetracker.exception.InvalidPaginatingException;

import java.util.Objects;

public interface PaginationUtility {
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

    default void paginatingValidation(Integer currentPage, Integer recordsPerPage) {
        if (Objects.isNull(currentPage) || Objects.isNull(recordsPerPage)) {
            throw new InvalidPaginatingException("Current page or records per page are invalid!");
        }
    }
}
