package project.service.impl;


import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import project.domain.Backlog;
import project.entity.BacklogEntity;
import project.exception.EntityNotFoundException;
import project.exception.InvalidEntityCreation;
import project.exception.InvalidPaginatingException;
import project.repository.BacklogRepository;
import project.service.BacklogService;
import project.service.mapper.BacklogMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BacklogServiceImpl.class})
public class BacklogServiceImplTest {
    private static final Backlog BACKLOG = new Backlog(1, "Test", "Test");
    private static final BacklogEntity ENTITY = new BacklogEntity(1);
    private static final Page<BacklogEntity> ENTITIES = new PageImpl<>(Arrays.asList(
            ENTITY, ENTITY));
    private static final List<Backlog> BACKLOGS = Arrays.asList(BACKLOG, BACKLOG);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private BacklogRepository repository;

    @MockBean
    private BacklogMapper mapper;

    @Autowired
    private BacklogService service;

    @After
    public void resetMock() {
        reset(repository, mapper);
    }

    @Test
    public void showBacklogByIdShouldReturnBacklogById() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(ENTITY));
        when(mapper.mapBacklogEntityToBacklog(any(BacklogEntity.class))).thenReturn(BACKLOG);
        Backlog actual = service.showBacklogById(1);

        verify(repository).findById(anyInt());
        verify(mapper).mapBacklogEntityToBacklog(any(BacklogEntity.class));

        assertThat(actual, equalTo(BACKLOG));
    }

    @Test
    public void showBacklogByIdShouldThrowEntityNotFoundExceptionWithNullId() {
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("There is no backlog by this id");

        service.showBacklogById(null);
    }

    @Test
    public void showBacklogByIdShouldThrowEntityNotFoundExceptionWithNoEntity() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("There is no backlog by this id");

        service.showBacklogById(null);

        verify(repository).findById(anyInt());
    }

    @Test
    public void createBacklogShouldCreateBacklog() {
        when(mapper.mapBacklogToBacklogEntity(any(Backlog.class))).thenReturn(ENTITY);
        when(repository.save(any(BacklogEntity.class))).thenReturn(ENTITY);
        when(mapper.mapBacklogEntityToBacklog(any(BacklogEntity.class))).thenReturn(BACKLOG);

        Backlog actual = service.createBacklog(BACKLOG);

        verify(repository).save(any(BacklogEntity.class));
        verify(mapper).mapBacklogEntityToBacklog(any(BacklogEntity.class));
        verify(mapper).mapBacklogToBacklogEntity(any(Backlog.class));

        assertThat(actual, equalTo(BACKLOG));
    }

    @Test
    public void createBacklogShouldThrowInvalidEntityCreationWithNullBacklog() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("Backlog is not valid");

        service.createBacklog(null);
    }

    @Test
    public void showAllBacklogsShouldShowAllBacklogs() {
        when(repository.findAll(any(PageRequest.class))).thenReturn(ENTITIES);
        when(mapper.mapBacklogEntityToBacklog(any(BacklogEntity.class))).thenReturn(BACKLOG);

        List<Backlog> actual = service.showAllBacklogs(1, 10);

        verify(repository).findAll(any(Pageable.class));
        verify(mapper, times(2)).mapBacklogEntityToBacklog(any(BacklogEntity.class));

        assertThat(actual, equalTo(BACKLOGS));
    }

    @Test
    public void showAllBacklogsShouldReturnEmptyList() {
        when(repository.findAll(any(PageRequest.class))).thenReturn(Page.empty());

        List<Backlog> actual = service.showAllBacklogs(1, 10);

        verify(repository).findAll(any(PageRequest.class));

        assertThat(actual, equalTo(Collections.emptyList()));
    }

    @Test
    public void showAllBacklogsShouldThrowInvalidPaginatingException() {
        exception.expect(InvalidPaginatingException.class);
        exception.expectMessage("Invalid number of current page or records per page");

        service.showAllBacklogs(-1, 1);
    }

    @Test
    public void showNumbersOfRowsShouldReturnCount() {
        when(repository.count()).thenReturn(10L);

        long actual = service.showNumberOfRows();

        verify(repository).count();

        assertThat(actual, equalTo(10L));
    }
}