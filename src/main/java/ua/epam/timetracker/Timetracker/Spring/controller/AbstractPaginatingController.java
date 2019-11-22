package ua.epam.timetracker.Timetracker.Spring.controller;


import lombok.extern.log4j.Log4j;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.timetracker.Timetracker.Spring.exception.InvalidPaginatingException;

import java.util.Objects;

@Log4j
public class AbstractPaginatingController {
    protected void paginating(Integer currentPage, Integer recordsPerPage, ModelAndView modelAndView, String command, String page, long rows) {
        long numberOfPages = rows / recordsPerPage;

        if (numberOfPages % recordsPerPage > 0 && rows % recordsPerPage != 0) {
            numberOfPages += 1;
        }

        modelAndView.addObject("command", command);
        modelAndView.addObject("numberOfPages", numberOfPages);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("recordsPerPage", recordsPerPage);

        modelAndView.setViewName(page);
    }

    void paginatingValidation(Integer currentPage, Integer recordsPerPage) {
        if (Objects.isNull(currentPage) || Objects.isNull(recordsPerPage)) {
            log.warn("Current page or records per page are invalid!");
            throw new InvalidPaginatingException("Current page or records per page are invalid!");
        }
    }
}
