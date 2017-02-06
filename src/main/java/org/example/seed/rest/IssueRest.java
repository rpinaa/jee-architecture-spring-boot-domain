package org.example.seed.rest;

import org.example.seed.event.issue.CatalogIssueEvent;
import org.example.seed.event.issue.CreateIssueEvent;
import org.example.seed.event.issue.ResponseIssueEvent;
import org.example.seed.event.issue.UpdateIssueEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ricardo Pina Arellano on 06/02/2017.
 */
public interface IssueRest {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Callable<CatalogIssueEvent> getAllIssues(final int numberPage, final int recordsPerPage) throws ExecutionException, InterruptedException;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Callable<ResponseIssueEvent> createIssue(final CreateIssueEvent issueEvent) throws ExecutionException, InterruptedException;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Callable<ResponseIssueEvent> getIssue(final UUID id) throws ExecutionException, InterruptedException;

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Callable<ResponseIssueEvent> updateIssue(final UpdateIssueEvent issueEvent) throws ExecutionException, InterruptedException;

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIssue(final UUID id);
}
