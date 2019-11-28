package ua.com.timetracker.controller;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.com.timetracker.exception.InvalidPaginatingException;

public class PaginationUtilityTest {
    private PaginationUtility util = new PaginationUtility() {};

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void paginatingValidationShouldThrowInvalidPaginatingExceptionWithNullCurrentPage() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Current page or records per page are invalid!");

        util.paginatingValidation(null, 10);
    }

    @Test
    public void paginatingValidationShouldThrowInvalidPaginatingExceptionWithNullRecordsPerPage() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Current page or records per page are invalid!");

        util.paginatingValidation(1, null);
    }

}