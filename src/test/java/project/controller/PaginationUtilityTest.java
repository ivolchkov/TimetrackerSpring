package project.controller;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import project.exception.InvalidPaginatingException;
import project.service.StoryService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DeveloperController.class})
public class PaginationUtilityTest {
    @MockBean
    private StoryService storyService;

    @Autowired
    private DeveloperController util;

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