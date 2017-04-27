package org.example.seed.rest;

import org.example.seed.event.issue.CatalogIssueEvent;
import org.example.seed.event.issue.CreateIssueEvent;
import org.example.seed.event.issue.ResponseIssueEvent;
import org.example.seed.event.issue.UpdateIssueEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ricardo Pina Arellano on 06/02/2017.
 */
@RequestMapping(path = "/issues")
public interface IssueRest {

    @GetMapping
    Callable<CatalogIssueEvent> getAllIssues(final int numberPage, final int recordsPerPage) throws ExecutionException, InterruptedException;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    Callable<ResponseIssueEvent> createIssue(final CreateIssueEvent issueEvent) throws ExecutionException, InterruptedException;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Callable<ResponseIssueEvent> getIssue(final UUID id) throws ExecutionException, InterruptedException;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    Callable<ResponseIssueEvent> updateIssue(final UpdateIssueEvent issueEvent) throws ExecutionException, InterruptedException;

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteIssue(final UUID id);
}
